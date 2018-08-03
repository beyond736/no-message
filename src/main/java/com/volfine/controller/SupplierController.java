package com.volfine.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.volfine.interceptor.LoginInterceptor;
import com.volfine.model.Supplier;

@Before(LoginInterceptor.class)
public class SupplierController extends Controller {

    public void add() {
        render("add.html");
    }

    public void list() {
        Page page = new Supplier().paginate(getParaToInt("pageNumber", 1), getParaToInt("pageSize", 10), "select *", "from v_supplier order by id desc");
        setAttr("page", page);
        render("list.html");
    }

    public void save() {
        Supplier supplier = getBean(Supplier.class);
        if (supplier.getId() != null) {
            supplier.update();
        } else {
            supplier.save();
        }
        redirect("list");
    }


    public void edit() {
        Long id = getParaToLong("id");

        Supplier supplier = new Supplier().findById(id);
        setAttr("supplier", supplier);
        render("edit.html");
    }

    public void delete() {
        Long id = getParaToLong("id");

        new Supplier().deleteById(id);
        renderJson("{\"message\":\"操作成功\"}");
    }

}
