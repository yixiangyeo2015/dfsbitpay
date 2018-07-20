<%-- 
    Document   : index
    Created on : May 15, 2018, 1:35:45 PM
    Author     : Yixiang
--%>

<%@page import="com.dfs.springbitpay.dfsbitpay.model.Rate"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout Page</title>
    </head>
    <body>
        <h1>Checkout Page!</h1>
        <p>Date: ${date}</p>
        <p>Message: ${message}</p>
       <!--   <form action = "https://dfsbitpay.herokuapp.com/pay" method = "POST">-->
       <form action = "https://dfsbitpay.herokuapp.com/pay" method = "POST">
            Order Id: <input type = "text" name = "id" value="Order">
            <br />
            Price: <input type = "text" name = "price" />
            <br />
            Currency: <input type = "text" name = "currency" value ="USD" />
            <br />
            <input type = "submit" value = "Submit" />
            <input type = "hidden" value = "https://dfsbitpay.herokuapp.com/webhook" name = "notify_url" />
            <input type = "hidden" value = "merchant_id_placeholder" name = "merchant_id"/>
            <input type = "hidden" value = "http://localhost:8080" name = "return_url" />
        </form>
        <br><br>
        <table border = "1">
            <%
                if (request.getAttribute("rates") != null) {
                    List<Rate> rates = (List<Rate>) request.getAttribute("rates");
                    for (Rate r : rates) {
                        out.println("<tr>");
                        out.println("<td>" + r.getName() + "</td>");
                        out.println("<td>" + r.getCode() + "</td>");
                        out.println("<td>" + r.getValue() + "</td>");
                        out.println("</tr>");
                    }
                }


            %>
        </table>
    </body>
</html>
