<%-- 
    Document   : alarmbybinding
    Created on : 2009-5-8, 20:31:35
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
        <h1>You can get the alarm by break id</h1>
        <f:view>
         <h:form>
             <h:outputText value="You can choose the number to get an alarm"/>&nbsp&nbsp
             <h:selectOneMenu value="#{alarmManage.breakId}">
               <f:selectItems value="#{alarmManage.blist}"/>
             </h:selectOneMenu>
             <h:commandButton action="showAlarm" value="show"/><br/>
             <h:commandButton action="back" value="back"/>
         </h:form>
        </f:view>
    </body>
</html>
