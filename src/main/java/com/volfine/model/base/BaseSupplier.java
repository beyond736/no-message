package com.volfine.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSupplier<M extends BaseSupplier<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Long id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}

	public M setCompany(java.lang.String company) {
		set("company", company);
		return (M)this;
	}
	
	public java.lang.String getCompany() {
		return getStr("company");
	}

	public M setPhone(java.lang.String phone) {
		set("phone", phone);
		return (M)this;
	}
	
	public java.lang.String getPhone() {
		return getStr("phone");
	}

	public M setAddress(java.lang.String address) {
		set("address", address);
		return (M)this;
	}
	
	public java.lang.String getAddress() {
		return getStr("address");
	}

	public M setContacts(java.lang.String contacts) {
		set("contacts", contacts);
		return (M)this;
	}
	
	public java.lang.String getContacts() {
		return getStr("contacts");
	}

	public M setQq(java.lang.String qq) {
		set("qq", qq);
		return (M)this;
	}
	
	public java.lang.String getQq() {
		return getStr("qq");
	}

	public M setWangwang(java.lang.String wangwang) {
		set("wangwang", wangwang);
		return (M)this;
	}
	
	public java.lang.String getWangwang() {
		return getStr("wangwang");
	}

	public M setWechat(java.lang.String wechat) {
		set("wechat", wechat);
		return (M)this;
	}
	
	public java.lang.String getWechat() {
		return getStr("wechat");
	}

	public M setCooperation(java.lang.Boolean cooperation) {
		set("cooperation", cooperation);
		return (M)this;
	}
	
	public java.lang.Boolean getCooperation() {
		return get("cooperation");
	}

	public M setBrands(java.lang.String brands) {
		set("brands", brands);
		return (M)this;
	}
	
	public java.lang.String getBrands() {
		return getStr("brands");
	}

	public M setEmail(java.lang.String email) {
		set("email", email);
		return (M)this;
	}
	
	public java.lang.String getEmail() {
		return getStr("email");
	}

	public M setRemark(java.lang.String remark) {
		set("remark", remark);
		return (M)this;
	}
	
	public java.lang.String getRemark() {
		return getStr("remark");
	}

	public M setCreateDate(java.util.Date createDate) {
		set("create_date", createDate);
		return (M)this;
	}
	
	public java.util.Date getCreateDate() {
		return get("create_date");
	}

}
