/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.dto;

/**
 * @author jianheng.hjh
 * @version $Id: OrderDTO.java, v 0.1 2017-03-29 上午10:52 jianheng.hjh Exp $$
 */
public class ShopDTO {
    private Long id;
    private String name;
    private String avatar;

    /**
     * Getter method for property avatar.
     *
     * @return property value of avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Setter method for property avatar.
     *
     * @param avatar value to be assigned to property avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Getter method for property id.
     *
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property id.
     *
     * @param id value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

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
}