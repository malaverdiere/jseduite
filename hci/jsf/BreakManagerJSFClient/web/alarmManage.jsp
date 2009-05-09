<%-- 
    Document   : alarmManage
    Created on : 2009-5-8, 20:06:55
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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>this is the alarm management page</h1>
        <f:view>
            <h:form>
                <h:commandLink action="listAllAlarm" value="list all alarms"/><br/>
                <h:commandLink action="alarmbybinding" value="get a special alarm by break id"/><br/>
                <h:commandButton action="back" value="back"/>
            </h:form>
        </f:view>
    </body>
</html>
