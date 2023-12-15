package cn.com.controller;

import cn.com.pojo.Grade;
import cn.com.service.GradeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("grade")
public class GradeController {

    @Resource
    private GradeService service;

    @PostMapping("/getGrade")
    public Map getGrade(){
        return service.getGrade();
    }
}
