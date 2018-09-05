/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.web.vo;

import com.koubei.demoapp.sell.tiny.app.core.dto.OrderItemDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * 下单支付页、订单状态页共用数据
 *
 * @author jianheng.hjh
 * @version $Id: OrderPageVO.java, v 0.1 2017-03-29 上午9:40 jianheng.hjh Exp $$
 */
public class OrderPageVO {
    private String orderNo;
    private String alipayTradeNo;
    private Long shopId;
    private String shopName;
    private String shopAvatar;
    private List<OrderItemDTO> orderItems;
    private BigDecimal price;
    private String orderStatusDesc;
    private Integer orderStatus;

    /**
     * Getter method for property alipayTradeNo.
     *
     * @return property value of alipayTradeNo
     */
    public String getAlipayTradeNo() {
        return alipayTradeNo;
    }

    /**
     * Setter method for property alipayTradeNo.
     *
     * @param alipayTradeNo value to be assigned to property alipayTradeNo
     */
    public void setAlipayTradeNo(String alipayTradeNo) {
        this.alipayTradeNo = alipayTradeNo;
    }

    /**
     * Getter method for property orderStatus.
     *
     * @return property value of orderStatus
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * Setter method for property orderStatus.
     *
     * @param orderStatus value to be assigned to property orderStatus
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Getter method for property orderStatusDesc.
     *
     * @return property value of orderStatusDesc
     */
    public String getOrderStatusDesc() {
        return orderStatusDesc;
    }

    /**
     * Setter method for property orderStatusDesc.
     *
     * @param orderStatusDesc value to be assigned to property orderStatusDesc
     */
    public void setOrderStatusDesc(String orderStatusDesc) {
        this.orderStatusDesc = orderStatusDesc;
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
     * Getter method for property orderItems.
     *
     * @return property value of orderItems
     */
    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    /**
     * Setter method for property orderItems.
     *
     * @param orderItems value to be assigned to property orderItems
     */
    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
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