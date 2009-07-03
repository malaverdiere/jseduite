<%--
    Document   : create
    Created on : 25 juin 2009, 15:57:12
    Author     : Steve Colombié
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/resources/stylesheet.css' />

            <title><h:outputText value="#{bundle.BREAKINGNEWS}" /></title>
        </head>
        <body>
            <h1><h:outputText value="#{bundle.BREAKINGNEWS_CREATE}" /></h1>

            <h:form>
                <h:panelGrid columns="2" >
                    <h:outputText value="#{bundle.BREAKINGNEWS_AUTHOR}"/>
                    <h:panelGroup>
                        <h:inputText id="author" value="#{BreakingNewsManagedBean.cBreakNew.author}" required="true" requiredMessage="#{bundle.BREAKINGNEWS_AUTHOR_REQUIRED}">
                            <f:validateLength maximum="50" />
                        </h:inputText>
                        <h:message for="author" errorClass="errorMessage"/>
                    </h:panelGroup>

                    <h:outputText value="#{bundle.BREAKINGNEWS_DATE}"/>
                    <h:panelGroup>
                        <h:inputText id="date" value="#{BreakingNewsManagedBean.date}" required="true" requiredMessage="#{bundle.BREAKINGNEWS_DATE_REQUIRED}">
                            <f:convertDateTime type="date" pattern="#{bundle.FORM_DATE_PATTERN}"/>
                       </h:inputText>
                        <h:message for="date" errorClass="errorMessage"/>
                    </h:panelGroup>

                    <h:outputText value="#{bundle.BREAKINGNEWS_TIME}"/>
                    <h:panelGroup>
                        <h:inputText id="time" value="#{BreakingNewsManagedBean.time}" required="true" requiredMessage="#{bundle.BREAKINGNEWS_TIME_REQUIRED}">
                            <f:convertDateTime type="time" pattern="HH:mm"/>
                       </h:inputText>
                        <h:message for="time" errorClass="errorMessage"/>
                    </h:panelGroup>

                    <h:outputText value="#{bundle.BREAKINGNEWS_CONTENT}"/>
                    <h:panelGroup>
                        <h:inputTextarea id="content" rows="5" value="#{BreakingNewsManagedBean.cBreakNew.content}" required="true" requiredMessage="#{bundle.BREAKINGNEWS_CONTENT_REQUIRED}">
                            <f:validateLength maximum="255" />
                        </h:inputTextarea>
                        <h:message for="content" errorClass="errorMessage"/>
                    </h:panelGroup>
                </h:panelGrid>
                
                <h:commandButton value="#{bundle.FORM_OK}" action="#{BreakingNewsManagedBean.create}"/>
                <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{BreakingNewsManagedBean.cancel}" immediate="true"/>
            </h:form>
        </body>
    </html>
</f:view>
