<%-- 
    Document   : operateAlarm
    Created on : 2009-5-8, 22:15:14
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
        <f:view>
           <h:form>
               <h:commandLink action="showAllAlarms" value="list all the alarms"/>
               <br/>
               <h:commandLink action="addNewAlarm" value="add a new alarm"/>
               <br/>
                 <h:commandButton action="back" value="back"/>
           </h:form>
        </f:view>
    </body>
</html>
