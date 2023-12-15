package cn.com.mapper;

import cn.com.pojo.StudentPermission;

public interface StudentPermissionMapper {

    /**
     * 添加权限
     * @param stuPermission
     * @return
     */
    Integer addPermission(StudentPermission stuPermission);
}
