package cn.com.pojo;

import java.io.Serializable;

public class StudentPermission implements Serializable {

    private String permissionGuid;

    /**
     * 权限类型
     */
    private Integer permissionType;

    /**
     * 给到组
     */
    private String toGroup;

    /**
     * 给到人
     */
    private String toPerson;

    /**
     * 生效时间
     */
    private String effectiveTime;

    /**
     * 失效时间
     */
    private String failureTime;

    /**
     * 创建者
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private String createdDate;

    /**
     * 更新者
     */
    private String modifyBy;

    public String getPermissionGuid() {
        return permissionGuid;
    }

    public void setPermissionGuid(String permissionGuid) {
        this.permissionGuid = permissionGuid;
    }

    public Integer getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }

    public String getToGroup() {
        return toGroup;
    }

    public void setToGroup(String toGroup) {
        this.toGroup = toGroup;
    }

    public String getToPerson() {
        return toPerson;
    }

    public void setToPerson(String toPerson) {
        this.toPerson = toPerson;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getFailureTime() {
        return failureTime;
    }

    public void setFailureTime(String failureTime) {
        this.failureTime = failureTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * 更新时间
     */
    private String modifyDate;
}
