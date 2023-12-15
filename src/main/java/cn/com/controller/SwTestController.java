package cn.com.controller;

import cn.com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Title: cn.com.controller-SwTestController
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/11/23 14:49
 */
@CrossOrigin
@RestController
@RequestMapping("/sw")
public class SwTestController {
    @Autowired
    private SwTestOnlineCustomerService onlineService;
    @Autowired
    private SwTestOnlineCustomerNoService onlineServiceNo;
    @Autowired
    private SwTestServiceWorkCustomerService serviceWorkService;
    @Autowired
    private SwTestCrmCustomerService crmService;
    @Autowired
    private SwTestUcisCustomerService ucisService;

    @GetMapping("/onLineGetCustomerList")
    public Map onLineGetCustomerList(){
        return onlineService.getCustomerList();
    }

    @GetMapping("/onLineGetCustomerNoList")
    public Map onLineGetCustomerNoList(){
        return onlineServiceNo.getCustomerList();
    }

    @GetMapping("/serviceWorkGetCustomerNoList")
    public Map serviceWorkGetCustomerNoList(){
        return serviceWorkService.getCustomerList();
    }

    @GetMapping("/crmGetCustomerNoList")
    public Map crmGetCustomerNoList(){
        return crmService.getCustomerList();
    }

    @GetMapping("/ucisGetCustomerNoList")
    public Map ucisGetCustomerNoList(){
        return ucisService.getCustomerList();
    }
}
