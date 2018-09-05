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
public class CategoryMapper implements RowMapper<Category>{

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("id"));
        category.setName(rs.getString("name"));
        category.setShopId(rs.getLong("shop_id"));
        category.setStatus(rs.getBoolean("status"));
        category.setGmtCreate(rs.getTimestamp("gmt_create"));
        category.setGmtModify(rs.getTimestamp("gmt_modified"));
        category.setCreateBy(rs.getString("create_by"));
        category.setModifyBy(rs.getString("modify_by"));
        return category;
    }

}