package cn.com.service;


import cn.com.dto.QuartzJobDTO;
import cn.com.vo.QuartzJobVO;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author william@StarImmortal
 * @date 2022/09/26
 */
public interface QuartzJobService {

	/**
	 * 调度定时任务
	 * @param dto 定时任务数据传输对象
	 */
	void scheduleJob(QuartzJobDTO dto);

	/**
	 * 重新定制定时任务
	 * @param dto 定时任务数据传输对象
	 */
	void rescheduleJob(QuartzJobDTO dto);

	/**
	 * 暂停定时任务
	 * @param jobName 定时任务名称
	 * @param jobGroupName 定时任务分组名称
	 */
	void pauseJob(String jobName, String jobGroupName);

	/**
	 * 恢复定时任务
	 * @param jobName 定时任务名称
	 * @param jobGroupName 定时任务分组名称
	 */
	void resumeJob(String jobName, String jobGroupName);

	/**
	 * 删除定时任务
	 * @param jobName 定时任务名称
	 * @param jobGroupName 定时任务分组名称
	 */
	void deleteJob(String jobName, String jobGroupName);

	/**
	 * 查询所有任务
	 * @return 所有任务
	 */
	PageInfo<QuartzJobVO> listJobs(Map map);

}
