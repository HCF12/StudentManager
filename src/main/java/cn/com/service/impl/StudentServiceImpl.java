package cn.com.service.impl;

import cn.com.mapper.LoginHisMapper;
import cn.com.mapper.StudentMapper;
import cn.com.pojo.LoginHis;
import cn.com.pojo.Student;
import cn.com.service.StudentService;
import cn.com.utils.IpAddressUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Log log= LogFactory.getLog(StudentServiceImpl.class);

    @Resource
    private StudentMapper studentMapper;
    @Autowired
    private LoginHisMapper loginHisMapper;
    @Override
    public Map getStudent(Map map) {
        Map mp = new HashMap();
        PageInfo<Student> page = null;
        List<Student> list = null;
        try {
            Integer pageNum = Integer.parseInt(map.get("pageNum").toString());
            if(pageNum == null){
                pageNum = 1;
            }
            Integer pageSize = Integer.parseInt(map.get("pageSize").toString());
            if(pageSize == null){
                pageSize = 20;
            }
            PageHelper.startPage( pageNum,pageSize);
            list = studentMapper.getStudent(map);
            page = new PageInfo<Student>(list);
            if(page != null){
                JSONObject jsonObject =JSONObject.fromObject(page);
                log.info("学生信息集：" + jsonObject.toString());
                mp.put("code","200");
                mp.put("data",page);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mp;
    }

    @Override
    public Map login(Map map) {
        Map mp = new HashMap<>();
        try {
            String loginName = map.get("loginName").toString();
            String password = map.get("password").toString();
            Integer count = Integer.parseInt(map.get("count").toString());
            Student stu =  studentMapper.login(loginName);//调用登录接口
            String pwd = MD5(password);//实现MD5加密
            Integer lockCount = studentMapper.getLockCount(loginName);
            if(stu==null){ //账号不存在判断
                mp.put("code","200");
                mp.put("msg","账号不存在!");
                mp.put("data",new Student(1));
                log.info("提示信息：" + mp);
            }else if(lockCount > 0){
                mp.put("code","200");
                mp.put("msg","账号已锁定!");
                mp.put("data",new Student(4));
                log.info("提示信息：" + mp);
                return mp;
            }else if(!pwd.equals(stu.getPassword())){//密码错误判断
                count++;
                if(count == 3){//当密码输错3次调用加锁接口
                    Integer lock = studentMapper.lock(stu.getStudentId());
                    if(lock == 1){//加锁
                        mp.put("code","200");
                        mp.put("msg","账号已锁定!");
                        mp.put("data",new Student(4));
                        log.info("提示信息：" + mp);
                        return mp;
                    }
                }
                mp.put("code","200");
                mp.put("msg","密码错误!");
                mp.put("data",new Student(2));
                log.info("提示信息：" + mp);
            }else if(stu.getWorkStatus().equals("0") || stu.getWorkStatus() == "0"){
                mp.put("data",new Student(4));
            }else {
                Student student = new Student(3);
                LoginHis loginHis = new LoginHis();
                student.setStudentName(stu.getStudentName());
                student.setLoginName(stu.getLoginName());
                student.setStudentId(stu.getStudentId());
                student.setUrl(stu.getUrl());
                student.setGradeId(stu.getGradeId());
                student.setClassId(stu.getClassId());
                student.setToken(UUID.randomUUID().toString());
                String ip = IpAddressUtils.getIp();
                log.info("登录ip:" +ip);
                String ipAddress = IpAddressUtils.getAddress(ip);
                log.info("登录地址：" + ipAddress);
                loginHis.setStuId(stu.getStudentId());
                loginHis.setLoginIp(ip);
                loginHis.setLoginAddress(ipAddress);
                loginHisMapper.addLoginHis(loginHis);
                mp.put("code","200");
                mp.put("msg","登录成功!");
                mp.put("data",student);
                log.info("提示信息：" + mp);
                return mp;
            }
        } catch (Exception e) {
            mp.put("code","500");
            mp.put("msg","程序异常!" + e.getMessage());
            mp.put("data",new Student(5));
            log.error("提示信息：" + mp);
        }
        return mp;
    }

    @Override
    public Map unlock(String studentId) {
        Map map = new HashMap<>();
        try {
            Integer unlockRow = studentMapper.unlock(studentId);
            if(unlockRow > 0){
                map.put("code","200");
                map.put("msg","解锁成功！");
                map.put("data",1);
                log.info("提示信息：" + map);
            }else {
                map.put("code","200");
                map.put("msg","解锁失败！");
                map.put("data",-1);
                log.info("提示信息：" + map);
            }
        } catch (Exception e) {
            map.put("code","500");
            map.put("msg","程序异常：" + e.getMessage() + "!");
            log.info("提示信息：" + map);
        }
        return map;
    }

    @Override
    public Map updPassword(Map map) {

        Map m = new HashMap();
        try {
            //获取MVC传过来得学生id
            String studentId = map.get("studentId").toString();
            //获取旧密码
            String oldPassword = studentMapper.getOldPassword(studentId);
            //MVC传过来得旧密码
            String password = MD5(map.get("password").toString());

            String newPassword = MD5(map.get("newPassword").toString());
            map.put("newPassword",newPassword);
            if(!password.equals(oldPassword)) {
                m.put("code","405");
                m.put("msg", "原密码不正确！");
                log.info("提示信息：" + m);
                return m;
            }
            Integer row = studentMapper.updPassword(map);
            if(row > 0){
                m.put("code","200");
                m.put("msg", "密码修改成功！");
                log.info("提示信息：" + m);
            }else{
                m.put("code","400");
                m.put("msg", "密码修改失败！");
                log.info("提示信息：" + m);
            }
        } catch (Exception e) {
            m.put("code","500");
            m.put("msg", "操作失败：" + e.getMessage() + "！");
            log.info("提示信息：" + m);
            return m;
        }
        return m;
    }

    @Override
    public Map register(Student student) {
        Map map = new HashMap();
        try {
            //随机生成伪id
            String studentId = UUID.randomUUID().toString().toUpperCase();
            student.setStudentId(studentId);
            if(student.getStudentName() == null){
                map.put("code","400");
                map.put("msg","学生姓名为必填项！");
                log.info("提示信息：" + map);
                return map;
            }
            if(student.getGradeId() == null){
                map.put("code","400");
                map.put("msg","学生年级为必填项！");
                log.info("提示信息：" + map);
                return map;
            }
            if(student.getClassId() == null){
                map.put("code","400");
                map.put("msg","学生班级为必填项！");
                log.info("提示信息：" + map);
                return map;
            }
            if(student.getMobile() == null){
                map.put("code","400");
                map.put("msg","手机号为必填项！");
                log.info("提示信息：" + map);
                return map;
            }
            if(student.getMobile() != null && student.getMobile().length() < 11){
                map.put("code","400");
                map.put("msg","手机号长度不能小于11位！");
                log.info("提示信息：" + map);
                return map;
            }
            if( student.getLoginName() == null){
                map.put("code","400");
                map.put("msg","登录名为必填项！");
                log.info("提示信息：" + map);
                return map;
            }
            if(student.getPassword() == null){
                map.put("code","400");
                map.put("msg","密码不能为空！");
                log.info("提示信息：" + map);
                return map;
            }else {
                String password = MD5(student.getPassword());
                student.setPassword(password);
            }
            Integer row = studentMapper.register(student);
            if(row > 0){
                map.put("code","200");
                map.put("msg","注册成功！");
                log.info("提示信息：" + map);
            }
        } catch (Exception e) {
            map.put("code","500");
            map.put("msg","操作失败：" + e.getMessage() + "！");
            log.info("提示信息：" + map);
        }
        return map;
    }

    @Override
    public Map getStudentById(String studentId) {
        Map map = new HashMap<>();
        try {
            Student stu = studentMapper.getStudentById(studentId);
            if(stu != null){
                map.put("code","200");
                map.put("msg","成功！");
                map.put("data",stu);
                log.info("根据id获取学生信息：" + map);
            }
        } catch (Exception e) {
            map.put("code","200");
            map.put("msg","操作失败：" + e.getMessage() + "!");
            map.put("data","");
            log.error(map);
            return map;
        }

        return map;
    }

    @Override
    public Map getStudentUnlock(Map map) {
        Map mp = new HashMap<>();
        List<Student> list = null;
        try {
            list = studentMapper.getStudentUnlock(map);
            if(list != null){
                mp.put("code","200");
                mp.put("msg","成功！");
                mp.put("data",list);
                log.info("解锁信息：" + mp);
            }
        } catch (Exception e) {
            mp.put("code","500");
            map.put("msg","操作失败：" + e.getMessage() + "!");
            log.info("解锁信息：" + mp);
        }
        return mp;
    }

    @Override
    public Map updStudent(Student student) {
        Map map = new HashMap<>();
        try {
            if(student.getStudentName() == null){
                map.put("code","400");
                map.put("msg","学生姓名为必填项！");
                log.info("提示信息：" + map);
                return map;
            }
            if(student.getGradeId() == null){
                map.put("code","400");
                map.put("msg","学生年级为必填项！");
                log.info("提示信息：" + map);
                return map;
            }
            if(student.getClassId() == null){
                map.put("code","400");
                map.put("msg","学生班级为必填项！");
                log.info("提示信息：" + map);
                return map;
            }
            if(student.getMobile() == null){
                map.put("code","400");
                map.put("msg","手机号为必填项！");
                log.info("提示信息：" + map);
                return map;
            }
            if(student.getMobile() != null && student.getMobile().length() < 11){
                map.put("code","400");
                map.put("msg","手机号长度不能小于11位！");
                log.info("提示信息：" + map);
                return map;
            }
            Integer updStudentRow = studentMapper.updStudent(student);
            if(updStudentRow > 0){
                map.put("code","200");
                map.put("msg","修改成功！");
                log.info("提示信息：" + map);
            }
        } catch (Exception e) {
            map.put("code","500");
            map.put("msg","操作失败：" + e.getMessage() + "！");
            log.info("提示信息：" + map);
            return map;
        }
        return map;
    }

    /***
     * MD5加码 生成32位md5码
     */
    public static String MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }
}
