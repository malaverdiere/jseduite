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
            <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/resources/stylesheet.css' />
            <title><h:outputText value="#{bundle.TITLE}" /></title>
        </head>
        <body>
            <div class="body">
                <div class="header">
                    <%@include file="layout/header.jsp" %>
                </div>

                <div class="center">
                    <div class="menu">
                        <%@include file="layout/menu.jsp" %>
                    </div>
                    <div class="content">
                        <h:outputText value="Link to every services + images"/>
                    </div>
                </div>

                <div class="footer">
                    <%@include file="layout/footer.jsp" %>
                </div>
            </div>
        </body>
    </html>
</f:view>
