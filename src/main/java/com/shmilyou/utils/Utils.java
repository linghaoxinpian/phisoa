package com.shmilyou.utils;

import com.alibaba.fastjson.JSON;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/16
 */
public class Utils {

    private static Logger logger = LoggerFactory.getLogger(Utils.class);

    /**
     * 驼峰法转下划线
     *
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camel2Underline(String line) {
        if (line == null || "".equals(line)) {
            return "";
        }
        line = String.valueOf(line.charAt(0)).toUpperCase()
                .concat(line.substring(1));
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(word.toLowerCase());
            sb.append(matcher.end() == line.length() ? "" : "_");
        }
        return sb.toString();
    }

    public static boolean isEmail(String emailStr) {
        if (StringUtils.isEmpty(emailStr)) return false;

        String regEx1 = "^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(emailStr);
        return m.matches();

    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 生成订单号
     */
    public static String generateOrderNum() {
        //时间
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String d = format.format(date);
        //随机数3位
        int random = (int) (Math.random() * 1000);
        return d + random;
    }

    /**
     * 利用Jackson解析json字符型数组
     *
     * @param json json数组, <b>NOTE:</b> ["1","2","3"]
     */
    public static List<String> parseJsonArr(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, List.class);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage(), e);
        }
        return Collections.emptyList();
    }

    /**
     * 利用fastJson生成json
     */
    public static String generateJson(Object obj) {
        if (obj == null) {
            return "";
        }
        return JSON.toJSONString(obj);
    }

    /**
     * 判断目录是否存在，不存在则创建目录
     */
    public static void isExistDirectory(String path) {
        new File(path).mkdirs();
    }

    /**
     * 生成时间字符串，如 20181026100630
     *
     * @return [yyyyMMddHHmmss]
     */
    public static String generateDateNum() {
        //时间
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }

    /** 利用QQ邮箱发送邮件 */
    public static boolean sendQQEmail(String myEmailAccount, String myEmailPassword, String receiveMailAccount, String content) {
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
            MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount, content);
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
            return false;
        }
        return true;
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
