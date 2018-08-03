package com.volfine.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.volfine.interceptor.LoginInterceptor;
import com.volfine.model.Customer;
import com.volfine.model.Order;
import com.volfine.model.Supplier;
import com.volfine.service.OrderService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Before(LoginInterceptor.class)
public class OrderController extends Controller {

    private OrderService orderService = new OrderService();

    public void add() {
        List customerList = new Customer().find("select * from v_customer");
        setAttr("customerList", customerList);

        List supplierList = new Supplier().find("select * from v_supplier");
        setAttr("supplierList", supplierList);
        render("add.html");
    }

    public void customerSelect() {
        String keyword = getPara("keyword");
        if (StrKit.isBlank(keyword)) {
            renderJson(Collections.EMPTY_LIST);
        }

        List<Customer> customerList = new Customer().find("select * from v_customer where name like '%" + keyword + "%'");
        renderJson(customerList);
    }

    public void supplierSelect() {
        List<Map<String, Object>> data = new ArrayList<>();
        String keyword = getPara("keyword");
        if (StrKit.isBlank(keyword)) {
            renderJson(data);
        }

        List<Supplier> supplierList = new Supplier().find("select * from v_supplier where company like  '%" + keyword + "%'");
        renderJson(supplierList);
    }


    public void list() {
        Page page = orderService.paginate(getParaToInt("pageNumber", 1), getParaToInt("pageSize", 20));
        setAttr("page", page);
        render("list.html");
    }

    public void save() {
        Order order = getBean(Order.class);
        order.save();
        System.out.println(order.getId());
        redirect("list");
    }
}
