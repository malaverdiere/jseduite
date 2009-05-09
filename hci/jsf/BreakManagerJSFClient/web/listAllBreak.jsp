<%-- 
    Document   : listAllBreak
    Created on : 2009-5-7, 10:26:26
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
        <h1>Hello World!</h1><f:view>
            <h:form>
                       <h:dataTable value="#{break.model}" var="b" border="1">
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
            <h:column>
				<f:facet name="header">
					<h:outputText value="Edit" />
				</f:facet>
                <h:commandButton action="#{break.edit}" value="Edit" />
			</h:column>
            <h:column>
				<f:facet name="header">
					<h:outputText value="Delete" />
				</f:facet>
				<h:commandButton action="#{break.delete}" value="delete" />
			</h:column>
    </h:dataTable>

 <h1>BreakAdd</h1>

        <h:commandButton action="add" value="add break" />
     <h:commandButton action="home" value="back" />
            </h:form>
        </f:view>
    </body>
</html>
