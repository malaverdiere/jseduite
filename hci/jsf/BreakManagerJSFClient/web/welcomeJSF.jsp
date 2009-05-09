<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>

    
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

<f:view>
    <h:form>
    <h1>BreakTimeToday</h1>
        <h:dataTable value="#{BreakManagedBean.tmodel}" var="b" border="1">
		<h:column>
				<f:facet name="header">
					<h:outputText value="promo" />
				</f:facet>
				<h:outputText value="#{b.promo}" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputText value="start" />
				</f:facet>
				<h:outputText value="#{b.start}" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputText value="end" />
				</f:facet>
				<h:outputText value="#{b.end}" />
			</h:column>
					<h:column>
				<f:facet name="header">
					<h:outputText value="break type" />
				</f:facet>
				<h:outputText value="#{b.kind}" />
			</h:column>
				<h:column>
				<f:facet name="header">
					<h:outputText value="Weekday" />
				</f:facet>
				<h:outputText value="#{b.day}" />
			</h:column>
    </h:dataTable>
            <hr/>
             <h1>Searchforbreak</h1>
        <h:outputText  value="Weekday:" />
             <h:selectOneMenu value="#{BreakManagedBean.day}">
                 <f:selectItem itemValue="Monday" itemLabel="Monday1"/>
                  <f:selectItem itemValue="Tuesday" itemLabel="Tuesday"/>
                  <f:selectItem itemValue="Wednesday" itemLabel="Wednesday"/>
                  <f:selectItem itemValue="Thursday" itemLabel="Thursday"/>
                  <f:selectItem itemValue="Friday" itemLabel="Friday"/>
             </h:selectOneMenu>
        <h:commandButton action="list" value="ok" />
        <hr/>
          <h1>List All Break</h1>
        <h:commandButton action="listAllBreak" value="ok" />
        <hr/>
         <h1>manage the alarm</h1>
         <h:commandButton action="manageAlarm" value="enter alarm"/>
         <h1>operate the alarms</h1>
         <h:commandButton action="operateAlarm" value="operate alarm"/>
</h:form>
</f:view>
 </body>
</html>


