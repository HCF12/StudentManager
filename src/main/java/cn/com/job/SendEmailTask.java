package cn.com.job;

import cn.com.service.SendEmailService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Title: cn.com.job-SendEmailTask
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/11/6 15:50
 */
public class SendEmailTask extends QuartzJobBean {
    @Autowired
    private SendEmailService service;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        service.goSendEmail();
    }
}
