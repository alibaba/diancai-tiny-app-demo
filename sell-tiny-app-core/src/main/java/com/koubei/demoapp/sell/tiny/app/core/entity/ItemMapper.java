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
 * @version $Id: Shop.java, v 0.1 2017-03-28 下午5:13 jianheng.hjh Exp $$
 */
public class ItemMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();
        item.setId(rs.getLong("id"));
        item.setName(rs.getString("name"));
        item.setShopId(rs.getLong("shop_id"));
        item.setPrice(rs.getBigDecimal("price"));
        item.setOriginPrice(rs.getBigDecimal("origin_price"));
        item.setDescription(rs.getString("description"));
        item.setSellCount(rs.getLong("sell_count"));
        item.setRating(rs.getBigDecimal("rating"));
        item.setInfo(rs.getString("info"));
        item.setImage(rs.getString("image"));
        item.setStatus(rs.getBoolean("status"));
        item.setGmtCreate(rs.getTimestamp("gmt_create"));
        item.setGmtModify(rs.getTimestamp("gmt_modified"));
        item.setCreateBy(rs.getString("create_by"));
        item.setModifyBy(rs.getString("modify_by"));
        return item;
    }

}