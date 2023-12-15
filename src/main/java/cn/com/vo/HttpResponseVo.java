package cn.com.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @Title: cn.com.vo-HttpResponseVo
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/11/23 16:27
 */
public class HttpResponseVo {

    private String code;

    private String msg;

    private List data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
