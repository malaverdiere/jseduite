<%-- 
    Document   : breakAdmin
    Created on : 2009-5-13, 17:08:50
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
        <title>admin breaks</title>
    </head>
    <body>
        <h1>you can administer the breaks </h1>
        <f:view>
        <h:form>
            <hr/>
           <h2>List all the breaks</h2>
           <h:commandButton action="listAllBreaks" value="list all breaks"/>
           <h2>List all the alarms</h2>
           <h:commandButton action="listAllAlarms" value="list all alarms"/>
           <br/>
           <br/>
           <h:commandButton action="back" value="back"/>
        </h:form>
        </f:view>
    </body>
</html>
