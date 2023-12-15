package cn.com.pojo;

/**
 * @Title: cn.com.pojo-EmailGroupAttachment
 * @Description:StudentManager 附件实体类
 * @Author: yzh
 * @Date 2023/11/7 9:47
 */
public class EmailGroupAttachment {

    private String recGuid;

    private String attachmentGuid;

    private String attachmentName;

    private String attachmentPath;

    private String createdDate;

    private String createdBy;

    public String getRecGuid() {
        return recGuid;
    }

    public void setRecGuid(String recGuid) {
        this.recGuid = recGuid;
    }

    public String getAttachmentGuid() {
        return attachmentGuid;
    }

    public void setAttachmentGuid(String attachmentGuid) {
        this.attachmentGuid = attachmentGuid;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
