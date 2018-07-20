/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dfs.springbitpay.dfsbitpay.controller;

import com.dfs.springbitpay.dfsbitpay.model.Invoice;
import com.dfs.springbitpay.dfsbitpay.service.UserService;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Yixiang
 */
@Controller
public class WebhookController {

    @Autowired
    UserService userService;

    private static final Logger _logger = Logger.getLogger(BitPaymentController.class.getName());

    @PostMapping("/webhook")
    public ResponseEntity webhook(@RequestBody Invoice invoice) {
        
        try {
            String id = invoice.getId();
            String status = invoice.getStatus();
            String posData = invoice.getPosData();
            userService.updateInvoiceStatus(id, status);
            //userService.updateInvoiceStatus(id, posData);
        } catch (Exception e) {
            _logger.log(Level.SEVERE, null, e);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
