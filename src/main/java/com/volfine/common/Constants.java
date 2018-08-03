package com.volfine.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Constants implements Serializable {

	private static final long serialVersionUID = -2475276886020769297L;

	// 用户session key
	public static final String SESSION_KEY = "user_session";

	// 用户名session key
	public static final String SESSION_USERNAME = "user_name";

	// 用户名session key
	public static final String SESSION_TRUENAME = "true_name";

	// 用户session key
	public static final String SHOP_SESSION_KEY = "member_session";

	// 前台用户名session key
	public static final String SHOP_SESSION_USERNAME = "username";

	// 手机用户session key
	public static final String MOBILE_SESSION_KEY = "mobile_session";
	// 前台手机版用户名session key
	public static final String MOBILE_SESSION_USERNAME = "mobile_username";

	// 用户的权限key
	public static final String AUTHORITY_KEY = "authority";
	// 管理员类型用户
	public static final String AUTHORITY_ADMIN = "admin";
	// 业务员类型用户
	public static final String AUTHORITY_SALES = "sales";

	// 有效状态
	public static final Integer STATUS_VALID = 1;
	// 无效状态
	public static final Integer STATUS_INVALID = 2;
	// 删除状态，数据库不做物理删除
	public static final Integer STATUS_DELETE = 0;

	// Cookie路径
	public static final String COOKIE_PATH = "/";

	// Cookie作用域
	public static final String COOKIE_DOMAIN = "";

	public static final Integer ERP_ORDER_STATUS_CANCEL = 8;

	public static final Integer H5_USER_SALESID = 179;

	/**
	 * 缓存key
	 */
	public static final String ERP_CACHE_KEY = "erpCache";
	
	
	public static final Map<String, Object> destinationMap = new HashMap<String, Object>();

	static {
		destinationMap.put("香港WIFI", 1);
		destinationMap.put("台湾WIFI", 2);
		destinationMap.put("泰国WIFI", 3);
		destinationMap.put("日本WIFI", 4);
		destinationMap.put("韩国WIFI", 5);
		destinationMap.put("澳门WIFI", 6);
		destinationMap.put("法国WIFI", 11);
		destinationMap.put("英国WIFI", 12);
	}
	
	public static final Map<String, Object> priceMap = new HashMap<String, Object>();

	static {
		priceMap.put("香港WIFI", 1);
		priceMap.put("台湾WIFI", 2);
		priceMap.put("泰国WIFI", 3);
		priceMap.put("日本WIFI", 5);
		priceMap.put("韩国WIFI", 6);
		priceMap.put("英国WIFI", 10);
	}
	

}
