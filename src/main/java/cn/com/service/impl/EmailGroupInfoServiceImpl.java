package cn.com.service.impl;

import cn.com.mapper.EmailGroupInfoMapper;
import cn.com.mapper.EmailGroupSendTaskMapper;
import cn.com.pojo.EmailGroupInfo;
import cn.com.pojo.EmailGroupSendTask;
import cn.com.pojo.Student;
import cn.com.service.EmailGroupInfoService;
import cn.com.utils.EmailUtils;
import cn.com.vo.EmailGroupInfoVo;
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
 * @Title: cn.com.service.impl-EmailGroupInfoServiceImpl
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/9/25 16:45
 */
@Service
public class EmailGroupInfoServiceImpl implements EmailGroupInfoService {
    private static final Log log = LogFactory.getLog(EmailGroupInfoServiceImpl.class);
    @Autowired
    private EmailGroupInfoMapper emailGroupInfoMapper;

    @Autowired
    private EmailGroupSendTaskMapper emailGroupSendTaskMapper;

    @Override
    public Map addEmailGroup(EmailGroupInfo emailGroupInfo) {
        Map map = new HashMap();
        try {
            if (emailGroupInfo.getCustomerNo() == null) {
                map.put("code", "400");
                map.put("msg", "客户编号必填！");
                return map;
            } else {
                Integer count = emailGroupInfoMapper.getCustomerCount(emailGroupInfo.getCustomerNo());
                if (count > 0) {
                    map.put("code", "400");
                    map.put("msg", emailGroupInfo.getCustomerNo() + "客户编号已存在！");
                    return map;
                }
            }
            if (emailGroupInfo.getCustomerName() == null) {
                map.put("code", "400");
                map.put("msg", "客户姓名必填！");
                return map;
            }
            if (emailGroupInfo.getSubject() == null) {
                map.put("code", "400");
                map.put("msg", "邮件主题必填！");
                return map;
            }
            if (emailGroupInfo.getReceiveEmail() == null) {
                map.put("code", "400");
                map.put("msg", "收件人邮箱必填！");
                return map;
            }
            if (emailGroupInfo.getEmailModel() == null) {
                map.put("code", "400");
                map.put("msg", "邮件模板必填！");
                return map;
            }
            if (emailGroupInfo.getContent() == null) {
                map.put("code", "400");
                map.put("msg", "邮件正文必填！");
                return map;
            }
            if (emailGroupInfo.getExpectTime() == null) {
                map.put("code", "400");
                map.put("msg", "期望发送时间必填！");
                return map;
            }
            try {
                Integer addEmailGroupCount = emailGroupInfoMapper.addEmailGroup(emailGroupInfo);
                if (addEmailGroupCount > 0) {
                    map.put("code", "200");
                    map.put("msg", "保存成功！");
                }
            } catch (Exception e) {
                log.error("数据存储异常：" + e.getMessage());
                map.put("code", "500");
                map.put("msg", "网络错误，存储数据异常：" + e.getMessage());
            }
        } catch (Exception e) {
            log.error("数据校验异常：" + e.getMessage());
            map.put("code", "500");
            map.put("msg", "网络错误，校验数据异常：" + e.getMessage());
        }
        return map;
    }

    @Override
    public Map getEmailGroupInfoById(Integer id) {
        Map map = new HashMap();
        try {
            EmailGroupInfo emailGroupInfo = emailGroupInfoMapper.getEmailGroupInfoById(id);
            if (emailGroupInfo != null) {
                map.put("code", "200");
                map.put("msg", "邮件数据获取成功！");
                map.put("data",emailGroupInfo);
            }else {
                map.put("code", "200");
                map.put("msg", "邮件数据为空！");
            }
        } catch (Exception e) {
            log.error("数据校验异常：" + e.getMessage());
            map.put("code", "500");
            map.put("msg", "网络错误，校验数据异常：" + e.getMessage());
        }
        return map;
    }

    @Override
    public Map updEmailGroup(EmailGroupInfo emailGroupInfo) {
        Map map = new HashMap();
        try {
            if (emailGroupInfo.getCustomerNo() == null) {
                map.put("code", "400");
                map.put("msg", "客户编号必填！");
                return map;
            }
            if (emailGroupInfo.getCustomerName() == null) {
                map.put("code", "400");
                map.put("msg", "客户姓名必填！");
                return map;
            }
            if (emailGroupInfo.getSubject() == null) {
                map.put("code", "400");
                map.put("msg", "邮件主题必填！");
                return map;
            }
            if (emailGroupInfo.getReceiveEmail() == null) {
                map.put("code", "400");
                map.put("msg", "收件人邮箱必填！");
                return map;
            }
            if (emailGroupInfo.getEmailModel() == null) {
                map.put("code", "400");
                map.put("msg", "邮件模板必填！");
                return map;
            }
            if (emailGroupInfo.getContent() == null) {
                map.put("code", "400");
                map.put("msg", "邮件正文必填！");
                return map;
            }
            if (emailGroupInfo.getExpectTime() == null) {
                map.put("code", "400");
                map.put("msg", "期望发送时间必填！");
                return map;
            }
            try {
                Integer addEmailGroupCount = emailGroupInfoMapper.updEmailGroup(emailGroupInfo);
                if (addEmailGroupCount > 0) {
                    map.put("code", "200");
                    map.put("msg", "保存成功！");
                }
            } catch (Exception e) {
                log.error("数据存储异常：" + e.getMessage());
                map.put("code", "500");
                map.put("msg", "网络错误，存储数据异常：" + e.getMessage());
            }
        } catch (Exception e) {
            log.error("数据校验异常：" + e.getMessage());
            map.put("code", "500");
            map.put("msg", "网络错误，校验数据异常：" + e.getMessage());
        }
        return map;
    }

