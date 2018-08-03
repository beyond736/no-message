package com.volfine.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.volfine.common.Constants;
import com.volfine.interceptor.LoginInterceptor;
import com.volfine.model.Admin;
import com.volfine.service.AdminService;

public class AdminController extends Controller {

    private AdminService adminService = new AdminService();

    @ActionKey("/")
    public void login() {
        render("login.html");
    }

    /**
     * 登录验证
     */
    public void checkLogin() {
        String username = getPara("username");
        String password = getPara("password");
        if (StrKit.isBlank(username) || StrKit.isBlank(password)) {
            setAttr("error", "用户名密码不能为空！");
            render("login.html");
        } else {
            Admin admin = adminService.checkLogin(username, password);
            if (admin != null) {
                this.setSessionAttr(Constants.SESSION_KEY, admin.getId());
                this.setSessionAttr(Constants.SESSION_TRUENAME, admin.getTrueName());
                if (getSessionAttr("returnUrl") != null) {
                    redirect(String.valueOf(getSessionAttr("returnUrl")));
                } else {
                    redirect("/main");
                }
            } else {
                setAttr("error", "用户名或密码错误！");
                render("login.html");
            }
        }
    }

    @Before(LoginInterceptor.class)
    public void main() {
        render("main.html");
    }

    public void loginOut() {
        this.removeSessionAttr(Constants.SESSION_KEY);
        this.removeSessionAttr(Constants.SESSION_USERNAME);
        this.redirect("/admin");
    }


}
