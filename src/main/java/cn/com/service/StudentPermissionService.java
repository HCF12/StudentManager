package cn.com.service;

import cn.com.pojo.StudentPermission;

import java.util.Map;

public interface StudentPermissionService {

    /**
     * 授权
     * @param stuPermission
     * @return
     */
    Map addPermission(StudentPermission stuPermission);
}
