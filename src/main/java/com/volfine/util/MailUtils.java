package com.volfine.util;

import org.apache.commons.io.FileUtils;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * java mail 工具类 发送开发信
 */
public class MailUtils {
    private static String host;
    private static String username;
    private static String password;
    private static String from;
    private static String nick;

    static {
        try {
            host = "smtp.exmail.qq.com";
            username = "jason@volfine.com";
            password = "Ysjwgi611!!";
            from = "jason@volfine.com";
            nick = "daffodil";
            // nick + from 组成邮箱的发件人信息
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送邮件
     *
     * @param to       收件人列表，以","分割
     * @param subject  标题
     * @param body     内容
     * @return
     * @throws MessagingException
     * @throws AddressException
     * @throws UnsupportedEncodingException
     */
    public static boolean sendMail(String to, String subject, String body) throws AddressException, MessagingException,
            UnsupportedEncodingException {
        // 参数修饰
        if (body == null) {
            body = "";
        }
        if (subject == null) {
            subject = "无主题";
        }
        // 创建Properties对象
        Properties props = System.getProperties();
        // 创建信件服务器
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true"); // 通过验证
        props.put("mail.smtp.port", "465");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.ssl.enable", "true");
        //props.put("mail.debug", "true");
        // 得到默认的对话对象
        Session session = Session.getDefaultInstance(props, null);
        // 创建一个消息，并初始化该消息的各项元素
        MimeMessage message = new MimeMessage(session);
        nick = MimeUtility.encodeText(nick);
        message.setFrom(new InternetAddress(nick + "<" + from + ">"));
        // 创建收件人列表
        if (to != null && to.trim().length() > 0) {
            String[] arr = to.split(",");
            int receiverCount = arr.length;
            if (receiverCount > 0) {
                InternetAddress[] address = new InternetAddress[receiverCount];
                for (int i = 0; i < receiverCount; i++) {
                    address[i] = new InternetAddress(arr[i]);
                }
                message.addRecipients(Message.RecipientType.TO, address);
                message.setSubject(subject);
                // 后面的BodyPart将加入到此处创建的Multipart中
                Multipart mp = new MimeMultipart("related");
                BodyPart bodyPart = new MimeBodyPart();
                bodyPart.setDataHandler(new DataHandler(body, "text/html;charset=UTF-8"));

                mp.addBodyPart(bodyPart);
                message.setContent(mp);// 设置邮件内容对象
                // 设置信件头的发送日期
                message.setSentDate(new Date());
                message.saveChanges();
                // 发送信件
                Transport transport = session.getTransport("smtp");
                transport.connect(username, password);
                transport.sendMessage(message,
                        message.getRecipients(Message.RecipientType.TO));
                transport.close();
                return true;
            } else {
                System.out.println("None receiver!");
                return false;
            }
        } else {
            System.out.println("None receiver!");
            return false;
        }
    }

    public static void main(String[] args) throws IOException, MessagingException {
        //sendMail("beyond736@qq.com", "注册信息邮件", "<h2>注册邮件，有附件</h2>", null);
        System.out.println("sendMail success!");

        String content = FileUtils.readFileToString(new File("F:\\volfine\\开发信\\mail.txt"), "UTF-8");
        String nameAndEmail = FileUtils.readFileToString(new File("F:\\volfine\\开发信\\name.txt"), "UTF-8");

        sendMail("sales@volfine.com", "注册信息邮件", content.replace("%name%","Tan Jian"));

        String[] nameAndEmail_arr = nameAndEmail.split("\r\n");
        for (String s : nameAndEmail_arr) {
            String[] s_arr = s.split(",");
            String name = s_arr[0].trim();
            String email = s_arr[1].trim();

            System.out.println(name);
        }

    }
}