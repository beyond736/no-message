package com.volfine.service;

import com.volfine.common.Constants;
import com.volfine.model.Admin;
import com.volfine.util.MD5Utils;

public class AdminService {

    public static final AdminService me = new AdminService();

    private Admin dao = new Admin();

    public Admin checkLogin(String username, String password) {
        try {
            Admin admin = dao.findFirst("select * from v_admin where username = ? and password = ? and status = ?", username, MD5Utils.getMD5ofStr(password),
                    Constants.STATUS_VALID);
            return admin;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
