package com.volfine.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.volfine.common.Constants;

public class LoginInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		Object user = inv.getController().getSessionAttr(Constants.SESSION_KEY);
		if (user == null) {
			inv.getController().setSessionAttr("returnUrl", inv.getActionKey());
			inv.getController().redirect("/");
		} else {
			inv.invoke();
		}
	}
}
