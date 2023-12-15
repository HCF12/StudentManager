package cn.com.controller;

import cn.com.pojo.EmailModel;
import cn.com.service.TestSendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Title: cn.com.controller-TestEmailController
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/9/25 15:25
 */
@CrossOrigin
@RestController
@RequestMapping("/testEmail")
public class TestEmailController {
    @Autowired
    private TestSendEmail testSendEmailService;
    @PostMapping("/sendEmail")
    public Map sendEmail(@RequestBody Map map){
        return testSendEmailService.sendEmail(map);
    }
}
