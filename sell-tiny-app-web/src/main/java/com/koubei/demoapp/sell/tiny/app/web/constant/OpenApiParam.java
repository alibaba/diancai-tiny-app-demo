/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.web.constant;

/**
 * 开放平台参数
 *
 * @author zhangpei.zp 甫卓
 * @version $Id: OpenApiParam.java, v 0.1 2017年3月29日 上午11:34:00 zhangpei.zp Exp $
 */
public class OpenApiParam {

    public static final String SELLER_ID = "";

    public static final String BODY = "测试交易";

    public static final String NOTIFY_URL = String.format("https://%s/pay/notify.htm", System.getenv().get("notify_url"));

}