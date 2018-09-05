package com.koubei.demoapp.sell.tiny.app.core.utils;

import java.util.UUID;

/**
 * Created by jackey on 17/4/4.
 */
public class UUIDGenerator {

    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
//        return s.replace("-", "");
    }
}

