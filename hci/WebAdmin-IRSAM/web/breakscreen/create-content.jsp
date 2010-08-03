<%-- 
    Document   : create-content
    Created on : 2 août 2010, 11:30:00
    Author     : desclaux Christophe
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.BREAKSCREEN_CREATE}" /></h1>

    <h:form id="form">
        <h:panelGrid columns="2" >
            <h:outputText value="#{bundle.BREAKSCREEN_DAYS}"/>
            <h:selectManyCheckbox id="days" value="#{BreakScreenManagedBean.selectedDays}" required="true" requiredMessage="#{bundle.BREAKSCREEN_DAYS_REQUIRED}">
                <f:selectItems value="#{BreakScreenManagedBean.days}"/>
                <h:message for="days" errorClass="errorMessage"/>
            </h:selectManyCheckbox>

            <h:outputText value="#{bundle.BREAKSCREEN_START}"/>
            <h:panelGroup>
                <h:inputText id="start"  size="5" value="#{BreakScreenManagedBean.startDate}" required="true" requiredMessage="#{bundle.BREAKSCREEN_START_REQUIRED}">
                    <f:convertDateTime type="time" pattern="#{bundle.FORM_TIME_PATTERN}"/>
                </h:inputText>
                <h:message for="start" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.BREAKSCREEN_END}"/>
            <h:panelGroup>
                <h:inputText id="end"  size="5" value="#{BreakScreenManagedBean.endDate}" required="true" requiredMessage="#{bundle.BREAKSCREEN_END_REQUIRED}">
                    <f:convertDateTime type="time" pattern="#{bundle.FORM_TIME_PATTERN}"/>
               </h:inputText>
                <h:message for="end" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.BREAKSCREEN_BUILDING}"/>
            <h:panelGroup>
                <h:selectOneMenu id="buildings" value="#{BreakScreenManagedBean.cBreakScreen.building}" onchange="otherDetection()">
                    <f:selectItems value="#{BreakScreenManagedBean.buildings}" />
                    <h:message for="buildings" errorClass="errorMessage"/>
                </h:selectOneMenu>
                <h:outputText value=" "/>
                <h:inputText id="building" value="#{BreakScreenManagedBean.alterBuilding}" required="true" requiredMessage="#{bundle.BREAKSCREEN_BUILDING_REQUIRED}">
                    <f:validateLength maximum="10" />
                </h:inputText>
               <h:message for="building" errorClass="errorMessage"/>
            </h:panelGroup>
            <h:outputText value="#{bundle.BREAKSCREEN_CONTENT}"/>
            <h:inputText id="content" value="#{BreakScreenManagedBean.content}" required="true" requiredMessage="#{bundle.BREAKSCREEN_CONTENT_REQUIRED}">
                <f:validateLength maximum="255" />
                <h:message for="content" errorClass="errorMessage"/>
            </h:inputText>

        </h:panelGrid>

        <h:commandButton value="#{bundle.FORM_OK}" action="#{BreakScreenManagedBean.create}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{BreakScreenManagedBean.cancel}" immediate="true"/>
    </h:form>
</f:subview>

