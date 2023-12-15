package cn.com.pojo;

import java.io.Serializable;
import java.util.Map;

/**
 * @Title: cn.com.pojo-EmailModel
 * @Description:StudentArchivesManagement 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/9/25 14:54
 */
public class EmailModel implements Serializable {
    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 收件人邮箱
     */
    private String[] recipientMailbox;

    /**
     * 抄送人邮箱
     */
    private String[] ccMailbox;

    /**
     * 加密抄送人邮箱
     */
    private String[] bccMailbox;

    /**
     * 发送内容
     */
    private String sendContent;

    /**
     * 真实名称/附件路径
     */
    private Map<String, String> enclosures;

    //    附件是否添加的到正文,默认false不添加
    //    private Boolean is_;


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String[] getRecipientMailbox() {
        return recipientMailbox;
    }

    public void setRecipientMailbox(String[] recipientMailbox) {
        this.recipientMailbox = recipientMailbox;
    }

    public String[] getCcMailbox() {
        return ccMailbox;
    }

    public void setCcMailbox(String[] ccMailbox) {
        this.ccMailbox = ccMailbox;
    }

    public String[] getBccMailbox() {
        return bccMailbox;
    }

    public void setBccMailbox(String[] bccMailbox) {
        this.bccMailbox = bccMailbox;
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

    public Map<String, String> getEnclosures() {
        return enclosures;
    }

    public void setEnclosures(Map<String, String> enclosures) {
        this.enclosures = enclosures;
    }
}
