<%--
    Document   : addAlarm
    Created on : 2009-5-16, 14:50:38
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
        <title>Add a new alarm</title>
    </head>
    <body>
        <h1>add a new alarm</h1>
        <h2>you can choose the break to make links with alarms or choose nothing</h2>
         <f:view>
            <h:form>
               <h:messages/>
                <table>
                    <tr><td>choose breaks</td>
                <td><h:dataTable value="#{AlarmAdmin.allBreakModel}" var="a" border="1">
            <h:column>
				<f:facet name="header">
					<h:outputText value="start" />
				</f:facet>
				<h:outputText value="#{a.start}" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputText value="end" />
				</f:facet>
				<h:outputText value="#{a.end}" />
			</h:column>
			  <h:column>
				 <f:facet name="header">
					<h:outputText value="break type" />
				 </f:facet>
				 <h:outputText value="#{a.kind}" />
			   </h:column>
				<h:column>
				<f:facet name="header">
					<h:outputText value="Weekday" />
				</f:facet>
				<h:outputText value="#{a.day}" />
		    	</h:column>
                <h:column>
                <f:facet name="header">
					<h:outputText value="select" />
				</f:facet>
                <h:selectBooleanCheckbox  value="#{a.breakChecked}"/>
		    	</h:column>
            </h:dataTable></td></tr>
            <tr>
            <td><h:outputText value="Content: "/></td>
            <td> <h:inputText value="#{AlarmAdmin.content}" required="true"
            requiredMessage="the content can't be null">
                </h:inputText>
                </td></tr>
            <tr><td><h:outputText value="Position"/></td>
                <td><h:selectOneMenu value="#{AlarmAdmin.position}">
                <f:selectItem itemLabel="start" itemValue="start"/>
                <f:selectItem itemLabel="end" itemValue="end"/>
                </h:selectOneMenu>
            </td></tr>
            </table>
            <h:commandButton action="#{AlarmAdmin.addAlarm}" value="ok"/>
            &nbsp&nbsp<input type="button" value="back" onclick="window.document.location.href='listAllAlarms.jsf'"/>
            </h:form>
            </f:view>

    </body>
</html>
