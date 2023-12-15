package cn.com.controller;

import cn.com.service.EmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Title: cn.com.controller-EmailTemplateController
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/11/8 15:56
 */
@RestController
@CrossOrigin
@RequestMapping("/template")
public class EmailTemplateController {
    @Autowired
    private EmailTemplateService service;

    @GetMapping("/getTemplate")
    public Map getEmailTemplate() {
        return service.getEmailTemplate();
    }

    @GetMapping("/getTemplateContent/{templateId}")
    public Map getTemplateContent(@PathVariable String templateId) {

        return service.getTemplateContent(templateId);
    }
}
