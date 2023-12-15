package cn.com.controller;

import cn.com.pojo.EmailGroupInfo;
import cn.com.service.EmailGroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Title: cn.com.controller-EmailGroupInfoController
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/9/25 17:02
 */
@CrossOrigin
@RestController
@RequestMapping("/emailGroup")
public class EmailGroupInfoController {
    @Autowired
    private EmailGroupInfoService service;
    @PostMapping("/addEmailGroup")
    public Map addEmailGroup(@RequestBody EmailGroupInfo emailGroupInfo){
        return service.addEmailGroup(emailGroupInfo);
    }
    @GetMapping("/getEmailGroupInfoById/{id}")
    public Map getEmailGroupInfoById(@PathVariable Integer id){
        return service.getEmailGroupInfoById(id);
    }
    @PostMapping("/updEmailGroup")
    public Map updEmailGroup(@RequestBody EmailGroupInfo emailGroupInfo) {
        return service.updEmailGroup(emailGroupInfo);
    }

    @PostMapping("/getEmailList")
    public Map getEmailGroupInfoList(@RequestBody Map map) {
        return service.getEmailGroupInfoList(map);
    }

    @GetMapping("/startConfiguration/{customerNos}")
    public Map startEmailConfiguration(@PathVariable List<Long> customerNos) {
        return service.startEmailConfiguration(customerNos);
    }
	
	@GetMapping("/checkEmailConfiguration/{customerNos}")
    public Map checkEmailConfiguration(@PathVariable List<Long> customerNos) {
        return service.checkEmailConfiguration(customerNos);
    }
	
	@GetMapping("/runEmailConfiguration/{customerNos}")
    public Map runEmailConfiguration(@PathVariable List<Long> customerNos) {
        return service.runEmailConfiguration(customerNos);
    }
    @GetMapping("/batchDeleteEmailConfiguration/{customerNos}")
    public Map batchDeleteEmailConfiguration(@PathVariable List<Long> customerNos) {
        return service.batchDelEmailConfiguration(customerNos);
    }

}
