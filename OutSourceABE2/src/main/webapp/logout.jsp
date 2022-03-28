<%-- 
    Document   : logout
    Created on : 23 Nov, 2015, 5:47:13 PM
    Author     : Sai Krishna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            alert ( "Your Account is Logout" );
        </script>
    </head>
    <body>
        <%
        session.invalidate();    
       response.sendRedirect("index.jsp");
        %>
    </body>
</html>
