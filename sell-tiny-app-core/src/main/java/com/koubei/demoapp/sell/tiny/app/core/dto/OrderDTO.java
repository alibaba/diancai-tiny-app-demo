/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.dto;

import java.util.Date;

/**
 * @author jianheng.hjh
 * @version $Id: OrderDTO.java, v 0.1 2017-03-29 上午10:52 jianheng.hjh Exp $$
 */
public class OrderDTO {
    private Long id;
    private String orderNO;
    private String alipayTradeNO;
    private String userId;
    private String snapshot;
    private Integer status;
    private Date gmtCreate;

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
     * Getter method for property gmtCreate.
     *
     * @return property value of gmtCreate
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * Setter method for property gmtCreate.
     *
     * @param gmtCreate value to be assigned to property gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
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

    /**
     * Getter method for property status.
     *
     * @return property value of status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Setter method for property status.
     *
     * @param status value to be assigned to property status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}