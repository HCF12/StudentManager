package cn.com.controller;

import cn.com.pojo.Student;
import cn.com.service.StudentService;
import cn.com.utils.IpAddressUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/studentManager")
public class StudentController {

    @Resource
    private StudentService studentService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/login")
    public Map login(@RequestBody Map map){
        /*String ip =IpAddressUtils.getIpAddress(request);
        map.put("ipAddress",ip);*/
        return studentService.login(map);
    }

    @PostMapping("/getStudent")
    public Map getStudent(@RequestBody Map map){
       return studentService.getStudent(map);
    }

    @GetMapping("/unlock/{studentId}")
    public Map unlock(@PathVariable String studentId){
        return studentService.unlock(studentId);
    }

    @PostMapping("/updPassword")
    public Map updPassword(@RequestBody Map map){
        return studentService.updPassword(map);
    }

    @PostMapping("/register")
    public Map register(@RequestBody Student student){
        return studentService.register(student);
    }

    @GetMapping("/getStudentById")
    public Map getStudentById(@RequestParam String studentId){
        return studentService.getStudentById(studentId);
    }

    @PostMapping("/getStudentUnlock")
    public Map getStudentUnlock(@RequestBody Map map) {
        return studentService.getStudentUnlock(map);
    }

    @PostMapping("/updStudent")
    public Map updStudent(Student student) {

        return studentService.updStudent(student);
    }
}
