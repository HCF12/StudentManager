package cn.com.service.impl;
import cn.com.mapper.GradeMapper;
import cn.com.pojo.Grade;
import cn.com.service.GradeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GradeServiceImpl implements GradeService {
    private static final Log log= LogFactory.getLog(GradeServiceImpl.class);
    @Resource
    private GradeMapper mapper;
    @Override
    public Map getGrade() {
        Map map = new HashMap();
        List<Grade> gradeList = null;
        try {
            gradeList = mapper.getGrades();
            if(gradeList != null){
                map.put("code","200");
                map.put("msg","查询数据成功");
                map.put("data",gradeList);
                log.info("年级信息：" + map);
            }
        } catch (Exception e) {
            map.put("code","500");
            map.put("msg","程序异常!" + e.getMessage());
            log.error("提示信息：" + map);
        }
        return map;
    }
}
