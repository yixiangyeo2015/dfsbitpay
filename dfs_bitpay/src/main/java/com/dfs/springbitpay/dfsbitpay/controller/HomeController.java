/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dfs.springbitpay.dfsbitpay.controller;

import com.dfs.springbitpay.dfsbitpay.model.BitPay;
import com.dfs.springbitpay.dfsbitpay.model.Rate;
import com.dfs.springbitpay.dfsbitpay.model.Rates;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Value("${bitpay.keyFilePath}")
    private String keyFilePath;
    
    private String message = "Will you like to check out now?"; //cant overwrite application variable

    private static final Logger _logger = Logger.getLogger(BitPaymentController.class.getName());
    private BitPay bitpay;
    private static URI myKeyFile;
    private static String clientName = "BitPay Java Library Tester";

    @GetMapping("/")
    public String index(Map<String, Object> model) {
//        try {
//            myKeyFile = new URI(keyFilePath); //if file exists, it will not overwrite
//            clientName += " on " + java.net.InetAddress.getLocalHost();
//            bitpay = new BitPay(myKeyFile, clientName, BitPay.BITPAY_TEST_URL);
//            Rates rates = bitpay.getRates();
//            List<Rate> ratesList = rates.getRates();
//            model.put("rates", ratesList);
//        } catch (Exception ex) {
//            _logger.log(Level.SEVERE, null, ex);
//        }
        model.put("date", new Date());
        model.put("message", message);
        return "index";
    }

}
