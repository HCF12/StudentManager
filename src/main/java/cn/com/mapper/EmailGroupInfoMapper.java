package cn.com.mapper;

import cn.com.pojo.EmailGroupInfo;
import cn.com.vo.EmailGroupInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Title: cn.com.mapper-EmailGroupInfoMapper
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/9/25 16:31
 */
public interface EmailGroupInfoMapper {
    /**
     * 邮件维护
     * @param emailGroupInfo
     * @return
     */
    Integer addEmailGroup(EmailGroupInfo emailGroupInfo);

    /**
     * 通过邮件编号获取维护配置
     * @param id
     * @return
     */
    EmailGroupInfo getEmailGroupInfoById(@Param("id") Integer id);

    /**
     * 邮件维护
     * @param emailGroupInfo
     * @return
     */
    Integer updEmailGroup(EmailGroupInfo emailGroupInfo);

    /**
     * 发送状态修改
     * @param customerNo
     * @param sendStatus
     */
    void updateStatus(@Param("customerNo") Integer customerNo,@Param("sendStatus") Integer sendStatus);

    /**
     * 通过客户号判断邮件是否存在邮件配置
     * @param customerNo
     * @return
     */
    Integer getCustomerCount(@Param("customerNo") Integer customerNo);

    /**
     * 邮件配置列表
     * @param map
     * @return
     */
    List<EmailGroupInfoVo> getEmailGroupInfoList(Map map);

    /**
     * 批量启用邮件配置
     * @param customerNos
     * @return
     */
    Integer startEmailConfiguration(@Param("customerNos") List<Long> customerNos);

    /**
     * 批量审核邮件配置
     * @param customerNos
     * @return
     */
    Integer checkEmailConfiguration(@Param("customerNos") List<Long> customerNos);

    /**
     * 批量执行邮件配置
     * @param customerNos
     * @return
     */
    Integer runEmailConfiguration(@Param("customerNos") List<Long> customerNos);

    /**
     * 批量删除邮件配置
     * @param customerNos
     * @return
     */
    Integer batchDelEmailConfiguration(@Param("customerNos") List<Long> customerNos);
}
