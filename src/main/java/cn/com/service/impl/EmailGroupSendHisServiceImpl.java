package cn.com.service.impl;

import cn.com.mapper.EmailGroupSendHisMapper;
import cn.com.pojo.Student;
import cn.com.service.EmailGroupSendHisService;
import cn.com.vo.EmailGroupHisVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: cn.com.service.impl-EmailGroupSendHisServiceImpl
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/12/14 16:14
 */
@Service
public class EmailGroupSendHisServiceImpl implements EmailGroupSendHisService {
    private static final Log log= LogFactory.getLog(EmailGroupSendHisServiceImpl.class);
    @Autowired
    private EmailGroupSendHisMapper mapper;
    @Override
    public Map getEmailGroupSendHisInfo(Map map) {
        Map mp = new HashMap();
        PageInfo<EmailGroupHisVo> page = null;
        List<EmailGroupHisVo> list = null;
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
            list = mapper.getEmailGroupSendHisInfo(map);
            page = new PageInfo<EmailGroupHisVo>(list);
            if(page != null){
                JSONObject jsonObject =JSONObject.fromObject(page);
                log.info("邮件历史集：" + jsonObject.toString());
                mp.put("code","200");
                mp.put("data",page);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mp;
    }
}
