package cn.com.controller;

import cn.com.service.LoginHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Title: cn.com.controller-LoginHisController
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/11/16 17:59
 */
@CrossOrigin
@RestController
@RequestMapping("/loginHis")
public class LoginHisController {
    @Autowired
    private LoginHisService service;
    @GetMapping("/getLoginHisByStudentId/{studentId}")
    public Map getLoginHisByStudentId(@PathVariable String studentId) {
        return service.getLoginHisByStudentId(studentId);
    }
}
