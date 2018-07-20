/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dfs.springbitpay.dfsbitpay.controller;

import com.dfs.springbitpay.dfsbitpay.model.Invoice;
import com.dfs.springbitpay.dfsbitpay.model.User;
import com.dfs.springbitpay.dfsbitpay.service.UserService;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Yixiang
 */
@Controller
public class ResultController {

    @Autowired
    private UserService userService;

    private static final Logger _logger = Logger.getLogger(BitPaymentController.class.getName());
    
    @GetMapping("/invoice/{username}")
    public String getInvoice(@PathVariable("username") String username, Map<String, Object> model) {
		String url = "";
		String urlEnd = "";
        List<User> users = userService.getUserByUsername(username);
        for (User user : users) {
            if (user != null) {
            	url = user.getUrl();
            	urlEnd += "?out_trade_no=" + username;
            	urlEnd += "&total_fee=" + user.getAmount();
            	urlEnd += "&trade_status=" + user.getStatus();
            }
        }
        String toRedirect = url + urlEnd;
        return "redirect:" + url;
    }
   
    
    @GetMapping("/invoice")
    public String getAllInvoice(Map<String, Object> model){
    	String username = "Order144";
    	String url = "";
		String urlEnd = "";
        List<User> users = userService.getUserByUsername(username);
        for (User user : users) {
            if (user != null) {
            	url = user.getUrl();
            	urlEnd += "?out_trade_no=" + username;
            	urlEnd += "&total_fee=" + user.getAmount();
            	urlEnd += "&trade_status=" + user.getStatus();
            }
        }
        String toRedirect = url + urlEnd;
        return "redirect:" + url;
    }

    

}
