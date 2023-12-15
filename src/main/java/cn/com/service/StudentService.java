package cn.com.service;

import cn.com.pojo.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface StudentService {

    /**
     * 获取学生信息
     * @param map
     * @return
     */
    Map getStudent(Map map);

    /**
     * 登录
     * @param map
     * @return
     */
    Map login(Map map);

    /**
     * 解锁
     * @param studentId
     * @return
     */
    Map unlock(String studentId);

    /**
     * 修改密码
     * @param map
     * @return
     */
    Map updPassword(Map map);

    /**
     * 注册学生信息
     * @param student
     * @return
     */
    Map register(Student student);

    /**
     * 根据学生id查询学生信息
     * @param studentId
     * @return
     */
    Map getStudentById(String studentId);

    /**
     * 解锁账户查询
     * @param map
     * @return
     */
    Map getStudentUnlock(Map map);

    /**
     * 编辑学生信息
     * @param student
     * @return
     */
    Map updStudent(Student student);



}
