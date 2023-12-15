package cn.com.utils;
import cn.com.job.SendEmailTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Quartz配置项：定义任务描述及任务触发规则
 *
 * @author william@StarImmortal
 * @date 2022/09/25
 */
@Configuration
public class QuartzConfiguration {
	@Bean
	public JobDetail SendEmailTaskDetail() {
		return JobBuilder.newJob(SendEmailTask.class)
				.withIdentity("SendEmailTaskDetail", "group")
				.withDescription("邮件发送")
				.storeDurably()
				.build();
	}

	@Bean
	public Trigger SendEmailTask() {
		return TriggerBuilder.newTrigger()
				.withIdentity("SendEmailTask", "group")
				.withDescription("邮件发送")
				.forJob(SendEmailTaskDetail())
				.startNow()
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))
				.build();
	}



}
