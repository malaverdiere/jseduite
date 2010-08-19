<%-- 
    Document   : create-content
    Created on : 17 août 2010, 12:22:27
    Author     : desclaux
--%>


<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="t" uri="http://myfaces.apache.org/tomahawk"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.PICTOGRAMS_CREATE}" /></h1>

    <h:form>
        <h:panelGrid columns="2" >
            <%--<h:outputText value="#{bundle.PICTOGRAMS_TITLE}"/>
            <h:panelGroup>
                <h:inputText id="title" value="#{PictogramManagedBean.cPictogram.title}" required="true" requiredMessage="#{bundle.PICTOGRAMS_TITLE_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputText>
                <h:message for="title" errorClass="errorMessage"/>
            </h:panelGroup>--%>
            
            <h:outputText value="#{bundle.PICTOGRAMS_START_FORM}"/>
            <h:panelGroup>
                <h:inputText id="start" value="#{PictogramManagedBean.startDate}" required="true" requiredMessage="#{bundle.PICTOGRAMS_START_REQUIRED}">
                    <f:convertDateTime type="date" pattern="#{bundle.FORM_DATE_TIME_PATTERN}"/>
               </h:inputText>
                <h:message for="start" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.PICTOGRAMS_END_FORM}"/>
            <h:panelGroup>
                <h:inputText id="end" value="#{PictogramManagedBean.endDate}" required="true" requiredMessage="#{bundle.PICTOGRAMS_END_REQUIRED}">
                    <f:convertDateTime type="time" pattern="#{bundle.FORM_DATE_TIME_PATTERN}"/>
               </h:inputText>
                <h:message for="end" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.PICTOGRAMS_PICTURE1}"/>
            <h:selectOneMenu id="picture1" value="#{PictogramManagedBean.cPictogram.picture1}">
                 <f:selectItems value="#{PictogramManagedBean.files2}" />
            </h:selectOneMenu>
            
            <h:outputText value="#{bundle.PICTOGRAMS_PICTURE2}"/>
            <h:selectOneMenu id="picture2" value="#{PictogramManagedBean.cPictogram.picture2}">
                 <f:selectItems value="#{PictogramManagedBean.files2}" />
            </h:selectOneMenu>

        </h:panelGrid>

        <h:commandButton value="#{bundle.FORM_OK}" action="#{PictogramManagedBean.create}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{PictogramManagedBean.cancel}" immediate="true"/>
    </h:form>

    <h:outputText value="#{bundle.PICTOGRAMS_UPLOAD}" styleClass="subtitle"/>
    <h:form id="uploadform" enctype="multipart/form-data" >
        <h:panelGrid columns="3" >
            <h:outputText value="#{bundle.PICTOGRAMS_UPLOAD}"/>
            <t:inputFileUpload id="upload" storage="file" value="#{PictogramManagedBean.file}" />
            <h:commandButton value="#{bundle.FORM_UPLOAD}" action="#{PictogramManagedBean.upload}"/>

            <h:outputText value="#{bundle.PICTOGRAMS_DELETION}"/>
            <h:selectOneMenu id="files" value="#{PictogramManagedBean.fileToDelete}">
                <f:selectItems value="#{PictogramManagedBean.files}" />
            </h:selectOneMenu>
            <h:commandButton value="#{bundle.FORM_DELETE}" action="#{PictogramManagedBean.deleteFile}"/>
        </h:panelGrid>
    </h:form>
</f:subview>


