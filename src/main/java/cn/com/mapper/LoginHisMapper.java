package cn.com.mapper;

import cn.com.pojo.LoginHis;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: cn.com.mapper-SendEmailMapper
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/9/26 16:29
 */
public interface LoginHisMapper {
    /**
     * 记录登录历史
     * @param loginHis
     */
    void addLoginHis(LoginHis loginHis);

    LoginHis getLoginHisByStudentId(@Param("studentId")String studentId);
}
