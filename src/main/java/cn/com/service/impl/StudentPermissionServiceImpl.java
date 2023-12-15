package cn.com.service.impl;

import cn.com.mapper.StudentPermissionHisMapper;
import cn.com.mapper.StudentPermissionMapper;
import cn.com.pojo.StudentPermission;
import cn.com.service.StudentPermissionService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

@Service
public class StudentPermissionServiceImpl implements StudentPermissionService {
    //配置日志
    private static final Log log = LogFactory.getLog(StudentPermissionServiceImpl.class);
    @Resource
    private StudentPermissionMapper studentPermissionMapper;
    @Resource
    private StudentPermissionHisMapper studentPermissionHisMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map addPermission(StudentPermission stuPermission) {

        Map map = new HashMap<>();
        try {
            String permissionGuid = UUID.randomUUID().toString();
            stuPermission.setPermissionGuid(permissionGuid);
            if(stuPermission.getPermissionType() == null){
                map.put("code","400");
                map.put("msg","权限类型不能为空！");
                log.info(map);
                return map;
            }
            if(stuPermission.getToGroup() == null){
                map.put("code","400");
                map.put("msg","给到组不能为空！");
                log.info(map);
                return map;
            }
            if(stuPermission.getToPerson() == null){
                map.put("code","400");
                map.put("msg","给到人不能为空！");
                log.info(map);
                return map;
            }
            if(stuPermission.getEffectiveTime() == null){
                map.put("code","400");
                map.put("msg","生效时间不能为空！");
                log.info(map);
                return map;
            }
            if(stuPermission.getFailureTime() == null){
                map.put("code","400");
                map.put("msg","失效时间不能为空！");
                log.info(map);
                return map;
            }
            //调用权限配置mapper执行到库
            Integer permissionRow = studentPermissionMapper.addPermission(stuPermission);
            String permissionGuids = stuPermission.getPermissionGuid().toUpperCase();
            //调用权限配置历史记录mapper执行到库
            Integer permissionHisRow = studentPermissionHisMapper.addPermissionHis(permissionGuids);
            if(permissionRow > 0 && permissionHisRow > 0){
                map.put("code","200");
                map.put("msg","权限配置成功");
                log.info(map);
            }else{
                map.put("code","400");
                map.put("msg","权限配置失败");
                log.info(map);
            }
        } catch (Exception e) {
            map.put("code","500");
            map.put("msg","操作失败！");
            log.error("操作失败：" + e.getMessage());
            return map;
        }

        return map;
    }
}
