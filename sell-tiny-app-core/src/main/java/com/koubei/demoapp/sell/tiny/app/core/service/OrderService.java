/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.service;

import com.koubei.demoapp.sell.tiny.app.core.entity.Order;

import java.util.List;

/**
 * @author jianheng.hjh
 * @version $Id: ItemService.java, v 0.1 2017-03-28 下午5:37 jianheng.hjh Exp $$
 */
public interface OrderService {

    Order createOrder(String userId, String data);

    int updateOrderByOrderNO(Order order);

    List<Order> getOrdersByUserId(String userId);

    Order loadByOrderNO(String orderNO);

    void cancelOrder(String orderNO);

    int updateOrderStatus(String orderNO, int status);

}