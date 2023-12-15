package cn.com.controller;

import cn.com.service.EmailGroupSendHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Title: cn.com.controller-EmailGroupSendHisController
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/12/14 16:19
 */
@CrossOrigin
@RestController
@RequestMapping("/emailHis")
public class EmailGroupSendHisController {
    @Autowired
    private EmailGroupSendHisService service;
    @PostMapping("/getEmailGroupSendHisInfo")
    public Map getEmailGroupSendHisInfo(@RequestBody Map map) {
        return service.getEmailGroupSendHisInfo(map);
    }
}
