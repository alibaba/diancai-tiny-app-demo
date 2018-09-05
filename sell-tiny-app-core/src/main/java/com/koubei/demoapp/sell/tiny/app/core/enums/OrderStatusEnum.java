/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.enums;

/**
 * @author jianheng.hjh
 * @version $Id: OrderStatusEnum.java, v 0.1 2017-03-31 上午9:49 jianheng.hjh Exp $$
 */
public enum OrderStatusEnum {
    UNPAY(1, "待支付"),
    PAYED(2, "已支付"),
    CLOSED(3, "已关闭"),;

    private int code;
    private String desc;

    OrderStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OrderStatusEnum fromCode(int code) {
        for (OrderStatusEnum orderStatusEnum : values()) {
            if (orderStatusEnum.getCode() == code)
                return orderStatusEnum;
        }
        return UNPAY;
    }

    /**
     * Getter method for property code.
     *
     * @return property value of code
     */
    public int getCode() {
        return code;
    }

    /**
     * Getter method for property desc.
     *
     * @return property value of desc
     */
    public String getDesc() {
        return desc;
    }

}