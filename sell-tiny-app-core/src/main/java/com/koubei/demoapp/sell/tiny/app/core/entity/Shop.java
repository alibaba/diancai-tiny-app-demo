/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.entity;

/**
 * @author jianheng.hjh
 * @version $Id: Shop.java, v 0.1 2017-03-28 下午5:13 jianheng.hjh Exp $$
 */
public class Shop extends BaseEntity {
    private String outShopId;
    private String name;
    private String address;
    private String bulletin;
    private String avatar;

    /**
     * Getter method for property address.
     *
     * @return property value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for property address.
     *
     * @param address value to be assigned to property address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter method for property outShopId.
     *
     * @return property value of outShopId
     */
    public String getOutShopId() {
        return outShopId;
    }

    /**
     * Setter method for property outShopId.
     *
     * @param outShopId value to be assigned to property outShopId
     */
    public void setOutShopId(String outShopId) {
        this.outShopId = outShopId;
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
     * Getter method for property bulletin.
     *
     * @return property value of bulletin
     */
    public String getBulletin() {
        return bulletin;
    }

    /**
     * Setter method for property bulletin.
     *
     * @param bulletin value to be assigned to property bulletin
     */
    public void setBulletin(String bulletin) {
        this.bulletin = bulletin;
    }

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

}