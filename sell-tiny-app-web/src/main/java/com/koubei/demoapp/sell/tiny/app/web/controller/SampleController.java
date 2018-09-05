/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jianheng.hjh
 * @version $Id: SampleController.java, v 0.1 2017-07-10 下午5:53 jianheng.hjh Exp $$
 */
@Controller
public class SampleController {

    @RequestMapping("/sample.json")
    @ResponseBody
    public String sample() {
        return "hello world!";
    }

}