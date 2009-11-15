<%--
    Document   : credits.jsp
    Created on : 27 août 2009
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
                        <h1><h:outputText value="#{bundle.CREDITS}" /></h1>

                        <h:outputText value="#{bundle.CREDITS_CONTACTS}" styleClass="subtitle"/>
                        <h:panelGrid columns="2">
                            <h:outputLink value="mailto:#{bundle.CREDITS_SC_MAIL}" styleClass="contact">
                                <h:outputText value="#{bundle.CREDITS_SC_NAME}"/>
                            </h:outputLink>
                            <h:outputText value="#{bundle.CREDITS_SC_POSITION}"/>

                            <h:outputLink value="mailto:#{bundle.CREDITS_SM_MAIL}" styleClass="contact">
                                <h:outputText value="#{bundle.CREDITS_SM_NAME}"/>
                            </h:outputLink>
                            <h:outputText value="#{bundle.CREDITS_SM_POSITION}"/>

                            <h:outputLink value="mailto:#{bundle.CREDITS_MBF_MAIL}" styleClass="contact">
                                <h:outputText value="#{bundle.CREDITS_MBF_NAME}"/>
                            </h:outputLink>
                            <h:outputText value="#{bundle.CREDITS_MBF_POSITION}"/>
                        </h:panelGrid>

                        <h:outputText value="#{bundle.CREDITS_PARTNERS}" styleClass="subtitle"/>
                        <h:panelGrid columns="3">
                            <h:outputLink value="#{bundle.CREDITS_CNRS_LINK}" title="#{bundle.CREDITS_CNRS_TITLE}">
                                <h:graphicImage value="./resources/images/credits/cnrs.jpg" alt="#{bundle.CREDITS_CNRS_TITLE}"/>
                            </h:outputLink>

                            <h:outputLink value="#{bundle.CREDITS_I3S_LINK}" title="#{bundle.CREDITS_I3S_TITLE}">
                                <h:graphicImage value="./resources/images/credits/i3s.jpg" alt="#{bundle.CREDITS_I3S_TITLE}"/>
                            </h:outputLink>

                            <h:outputLink value="#{bundle.CREDITS_UNICE_LINK}" title="#{bundle.CREDITS_UNICE_TITLE}">
                                <h:graphicImage value="./resources/images/credits/unsa.jpg" alt="#{bundle.CREDITS_UNICE_TITLE}"/>
                            </h:outputLink>
                        </h:panelGrid>
                        <h:panelGrid columns="2">
                            <h:panelGroup styleClass="alignCenter">
                            <h:outputLink value="#{bundle.CREDITS_MODALIS_LINK}" title="#{bundle.CREDITS_MODALIS_TITLE}">
                                <h:graphicImage value="./resources/images/credits/modalis.png" alt="#{bundle.CREDITS_MODALIS_TITLE}" />
                            </h:outputLink>
                            </h:panelGroup>
                            
                            <h:panelGroup styleClass="alignCenter">
                            <h:outputLink value="#{bundle.CREDITS_RAINBOW_LINK}" title="#{bundle.CREDITS_RAINBOW_TITLE}">
                                <h:graphicImage value="./resources/images/credits/rainbow.jpg" alt="#{bundle.CREDITS_RAINBOW_TITLE}"/>
                            </h:outputLink>
                            </h:panelGroup>
                        </h:panelGrid>

                        <h:outputText value="#{bundle.CREDITS_POWERED}" styleClass="subtitle"/>
                        <h:outputLink value="#{bundle.CREDITS_SILK_ICONS_LINK}" title="#{bundle.CREDITS_SILK_ICONS}" target="_blank" styleClass="website">
                            <h:outputText value="#{bundle.CREDITS_SILK_ICONS}"/>
                        </h:outputLink>
                        <h:outputText value=" #{bundle.BY} #{bundle.CREDITS_SILK_ICONS_AUTHOR}"/>
                    </div>
                </div>

                <div class="footer">
                    <%@include file="layout/footer.jsp" %>
                </div>
            </div>
        </body>
    </html>
</f:view>
