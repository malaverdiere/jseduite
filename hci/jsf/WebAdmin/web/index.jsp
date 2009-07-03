<%--
    Document   : index.jsp
    Created on : 26 juin 2009, 16:07:04
    Author     : Steve Colombié
--%>

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
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title><h:outputText value="#{bundle.TITLE}" /></title>
        </head>
        <body>
            <h:outputLink value="breakingnews/listing.jsf">
                <h:outputText value="#{bundle.BREAKINGNEWS}"/>
            </h:outputLink>
        </body>
    </html>
</f:view>
