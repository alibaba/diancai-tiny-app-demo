package com.koubei.demoapp.sell.tiny.app.core.entity;

/**
 * Created by jackey on 17/4/5.
 */
public class AppInfo extends BaseEntity {

    private String ip;

    private String appId;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
