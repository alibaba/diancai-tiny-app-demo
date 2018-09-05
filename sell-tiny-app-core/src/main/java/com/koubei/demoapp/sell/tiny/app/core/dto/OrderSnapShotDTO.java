package com.koubei.demoapp.sell.tiny.app.core.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jackey on 17/3/31.
 */
public class OrderSnapShotDTO {

    private BigDecimal cut;

    private List<OrderItemDTO> orderItems;

    private BigDecimal originPrice;

    private BigDecimal price;

    private String shopName;

    private Long shopId;

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

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public BigDecimal getCut() {
        return cut;
    }

    public void setCut(BigDecimal cut) {
        this.cut = cut;
    }
}
