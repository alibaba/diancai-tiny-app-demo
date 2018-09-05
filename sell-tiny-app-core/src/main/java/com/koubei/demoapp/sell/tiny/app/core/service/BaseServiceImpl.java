/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author jianheng.hjh
 * @version $Id: BaseServiceImpl.java, v 0.1 2017-03-28 下午7:26 jianheng.hjh Exp $$
 */
public class BaseServiceImpl {

    @Autowired
    protected DataSource ds;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    private Connection connection;

//    protected PreparedStatement prepareStatement(String sql, int key) {
//        try {
////            if (connection == null) {
////                this.connection = ds.getConnection();
////            }
////            return this.connection.prepareStatement(sql, key);
//            return ds.getConnection().prepareStatement(sql, key);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }

//    protected PreparedStatement prepareStatement(String sql) {
//        try {
////            if (connection == null) {
////                this.connection = ds.getConnection();
////            }
////            return this.connection.prepareStatement(sql);
//            return ds.getConnection().prepareStatement(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }

}