/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.web.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jianheng.hjh
 * @version $Id: SavedRequest.java, v 0.1 2017-04-07 上午10:48 jianheng.hjh Exp $$
 */
public class SavedRequest {
    private String servletPath;
    private Map<String, Object> attrs;

    public void addAttr(String key, Object object) {
        if (attrs == null)
            attrs = new HashMap<>();
        attrs.put(key, object);
    }

    public Object getAttr(String key) {
        return attrs == null ? null : attrs.get(key);
    }

    /**
     * Getter method for property servletPath.
     *
     * @return property value of servletPath
     */
    public String getServletPath() {
        return servletPath;
    }

    /**
     * Setter method for property servletPath.
     *
     * @param servletPath value to be assigned to property servletPath
     */
    public void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }
}