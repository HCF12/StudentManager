package cn.com.mapper;

import cn.com.pojo.EmailTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: cn.com.mapper-EmailTemplateMapper
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/9/26 11:08
 */
public interface EmailTemplateMapper {

    List<EmailTemplate> getEmailTemplate();

    String getTemplateContent(@Param("templateId") String templateId);
}
