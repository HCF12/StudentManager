package cn.com.vo;

/**
 * @Title: cn.com.vo-EmailGroupHisVo
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/12/14 15:51
 */
public class EmailGroupHisVo {

    private String batchGuid;

    private String description;

    private String customerNo;

    private String customerName;

    private String email;

    private String carbon;

    private String subject;

    private String content;

    private String contentType;

    private String priority;

    private String pie;

    private Integer trailCount;

    private Integer trailCountLimit;

    private String expectedStartDate;

    private String createdDate;

    public String getBatchGuid() {
        return batchGuid;
    }

    public void setBatchGuid(String batchGuid) {
        this.batchGuid = batchGuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPie() {
        return pie;
    }

    public void setPie(String pie) {
        this.pie = pie;
    }

    public Integer getTrailCount() {
        return trailCount;
    }

    public void setTrailCount(Integer trailCount) {
        this.trailCount = trailCount;
    }

    public Integer getTrailCountLimit() {
        return trailCountLimit;
    }

    public void setTrailCountLimit(Integer trailCountLimit) {
        this.trailCountLimit = trailCountLimit;
    }

    public String getExpectedStartDate() {
        return expectedStartDate;
    }

    public void setExpectedStartDate(String expectedStartDate) {
        this.expectedStartDate = expectedStartDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
