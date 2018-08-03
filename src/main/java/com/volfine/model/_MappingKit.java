package com.volfine.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("v_admin", "id", Admin.class);
		arp.addMapping("v_category", "id", Category.class);
		arp.addMapping("v_country", "id", Country.class);
		arp.addMapping("v_customer", "id", Customer.class);
		arp.addMapping("v_email_account", "id", EmailAccount.class);
		arp.addMapping("v_mail", "id", Mail.class);
		arp.addMapping("v_order", "id", Order.class);
		arp.addMapping("v_order_detail", "id", OrderDetail.class);
		arp.addMapping("v_product", "id", Product.class);
		arp.addMapping("v_purchase", "id", Purchase.class);
		arp.addMapping("v_quoted_price", "id", QuotedPrice.class);
		arp.addMapping("v_supplier", "id", Supplier.class);
	}
}

