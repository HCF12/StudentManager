package cn.com.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Title: cn.com.pojo-EmailGroupSendTask
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/9/25 18:05
 */
public class EmailGroupSendTask {

    private String recGuid;

    public String getRecGuid() {
        return recGuid;
    }

    public void setRecGuid(String recGuid) {
        this.recGuid = recGuid;
    }

    public String getBatchId() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        batchId = "EM" + simpleDateFormat.format(new Date()) + "9998";
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getPie() {
        return pie;
    }

    public void setPie(Integer pie) {
        this.pie = pie;
    }


    public Integer getTrialCount() {
        return trialCount;
    }

    public void setTrialCount(Integer trialCount) {
        this.trialCount = trialCount;
    }

    public Map getAttachmentMap() {
        return attachmentMap;
    }

    public void setAttachmentMap(Map attachmentMap) {
        this.attachmentMap = attachmentMap;
    }

    public Integer getTrialCountLimit() {
        return trialCountLimit;
    }

    public void setTrialCountLimit(Integer trialCountLimit) {
        this.trialCountLimit = trialCountLimit;
    }

    private String batchId;

    private String templateId;

    private Integer customerNo;

    private String customerName;

    private String email;

    private String carbon;

    private String subject;

    private String content;

    private String contentType;

    private Integer state;

    private Integer priority;

    private Integer pie;

    private String createdDate;

    private String expectedStartDate;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getExpectedStartDate() {
        return expectedStartDate;
    }

    public void setExpectedStartDate(String expectedStartDate) {
        this.expectedStartDate = expectedStartDate;
    }

    private Integer trialCount;

    public List<Long> getCustomerNos() {
        return customerNos;
    }

    public void setCustomerNos(List<Long> customerNos) {
        this.customerNos = customerNos;
    }

    private Integer trialCountLimit;

    private Map attachmentMap;

    private List<Long> customerNos;
}
