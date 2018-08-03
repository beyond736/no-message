package com.volfine.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.volfine.interceptor.LoginInterceptor;
import com.volfine.model.Country;
import com.volfine.model.Customer;

import java.util.List;

@Before(LoginInterceptor.class)
public class CustomerController extends Controller {

    public void add() {
        List<Country> list = new Country().find("select * from v_country");
        setAttr("countryList", list);
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

        Page page = new Customer().paginate(getParaToInt("pageNumber", 1), getParaToInt("pageSize", 10), "select *", "from v_customer where  1=1 " + condition + " order by id desc");
        setAttr("page", page);
        render("list.html");
    }

    public void save() {
        Customer customer = getBean(Customer.class);
        if (customer.getId() != null) {
            customer.update();
        } else {
            customer.save();
        }
        redirect("list");
    }

    public void edit() {
        Long id = getParaToLong("id");

        Customer customer = new Customer().findById(id);
        setAttr("customer", customer);

        if (customer != null) {
            List<Country> list = new Country().find("select * from v_country");
            setAttr("countryList", list);
        }
        render("edit.html");
    }

    public void delete() {
        Long id = getParaToLong("id");

        new Customer().deleteById(id);
        renderJson("{\"message\":\"操作成功\"}");
    }

}
