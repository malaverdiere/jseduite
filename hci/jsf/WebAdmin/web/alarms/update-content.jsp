<%-- 
    Document   : update-content
    Created on : 27 juil. 2009
    Author     : Steve Colombié
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.ALARM_UPDATE}" /></h1>

    <h:form id="form">
        <h:panelGroup>
            <h:outputText value="#{bundle.ALARM_START}" styleClass="subtitle"/>

            <h:panelGrid columns="2" >
                <h:outputText value="#{bundle.ALARM_ACTIVATE}"/>
                <h:selectBooleanCheckbox id="startActivate" value="#{AlarmManagedBean.start}" onchange="startActivation()">
                    <h:message for="startActivate" errorClass="errorMessage"/>
                </h:selectBooleanCheckbox>

                <h:outputText value="#{bundle.ALARM_MESSAGE}"/>
                <h:panelGroup>
                    <h:inputTextarea id="startMessage" value="#{AlarmManagedBean.uAlarm.alarmStart.message}" required="true" requiredMessage="#{bundle.ALARM_MESSAGE_REQUIRED}">
                        <f:validateLength maximum="255" />
                   </h:inputTextarea>
                    <h:message for="startMessage" errorClass="errorMessage"/>
                </h:panelGroup>

                <h:outputText value="#{bundle.ALARM_SOUND}"/>
                <h:panelGroup>
                    <h:inputText id="startSound" value="#{AlarmManagedBean.uAlarm.alarmStart.sound}">
                        <f:validateLength maximum="255" />
                   </h:inputText>
                </h:panelGroup>
            </h:panelGrid>
        </h:panelGroup>

        <h:panelGroup>
            <h:outputText value="#{bundle.ALARM_ALMOST_END}" styleClass="subtitle"/>

            <h:panelGrid columns="2" >
                <h:outputText value="#{bundle.ALARM_ACTIVATE}"/>
                <h:selectBooleanCheckbox id="almostEndActivate" value="#{AlarmManagedBean.almostEnd}" onchange="almostEndActivation()">
                    <h:message for="almostEndActivate" errorClass="errorMessage"/>
                </h:selectBooleanCheckbox>

                <h:outputText value="#{bundle.ALARM_MESSAGE}"/>
                <h:panelGroup>
                    <h:inputTextarea id="almostEndMessage" value="#{AlarmManagedBean.uAlarm.alarmAlmostEnd.message}" required="true" requiredMessage="#{bundle.ALARM_MESSAGE_REQUIRED}">
                        <f:validateLength maximum="255" />
                   </h:inputTextarea>
                    <h:message for="almostEndMessage" errorClass="errorMessage"/>
                </h:panelGroup>

                <h:outputText value="#{bundle.ALARM_SOUND}"/>
                <h:panelGroup>
                    <h:inputText id="almostEndSound" value="#{AlarmManagedBean.uAlarm.alarmAlmostEnd.sound}">
                        <f:validateLength maximum="255" />
                   </h:inputText>
                </h:panelGroup>
            </h:panelGrid>
        </h:panelGroup>

        <h:panelGroup>
            <h:outputText value="#{bundle.ALARM_END}" styleClass="subtitle"/>

            <h:panelGrid columns="2" >
                <h:outputText value="#{bundle.ALARM_ACTIVATE}"/>
                <h:selectBooleanCheckbox id="endActivate" value="#{AlarmManagedBean.end}" onchange="endActivation()">
                    <h:message for="endActivate" errorClass="errorMessage"/>
                </h:selectBooleanCheckbox>

                <h:outputText value="#{bundle.ALARM_MESSAGE}"/>
                <h:panelGroup>
                    <h:inputTextarea id="endMessage" value="#{AlarmManagedBean.uAlarm.alarmEnd.message}" required="true" requiredMessage="#{bundle.ALARM_MESSAGE_REQUIRED}">
                        <f:validateLength maximum="255" />
                   </h:inputTextarea>
                    <h:message for="endMessage" errorClass="errorMessage"/>
                </h:panelGroup>

                <h:outputText value="#{bundle.ALARM_SOUND}"/>
                <h:panelGroup>
                    <h:inputText id="endSound" value="#{AlarmManagedBean.uAlarm.alarmEnd.sound}">
                        <f:validateLength maximum="255" />
                   </h:inputText>
                </h:panelGroup>
            </h:panelGrid>
        </h:panelGroup>
<%--




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
            <h:selectManyCheckbox layout="pageDirection" id="promos" value="#{BreakTimeManagedBean.selectedPromos}" required="true" requiredMessage="#{bundle.BREAKTIME_PROMOTIONS_REQUIRED}"  styleClass="checkboxes">
                <f:selectItems value="#{BreakTimeManagedBean.promos}"/>
                <h:message for="promos" errorClass="errorMessage"/>
            </h:selectManyCheckbox>

            <h:outputText value="#{bundle.BREAKTIME_BUILDING}"/>
            <h:selectOneMenu id="building" value="#{BreakTimeManagedBean.uBreakTime.building}">
                <f:selectItems value="#{BreakTimeManagedBean.buildings}" />
                <h:message for="building" errorClass="errorMessage"/>
            </h:selectOneMenu>

            <h:outputText value="#{bundle.BREAKTIME_KIND}"/>
            <h:selectOneMenu id="kind" value="#{BreakTimeManagedBean.uBreakTime.kind}">
                <f:selectItems value="#{BreakTimeManagedBean.kinds}" />
                <h:message for="kind" errorClass="errorMessage"/>
            </h:selectOneMenu>

        </h:panelGrid>
--%>
        <h:commandButton value="#{bundle.FORM_OK}" action="#{AlarmManagedBean.update}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{AlarmManagedBean.cancel}" immediate="true"/>
    </h:form>
</f:subview>


