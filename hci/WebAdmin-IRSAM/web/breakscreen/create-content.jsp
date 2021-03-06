<%-- 
    Document   : create-content
    Created on : 2 ao�t 2010, 11:30:00
    Author     : Desclaux Christophe
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="t" uri="http://myfaces.apache.org/tomahawk"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.BREAKSCREEN_CREATE}" /></h1>
    <h:form id="uploadform" enctype="multipart/form-data" >
        <h:panelGrid columns="3" >
            <h:outputText value="#{bundle.ALARM_UPLOAD}"/>
            <t:inputFileUpload id="upload" storage="file" value="#{BreakScreenManagedBean.file}" />
            <h:commandButton value="#{bundle.FORM_UPLOAD}" action="#{BreakScreenManagedBean.upload}"/>

            <h:outputText value="#{bundle.ALARM_DELETION}"/>
            <h:selectOneMenu id="files" value="#{BreakScreenManagedBean.fileToDelete}">
                <f:selectItems value="#{BreakScreenManagedBean.files}" />
            </h:selectOneMenu>
            <h:commandButton value="#{bundle.FORM_DELETE}" action="#{BreakScreenManagedBean.deleteFile}"/>
        </h:panelGrid>
    </h:form>
    
    <h:form id="form">
        <h:outputText value="" styleClass="subtitle"/>
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
                <h:selectOneMenu id="buildings" value="#{BreakScreenManagedBean.cBreakScreen.building}" onchange="otherDetectionBuilding()">
                    <f:selectItems value="#{BreakScreenManagedBean.buildings}" />
                    <h:message for="buildings" errorClass="errorMessage"/>
                </h:selectOneMenu>
                <h:outputText value=" "/>
                <h:inputText id="building" value="#{BreakScreenManagedBean.alterBuilding}" required="true" requiredMessage="#{bundle.BREAKSCREEN_BUILDING_REQUIRED}">
                    <f:validateLength maximum="10" />
                </h:inputText>
               <h:message for="building" errorClass="errorMessage"/>
            </h:panelGroup>
            <h:panelGroup>
                <h:outputText value="#{bundle.BREAKSCREEN_CONTENT}"/>
                <h:outputText value=": (#{bundle.BREAKSCREEN_CONTENT_WARNING})"/>
            </h:panelGroup>
            <h:panelGroup>
                <h:selectOneMenu id="contents" value="#{BreakScreenManagedBean.cBreakScreen.content}" onchange="otherDetectionContent()">
                    <f:selectItems value="#{BreakScreenManagedBean.contents}" />
                    <h:message for="contents" errorClass="errorMessage"/>
                </h:selectOneMenu>
                <h:inputText id="content" value="#{BreakScreenManagedBean.alterContent}" required="true" requiredMessage="#{bundle.BREAKSCREEN_CONTENT_REQUIRED}">
                    <f:validateLength maximum="100" />
                </h:inputText>
               <h:message for="content" errorClass="errorMessage"/>
            </h:panelGroup>
            <h:outputText id="sound_title" value="#{bundle.ALARM_SOUND}"/>
                <h:selectOneMenu id="sound" value="#{BreakScreenManagedBean.sound}">
                    <f:selectItems value="#{BreakScreenManagedBean.files2}" />
                </h:selectOneMenu>

        </h:panelGrid>

        <h:commandButton value="#{bundle.FORM_OK}" action="#{BreakScreenManagedBean.create}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{BreakScreenManagedBean.cancel}" immediate="true"/>
    </h:form>
</f:subview>

