package cn.com.service;

import cn.com.pojo.EmailGroupSendTask;

import java.util.List;
import java.util.Map;

/**
 * @Title: cn.com.service-EmailGroupSendTaskService
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/9/25 18:28
 */
public interface EmailGroupSendTaskService {
    void addEmailGroupSendTask();

    void addEmailGroupSendTaskByTemplate();

    Map getAllEmailGroupSendTask(Map map);
}
