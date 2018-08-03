package com.volfine.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.volfine.interceptor.LoginInterceptor;
import com.volfine.model.Category;
import com.volfine.model.Product;
import com.volfine.service.ProductService;

import java.util.List;

@Before(LoginInterceptor.class)
public class ProductController extends Controller {

    ProductService productService = ProductService.me;

    public void add() {
        List<Category> list = new Category().find("select * from v_category order by sort");
        setAttr("list", list);
        render("add.html");
    }

    public void list() {
        Page page = productService.paginate(getParaToInt("pageNumber", 1), getParaToInt("pageSize", 20));
        setAttr("page", page);
        render("list.html");
    }

    public void save() {
        Product product = getBean(Product.class);
        product.save();
    }
}
