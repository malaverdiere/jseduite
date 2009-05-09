<%-- 
    Document   : addAlarmsAdmin
    Created on : 2009-5-8, 22:28:58
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
        <title>add a new alarm</title>
    </head>
    <body>
        <f:view>
            <h:form>
             <h1>add a new alarm</h1>
                <h:panelGrid  columns="2">
                    <f:facet name="footer">
                        <h:panelGroup>
                            <h:commandButton value="sumbit" action="#{alarmAdmin.addNewAlarm}" />
                        </h:panelGroup>
                    </f:facet>
                    <h:outputText value="choose a break:" />
                    <h:selectOneMenu value="#{alarmAdmin.breakId}">
                        <f:selectItems value="#{alarmAdmin.blist}"/>
                    </h:selectOneMenu>
                    <h:outputText value="alarm content:" /><h:inputText id="content" value="#{alarmAdmin.alarmContent}" />
                    <h:commandButton action="back" value="back"/>
                </h:panelGrid>
                </h:form>
            </f:view>
    </body>
</html>
