package com.volfine.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.volfine.service.ProductService;

public class IndexController extends Controller {

    ProductService productService = ProductService.me;

    public void index() {
        renderJsp("login.html");
    }

    public void save() {
        Page page = productService.paginate(1, 10);
        renderJson(page);
    }

    public void delete() {

    }

}
