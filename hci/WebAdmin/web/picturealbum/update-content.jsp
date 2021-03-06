<%-- 
    Document   : update-content
    Created on : 13 juil. 2009, 13:04:12
    Author     : Steve Colombi�
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.PICTUREALBUM_UPDATE}" /></h1>
    <h:panelGrid columns="3" >
        <h:outputText value="#{bundle.PICTUREALBUM_DIRECTLINK}"/>
        <h:inputText id="directLink" size="60" />
        <h:commandButton onclick="directLink()" value="#{bundle.FORM_LOAD}"/>
    </h:panelGrid>

    <h:form id="form">
        <h:outputText value="#{bundle.PICTUREALBUM_HOSTER}" styleClass="subtitle"/>
        <h:panelGrid columns="2" >
            <h:outputText value="#{bundle.PICTUREALBUM_REPOSITORY}"/>
            <h:selectOneMenu id="repository" value="#{PictureAlbumManagedBean.uPictureAlbum.repository}" onchange="flickrDetection()">
                <f:selectItems value="#{PictureAlbumManagedBean.repositories}" />
                <h:message for="repository" errorClass="errorMessage"/>
            </h:selectOneMenu>

            <h:outputText id="userField" value="#{bundle.PICTUREALBUM_USER}"/>
            <h:panelGroup>
                <h:inputText id="user" value="#{PictureAlbumManagedBean.uPictureAlbum.user}" required="true" requiredMessage="#{bundle.PICTUREALBUM_USER_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputText>
                <h:message for="user" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.PICTUREALBUM_ALBUM}"/>
            <h:panelGroup>
                <h:inputText id="album" value="#{PictureAlbumManagedBean.uPictureAlbum.album}" required="true" requiredMessage="#{bundle.PICTUREALBUM_ALBUM_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputText>
                <h:message for="album" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText id="keyField" value="#{bundle.PICTUREALBUM_KEY}"/>
            <h:panelGroup>
                <h:inputText id="key" value="#{PictureAlbumManagedBean.uPictureAlbum.authKey}">
                    <f:validateLength maximum="255" />
                </h:inputText>
                <h:message for="key" errorClass="errorMessage"/>
            </h:panelGroup>
        </h:panelGrid>

        <h:outputText value="#{bundle.PICTUREALBUM_LOCAL}" styleClass="subtitle"/>
        <h:panelGrid columns="2" >
            <h:outputText value="#{bundle.PICTUREALBUM_NAME}"/>
            <h:panelGroup>
                <h:inputText id="name" value="#{PictureAlbumManagedBean.uPictureAlbum.name}" required="true" requiredMessage="#{bundle.PICTUREALBUM_NAME_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputText>
                <h:message for="name" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.PICTUREALBUM_START}"/>
            <h:panelGroup>
                <h:inputText id="date" value="#{PictureAlbumManagedBean.date}" required="true" requiredMessage="#{bundle.PICTUREALBUM_START_REQUIRED}">
                    <f:convertDateTime type="date" pattern="#{bundle.FORM_DATE_PATTERN}"/>
               </h:inputText>
                <h:message for="date" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.PICTUREALBUM_DURATION}"/>
            <h:panelGroup>
                <h:inputText id="duration" value="#{PictureAlbumManagedBean.uPictureAlbum.duration}" required="true" requiredMessage="#{bundle.PICTUREALBUM_DURATION_REQUIRED}" />
                <h:message for="duration" errorClass="errorMessage"/>
            </h:panelGroup>
        </h:panelGrid>


        <h:commandButton value="#{bundle.FORM_OK}" action="#{PictureAlbumManagedBean.update}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{PictureAlbumManagedBean.cancel}" immediate="true"/>

    </h:form>
</f:subview>


