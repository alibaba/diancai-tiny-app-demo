/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.web.vo;

/**
 * @author jianheng.hjh
 * @version $Id: OrderInfoVO.java, v 0.1 2017-04-03 下午8:20 jianheng.hjh Exp $$
 */
public class OrderInfoVO {
    private String orderStr;
    private String orderNo;

    /**
     * Getter method for property orderStr.
     *
     * @return property value of orderStr
     */
    public String getOrderStr() {
        return orderStr;
    }

    /**
     * Setter method for property orderStr.
     *
     * @param orderStr value to be assigned to property orderStr
     */
    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
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
}