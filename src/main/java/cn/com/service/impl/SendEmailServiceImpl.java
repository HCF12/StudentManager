package cn.com.service.impl;

import cn.com.mapper.EmailGroupInfoMapper;
import cn.com.mapper.EmailGroupSendHisMapper;
import cn.com.mapper.EmailGroupSendTaskMapper;
import cn.com.mapper.StudentMapper;
import cn.com.pojo.EmailGroupSendTask;
import cn.com.pojo.EmailModel;
import cn.com.service.SendEmailService;
import cn.com.utils.EmailUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: cn.com.service.impl-SendEmailServiceImpl
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/9/26 16:35
 */
@Service
public class SendEmailServiceImpl implements SendEmailService {
    private static final Log log = LogFactory.getLog(SendEmailServiceImpl.class);
    @Autowired
    private EmailGroupSendTaskMapper mapper;

    @Autowired
    private EmailGroupInfoMapper emailMapper;

    @Autowired
    private EmailGroupSendHisMapper hisMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    EmailUtils emailUtils;
    @Override
    public void goSendEmail() {
            Map map = null;
            List<EmailGroupSendTask> list = mapper.getEmailGroupSendTask();
            for(EmailGroupSendTask eg : list){
                try {
                    EmailModel emailModel = new EmailModel();
                    //邮件正文
                    emailModel.setSendContent(eg.getContent());
                    //主题
                    emailModel.setSubject(eg.getSubject());
                    //判断接收人是否为空
                    if(eg.getEmail() != null && !eg.getEmail().isEmpty()){
                        emailModel.setRecipientMailbox(eg.getEmail().split(","));
                    }
                    //判断抄送是否为空
                    if(eg.getCarbon() != null && !eg.getCarbon().isEmpty()){
                        emailModel.setCcMailbox(eg.getCarbon().split(","));
                    }
					//判断附件是否为空
					if(eg.getAttachmentMap() != null){
						 map = eg.getAttachmentMap();
					}else{
						map = new HashMap<String,String>();
					}
                    //附件
                    emailModel.setEnclosures(map);
                    //执行发送操作
                    int row = emailUtils.sendSimpleMail(emailModel);
                    if(row == 1){
                        //更新邮件维护表状态
                        studentMapper.updateStatus(eg.getCustomerNo());
                        emailMapper.updateStatus(eg.getCustomerNo(),3);
                        //加入历史表
                        hisMapper.addEmailGroupHis(eg.getCustomerNo(),3);
                        //删除待发送任务
                        mapper.deleteSendTask(eg.getCustomerNo());
                        log.info("邮件发送成功！");
                    }else{
                        emailMapper.updateStatus(eg.getCustomerNo(),4);
                        studentMapper.updateStatus(eg.getCustomerNo());
                        //加入历史表
                        hisMapper.addEmailGroupHis(eg.getCustomerNo(),4);
                        //删除待发送任务
                        mapper.deleteSendTask(eg.getCustomerNo());
                    }
                } catch (Exception e) {
                    log.error("邮件发送失败:" + e.getMessage());
                }
            }
    }
}
