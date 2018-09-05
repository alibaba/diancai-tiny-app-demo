/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author jianheng.hjh
 * @version $Id: ShopMapper.java, v 0.1 2017-04-02 上午10:40 jianheng.hjh Exp $$
 */
public class OrderMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setOrderNO(rs.getString("order_no"));
        order.setAlipayTradeNO(rs.getString("alipay_trade_no"));
        order.setSnapshot(rs.getString("snapshot"));
        order.setOrderStatus(rs.getInt("order_status"));
        order.setGmtCreate(rs.getTimestamp("gmt_create"));
        order.setGmtModify(rs.getTimestamp("gmt_modified"));
        order.setCreateBy(rs.getString("create_by"));
        order.setModifyBy(rs.getString("modify_by"));
        order.setUserId(rs.getString("user_id"));
        return order;
    }

}