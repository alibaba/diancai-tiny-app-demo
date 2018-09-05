/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.dto;

import java.math.BigDecimal;

/**
 * @author jianheng.hjh
 * @version $Id: OrderItemDTO.java, v 0.1 2017-03-29 上午9:41 jianheng.hjh Exp $$
 */
public class OrderItemDTO {
    private Long id;
    private String name;
    private int quantity;
    private BigDecimal price;

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
     * Getter method for property quantity.
     *
     * @return property value of quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter method for property quantity.
     *
     * @param quantity value to be assigned to property quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter method for property price.
     *
     * @return property value of price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Setter method for property price.
     *
     * @param price value to be assigned to property price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}