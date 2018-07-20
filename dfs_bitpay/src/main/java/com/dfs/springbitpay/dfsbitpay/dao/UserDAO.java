/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dfs.springbitpay.dfsbitpay.dao;

import com.dfs.springbitpay.dfsbitpay.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Yixiang
 */
@Transactional
@Repository
public class UserDAO {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jt) {
        jdbcTemplate = jt;
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM invoice";
        RowMapper<User> rowMapper = new UserRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public void addUser(User user) {
        String sql = "INSERT INTO invoice (id, username, amount, date, status) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getInvoiceId(), user.getUsername(), user.getAmount(), user.getDate(), user.getStatus());
    }

    public List<User> getUserByUsername(String username) {
        String sql = "SELECT * FROM invoice WHERE username = ?";
        RowMapper<User> rowMapper = new UserRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, username);
    }
    
    public List<User> getUserByInvoiceId(String id) {
        String sql = "SELECT * FROM invoice WHERE id = ?";
        RowMapper<User> rowMapper = new UserRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, id);
    }

    public void updateInvoiceStatus(String id, String status) {
        String sql = "UPDATE invoice SET status=? WHERE id=?";
        jdbcTemplate.update(sql, status, id);
    }

    public void deleteUser(String username) {
        String sql = "DELETE FROM invoice WHERE username=?";
        jdbcTemplate.update(sql, username);
    }

    public boolean userExists(String username, String id) {
        String sql = "SELECT count(*) FROM invoice WHERE username = ? and id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, username, id);
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }
}
