package cn.com.pojo;

import java.io.Serializable;

/**
 * @Title: cn.com.pojo-LoginHis
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/11/16 16:18
 */
public class LoginHis implements Serializable {

    private String stuId;

    private String loginIp;

    private String loginAddress;

    private String loginDate;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }
}
