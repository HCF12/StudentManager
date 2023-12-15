package cn.com.service.impl;

import cn.com.dto.QuartzJobDTO;
import cn.com.service.QuartzJobService;
import cn.com.vo.EmailGroupInfoVo;
import cn.com.vo.QuartzJobVO;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static cn.com.utils.CronExpParserUtil.translateToChinese;

/**
 * @author william@StarImmortal
 * @date 2022/09/26
 */
@Service
public class QuartzJobServiceImpl implements QuartzJobService {
	private static final Log log = LogFactory.getLog(QuartzJobServiceImpl.class);

	private final static String[] CRON_TIME_CN = new String[]{"秒", "分钟", "小时", "天", "月", "周", "年"};
	@Autowired
	private Scheduler scheduler;

	@SneakyThrows
	@Override
	public void scheduleJob(QuartzJobDTO dto) {
		JobDetail jobDetail = JobBuilder.newJob(getClass(dto.getJobClassName()).getClass())
			.withIdentity(dto.getJobName(), dto.getJobGroupName())
			.withDescription(dto.getDescription())
			.storeDurably()
			.build();

		// 设置任务参数
		if (Objects.nonNull(dto.getParams()) && !dto.getParams().isEmpty()) {
			jobDetail.getJobDataMap().putAll(dto.getParams());
		}

		CronTrigger cronTrigger = TriggerBuilder.newTrigger()
			.withIdentity(dto.getTriggerName(), dto.getTriggerGroupName())
			.startNow()
			.withSchedule(CronScheduleBuilder.cronSchedule(dto.getCron()))
			.build();

		scheduler.scheduleJob(jobDetail, cronTrigger);

		// 启动调度器
		if (!scheduler.isShutdown()) {
			scheduler.start();
		}
	}

	@SneakyThrows
	@Override
	public void rescheduleJob(QuartzJobDTO dto) {
		TriggerKey triggerKey = TriggerKey.triggerKey(dto.getTriggerName(), dto.getTriggerGroupName());
		CronTrigger cronTrigger = TriggerBuilder.newTrigger()
			.withIdentity(triggerKey)
			.withSchedule(CronScheduleBuilder.cronSchedule(dto.getCron()))
			.startNow()
			.build();
		scheduler.rescheduleJob(triggerKey, cronTrigger);
	}

	@SneakyThrows
	@Override
	public void pauseJob(String jobName, String jobGroupName) {
		scheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
	}

	@SneakyThrows
	@Override
	public void resumeJob(String jobName, String jobGroupName) {
		scheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
	}

	@SneakyThrows
	@Override
	public void deleteJob(String jobName, String jobGroupName) {
		scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, jobGroupName));
		scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, jobGroupName));
		scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
	}

	@SneakyThrows
	@Override
	public PageInfo<QuartzJobVO> listJobs(Map map) {
		PageInfo<QuartzJobVO> page = null;
		List<QuartzJobVO> jobList = new ArrayList<>();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				QuartzJobVO quartzJob = new QuartzJobVO();
				quartzJob.setTriggerGroupName(trigger.getKey().getName());
				quartzJob.setTriggerName(trigger.getKey().getGroup());
				quartzJob.setJobGroupName(jobKey.getGroup());
				quartzJob.setJobName(jobKey.getName());
				quartzJob.setStartTime(trigger.getStartTime());
				quartzJob.setJobClassName(scheduler.getJobDetail(jobKey).getJobClass().getName());
				quartzJob.setNextFireTime(trigger.getNextFireTime());
				quartzJob.setPreviousFireTime(trigger.getPreviousFireTime());
				if (scheduler.getTriggerState(trigger.getKey()).name().equals("NORMAL")) {
					quartzJob.setJobStatus("正常");
				} else {
					quartzJob.setJobStatus(scheduler.getTriggerState(trigger.getKey()).name());
				}
				quartzJob.setDescription(scheduler.getJobDetail(jobKey).getDescription());
				quartzJob.setParams(scheduler.getJobDetail(jobKey).getJobDataMap());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					quartzJob.setCron(translateToChinese(cronTrigger.getCronExpression(),CRON_TIME_CN));
					quartzJob.setTimeZone(cronTrigger.getTimeZone().getDisplayName());
				}
				Integer pageNum = Integer.parseInt(map.get("pageNum").toString());
				if (pageNum == null) {
					pageNum = 1;
				}
				Integer pageSize = Integer.parseInt(map.get("pageSize").toString());
				if (pageSize == null) {
					pageSize = 20;
				}
				jobList.add(quartzJob);
				page = new PageInfo<QuartzJobVO>(jobList);
			}
		}
		return page;
	}

	/**
	 * 通过反射获取类
	 * @param className 类名
	 * @return {@link Job}
	 */
	@SneakyThrows
	private static Job getClass(String className) {
		Class<?> clazz = Class.forName(className);
		return (Job) clazz.newInstance();
	}

}
