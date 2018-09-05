/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.web.vo;

import com.alibaba.fastjson.JSONObject;
import com.koubei.demoapp.sell.tiny.app.core.dto.CategoryDTO;

import java.util.List;

/**
 * @author jianheng.hjh
 * @version $Id: OrderInfoVO.java, v 0.1 2017-04-03 下午8:20 jianheng.hjh Exp $$
 */
public class GoodsPageVO {
    private List<CategoryDTO> items;
    private JSONObject shopInfo;

    /**
     * Getter method for property items.
     *
     * @return property value of items
     */
    public List<CategoryDTO> getItems() {
        return items;
    }

    /**
     * Setter method for property items.
     *
     * @param items value to be assigned to property items
     */
    public void setItems(List<CategoryDTO> items) {
        this.items = items;
    }

    /**
     * Getter method for property shopInfo.
     *
     * @return property value of shopInfo
     */
    public JSONObject getShopInfo() {
        return shopInfo;
    }

    /**
     * Setter method for property shopInfo.
     *
     * @param shopInfo value to be assigned to property shopInfo
     */
    public void setShopInfo(JSONObject shopInfo) {
        this.shopInfo = shopInfo;
    }
}