package cn.com.service.impl;

import cn.com.mapper.ClassMapper;
import cn.com.pojo.StudentClass;
import cn.com.service.StuClassService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StuClassServiceImpl implements StuClassService {
    private static final Log log= LogFactory.getLog(StuClassServiceImpl.class);
    @Autowired
    private ClassMapper mapper;
    @Override
    public Map getClassByGrade(String gradeId) {
        Map map = new HashMap();
        List<StudentClass> list =null;
        try {
            list = mapper.getClassByGrade(gradeId);
            if(list != null){
                map.put("code","200");
                map.put("msg","查询数据成功");
                map.put("data",list);
                log.info("班级信息：" + map);
            }
        } catch (Exception e) {
            map.put("code","500");
            map.put("msg","程序异常!" + e.getMessage());
            log.error("提示信息：" + map);
        }
        return map;
    }
}
