package com.volfine.interceptor;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class UrlInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        inv.invoke();
        Controller c = inv.getController();
        c.setAttr("controllerKey", inv.getControllerKey());
        c.setAttr("actionKey", inv.getActionKey());
    }
}
