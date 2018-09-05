/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.service.impl;

import com.koubei.demoapp.sell.tiny.app.core.entity.Shop;
import com.koubei.demoapp.sell.tiny.app.core.entity.ShopMapper;
import com.koubei.demoapp.sell.tiny.app.core.service.BaseServiceImpl;
import com.koubei.demoapp.sell.tiny.app.core.service.ShopService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jianheng.hjh
 * @version $Id: ShopServiceImpl.java, v 0.1 2017-03-30 下午10:04 jianheng.hjh Exp $$
 */
@Service
public class ShopServiceImpl extends BaseServiceImpl implements ShopService {

    @Override
    public Shop loadById(Long shopId) {
        return jdbcTemplate.queryForObject("select * from da_shop where id = ?", new ShopMapper(), shopId);
    }

    @Override
    public Shop loadByOutShopId(String outShopId) {
        try {
            return jdbcTemplate.queryForObject("select * from da_shop where out_shop_id = ?", new ShopMapper(), outShopId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Shop> find(int offset, int limit) {
        return jdbcTemplate.query("select * from da_shop limit ?,?", new ShopMapper(), offset, limit);
    }

    @Override
    public int updateByShopId(Shop shop) {
        StringBuilder sqlBuilder = new StringBuilder();
        List<Object> params = new ArrayList<>();

        sqlBuilder.append("update da_shop set gmt_modified = ?");
        params.add(new Timestamp(System.currentTimeMillis()));

        if (shop.getName() != null) {
            sqlBuilder.append(", name = ?");
            params.add(shop.getName());
        }
        if (shop.getBulletin() != null) {
            sqlBuilder.append(", bulletin = ?");
            params.add(shop.getBulletin());
        }
        if (shop.getAddress() != null) {
            sqlBuilder.append(", address = ?");
            params.add(shop.getAddress());
        }
        if (shop.getAvatar() != null) {
            sqlBuilder.append(", avatar = ? ");
            params.add(shop.getAvatar());
        }

        sqlBuilder.append("where id = ?");
        params.add(shop.getId());
        return jdbcTemplate.update(sqlBuilder.toString(), params.toArray());
    }

}