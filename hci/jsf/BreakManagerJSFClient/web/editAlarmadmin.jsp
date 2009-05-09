<%-- 
    Document   : editAlarmadmin
    Created on : 2009-5-8, 23:06:35
    Author     : Administrator
--%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>edit an alarm</title>
    </head>
    <body>
         <f:view>
            <h:form>
             <h1>edit a new alarm</h1>
                <h:panelGrid  columns="2">
                    <f:facet name="footer">
                        <h:panelGroup>
                            <h:commandButton value="sumbit" action="#{alarmAdmin.updateAlarm}" />
                        </h:panelGroup>
                    </f:facet>
                    <h:outputText value="modify the content:" /><h:inputText id="content" value="#{alarmAdmin.editAlarm.alarmContent}" />
                    <br/>
                </h:panelGrid>
                   <h:commandButton action="back" value="back"/>
                </h:form>
            </f:view>
    </body>
</html>
