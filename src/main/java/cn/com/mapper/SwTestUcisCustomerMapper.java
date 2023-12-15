package cn.com.mapper;

import cn.com.pojo.SwTestUcisCustomer;

import java.util.List;

/**
 * @Title: cn.com.mapper-SwTestUcisCustomerMapper
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/11/23 14:29
 */
public interface SwTestUcisCustomerMapper {

    List<SwTestUcisCustomer> getCustomerList();
}
