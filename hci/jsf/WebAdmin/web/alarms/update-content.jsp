<%--
    Document   : update-content
    Created on : 27 juil. 2009
    Author     : Steve Colombié
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="t" uri="http://myfaces.apache.org/tomahawk"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.ALARM_UPDATE}" /></h1>
    <h:form id="uploadform" enctype="multipart/form-data" >
        <h:panelGrid columns="3" >
            <h:outputText value="#{bundle.ALARM_UPLOAD}"/>
            <t:inputFileUpload id="upload" storage="file" value="#{AlarmManagedBean.file}" />
            <h:commandButton value="#{bundle.FORM_UPLOAD}" action="#{AlarmManagedBean.upload}"/>

            <h:outputText value="#{bundle.ALARM_DELETION}"/>
            <h:selectOneMenu id="files" value="#{AlarmManagedBean.fileToDelete}">
                <f:selectItems value="#{AlarmManagedBean.files}" />
            </h:selectOneMenu>
            <h:commandButton value="#{bundle.FORM_DELETE}" action="#{AlarmManagedBean.deleteFile}"/>
        </h:panelGrid>
    </h:form>


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
                <h:selectOneMenu id="startSound" value="#{AlarmManagedBean.uAlarm.alarmStart.sound}">
                    <f:selectItems value="#{AlarmManagedBean.files2}" />
                </h:selectOneMenu>
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
                <h:selectOneMenu id="almostEndSound" value="#{AlarmManagedBean.uAlarm.alarmAlmostEnd.sound}">
                    <f:selectItems value="#{AlarmManagedBean.files2}" />
                </h:selectOneMenu>
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
                <h:selectOneMenu id="endSound" value="#{AlarmManagedBean.uAlarm.alarmEnd.sound}">
                    <f:selectItems value="#{AlarmManagedBean.files2}" />
                </h:selectOneMenu>
            </h:panelGrid>
        </h:panelGroup>

        <h:commandButton value="#{bundle.FORM_OK}" action="#{AlarmManagedBean.update}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{AlarmManagedBean.cancel}" immediate="true"/>
    </h:form>
</f:subview>


