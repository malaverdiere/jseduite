<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>
<f:view>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>jSeduite Administration Interface</title>
    </head>
    <body>
        <h1><h:outputText value="My JavaServer Faces Page" /></h1>
        <h:dataTable value="#{BreakingNewsManagedBean.breakingNews}" var="iterator">
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Author" />
                </f:facet>
                <h:outputText value="#{iterator.author}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Stamp" />
                </f:facet>
                <h:outputText value="#{iterator.stamp}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Content" />
                </f:facet>
                <h:outputText value="#{iterator.content}"/>
            </h:column>
        </h:dataTable>
    </body>
</html>
</f:view>
