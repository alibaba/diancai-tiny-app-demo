/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.service;

import com.koubei.demoapp.sell.tiny.app.core.entity.Item;
import com.koubei.demoapp.sell.tiny.app.core.dto.CategoryDTO;
import com.koubei.demoapp.sell.tiny.app.core.dto.ItemDTO;

import java.util.List;

/**
 * @author jianheng.hjh
 * @version $Id: ItemService.java, v 0.1 2017-03-28 下午5:37 jianheng.hjh Exp $$
 */
public interface ItemService {

    List<CategoryDTO> getItemsByShopId(Long shopId);

    ItemDTO loadById(long itemId);

    List<Item> findByIds(List<Long> itemIds);

}