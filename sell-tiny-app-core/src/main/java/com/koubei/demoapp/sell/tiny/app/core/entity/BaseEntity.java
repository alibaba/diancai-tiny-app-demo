/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.entity;

import java.util.Date;

/**
 * @author jianheng.hjh
 * @version $Id: BaseEntity.java, v 0.1 2017-03-28 下午5:14 jianheng.hjh Exp $$
 */
public abstract class BaseEntity {
    private Long id;
    private Boolean status;
    private String createBy;
    private String modifyBy;
    private Date gmtCreate;
    private Date gmtModify;

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

    public Boolean getStatus() {
        return status;
    }

    /**
     * Setter method for property status.
     *
     * @param status value to be assigned to property status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * Getter method for property createBy.
     *
     * @return property value of createBy
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * Setter method for property createBy.
     *
     * @param createBy value to be assigned to property createBy
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * Getter method for property modifyBy.
     *
     * @return property value of modifyBy
     */
    public String getModifyBy() {
        return modifyBy;
    }

    /**
     * Setter method for property modifyBy.
     *
     * @param modifyBy value to be assigned to property modifyBy
     */
    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
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
     * Getter method for property gmtModify.
     *
     * @return property value of gmtModify
     */
    public Date getGmtModify() {
        return gmtModify;
    }

    /**
     * Setter method for property gmtModify.
     *
     * @param gmtModify value to be assigned to property gmtModify
     */
    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}