package cn.com.service;

import cn.com.pojo.EmailGroupInfo;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

/**
 * @Title: cn.com.service-EmailGroupInfoService
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/9/25 16:43
 */
public interface EmailGroupInfoService {
    /**
     * 邮件维护
     * @param emailGroupInfo
     * @return
     */
    Map addEmailGroup(EmailGroupInfo emailGroupInfo);

    Map getEmailGroupInfoById(Integer id);

    Map updEmailGroup(EmailGroupInfo emailGroupInfo);

    /**
     * 邮件列表
     * @param map
     * @return
     */
    Map getEmailGroupInfoList(Map map);

    /**
     * 启用邮件配置
     * @param customerNos
     * @return
     */
    Map startEmailConfiguration(List<Long> customerNos);

    /**
     * 审核邮件配置
     * @param customerNos
     * @return
     */
	Map checkEmailConfiguration(List<Long> customerNos);

    /**
     * 执行邮件配置
     * @param customerNos
     * @return
     */
    Map runEmailConfiguration(List<Long> customerNos);

    /**
     * 批量删除邮件配置
     * @param customerNos
     * @return
     */
    Map batchDelEmailConfiguration(List<Long> customerNos);
}
