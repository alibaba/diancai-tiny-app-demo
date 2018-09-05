package com.koubei.demoapp.sell.tiny.app.core.entity;

/**
 * Created by jackey on 17/4/5.
 */
public class OrderDetail extends BaseEntity  {

    private String userId;// 订单顾客用户id

    private String orderNO;// 当前餐饮订单号

    private Long totalPrice;// 订单总金额

    private Long discountPrice;// 优惠金额

    private Long realPrice;// 订单实际金额

    private String hasClosed;//是否已清桌 , 0 未清桌; 1 清桌

    public String getOrderNO() {
        return orderNO;
    }

    public void setOrderNO(String orderNO) {
        this.orderNO = orderNO;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Long discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Long getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Long realPrice) {
        this.realPrice = realPrice;
    }

    public String getHasClosed() {
        return hasClosed;
    }

    public void setHasClosed(String hasClosed) {
        this.hasClosed = hasClosed;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
