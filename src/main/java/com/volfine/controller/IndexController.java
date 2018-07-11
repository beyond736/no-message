package com.volfine.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.volfine.service.ProductService;

public class IndexController extends Controller {

    ProductService service = ProductService.me;

    public void save() {
        Page page = service.paginate(1, 10);
        renderJson(page);
    }

}
