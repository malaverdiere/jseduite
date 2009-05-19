<%-- 
    Document   : Welcome
    Created on : 2009-5-13, 11:55:11
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <h1>Welcome!</h1>
        <br/>
        <f:view>
        <h:form>
           <h2>Show breaks and relevant alarms</h2>
           <h:commandButton action="breakUse" value="show break"/>
           <h2>Break and alarm admin</h2>
           <h:commandButton action="breakAdmin" value="admin break and alarm"/>
        </h:form>
        </f:view>
    </body>
</html>
