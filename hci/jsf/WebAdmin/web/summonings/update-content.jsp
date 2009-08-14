<%-- 
    Document   : update-content
    Created on : 14 août 2009
    Author     : Steve Colombié
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.SUMMONINGS_UPDATE}" /></h1>

    <h:form>
        <h:panelGrid columns="2" >
            <h:outputText value="#{bundle.SUMMONINGS_STUDENT}"/>
            <h:panelGroup>
                <h:inputText id="student" value="#{SummoningManagedBean.uSummoning.student}" required="true" requiredMessage="#{bundle.SUMMONINGS_STUDENT_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputText>
                <h:message for="student" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.SUMMONINGS_PROMO}"/>
            <h:selectOneMenu id="promo" value="#{SummoningManagedBean.selectedPromo}" required="true" requiredMessage="#{bundle.SUMMONINGS_PROMO_REQUIRED}">
                <f:selectItems value="#{SummoningManagedBean.promos}"/>
                <h:message for="promo" errorClass="errorMessage"/>
            </h:selectOneMenu>

            <h:outputText value="#{bundle.SUMMONINGS_OWNER}"/>
            <h:panelGroup>
                <h:inputText id="owner" value="#{SummoningManagedBean.uSummoning.owner}" required="true" requiredMessage="#{bundle.SUMMONINGS_OWNER_REQUIRED}">
                    <f:validateLength maximum="50" />
                </h:inputText>
                <h:message for="owner" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.SUMMONINGS_DATE}"/>
            <h:panelGroup>
                <h:inputText id="date" value="#{SummoningManagedBean.date}" required="true" requiredMessage="#{bundle.SUMMONINGS_DATE_REQUIRED}">
                    <f:convertDateTime type="date" pattern="#{bundle.FORM_DATE_PATTERN}"/>
               </h:inputText>
                <h:message for="date" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.SUMMONINGS_TIME}"/>
            <h:panelGroup>
                <h:inputText id="time" value="#{SummoningManagedBean.time}" required="true" requiredMessage="#{bundle.SUMMONINGS_TIME_REQUIRED}">
                    <f:convertDateTime type="time" pattern="HH:mm"/>
               </h:inputText>
                <h:message for="time" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.SUMMONINGS_LEVEL}"/>
            <h:selectOneMenu id="level" value="#{SummoningManagedBean.uSummoning.level}" required="true" requiredMessage="#{bundle.SUMMONINGS_LEVEL_REQUIRED}">
                <f:selectItems value="#{SummoningManagedBean.levels}"/>
                <h:message for="level" errorClass="errorMessage"/>
            </h:selectOneMenu>

            <h:outputText value="#{bundle.SUMMONINGS_VALID}"/>
            <h:selectBooleanCheckbox value="#{SummoningManagedBean.uSummoning.valid}"/>

        </h:panelGrid>

        <h:commandButton value="#{bundle.FORM_OK}" action="#{SummoningManagedBean.update}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{SummoningManagedBean.cancel}" immediate="true"/>
    </h:form>
</f:subview>


