package cn.com.service.impl;

import cn.com.pojo.EmailModel;
import cn.com.service.TestSendEmail;
import cn.com.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: cn.com.service.impl-TestSendEmailImpl
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/9/25 15:22
 */
@Service
public class TestSendEmailImpl implements TestSendEmail {
    @Autowired
    EmailUtils emailUtils;

    @Override
    public Map sendEmail(Map map) {
        Map result = new HashMap();
        EmailModel emailModel = new EmailModel();
        Map mp = new HashMap<String, String>();
        //收件人
        if (map.get("recipientMailbox") != null && !map.get("recipientMailbox").toString().isEmpty()) {
            emailModel.setRecipientMailbox(map.get("recipientMailbox").toString().split(","));
        }
        //抄送
        if (map.get("ccMailbox") != null && !map.get("ccMailbox").toString().isEmpty()) {
            emailModel.setCcMailbox(map.get("ccMailbox").toString().split(","));
        }
        //密送
        if (map.get("bccMailbox") != null && !map.get("bccMailbox").toString().isEmpty()) {
            emailModel.setBccMailbox(map.get("bccMailbox").toString().split(","));
        }
        //附件
        if (map.get("enclosures") != null && !map.get("enclosures").toString().isEmpty()) {
            mp.put("url", map.get("enclosures").toString());
        }
        emailModel.setEnclosures(mp);
        //主题
        emailModel.setSubject(map.get("subject").toString());
        //内容
        emailModel.setSendContent(map.get("sendContent").toString());

        //发送操作
        int res = emailUtils.sendSimpleMail(emailModel);
        if (res == 1) {
            result.put("code","200");
            result.put("msg","发送成功");
        }else {
            result.put("code","200");
            result.put("msg","发送失败");
        }
        result.put("data",res);

        return result;
    }
}
