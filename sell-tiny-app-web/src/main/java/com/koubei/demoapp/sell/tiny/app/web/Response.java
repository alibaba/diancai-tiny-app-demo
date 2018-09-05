/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.web;

/**
 * @author jianheng.hjh
 * @version $Id: Response.java, v 0.1 2017-03-28 下午5:31 jianheng.hjh Exp $$
 */
public class Response<T> {

    private final static int SUCCESS = 200;

    private T data;
    private int code;
    private String msg;

    public Response(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public static <T> Response success(T data) {
        return new Response(data, SUCCESS, null);
    }

    public static <T> Response success() {
        return new Response(null, SUCCESS, null);
    }

    public static <T> Response fail(String msg) {
        return new Response(null, 500, msg);
    }

    public boolean isSuccessful() {
        return code == SUCCESS;
    }

    /**
     * Getter method for property data.
     *
     * @return property value of data
     */
    public T getData() {
        return data;
    }

    /**
     * Setter method for property data.
     *
     * @param data value to be assigned to property data
     */
    public void setData(T data) {
        this.data = data;
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
     * Setter method for property code.
     *
     * @param code value to be assigned to property code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Getter method for property msg.
     *
     * @return property value of msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Setter method for property msg.
     *
     * @param msg value to be assigned to property msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}