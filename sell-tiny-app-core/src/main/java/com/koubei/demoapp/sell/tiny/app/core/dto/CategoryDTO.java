/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.dto;

import com.koubei.demoapp.sell.tiny.app.core.entity.Item;

import java.util.List;

/**
 * @author jianheng.hjh
 * @version $Id: CategoryDTO.java, v 0.1 2017-03-28 下午5:40 jianheng.hjh Exp $$
 */
public class CategoryDTO {
    private Long id;
    private String name;
    private List<Item> items;

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

    /**
     * Getter method for property items.
     *
     * @return property value of items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Setter method for property items.
     *
     * @param items value to be assigned to property items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }
}