package com.volfine.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseCategory<M extends BaseCategory<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Long id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public M setParentId(java.lang.Long parentId) {
		set("parent_id", parentId);
		return (M)this;
	}
	
	public java.lang.Long getParentId() {
		return getLong("parent_id");
	}

	public M setSort(java.lang.Integer sort) {
		set("sort", sort);
		return (M)this;
	}
	
	public java.lang.Integer getSort() {
		return getInt("sort");
	}

	public M setCreateDate(java.util.Date createDate) {
		set("create_date", createDate);
		return (M)this;
	}
	
	public java.util.Date getCreateDate() {
		return get("create_date");
	}

}
