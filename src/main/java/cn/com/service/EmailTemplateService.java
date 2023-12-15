package cn.com.service;

import java.util.Map;

/**
 * @Title: cn.com.service-EmailTemplateService
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/11/8 15:47
 */
public interface EmailTemplateService {
    Map getEmailTemplate();

    Map getTemplateContent(String templateId);
}
