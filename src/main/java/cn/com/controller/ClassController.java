package cn.com.controller;

import cn.com.service.StuClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/stuClass")
public class ClassController {
    @Autowired
    private StuClassService classService;

    @GetMapping("/getClassByGrade/{gradeId}")
    public Map getClassByGrade(@PathVariable String gradeId){

        return classService.getClassByGrade(gradeId);
    }
}
