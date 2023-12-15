package cn.com.service.impl;

import cn.com.mapper.SwTestCrmCustomerMapper;
import cn.com.pojo.SwTestCrmCustomer;
import cn.com.service.SwTestCrmCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: cn.com.service.impl-SwTestCrmCustomerServiceImpl
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/11/23 14:37
 */
@Service
public class SwTestCrmCustomerServiceImpl implements SwTestCrmCustomerService {
    @Autowired
    private SwTestCrmCustomerMapper mapper;
    @Override
    public Map getCustomerList() {
        Map map = new HashMap();
        List<SwTestCrmCustomer> list = null;
        try {
            list = mapper.getCustomerList();
            map.put("code",1);
            map.put("msg","查询成功");
            map.put("data",list);
        } catch (Exception e) {
            map.put("code",-1);
            map.put("msg","查询失败");
            map.put("data",list);
        }
        return map;
    }
}
