/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.entity;

/**
 * @author jianheng.hjh
 * @version $Id: Shop.java, v 0.1 2017-03-28 下午5:13 jianheng.hjh Exp $$
 */
public class CategoryItem extends BaseEntity {
    private Long categoryId;
    private Long itemId;

    /**
     * Getter method for property categoryId.
     *
     * @return property value of categoryId
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * Setter method for property categoryId.
     *
     * @param categoryId value to be assigned to property categoryId
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Getter method for property itemId.
     *
     * @return property value of itemId
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * Setter method for property itemId.
     *
     * @param itemId value to be assigned to property itemId
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}