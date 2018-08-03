package com.volfine.service;

import com.volfine.model.EmailAccount;
import com.volfine.model.Mail;
import org.apache.commons.io.FileUtils;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class EmailService {

    public static final EmailService me = new EmailService();

    private EmailAccount dao = new EmailAccount();

    public List<EmailAccount> findAll() {
        return dao.find("select * from v_email_account order by id desc");
    }

    public void sendMail(Integer emailAccountId, String recipients, String content) throws UnsupportedEncodingException, MessagingException, InterruptedException {
        EmailAccount account = new EmailAccount().findById(emailAccountId);

        String[] nameAndEmail_arr = recipients.split("\r\n");
        for (String s : nameAndEmail_arr) {
            String[] s_arr = s.split("#");
            String name = s_arr[0].trim();
            String to = s_arr[1].trim();
            String subject = s_arr[2].trim();
            sendMail(account.getAccount(), account.getAccount(), account.getPassword(), account.getNickName(), to, subject, content.replace("{customerName}", name));
            System.out.println(to + "发送成功");
            Thread.sleep(12000L);
        }
    }

    public static boolean sendMail(String from, String username, String password, String nick, String to, String subject, String body) throws MessagingException,
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
        props.put("mail.smtp.host", "smtp.exmail.qq.com");
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
}
