<%-- 
    Document   : listBreak
    Created on : 2009-5-6, 10:53:34
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
        <title>JSP Page</title>
    </head>
    <body>
    <f:view>
        <h1>Break Time for <h:outputText value="#{BreakManagedBean.day}" /></h1>
      
       <h:form>
      <h:dataTable value="#{BreakManagedBean.model}" var="b" border="1">
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

         <h:commandButton action="home" value="back" />

            </h:form>
        </f:view>
    </body>
</html>
