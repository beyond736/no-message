package com.volfine.service;

import com.jfinal.plugin.activerecord.Page;
import com.volfine.model.Order;

public class OrderService {

    public static final OrderService me = new OrderService();

    private Order dao = new Order();

    public Page<Order> paginate(int pageNumber, int pageSize) {
        return dao.paginate(pageNumber, pageSize, "select *", "from v_order order by id asc");
    }

    public Order findById(int id) {
        return dao.findById(id);
    }

    public void deleteById(int id) {
        dao.deleteById(id);
    }
}
