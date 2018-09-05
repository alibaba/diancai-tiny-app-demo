/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.constant;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.koubei.cavalry.architect.support.DummyDataSource;
import com.koubei.demoapp.sell.tiny.app.core.entity.SystemVar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author jianheng.hjh
 * @version $Id: SystemConstant.java, v 0.1 2017-07-05 下午2:34 jianheng.hjh Exp $$
 */
@Component
public class SystemConstant {

    private static final Logger logger = LoggerFactory.getLogger(SystemConstant.class);

//    public static String appId = "";
//    public static String appPrivateKey = "";
//    public static String alipayPublicKey = "";

    private static JdbcTemplate jdbcTemplate;

//    @PostConstruct
//    public void init() {
//        if (jdbcTemplate.getDataSource() instanceof DummyDataSource)
//            return;
//
//        SystemConstant.appId = getSystemVar("appId");
//        SystemConstant.appPrivateKey = getSystemVar("appPrivateKey");
//        SystemConstant.alipayPublicKey = getSystemVar("alipayPublicKey");
//    }

    private static String getSystemVar(String key) {
        if (jdbcTemplate.getDataSource() instanceof DummyDataSource)
            return null;

        try {
            SystemVar systemVar = jdbcTemplate.queryForObject("SELECT * FROM csys_system_vars WHERE `key` = ?", new RowMapper<SystemVar>() {
                @Override
                public SystemVar mapRow(ResultSet rs, int rowNum) throws SQLException {
                    SystemVar var = new SystemVar();
                    var.setId(rs.getLong("id"));
                    var.setKey(rs.getString("key"));
                    var.setValue(rs.getString("value"));
                    var.setStatus(rs.getBoolean("status"));
                    var.setGmtCreate(rs.getTimestamp("gmt_create"));
                    var.setGmtModified(rs.getTimestamp("gmt_modified"));
                    return var;
                }
            }, key);
            if (systemVar != null) {
                logger.info(String.format("获取db系统变量 [%s]: %s", key, systemVar.getValue()));
                return systemVar.getValue();
            } else {
                logger.info(String.format("获取db系统变量失败 [%s]", key));
                return null;
            }
        } catch (Exception e) {
            logger.error(String.format("获取db系统变量失败 [%s]", key), e);
            return null;
        }
    }

    public static String getAppId() {
        return getSystemVar("appId");
    }

    public static String getAppPrivateKey() {
        return getSystemVar("appPrivateKey");
    }

    public static String getAlipayPublicKey() {
        return getSystemVar("alipayPublicKey");
    }

    public static AlipayClient getAlipayClient() {
        return new DefaultAlipayClient(
                "https://openapi.alipay.com/gateway.do",
                getAppId(),
                getAppPrivateKey(),
                "json",
                "utf-8",
                getAlipayPublicKey(),
                "RSA2");
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        SystemConstant.jdbcTemplate = jdbcTemplate;
    }

}