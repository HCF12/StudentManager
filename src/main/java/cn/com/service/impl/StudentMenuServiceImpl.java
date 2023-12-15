package cn.com.service.impl;

import cn.com.mapper.StudentMenuMapper;
import cn.com.pojo.StudentMenu;
import cn.com.service.StudentMenuService;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentMenuServiceImpl implements StudentMenuService {
    private static final Log log= LogFactory.getLog(StudentMenuServiceImpl.class);
    @Resource
    private StudentMenuMapper studentMenuMapper;
    @Override
    public Map getStudentMenu() {
        Map map = new HashMap();
        List<StudentMenu> list = null;
        try {
            list = studentMenuMapper.getStudentMenu();
            if(list != null){
                map.put("code","200");
                map.put("msg","查询数据成功");
                map.put("data",list);
                JSONObject object = JSONObject.fromObject(map);
                log.info("菜单信息集：" + object);
            }
        } catch (Exception e) {
            map.put("code","500");
            map.put("msg","程序异常!" + e.getMessage());
            log.error("程序异常：" + e.getMessage());
        }
        return map;
    }
}
