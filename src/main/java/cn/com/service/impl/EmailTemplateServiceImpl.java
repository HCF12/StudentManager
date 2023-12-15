package cn.com.service.impl;

import cn.com.mapper.EmailTemplateMapper;
import cn.com.pojo.EmailTemplate;
import cn.com.service.EmailTemplateService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: cn.com.service.impl-EmailTemplateServiceImpl
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/11/8 15:48
 */
@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {
    private static final Log log = LogFactory.getLog(EmailTemplateServiceImpl.class);
    @Autowired
    private EmailTemplateMapper mapper;
    @Override
    public Map getEmailTemplate() {
        Map map = new HashMap();
        List<EmailTemplate> list = null;
        try {
            list = mapper.getEmailTemplate();
            map.put("code","200");
            map.put("msg","查询模板成功！");
            map.put("data",list);
        } catch (Exception e) {
            map.put("code","500");
            map.put("msg","查询模板异常！");
            log.error("获取模板数据异常：" + e.getMessage());
        }
        return map;
    }

    @Override
    public Map getTemplateContent(String templateId) {
        Map map = new HashMap();
        try {
            String content = mapper.getTemplateContent(templateId);
            map.put("code","200");
            map.put("msg","查询模板内容成功！");
            map.put("data",content);
        } catch (Exception e) {
            map.put("code","500");
            map.put("msg","查询模板内容异常！");
            log.error("获取模板内容数据异常：" + e.getMessage());
        }
        return map;
    }
}
