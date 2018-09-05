/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.service.impl;

import com.koubei.demoapp.sell.tiny.app.core.entity.Order;
import com.koubei.demoapp.sell.tiny.app.core.entity.OrderMapper;
import com.koubei.demoapp.sell.tiny.app.core.enums.OrderStatusEnum;
import com.koubei.demoapp.sell.tiny.app.core.service.BaseServiceImpl;
import com.koubei.demoapp.sell.tiny.app.core.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author jianheng.hjh
 * @version $Id: OrderServiceImpl.java, v 0.1 2017-03-29 上午11:27 jianheng.hjh Exp $$
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public Order createOrder(final String userId, final String data) {
        String time = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        final String orderNO = time + userId;

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement stat = con.prepareStatement("insert into da_order (" +
                        "order_no, user_id, snapshot, order_status, gmt_create, create_by, modify_by) VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                stat.setString(1, orderNO);
                stat.setString(2, userId);
                stat.setString(3, data);
                stat.setInt(4, OrderStatusEnum.UNPAY.getCode());
                stat.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
                stat.setString(6, "0");
                stat.setString(7, "0");
                return stat;
            }
        }, holder);

        return loadByOrderNO(orderNO);
    }

    @Override
    public int updateOrderStatus(String orderNO, int status) {
        return jdbcTemplate.update("update da_order set status = ? where orderNO = ?", status, orderNO);
    }

    @Override
    public int updateOrderByOrderNO(Order order) {
        StringBuilder sqlBuilder = new StringBuilder();
        List<Object> params = new ArrayList<>();

        sqlBuilder.append("update da_order set gmt_modified = ?");
        params.add(new Timestamp(System.currentTimeMillis()));

        if (order.getAlipayTradeNO() != null) {
            sqlBuilder.append(", alipay_trade_no = ?");
            params.add(order.getAlipayTradeNO());
        }
        if (order.getSnapshot() != null) {
            sqlBuilder.append(", snapshot = ?");
            params.add(order.getSnapshot());
        }
        if (order.getOrderStatus() != null) {
            sqlBuilder.append(", order_status = ? ");
            params.add(order.getOrderStatus());
        }

        sqlBuilder.append("where order_no = ?");
        params.add(order.getOrderNO());
        return jdbcTemplate.update(sqlBuilder.toString(), params.toArray());
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        return jdbcTemplate.query("select * from da_order where user_id = ? ORDER BY gmt_create DESC", new OrderMapper(), userId);
    }

    @Override
    public Order loadByOrderNO(String orderNO) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM da_order WHERE order_no = ?", new OrderMapper(), orderNO);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void cancelOrder(String orderNO) {
        Order orderDTO = loadByOrderNO(orderNO);
        orderDTO.setOrderStatus(OrderStatusEnum.CLOSED.getCode());
        updateOrderByOrderNO(orderDTO);
    }

}