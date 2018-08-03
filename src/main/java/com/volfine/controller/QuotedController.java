package com.volfine.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.volfine.interceptor.LoginInterceptor;
import com.volfine.model.Customer;
import com.volfine.model.QuotedPrice;
import com.volfine.model.Supplier;

@Before(LoginInterceptor.class)
public class QuotedController extends Controller {

    public void add() {
        render("add.html");
    }

    public void list() {
        String searchProperty = getPara("searchProperty");
        String searchValue = getPara("searchValue");
        String condition = "";
        if (StrKit.notBlank(searchProperty, searchValue)) {
            condition = " and " + searchProperty + " like '%" + searchValue + "%'";
            setAttr("searchProperty", searchProperty);
            setAttr("searchValue", searchValue);
        }

        Page page = new QuotedPrice().paginate(getParaToInt("pageNumber", 1), getParaToInt("pageSize", 10), "select *", "from v_quoted_price where  1=1 " + condition + " order by id desc");
        setAttr("page", page);
        render("list.html");
    }

    public void save() {
        QuotedPrice quotedPrice = getBean(QuotedPrice.class, "quoted");
        if (quotedPrice.getId() != null) {
            quotedPrice.update();
        } else {
            quotedPrice.save();
        }
        redirect("list");
    }


    public void edit() {
        Long id = getParaToLong("id");

        QuotedPrice quotedPrice = new QuotedPrice().findById(id);
        setAttr("quoted", quotedPrice);
        render("edit.html");
    }

    public void delete() {
        Long id = getParaToLong("id");

        new QuotedPrice().deleteById(id);
        renderJson("{\"message\":\"操作成功\"}");
    }

}
