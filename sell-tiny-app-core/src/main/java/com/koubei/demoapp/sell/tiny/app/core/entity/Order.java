/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.entity;

/**
 * @author jianheng.hjh
 * @version $Id: OrderDTO.java, v 0.1 2017-03-29 上午10:52 jianheng.hjh Exp $$
 */
public class Order extends BaseEntity {
    private String orderNO;// 当前餐饮订单号
    private String alipayTradeNO;// 关联支付宝订单号
    private String userId;
    private String snapshot;
    private Integer orderStatus;

    /**
     * Getter method for property alipayTradeNO.
     *
     * @return property value of alipayTradeNO
     */
    public String getAlipayTradeNO() {
        return alipayTradeNO;
    }

    /**
     * Setter method for property alipayTradeNO.
     *
     * @param alipayTradeNO value to be assigned to property alipayTradeNO
     */
    public void setAlipayTradeNO(String alipayTradeNO) {
        this.alipayTradeNO = alipayTradeNO;
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
     * Getter method for property orderNO.
     *
     * @return property value of orderNO
     */
    public String getOrderNO() {
        return orderNO;
    }

    /**
     * Setter method for property orderNO.
     *
     * @param orderNO value to be assigned to property orderNO
     */
    public void setOrderNO(String orderNO) {
        this.orderNO = orderNO;
    }

    /**
     * Getter method for property userId.
     *
     * @return property value of userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Setter method for property userId.
     *
     * @param userId value to be assigned to property userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Getter method for property snapshot.
     *
     * @return property value of snapshot
     */
    public String getSnapshot() {
        return snapshot;
    }

    /**
     * Setter method for property snapshot.
     *
     * @param snapshot value to be assigned to property snapshot
     */
    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

}