package cn.com.controller;

import cn.com.pojo.SwTestOnlineCustomerNo;
import cn.com.service.impl.TestServiceImpl;
import cn.com.vo.HttpResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Title: cn.com.controller-TestController
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/11/23 15:42
 */
@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestServiceImpl service;
    @GetMapping("/run")
    public HttpResponseVo run() {

        return service.run();
    }
}
