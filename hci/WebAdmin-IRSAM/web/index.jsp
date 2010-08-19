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
                        <h1><h:outputText value="#{bundle.HOME}" /></h1>
                        <h:outputText value="#{bundle.MENU_NEWS}" styleClass="homeTitle newsTitle"/>
                        <h:panelGrid columns="2">
                            <h:panelGroup><div class="homeColumn">
                                <h:panelGroup styleClass="menuItem">
                                    <h:outputLink value="#{request.contextPath}/internalnews/listing.jsf">
                                        <h:outputText value="#{bundle.INTERNALNEWS}"/>
                                    </h:outputLink>
                                </h:panelGroup>
                                <h:outputText value="#{bundle.INTERNALNEWS_DESCRIPTION}"/>
                            </div></h:panelGroup>
                            <h:panelGroup><div class="homeColumn">
                                <h:panelGroup styleClass="menuItem">
                                    <h:outputLink value="#{request.contextPath}/internalnews/listing.jsf">
                                        <h:outputText value="#{bundle.PICTOGRAMS}"/>
                                    </h:outputLink>
                                </h:panelGroup>
                                <h:outputText value="#{bundle.PICTOGRAMS_DESCRIPTION}"/>
                            </div></h:panelGroup>
                        </h:panelGrid>

                        <h:outputText value="#{bundle.MENU_RESTAURATION}" styleClass="homeTitle restaurationTitle"/>
                        <h:panelGrid columns="2">
                            <h:panelGroup><div class="homeColumn">
                                <h:panelGroup styleClass="menuItem">
                                    <h:outputLink value="#{request.contextPath}/courses/listing.jsf">
                                        <h:outputText value="#{bundle.COURSE}"/>
                                    </h:outputLink>
                                </h:panelGroup>
                                <h:outputText value="#{bundle.COURSE_DESCRIPTION}"/>
                            </div></h:panelGroup>
                            <h:panelGroup><div class="homeColumn">
                                <h:panelGroup styleClass="menuItem">
                                    <h:outputLink value="#{request.contextPath}/menus/listing.jsf">
                                        <h:outputText value="#{bundle.MENU}"/>
                                    </h:outputLink>
                                </h:panelGroup>
                                <h:outputText value="#{bundle.MENU_DESCRIPTION}"/>
                            </div></h:panelGroup>
                        </h:panelGrid>

                        <h:outputText value="#{bundle.MENU_PICTURES}" styleClass="homeTitle picturesTitle"/>
                        <h:panelGrid columns="2">
                            <h:panelGroup><div class="homeColumn">
                                <h:panelGroup styleClass="menuItem">
                                    <h:outputLink value="#{request.contextPath}/picturealbum/listing.jsf">
                                        <h:outputText value="#{bundle.PICTUREALBUM}"/>
                                    </h:outputLink>
                                </h:panelGroup>
                                <h:outputText value="#{bundle.PICTUREALBUM_DESCRIPTION}"/>
                            </div></h:panelGroup>
                        </h:panelGrid>

                        <h:outputText value="#{bundle.MENU_SCHOOLLIFE}" styleClass="homeTitle schoollifeTitle"/>
                        <h:panelGrid columns="2">

                            <h:panelGroup><div class="homeColumn">
                                <h:panelGroup styleClass="menuItem">
                                    <h:outputLink value="#{request.contextPath}/breakscreen/listing.jsf">
                                        <h:outputText value="#{bundle.BREAKSCREEN}"/>
                                    </h:outputLink>
                                </h:panelGroup>
                                <h:outputText value="#{bundle.BREAKSCREEN_DESCRIPTION}"/>
                            </div></h:panelGroup>
                            <h:panelGroup><div class="homeColumn">
                                <h:panelGroup styleClass="menuItem">
                                    <h:outputLink value="#{request.contextPath}/breaktime/listing.jsf">
                                        <h:outputText value="#{bundle.BREAKTIME}"/>
                                    </h:outputLink>
                                </h:panelGroup>
                                <h:outputText value="#{bundle.BREAKTIME_DESCRIPTION}"/>
                            </div></h:panelGroup>
                            <h:panelGroup><div class="homeColumn">
                                <h:panelGroup styleClass="menuItem">
                                    <h:outputLink value="#{request.contextPath}/alarms/listing.jsf">
                                        <h:outputText value="#{bundle.ALARM}"/>
                                    </h:outputLink>
                                </h:panelGroup>
                                <h:outputText value="#{bundle.ALARM_DESCRIPTION}"/>
                            </div></h:panelGroup>
                            <h:panelGroup><div class="homeColumn">
                                <h:panelGroup styleClass="menuItem">
                                    <h:outputLink value="#{request.contextPath}/absences/listing.jsf">
                                        <h:outputText value="#{bundle.ABSENCES}"/>
                                    </h:outputLink>
                                </h:panelGroup>
                                <h:outputText value="#{bundle.ABSENCES_DESCRIPTION}"/>
                            </div></h:panelGroup>
                            <h:panelGroup><div class="homeColumn">
                                <h:panelGroup styleClass="menuItem">
                                    <h:outputLink value="#{request.contextPath}/promotions/listing.jsf">
                                        <h:outputText value="#{bundle.PROMOTION}"/>
                                    </h:outputLink>
                                </h:panelGroup>
                                <h:outputText value="#{bundle.PROMOTION_DESCRIPTION}"/>
                            </div></h:panelGroup>
                            
                        </h:panelGrid>

                        <h:outputText value="#{bundle.MENU_FEEDS}" styleClass="homeTitle feedsTitle"/>
                        <h:panelGrid columns="2">
                            <h:panelGroup><div class="homeColumn">
                                <h:panelGroup styleClass="menuItem">
                                    <h:outputLink value="#{request.contextPath}/feedregistry/listing.jsf">
                                        <h:outputText value="#{bundle.FEEDREGISTRY}"/>
                                    </h:outputLink>
                                </h:panelGroup>
                                <h:outputText value="#{bundle.FEEDREGISTRY_DESCRIPTION}"/>
                            </div></h:panelGroup>
                        </h:panelGrid>

                        <h:outputText value="#{bundle.MENU_ADMIN}" styleClass="homeTitle adminTitle"/>
                        <h:panelGrid columns="2" styleClass="homeColumn">
                            <h:panelGroup><div class="homeColumn">
                                <h:panelGroup styleClass="menuItem">
                                    <h:outputLink value="#{request.contextPath}/devices/listing.jsf">
                                        <h:outputText value="#{bundle.DEVICES}"/>
                                    </h:outputLink>
                                </h:panelGroup>
                                <h:outputText value="#{bundle.DEVICES_DESCRIPTION}"/>
                            </div></h:panelGroup>
                            <h:panelGroup><div class="homeColumn">
                                <h:panelGroup styleClass="menuItem">
                                    <h:outputLink value="#{request.contextPath}/partnerkeys/listing.jsf">
                                        <h:outputText value="#{bundle.PARTNERKEYS}"/>
                                    </h:outputLink>
                                </h:panelGroup>
                                <h:outputText value="#{bundle.PARTNERKEYS_DESCRIPTION}"/>
                            </div></h:panelGroup>
                            <h:panelGroup><div class="homeColumn">
                                <h:panelGroup styleClass="menuItem">
                                    <h:outputLink value="#{request.contextPath}/errorlogger/listing.jsf">
                                        <h:outputText value="#{bundle.ERRORLOGGER}"/>
                                    </h:outputLink>
                                </h:panelGroup>
                                <h:outputText value="#{bundle.ERRORLOGGER_DESCRIPTION}"/>
                            </div></h:panelGroup>
                        </h:panelGrid>
                    </div>
                </div>

                <div class="footer">
                    <%@include file="layout/footer.jsp" %>
                </div>
            </div>
        </body>
    </html>
</f:view>
