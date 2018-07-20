/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dfs.springbitpay.dfsbitpay.dao;

import com.dfs.springbitpay.dfsbitpay.model.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Yixiang
 */
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet row, int rowNum) throws SQLException {
        User user = new User();
        user.setInvoiceId(row.getString("id"));
        user.setUsername(row.getString("username"));
        user.setAmount(row.getDouble("amount"));
        user.setDate(row.getString("date"));
        user.setStatus(row.getString("status"));
        user.setUrl(row.getString("url"));
        return user;
    }

    
}
