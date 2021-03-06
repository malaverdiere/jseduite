<%-- 
    Document   : create-content
    Created on : 20 juil. 2009
    Author     : Steve Colombi�
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.BREAKTIME_CREATE}" /></h1>

    <h:form id="form">
        <h:panelGrid columns="2" >
            <h:outputText value="#{bundle.BREAKTIME_DAYS}"/>
            <h:selectManyCheckbox id="days" value="#{BreakTimeManagedBean.selectedDays}" required="true" requiredMessage="#{bundle.BREAKTIME_DAYS_REQUIRED}">
                <f:selectItems value="#{BreakTimeManagedBean.days}"/>
                <h:message for="days" errorClass="errorMessage"/>
            </h:selectManyCheckbox>

            <h:outputText value="#{bundle.BREAKTIME_START}"/>
            <h:panelGroup>
                <h:inputText id="start"  size="5" value="#{BreakTimeManagedBean.startDate}" required="true" requiredMessage="#{bundle.BREAKTIME_START_REQUIRED}">
                    <f:convertDateTime type="time" pattern="#{bundle.FORM_TIME_PATTERN}"/>
                </h:inputText>
                <h:message for="start" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.BREAKTIME_END}"/>
            <h:panelGroup>
                <h:inputText id="end"  size="5" value="#{BreakTimeManagedBean.endDate}" required="true" requiredMessage="#{bundle.BREAKTIME_END_REQUIRED}">
                    <f:convertDateTime type="time" pattern="#{bundle.FORM_TIME_PATTERN}"/>
               </h:inputText>
                <h:message for="end" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.BREAKTIME_PROMOTIONS}"/>
            <h:selectManyCheckbox layout="pageDirection" id="promos" value="#{BreakTimeManagedBean.selectedPromos}" required="true" requiredMessage="#{bundle.BREAKTIME_PROMOTIONS_REQUIRED}" styleClass="checkboxes">
                <f:selectItems value="#{BreakTimeManagedBean.promos}"/>
                <h:message for="promos" errorClass="errorMessage"/>
            </h:selectManyCheckbox>

            <h:outputText value="#{bundle.BREAKTIME_BUILDING}"/>
            <h:panelGroup>
                <h:selectOneMenu id="buildings" value="#{BreakTimeManagedBean.cBreakTime.building}" onchange="otherDetection()">
                    <f:selectItems value="#{BreakTimeManagedBean.buildings}" />
                    <h:message for="buildings" errorClass="errorMessage"/>
                </h:selectOneMenu>
                <h:outputText value=" "/>
                <h:inputText id="building" value="#{BreakTimeManagedBean.alterBuilding}" required="true" requiredMessage="#{bundle.BREAKTIME_BUILDING_REQUIRED}">
                    <f:validateLength maximum="10" />
                </h:inputText>
               <h:message for="building" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.BREAKTIME_KIND}"/>
            <h:selectOneMenu id="kind" value="#{BreakTimeManagedBean.cBreakTime.kind}">
                <f:selectItems value="#{BreakTimeManagedBean.kinds}" />
                <h:message for="kind" errorClass="errorMessage"/>
            </h:selectOneMenu>

        </h:panelGrid>

        <h:commandButton value="#{bundle.FORM_OK}" action="#{BreakTimeManagedBean.create}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{BreakTimeManagedBean.cancel}" immediate="true"/>
    </h:form>
</f:subview>

