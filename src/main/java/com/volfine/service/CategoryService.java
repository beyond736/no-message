package com.volfine.service;

import com.jfinal.plugin.activerecord.Page;
import com.volfine.model.Category;
import com.volfine.model.Product;

public class CategoryService {

    public static final CategoryService me = new CategoryService();

    private Category dao = new Category();

    public Page<Category> paginate(int pageNumber, int pageSize) {
        return dao.paginate(pageNumber, pageSize, "select *", "from v_category order by id asc");
    }

    public Category findById(int id) {
        return dao.findById(id);
    }

    public void deleteById(int id) {
        dao.deleteById(id);
    }
}
