/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.entity;

import java.util.Date;

/**
 * @author jianheng.hjh
 * @version $Id: SystemVar.java, v 0.1 2017-07-05 下午3:01 jianheng.hjh Exp $$
 */
public class SystemVar {
    private Long id;
    private String key;
    private String value;
    private Boolean status;
    private Date gmtCreate;
    private Date gmtModified;

    /**
     * Setter method for property status.
     *
     * @param status value to be assigned to property status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    /**
     * Getter method for property id.
     *
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property id.
     *
     * @param id value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for property key.
     *
     * @return property value of key
     */
    public String getKey() {
        return key;
    }

    /**
     * Setter method for property key.
     *
     * @param key value to be assigned to property key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Getter method for property value.
     *
     * @return property value of value
     */
    public String getValue() {
        return value;
    }

    /**
     * Setter method for property value.
     *
     * @param value value to be assigned to property value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Getter method for property gmtCreate.
     *
     * @return property value of gmtCreate
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * Setter method for property gmtCreate.
     *
     * @param gmtCreate value to be assigned to property gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * Getter method for property gmtModified.
     *
     * @return property value of gmtModified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * Setter method for property gmtModified.
     *
     * @param gmtModified value to be assigned to property gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}