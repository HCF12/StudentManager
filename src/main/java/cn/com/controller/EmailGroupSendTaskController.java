package cn.com.controller;

import cn.com.service.EmailGroupSendTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Title: cn.com.controller-EmailGroupSendTaskController
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/9/25 18:30
 */
@RestController
@CrossOrigin
@RequestMapping("/email")
public class EmailGroupSendTaskController {
    @Autowired
    private EmailGroupSendTaskService emailGroupSendTaskService;

    @PostMapping("/getAllEmailGroupSendTask")
    public Map getAllEmailGroupSendTask(@RequestBody Map map){

        return emailGroupSendTaskService.getAllEmailGroupSendTask(map);
    }
}
