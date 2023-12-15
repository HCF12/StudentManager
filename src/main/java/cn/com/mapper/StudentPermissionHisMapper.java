package cn.com.mapper;

import org.apache.ibatis.annotations.Param;

public interface StudentPermissionHisMapper {

    /**
     * 权限申请历史记录
     * @param stuPermissionHisGuid
     * @return
     */
    Integer addPermissionHis(@Param("stuPermissionHisGuid") String stuPermissionHisGuid);
}
