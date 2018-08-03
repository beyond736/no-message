package com.volfine.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.volfine.interceptor.LoginInterceptor;
import com.volfine.model.EmailAccount;
import com.volfine.service.EmailService;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Before(LoginInterceptor.class)
public class EmailController extends Controller {

    private EmailService emailService = EmailService.me;

    public void send() {
        setAttr("list", emailService.findAll());
        render("send.html");
    }

    public void doSend() {
        final Integer emailAccountId = getParaToInt("emailAccountId");
        final String recipients = getPara("recipients");
        final String content = getPara("content");
        // 异步发送邮件
        new Thread() {
            public void run() {
                try {
                    emailService.sendMail(emailAccountId, recipients, content);
                } catch (Exception e1) {
                    // 不做处理.
                    e1.printStackTrace();
                }
                System.out.println("date = " + new Date());
            }
        }.start();
        redirect("send");
    }

    public void list() {
        setAttr("list", emailService.findAll());
        render("list.html");
    }

    public void add() {
        render("add.html");
    }

    public void save() {
        EmailAccount emailAccount = getBean(EmailAccount.class);
        emailAccount.save();
        redirect("list");
    }
}
