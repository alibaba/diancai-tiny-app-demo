/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.web.controller;

import com.koubei.demoapp.sell.tiny.app.core.dto.CategoryDTO;
import com.koubei.demoapp.sell.tiny.app.core.service.ItemService;
import com.koubei.demoapp.sell.tiny.app.core.service.ShopService;
import com.koubei.demoapp.sell.tiny.app.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author jianheng.hjh
 * @version $Id: ItemController.java, v 0.1 2017-03-28 涓嬪崍5:29 jianheng.hjh Exp $$
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/items.json")
    public Response getShopItems(HttpServletRequest request) {
        Long shopId = (Long) request.getSession().getAttribute("shopId");
        List<CategoryDTO> itemsByShopId = itemService.getItemsByShopId(shopId);

        return new Response(itemsByShopId, 200, null);
    }

}