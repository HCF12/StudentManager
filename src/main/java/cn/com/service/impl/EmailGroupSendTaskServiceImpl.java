package cn.com.service.impl;

import cn.com.mapper.EmailGroupSendTaskMapper;
import cn.com.mapper.EmailTemplateMapper;
import cn.com.mapper.StudentMapper;
import cn.com.pojo.EmailGroupSendTask;
import cn.com.pojo.EmailTemplate;
import cn.com.pojo.Student;
import cn.com.service.EmailGroupSendTaskService;
import cn.com.utils.TraditionalChineseCalenderUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: cn.com.service.impl-EmailGroupSendTaskServiceImpl
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/9/25 18:29
 */
@Service
public class EmailGroupSendTaskServiceImpl implements EmailGroupSendTaskService {
    private static final Log log = LogFactory.getLog(EmailGroupSendTaskServiceImpl.class);
    @Autowired
    private EmailGroupSendTaskMapper emailGroupSendTaskMapper;
    @Autowired
    private EmailTemplateMapper emailTemplateMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public void addEmailGroupSendTask() {
        EmailGroupSendTask emailGroupSendTask = new EmailGroupSendTask();
        emailGroupSendTaskMapper.addEmailGroupSendTask(emailGroupSendTask);
    }

    @Override
    public void addEmailGroupSendTaskByTemplate() {
        //获取模板数据
        List<EmailTemplate> emailTemplateList = emailTemplateMapper.getEmailTemplate();
        Map map = new HashMap();
        //获取用户生日信息
        List<Student> studentList = studentMapper.getStudent(map);
        for(Student stu : studentList){
            String str[] = stu.getBrithDate().split("-");
            Calendar cal = Calendar.getInstance();
            Integer year = cal.get(Calendar.YEAR);
            Integer month = Integer.parseInt(str[1]);
            Integer day = Integer.parseInt(str[2]);
            int [] birthDate = TraditionalChineseCalenderUtils.lunarToSolar(year,month,day,false);
            String traditionalYear = String.valueOf(birthDate[0]);
            String traditionalMonth = String.valueOf(birthDate[1]);
            String traditionalDay = String.valueOf(birthDate[2]);
            String expectedStartDate = traditionalYear + "-" + traditionalMonth + "-" + traditionalDay;
            //遍历模板集合取值
            for(EmailTemplate et : emailTemplateList){
                EmailGroupSendTask emailGroupSendTask = new EmailGroupSendTask();
                emailGroupSendTask.setTemplateId(et.getDocTemplateId());
                emailGroupSendTask.setContent(getContent(et.getDocumentPath()));
                emailGroupSendTask.setCarbon(et.getCarbon());
                emailGroupSendTask.setCustomerNo(Integer.parseInt(stu.getStuNo()));
                emailGroupSendTask.setSubject("生日快乐");
                emailGroupSendTask.setExpectedStartDate(expectedStartDate);
                emailGroupSendTaskMapper.addSingleEmailGroupSendTask(emailGroupSendTask);
            }
        }
    }

    /**
     * 获取所有邮件待发送任务
     * @return
     */
    @Override
    public Map getAllEmailGroupSendTask(Map map) {
        Map mp = new HashMap();
        PageInfo<EmailGroupSendTask> page = null;
        List<EmailGroupSendTask> list = null;
        try {
            Integer pageNum = Integer.parseInt(map.get("pageNum").toString());
            if(pageNum == null){
                pageNum = 1;
            }
            Integer pageSize = Integer.parseInt(map.get("pageSize").toString());
            if(pageSize == null){
                pageSize = 20;
            }
            PageHelper.startPage( pageNum,pageSize);
            list = emailGroupSendTaskMapper.getAllEmailGroupSendTask(map);
            page = new PageInfo<EmailGroupSendTask>(list);
            if(page != null){
                JSONObject jsonObject =JSONObject.fromObject(page);
                log.info("邮件待发送集：" + jsonObject.toString());
                mp.put("code","200");
                mp.put("data",page);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mp;
    }

    /**
     * 读取模板内容
     * @param filePath
     * @return
     */
    public  String getContent(String filePath){
        String content = "";
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            content = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
