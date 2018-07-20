<%-- 
    Document   : result
    Created on : May 15, 2018, 2:22:45 PM
    Author     : Yixiang
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Page</title>
    </head>
    <body>
        <h1>${username} Invoices</h1>
        <br>
        <table border = "1">
            <%
                JSONArray invoices = (JSONArray) request.getAttribute("invoices");
                for (Object obj : invoices) {
                    JSONObject r = (JSONObject) obj;
                    out.println("<tr>");
                    out.println("<td>" + r.get("date") + "</td>");
                    out.println("<td>" + r.get("invoiceId") + "</td>");
                    out.println("<td>" + r.get("price") + "</td>");
                    out.println("<td>" + r.get("status") + "</td>");
                    out.println("</tr>");
                }
                
            %>
        </table>
        
    </body>
</html>
