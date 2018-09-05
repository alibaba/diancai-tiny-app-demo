/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.entity;

/**
 * @author jianheng.hjh
 * @version $Id: Shop.java, v 0.1 2017-03-28 下午5:13 jianheng.hjh Exp $$
 */
public class Category extends BaseEntity {
    private String name;
    private Long shopId;
    private Integer type;

    /**
     * Getter method for property name.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property name.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property shopId.
     *
     * @return property value of shopId
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * Setter method for property shopId.
     *
     * @param shopId value to be assigned to property shopId
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * Getter method for property type.
     *
     * @return property value of type
     */
    public Integer getType() {
        return type;
    }

    /**
     * Setter method for property type.
     *
     * @param type value to be assigned to property type
     */
    public void setType(Integer type) {
        this.type = type;
    }
}