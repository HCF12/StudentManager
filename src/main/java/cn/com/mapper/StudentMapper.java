package cn.com.mapper;

import cn.com.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Map;

import java.util.List;

@Component
public interface StudentMapper {

    /**
     * 登录
     * @param loginName 账号
     * @return
     */
    Student login(@Param("loginName") String loginName);

    /**
     *获取学生信息
     * @param map 参数集
     * @return 学生信息集
     */
    List<Student> getStudent(Map map);

    /**
     * 加锁
     * @param studentId
     * @return
     */
    Integer lock(@Param("studentId") String studentId);

    /**
     * 解锁
     * @param studentId
     * @return
     */
    Integer unlock(@Param("studentId") String studentId);

    /**
     * 获取原密码
     * @param studentId
     * @return
     */
    String getOldPassword(@Param("studentId") String studentId);

    /**
     * 修改密码
     * @param map
     * @return
     */
    Integer updPassword(Map map);

    /**
     * 注册学生信息
     * @param student
     * @return
     */
    Integer register(Student student);

    /**
     * 根据学生id查询学生信息
     * @param studentId
     * @return
     */
    Student getStudentById(@Param("studentId") String studentId);

    /**
     * 解锁账户查询
     * @param map
     * @return
     */
    List<Student> getStudentUnlock(Map map);

    /**
     * 编辑学生信息
     * @param student
     * @return
     */
    Integer updStudent(Student student);

    /**
     * 判断当前账号是否锁定
     * @param loginName
     * @return
     */
    Integer getLockCount(@Param("loginName") String loginName);

    Integer updateStatus(@Param("stuNo")Integer stuNo);
}
