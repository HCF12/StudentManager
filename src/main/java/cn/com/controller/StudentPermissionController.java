package cn.com.controller;

import cn.com.pojo.StudentPermission;
import cn.com.service.StudentPermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/studentPermission")
public class StudentPermissionController {

    @Resource
    private StudentPermissionService studentPermissionService;

    @PostMapping("/addPermission")
    public Map addPermission(@RequestBody StudentPermission stuPermission){

        return studentPermissionService.addPermission(stuPermission);
    }
}
