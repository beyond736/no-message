package com.volfine.service;

import com.jfinal.plugin.activerecord.Page;
import com.volfine.model.Product;

public class ProductService {

    public static final ProductService me = new ProductService();

    private Product dao = new Product();

    public Page<Product> paginate(int pageNumber, int pageSize) {
        return dao.paginate(pageNumber, pageSize, "select *", "from v_product order by id asc");
    }

    public Product findById(int id) {
        return dao.findById(id);
    }

    public void deleteById(int id) {
        dao.deleteById(id);
    }
}
