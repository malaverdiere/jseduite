<%-- 
    Document   : menu
    Created on : 6 juil. 2009, 14:15:00
    Author     : Steve Colombié
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="menu">
    <h:panelGroup styleClass="menuItem">
        <h:outputLink value="#{request.contextPath}">
            <h:outputText value="#{bundle.HOME}"/>
        </h:outputLink>
    </h:panelGroup>

    <h:panelGroup styleClass="menuCategory">
        <h:outputText value="#{bundle.MENU_NEWS}" styleClass="menuTitle"/>
        <h:panelGroup styleClass="menuItem">
            <h:outputLink value="#{request.contextPath}/internalnews/listing.jsf">
                <h:outputText value="#{bundle.INTERNALNEWS}"/>
            </h:outputLink>
        </h:panelGroup>
        <h:panelGroup styleClass="menuItem">
            <h:outputLink value="#{request.contextPath}/breakingnews/listing.jsf">
                <h:outputText value="#{bundle.BREAKINGNEWS}"/>
            </h:outputLink>
        </h:panelGroup>
    </h:panelGroup>

    <h:panelGroup styleClass="menuCategory">
        <h:outputText value="#{bundle.MENU_PICTURES}" styleClass="menuTitle"/>
        <h:panelGroup styleClass="menuItem">
            <h:outputLink value="#{request.contextPath}/picturealbum/listing.jsf">
                <h:outputText value="#{bundle.PICTUREALBUM}"/>
            </h:outputLink>
        </h:panelGroup>
    </h:panelGroup>

    <h:panelGroup styleClass="menuCategory">
        <h:outputText value="#{bundle.MENU_SCHOOLLIFE}" styleClass="menuTitle"/>
        <h:panelGroup styleClass="menuItem">
<%--            <h:outputLink value="#{request.contextPath}/promotions/listing.jsf">--%>
                <h:outputText value="#{bundle.PROMOTIONS}"/>
<%--            </h:outputLink>--%>
        </h:panelGroup>
        <h:panelGroup styleClass="menuItem">
<%--            <h:outputLink value="#{request.contextPath}/teacherabsences/listing.jsf">--%>
                <h:outputText value="#{bundle.TEACHERABSENCES}"/>
<%--            </h:outputLink>--%>
        </h:panelGroup>

        <h:panelGroup styleClass="menuItem">
<%--            <h:outputLink value="#{request.contextPath}/summonings/listing.jsf">--%>
                <h:outputText value="#{bundle.SUMMONINGS}"/>
<%--            </h:outputLink>--%>
        </h:panelGroup>
    </h:panelGroup>

    <h:panelGroup styleClass="menuItem">
        <h:outputLink value="#{request.contextPath}">
            <h:outputText value="#{bundle.CREDITS}"/>
        </h:outputLink>
    </h:panelGroup>
</f:subview>
