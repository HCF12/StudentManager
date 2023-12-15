package cn.com.service;

import java.util.Map;

/**
 * @Title: cn.com.service-LoginHisService
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/11/16 17:54
 */
public interface LoginHisService {

    Map getLoginHisByStudentId(String studentId);
}