    @Override
    public Map getEmailGroupInfoList(Map map) {
        Map mp = new HashMap();
        PageInfo<EmailGroupInfoVo> page = null;
        List<EmailGroupInfoVo> list = null;
        try {
            Integer pageNum = Integer.parseInt(map.get("pageNum").toString());
            if (pageNum == null) {
                pageNum = 1;
            }
            Integer pageSize = Integer.parseInt(map.get("pageSize").toString());
            if (pageSize == null) {
                pageSize = 20;
            }
            PageHelper.startPage(pageNum, pageSize);
            list = emailGroupInfoMapper.getEmailGroupInfoList(map);
            page = new PageInfo<EmailGroupInfoVo>(list);
            if (page != null) {
                JSONObject jsonObject = JSONObject.fromObject(page);
                mp.put("code", "200");
                mp.put("data", page);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mp;
    }

    @Override
    public Map startEmailConfiguration(List<Long> customerNos) {
        Map mp = new HashMap();
        int row = 0;
        try {
            row = emailGroupInfoMapper.startEmailConfiguration(customerNos);
            if (row > 0) {
                mp.put("code", "200");
                mp.put("msg", "启用成功！");
                mp.put("data",1);
            } else {
                mp.put("code", "200");
                mp.put("msg", "启用失败！");
                mp.put("data",0);
            }
        } catch (Exception e) {
			mp.put("code", "500");
			mp.put("msg", "邮件启用异常：" + e.getMessage());
            mp.put("data",-1);
            log.error("邮件启用异常：" + e.getMessage());
        }
        return mp;
    }

    @Override
    public Map checkEmailConfiguration(List<Long> customerNos) {
        Map mp = new HashMap();
        int row = 0;
        try {
            row = emailGroupInfoMapper.checkEmailConfiguration(customerNos);
            mp.put("code", "200");
            if (row > 0) {
                mp.put("msg", "审核成功！");
                mp.put("data",1);
            } else {
                mp.put("msg", "审核失败！");
                mp.put("data",0);
            }
        } catch (Exception e) {
			mp.put("code", "500");
            mp.put("data",-1);
			mp.put("msg", "邮件审核异常：" + e.getMessage());
            log.error("邮件审核异常：" + e.getMessage());
        }
        return mp;
    }

    @Override
    public Map runEmailConfiguration(List<Long> customerNos) {
        Map mp = new HashMap();
        int row = 0;
        int taskRow = 0;
        try {
            row = emailGroupInfoMapper.runEmailConfiguration(customerNos);
            EmailGroupSendTask emailGroupSendTask = new EmailGroupSendTask();
            emailGroupSendTask.setCustomerNos(customerNos);
            taskRow = emailGroupSendTaskMapper.addEmailGroupSendTask(emailGroupSendTask);
            mp.put("code", "200");
            if (row > 0 && taskRow > 0) {
                mp.put("msg", "执行成功！");
                mp.put("data",1);
            } else {
                mp.put("msg", "执行失败！");
                mp.put("data",0);
            }
        } catch (Exception e) {
			mp.put("code", "500");
			mp.put("msg", "邮件执行异常：" + e.getMessage());
            mp.put("data",-1);
            log.error("邮件执行异常：" + e.getMessage());
        }
        return mp;
    }

    @Override
    public Map batchDelEmailConfiguration(List<Long> customerNos) {
        Map mp = new HashMap();
        int row = 0;
        try {
            row = emailGroupInfoMapper.batchDelEmailConfiguration(customerNos);
            mp.put("code", "200");
            if (row > 0) {
                mp.put("msg", "删除成功！");
                mp.put("data",1);
            } else {
                mp.put("msg", "删除失败！");
                mp.put("data",0);
            }
        } catch (Exception e) {
            mp.put("code", "500");
            mp.put("msg", "邮件删除异常：" + e.getMessage());
            mp.put("data",-1);
            log.error("邮件删除异常：" + e.getMessage());
        }
        return mp;
    }
}
