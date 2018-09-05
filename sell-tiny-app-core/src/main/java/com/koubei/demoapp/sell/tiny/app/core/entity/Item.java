/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.entity;

import java.math.BigDecimal;

/**
 * @author jianheng.hjh
 * @version $Id: Shop.java, v 0.1 2017-03-28 下午5:13 jianheng.hjh Exp $$
 */
public class Item extends BaseEntity {
    private String name;
    private Long shopId;
    private BigDecimal price;
    private BigDecimal originPrice;
    private String description;
    private String info;
    private Long sellCount;
    private BigDecimal rating;
    private String image;

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

    /**
     * Getter method for property originPrice.
     *
     * @return property value of originPrice
     */
    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    /**
     * Setter method for property originPrice.
     *
     * @param originPrice value to be assigned to property originPrice
     */
    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    /**
     * Getter method for property description.
     *
     * @return property value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for property description.
     *
     * @param description value to be assigned to property description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter method for property info.
     *
     * @return property value of info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Setter method for property info.
     *
     * @param info value to be assigned to property info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Getter method for property sellCount.
     *
     * @return property value of sellCount
     */
    public Long getSellCount() {
        return sellCount;
    }

    /**
     * Setter method for property sellCount.
     *
     * @param sellCount value to be assigned to property sellCount
     */
    public void setSellCount(Long sellCount) {
        this.sellCount = sellCount;
    }

    /**
     * Getter method for property rating.
     *
     * @return property value of rating
     */
    public BigDecimal getRating() {
        return rating;
    }

    /**
     * Setter method for property rating.
     *
     * @param rating value to be assigned to property rating
     */
    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    /**
     * Getter method for property image.
     *
     * @return property value of image
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter method for property image.
     *
     * @param image value to be assigned to property image
     */
    public void setImage(String image) {
        this.image = image;
    }
}