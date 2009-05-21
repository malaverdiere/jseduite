<%-- 
    Document   : listAllAlarms
    Created on : 2009-5-19, 10:37:16
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
        <title>list all the alarms</title>
    </head>
    <body>
        <h1>you can get all the alarms</h1>
         <f:view>
            <h:form>
                <h:dataTable value="#{AlarmAdmin.allAlarmModel}" var="a" border="1">
            <h:column>
				<f:facet name="header">
					<h:outputText value="alarm id" />
				</f:facet>
				<h:outputText value="#{a.alarmId}" />
			</h:column>
            <h:column>
				<f:facet name="header">
					<h:outputText value="content" />
				</f:facet>
				<h:outputText value="#{a.alarmContent}" />
			</h:column>
             <h:column>
				<f:facet name="header">
					<h:outputText value="position" />
				</f:facet>
				<h:outputText value="#{a.position}" />
			</h:column>
            <h:column>
				<f:facet name="header">
					<h:outputText value="relevant breaks" />
				</f:facet>
                <h:dataTable value="#{AlarmAdmin.breakByAlarmModel}" var="b">
                     <h:column>
                    <h:outputText value="#{b.start}"/>
			       </h:column>
                    <h:column>
                    <h:outputText value="#{b.end}"/>
			       </h:column>
                   <h:column>
                    <h:outputText value="#{b.kind}"/>
			       </h:column>
                   <h:column>
                    <h:outputText value="#{b.day}"/>
			       </h:column>
                </h:dataTable>
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputText value="edit" />
				</f:facet>
                <h:commandLink action="#{AlarmAdmin.goToEdit}" value="edit"/>
			 </h:column>
			 <h:column>
				<f:facet name="header">
					<h:outputText value="delete" />
				</f:facet>
                <h:commandLink action="#{AlarmAdmin.deleteAlarm}" value="delete"/>
		     	</h:column>
                </h:dataTable>
                <br/>
                <h:commandButton action="addNewAlarm" value="add a new alarm"/>
                <br/><br/>
                <h:commandButton action="back" value="back"/>
                </h:form>
                </f:view>
    </body>
</html>