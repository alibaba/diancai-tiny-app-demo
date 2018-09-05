package com.koubei.demoapp.sell.tiny.app.core.enums;

/**
 * 支付宝订单状态
 * Created by jackey on 17/4/5.
 */
public enum AlipayOrderStatusEnum {

    waitSellerAccept("11","已下单"),

    sellerAccepted("12","已确认订单"),

    waitPay("13","订单待支付"),

    payed("14","订单支付完成"),

    dishClose("15","订单已关闭");

    private String orderStatusCode;

    private String orderStatusMsg;

    private AlipayOrderStatusEnum(String statusCode,String statusMsg) {
        this.orderStatusCode = statusCode;
        this.orderStatusMsg = statusMsg;
    }

    public static AlipayOrderStatusEnum getByCode(String statusCode) {
        for(AlipayOrderStatusEnum orderStatus : AlipayOrderStatusEnum.values()) {
            if(orderStatus.getOrderStatusCode().equals(statusCode)) {
                return orderStatus;
            }
        }
        return null;
    }


    public String getOrderStatusCode() {
        return orderStatusCode;
    }

    public void setOrderStatusCode(String orderStatusCode) {
        this.orderStatusCode = orderStatusCode;
    }

    public String getOrderStatusMsg() {
        return orderStatusMsg;
    }

    public void setOrderStatusMsg(String orderStatusMsg) {
        this.orderStatusMsg = orderStatusMsg;
    }
}
