package cn.com.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Title: cn.com.pojo-EmailGroupInfo
 * @Description:StudentManager 分组邮件实体类
 * @Author: hucefu
 * @Date 2023/9/25 16:11
 */
public class EmailGroupInfo {

    private String recGuid;

    private Integer customerNo;

    private String customerName;

    private String receiveEmail;

    private String carbon;

    private String subject;

    private String emailModel;

    private Integer isModel;

    public Integer getIsModel() {
        return isModel;
    }

    public void setIsModel(Integer isModel) {
        this.isModel = isModel;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd hh:mm:ss",timezone="GMT+8")
    private Date expectTime;

    private Integer status;

    private Date createdDate;

    private String createdBy;

    private Date modifiedDate;

    public String getRecGuid() {
        return recGuid;
    }

    public void setRecGuid(String recGuid) {
        this.recGuid = recGuid;
    }

    public Integer getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(Integer customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }

    public String getCarbon() {
        return carbon;
    }

    public void setCarbon(String carbon) {
        this.carbon = carbon;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmailModel() {
        return emailModel;
    }

    public void setEmailModel(String emailModel) {
        this.emailModel = emailModel;
    }

    public Date getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(Date expectTime) {
        this.expectTime = expectTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachmentGuid() {
        return attachmentGuid;
    }

    public void setAttachmentGuid(String attachmentGuid) {
        this.attachmentGuid = attachmentGuid;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    private String modifiedBy;

    private String content;

    private String attachmentGuid;

    private Integer sendStatus;

    private Integer checkStatus;
}
