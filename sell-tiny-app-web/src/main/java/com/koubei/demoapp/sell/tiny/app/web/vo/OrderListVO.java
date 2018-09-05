/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.web.vo;

import java.util.List;

/**
 * @author jianheng.hjh
 * @version $Id: OrderItemDTO.java, v 0.1 2017-03-29 上午9:41 jianheng.hjh Exp $$
 */
public class OrderListVO {
    private String orderNo;
    private String shopAvatar;
    private String shopName;
    private String dateTime;
    private String orderStatus;
    private List<String> items;
    private String price;

    /**
     * Getter method for property orderNo.
     *
     * @return property value of orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * Setter method for property orderNo.
     *
     * @param orderNo value to be assigned to property orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * Getter method for property shopAvatar.
     *
     * @return property value of shopAvatar
     */
    public String getShopAvatar() {
        return shopAvatar;
    }

    /**
     * Setter method for property shopAvatar.
     *
     * @param shopAvatar value to be assigned to property shopAvatar
     */
    public void setShopAvatar(String shopAvatar) {
        this.shopAvatar = shopAvatar;
    }

    /**
     * Getter method for property shopName.
     *
     * @return property value of shopName
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * Setter method for property shopName.
     *
     * @param shopName value to be assigned to property shopName
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * Getter method for property dateTime.
     *
     * @return property value of dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Setter method for property dateTime.
     *
     * @param dateTime value to be assigned to property dateTime
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Getter method for property orderStatus.
     *
     * @return property value of orderStatus
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * Setter method for property orderStatus.
     *
     * @param orderStatus value to be assigned to property orderStatus
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Getter method for property items.
     *
     * @return property value of items
     */
    public List<String> getItems() {
        return items;
    }

    /**
     * Setter method for property items.
     *
     * @param items value to be assigned to property items
     */
    public void setItems(List<String> items) {
        this.items = items;
    }

    /**
     * Getter method for property price.
     *
     * @return property value of price
     */
    public String getPrice() {
        return price;
    }

    /**
     * Setter method for property price.
     *
     * @param price value to be assigned to property price
     */
    public void setPrice(String price) {
        this.price = price;
    }
}