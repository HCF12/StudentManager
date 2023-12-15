package cn.com.mapper;

import cn.com.pojo.EmailGroupSendTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Title: cn.com.mapper-EmailGroupSendTaskMapper
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/9/25 18:02
 */
public interface EmailGroupSendTaskMapper {

    Integer addEmailGroupSendTask(EmailGroupSendTask emailGroupSendTask);

    Integer addSingleEmailGroupSendTask(EmailGroupSendTask emailGroupSendTask);

    List<EmailGroupSendTask> getEmailGroupSendTask();

    List<EmailGroupSendTask> getAllEmailGroupSendTask(Map map);

    Integer deleteSendTask(@Param("customerNo") Integer customerNo);
}
