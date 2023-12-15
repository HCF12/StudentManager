package cn.com.controller;

import cn.com.pojo.StudentMenu;
import cn.com.service.StudentMenuService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/studentMenu")
public class StudentMenuController {
    @Resource
    private StudentMenuService studentMenuService;
    @PostMapping("/getStudentMenu")
    public Map getStudentMenu(){
        return studentMenuService.getStudentMenu();
    }
}
