package cn.com.service.impl;

import cn.com.mapper.LoginHisMapper;
import cn.com.pojo.LoginHis;
import cn.com.service.LoginHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: cn.com.service.impl-LoginHisServiceImpl
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/11/16 17:55
 */
@Service
public class LoginHisServiceImpl implements LoginHisService {
    @Autowired
    private LoginHisMapper mapper;
    @Override
    public Map getLoginHisByStudentId(String studentId) {
        Map map = new HashMap();
        try {
            LoginHis getLoginHisByStudentId = mapper.getLoginHisByStudentId(studentId);
            map.put("code","200");
            map.put("msg","获取登录记录信息成功！");
            map.put("data",getLoginHisByStudentId);
        } catch (Exception e) {
            map.put("code","500");
            map.put("msg","获取登录记录信息异常！");
        }
        return map;
    }
}
