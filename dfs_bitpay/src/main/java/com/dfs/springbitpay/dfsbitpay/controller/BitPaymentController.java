/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dfs.springbitpay.dfsbitpay.controller;

import com.dfs.springbitpay.dfsbitpay.logger.BitPayException;
import com.dfs.springbitpay.dfsbitpay.model.BitPay;
import com.dfs.springbitpay.dfsbitpay.model.Invoice;
import com.dfs.springbitpay.dfsbitpay.model.User;
import com.dfs.springbitpay.dfsbitpay.service.UserService;
import java.io.File;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Yixiang
 */
@Controller
public class BitPaymentController {
    
    @Value("${bitpay.hostURL}")
    private String hostURL;
    
    @Value("${bitpay.keyFilePath}")
    private String keyFilePath;

    private static final Logger _logger = Logger.getLogger(BitPaymentController.class.getName());
    private BitPay bitpay;
    private Invoice basicInvoice;
    private final static double BTC_EPSILON = .000000001;
    private final static double EPSILON = .001;
    

    private static String clientName = "BitPay Java Library Tester";
    private static String pairingCode;
    private static String refundInvoiceId = null;
    private static URI myKeyFile;

    @Autowired
    private UserService userService;

    @PostMapping("/pay")
    public String pay(@RequestParam("price") String price, @RequestParam("id") String id, @RequestParam("currency") String currency,
    		@RequestParam("return_url") String return_url, @RequestParam("notify_url") String notify_url, @RequestParam("merchant_id") String merchant_id, ModelMap model) {
        
        boolean dumpOut = false;
        Invoice invoice = new Invoice(Double.parseDouble(price), currency);
        invoice.setPosData(id + "," + merchant_id);
        invoice.setNotificationURL(notify_url);
        invoice.setFullNotifications(true);
        //invoice.setRedirectURL(hostURL + "/invoice/" + id);
        invoice.setRedirectURL(return_url + "?out_trade_no=" + id + "&total_fee=" + price + "&trade_status=" + "new");
        try {
            myKeyFile = new URI(keyFilePath); //if file exists, it will not overwrite
            clientName += " on " + java.net.InetAddress.getLocalHost();
            bitpay = new BitPay(myKeyFile, clientName, BitPay.BITPAY_TEST_URL);
            if (!bitpay.clientIsAuthorized(BitPay.FACADE_POS)) {
                pairingCode = bitpay.requestClientAuthorization(BitPay.FACADE_POS);
                // Signal the device operator that this client needs to be paired with a merchant account.
                _logger.info("Client is requesting POS facade access. Go to " + BitPay.BITPAY_TEST_URL + " and pair this client with your merchant account using the pairing code: " + pairingCode);
                dumpOut = true;
                //we already failed to authorize for a POS token, therefore we must sleep a bit to try to authorize for any other facade (rate limiter on the api side)
                Thread.sleep(10000);
            }

            if (!bitpay.clientIsAuthorized(BitPay.FACADE_MERCHANT)) {
                pairingCode = bitpay.requestClientAuthorization(BitPay.FACADE_MERCHANT);

                // Signal the device operator that this client needs to be paired with a merchant account.
                _logger.info("Client is requesting MERCHANT facade access. Go to " + BitPay.BITPAY_TEST_URL + " and pair this client with your merchant account using the pairing code: " + pairingCode);
                dumpOut = true;
            }

            if (dumpOut) {
                throw new BitPayException("Error: client is not authorized.");
            }

            basicInvoice = bitpay.createInvoice(invoice);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            User user = new User(id, basicInvoice.getId(), Double.parseDouble(price), dtf.format(now), basicInvoice.getStatus());
            userService.addUser(user);

            model.put("url", basicInvoice.getUrl());

        } catch (Exception ex) {
            _logger.log(Level.SEVERE, null, ex);
            model.put("error", ex.getMessage());
        } 	
        model.put("price", price);
        model.put("name", id);
        return "redirect:" + basicInvoice.getUrl();
    }

}
