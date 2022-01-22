package com.yph.util.mail;



import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class Mail {


    public static Properties getProperties() {
        Properties props = new Properties();
//        props.setProperty("proxySet", "true");
//        props.setProperty("mail.smtp.socks.host","74.120.172.109");
//        props.setProperty("mail.smtp.socks.port","45236");

        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.host", "smtp.163.com");
//        MailSSLSocketFactory sf = null;
//        try {
//            sf = new MailSSLSocketFactory();
//        } catch (GeneralSecurityException e) {
//            e.printStackTrace();
//        }
//        sf.setTrustAllHosts(true);
//        props.put("mail.smtp.ssl.enable", "true");
//        props.put("mail.smtp.ssl.socketFactory", sf);
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
//        props.put("mail.smtp.starttls.enable",true);
        props.put("mail.smtp.port", "465");

        return props;
    }

    public  static  void senMessage(String title,String msg,String... to ) throws Exception{
        for (String s : to) {
            sendMessage(msg,title,"513667225@qq.com","tkijzjczalbrcajg",s);
        }
    }

    public static void main(String[] args) throws Exception {
        senMessage("2631605104@qq.com","系统警报","测试信息");
    }

    public static void sendMessage(String msg,String title,String from,String password,String to) throws Exception {
        Properties props = getProperties();
        // 获得邮件会话对象
                Session session = Session.getInstance(props, new SmtpAuthenticator(
                from, password));
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(from));// 发件人
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));// 收件人
            mimeMessage.setSubject(title);
            mimeMessage.setSentDate(new Date());// 发送日期
            Multipart mp = new MimeMultipart("related");// 发送HTML格式的邮箱
            BodyPart bodyPart = new MimeBodyPart();// 正文
            bodyPart.setDataHandler(new DataHandler(msg, "text/html;charset=GBK"));// 网页格式
            mp.addBodyPart(bodyPart);
            mimeMessage.setContent(mp);// 设置邮件内容对象
            Transport.send(mimeMessage);// 发送邮件
        } catch (MessagingException e) {
           throw new Exception("发送账号:"+from+"\n错误信息:"+e.getMessage());
        }
    }
}
