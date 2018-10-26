package com.shmilyou.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/25
 */
public class WebUtils {

    private static Logger logger = LoggerFactory.getLogger(WebUtils.class);

    /**
     * 保存上传的图片
     *
     * @param path     目录
     * @param fileName 保存时的文件名,不带后缀
     * @return 带后缀的文件名
     */
    public static String uploadPicture(MultipartFile pic, String path, String fileName) {
        if (pic != null) {
            String picName = pic.getOriginalFilename();
            String picType = picName.substring(picName.lastIndexOf("."));
            if (".png".equals(picType) || ".jpg".equals(picType) || ".jpeg".equals(picType)) {
                //检查目录是否存在
                Utils.isExistDirectory(path);
                try {
                    //存储图片
                    pic.transferTo(new File(path + fileName + picType));
                    return fileName + picType;
                } catch (IOException e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
            }
        }
        return "default.jpg";
    }

    /** 返回状态码200，以及json数据 */
    public static ResponseEntity<Map<String, Object>> ok(Object msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("msg", msg);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /** 返回状态码200 */
    public static ResponseEntity<Map<String, Object>> ok() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("msg", "ok");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /** 返回状态码400，以及json数据 */
    public static ResponseEntity<Map<String, Object>> error(Object msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 400);
        map.put("msg", msg);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /** 利用QQ邮箱发送邮件 */
    public static void sendQQEmail(String myEmailAccount, String myEmailPassword, String receiveMailAccount,
                                   String code) {
        String myEmailSMTPHost = "smtp.qq.com"; //qq邮箱的 SMTP 服务器地址为: smtp.qq.com
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        // session.setDebug(true);          // 设置为debug模式, 默认为false，控制台打印信息
        try {
            // 3. 创建一封邮件
            MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount, code);
            // 3.1. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();
            // 3.2. 使用 邮箱账号 和 密码 连接邮件服务器，这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
            transport.connect(myEmailAccount, myEmailPassword);
            transport.sendMessage(message, message.getAllRecipients());

            // 7. 关闭连接
            transport.close();
        } catch (Exception e) {
            logger.error("发送邮件失败，收件人:" + receiveMailAccount);
            logger.error(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 创建一封邮件（验证码）
     *
     * @param session     会话对象
     * @param sendMail    发件人邮箱
     * @param receiveMail 收件人邮箱
     * @param code        验证码
     *                    <p>
     *                    // 1. 创建参数配置, 用于连接邮件服务器的参数配置
     *                    Properties props = new Properties();                    // 参数配置
     *                    props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
     *                    props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
     *                    props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
     *                    final String smtpPort = "465";
     *                    props.setProperty("mail.smtp.port", smtpPort);
     *                    props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
     *                    props.setProperty("mail.smtp.socketFactory.fallback", "false");
     *                    props.setProperty("mail.smtp.socketFactory.port", smtpPort);
     *                    // 2. 根据配置创建会话对象, 用于和邮件服务器交互
     *                    Session session = Session.getInstance(props);
     *                    </p>
     */
    private static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String code) throws Exception {
        // 1. 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "", "UTF-8"));
        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail, "", "UTF-8"));
        // 4. Subject: 邮件主题
        message.setSubject("【文耀科技】", "UTF-8");
        // 6. 创建文本
        MimeBodyPart text = new MimeBodyPart();
        //7. 邮件主题内容，可以以 http 链接的形式添加网络图片
        text.setContent("您好！感谢您使用本次服务，本次请求的验证码为：<strong>" + code + "</strong><br/><img src='http://baidu.com'/>", "text/html;charset=UTF-8");
        MimeMultipart mimeMultipart = new MimeMultipart();
        mimeMultipart.addBodyPart(text);
        // 添加到邮件的 Content 是由多个 BodyPart 组成的 Multipart, 所以我们需要的是 BodyPart,
        // 上面的 mimeMultipart 并非 BodyPart, 所有要将其封装成一个 BodyPart
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(mimeMultipart);
        // 10. 设置文本和附件(如果有)的关系
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(bodyPart);
        mm.setSubType("mixed");         // 混合关系
        // 11. 设置整个邮件的关系（将最终的混合结果作为邮件的内容添加到邮件对象）
        message.setContent(mm);
        // 12. 设置发件时间
        message.setSentDate(new Date());
        // 13. 保存上面的所有设置
        message.saveChanges();
        return message;
    }

}
