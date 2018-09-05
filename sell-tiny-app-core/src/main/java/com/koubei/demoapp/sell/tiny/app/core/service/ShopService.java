/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.service;

import com.koubei.demoapp.sell.tiny.app.core.entity.Shop;

import java.util.List;

/**
 * @author jianheng.hjh
 * @version $Id: ItemService.java, v 0.1 2017-03-28 下午5:37 jianheng.hjh Exp $$
 */
public interface ShopService {

    Shop loadById(Long shopId);

    Shop loadByOutShopId(String outShopId);

    List<Shop> find(int offset, int limit);

    int updateByShopId(Shop shop);

}