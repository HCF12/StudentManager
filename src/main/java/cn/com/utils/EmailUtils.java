package cn.com.utils;

import cn.com.pojo.EmailModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Component
public class EmailUtils {
    private static final Log log = LogFactory.getLog(EmailUtils.class);
    @Autowired(required = false)
    private JavaMailSender javaMailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.javaMailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String from;

    public void sendEmail(String to, String subject, String context, String picPath) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(context, true);
            mimeMessageHelper.addInline("pic", new FileSystemResource(new File(picPath)));
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 邮件发送
     * @param model
     */
    public int sendSimpleMail(EmailModel model) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject(model.getSubject()); // 邮件标题
            helper.setFrom(from); // 发送者邮箱
            helper.setTo(model.getRecipientMailbox()); // 收件人邮箱
            if (model.getCcMailbox() != null && model.getCcMailbox().length != 0) {
                helper.setCc(model.getCcMailbox()); // 抄送人
            }
            if (model.getBccMailbox() != null && model.getBccMailbox().length != 0) {
                helper.setBcc(model.getBccMailbox()); // 加密抄送
            }
            helper.setSentDate(new Date()); // 发送日期
            helper.setText(model.getSendContent(),true); // 发送内容
            if (!model.getEnclosures().isEmpty()) {
                log.info("-------[Iterator循环遍历]通过keySet取出map数据---------");
                for (String key : model.getEnclosures().keySet()) {
                    helper.addAttachment(key, new File(model.getEnclosures().get(key)));// 预览文件
                    // System.out.println("key值："+key+" value值："+model.getEnclosures().get(key));
                }
            }
            javaMailSender.send(mimeMessage);
            return 1;
        } catch (Exception e) {
            log.error("邮件发送异常：" + e.getMessage());
            return -1;
        }
    }
}
