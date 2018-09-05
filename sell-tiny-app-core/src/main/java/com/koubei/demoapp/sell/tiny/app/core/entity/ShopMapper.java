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
public class ShopMapper implements RowMapper<Shop> {

    @Override
    public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
        Shop shop = new Shop();
        shop.setId(rs.getLong("id"));
        shop.setOutShopId(rs.getString("out_shop_id"));
        shop.setName(rs.getString("name"));
        shop.setBulletin(rs.getString("bulletin"));
        shop.setAddress(rs.getString("address"));
        shop.setAvatar(rs.getString("avatar"));
        shop.setStatus(rs.getBoolean("status"));
        shop.setGmtCreate(rs.getTimestamp("gmt_create"));
        shop.setGmtModify(rs.getTimestamp("gmt_modified"));
        shop.setCreateBy(rs.getString("create_by"));
        shop.setModifyBy(rs.getString("modify_by"));
        return shop;
    }

}