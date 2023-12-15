package cn.com.service.impl;

import cn.com.pojo.SwTestOnlineCustomer;
import cn.com.pojo.SwTestOnlineCustomerNo;
import cn.com.vo.HttpResponseVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @Title: cn.com.service.impl-TestServiceImpl
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/11/23 15:40
 */
@Service
public class TestServiceImpl {
    private static final Log log = LogFactory.getLog(TestServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    public HttpResponseVo run() {
        //调用线上平台实名认证的数据
        String onLineUrl = "http://172.20.10.2:8082/sw/onLineGetCustomerList";
        //调用接口
        HttpEntity<T> entity = null;
        ResponseEntity<HttpResponseVo> responseVoResponseEntity = restTemplate.exchange(onLineUrl, HttpMethod.GET, null, HttpResponseVo.class);
        HttpResponseVo result = responseVoResponseEntity.getBody();
        if (result.getData() != null && result.getData().size() > 0) {
            log.info(result.getData().toString());
            String json = JSON.toJSONString(result.getData());
            JSONArray jsonArray = JSONObject.parseArray(json);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String userCode = jsonObject.getString("userCode");
                log.info(userCode);
            }
        }
        return result;
    }
}
