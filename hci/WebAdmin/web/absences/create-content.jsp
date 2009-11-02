<%-- 
    Document   : create-content
    Created on : 10 août 2009
    Author     : Steve Colombié
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.ABSENCES_CREATE}" /></h1>

    <h:form>
        <h:panelGrid columns="2" >
            <h:outputText value="#{bundle.ABSENCES_TEACHER}"/>
            <h:panelGroup>
                <h:inputText id="teacher" value="#{AbsenceManagedBean.cAbsence.teacher}" required="true" requiredMessage="#{bundle.ABSENCES_TEACHER_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputText>
                <h:message for="teacher" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.ABSENCES_FROM}"/>
            <h:panelGroup>
                <h:inputText id="from" size="10" value="#{AbsenceManagedBean.from}" required="true" requiredMessage="#{bundle.ABSENCES_FROM_REQUIRED}">
                    <f:convertDateTime type="date" pattern="#{bundle.FORM_DATE_PATTERN}"/>
                </h:inputText>
                <h:outputText value=" "/>
                <h:inputText id="fromTime"  size="5" value="#{AbsenceManagedBean.fromTime}" required="true" requiredMessage="#{bundle.ABSENCES_FROMTIME_REQUIRED}">
                    <f:convertDateTime type="time" pattern="#{bundle.FORM_TIME_PATTERN}"/>
                </h:inputText>
                <h:message for="from" errorClass="errorMessage"/>
                <h:message for="fromTime" errorClass="errorMessage"/>
            </h:panelGroup>


            <h:outputText value="#{bundle.ABSENCES_UNTIL}"/>
            <h:panelGroup>
                <h:inputText id="until" size="10" value="#{AbsenceManagedBean.until}" required="true" requiredMessage="#{bundle.ABSENCES_UNTIL_REQUIRED}">
                    <f:convertDateTime type="date" pattern="#{bundle.FORM_DATE_PATTERN}"/>
                </h:inputText>
                <h:outputText value=" "/>
                <h:inputText id="untilTime"  size="5" value="#{AbsenceManagedBean.untilTime}" required="true" requiredMessage="#{bundle.ABSENCES_UNTILTIME_REQUIRED}">
                    <f:convertDateTime type="time" pattern="#{bundle.FORM_TIME_PATTERN}"/>
                </h:inputText>
                <h:message for="until" errorClass="errorMessage"/>
                <h:message for="untilTime" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.ABSENCES_REASON}"/>
            <h:panelGroup>
                <h:inputText id="reason" value="#{AbsenceManagedBean.cAbsence.reason}" required="true" requiredMessage="#{bundle.ABSENCES_REASON_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputText>
                <h:message for="reason" errorClass="errorMessage"/>
            </h:panelGroup>
        </h:panelGrid>

        <h:commandButton value="#{bundle.FORM_OK}" action="#{AbsenceManagedBean.create}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{AbsenceManagedBean.cancel}" immediate="true"/>
    </h:form>
</f:subview>

