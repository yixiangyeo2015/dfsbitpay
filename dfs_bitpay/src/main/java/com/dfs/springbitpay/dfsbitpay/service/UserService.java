/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dfs.springbitpay.dfsbitpay.service;

import com.dfs.springbitpay.dfsbitpay.dao.UserDAO;
import com.dfs.springbitpay.dfsbitpay.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yixiang
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public List<User> getUserByUsername(String username) {
        List<User> obj = userDAO.getUserByUsername(username);
        return obj;
    }

    public List<User> getUserByInvoiceId(String id) {
        List<User> obj = userDAO.getUserByInvoiceId(id);
        return obj;
    }
    
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public synchronized boolean addUser(User user) {
        if (userDAO.userExists(user.getUsername(), user.getInvoiceId())) {
            return false;
        } else {
            userDAO.addUser(user);
            return true;
        }
    }

    public void updateInvoiceStatus(String id, String status) {
        userDAO.updateInvoiceStatus(id, status);
    }

    public void deleteUser(String username) {
        userDAO.deleteUser(username);
    }
}
