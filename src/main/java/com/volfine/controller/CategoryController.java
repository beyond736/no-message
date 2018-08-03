package com.volfine.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.volfine.interceptor.LoginInterceptor;
import com.volfine.model.Category;
import com.volfine.model.Product;
import com.volfine.service.CategoryService;
import com.volfine.service.ProductService;

@Before(LoginInterceptor.class)
public class CategoryController extends Controller {

    private CategoryService categoryService = CategoryService.me;

    public void add() {
        render("add.html");
    }

    public void list() {
        Page page = categoryService.paginate(1, 10);
        setAttr("page", page);
        render("list.html");
    }

    public void save() {
        Category category = getBean(Category.class);
        category.save();
        redirect("list");
    }
}
