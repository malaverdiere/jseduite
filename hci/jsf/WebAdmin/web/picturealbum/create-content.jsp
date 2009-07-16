<%-- 
    Document   : create-content
    Created on : 13 juil. 2009, 13:04:12
    Author     : Steve Colombié
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.PICTUREALBUM_CREATE}" /></h1>

    <h:form>
        <h:panelGrid columns="2" >
            <h:outputText value="#{bundle.PICTUREALBUM_NAME}"/>
            <h:panelGroup>
                <h:inputText id="name" value="#{PictureAlbumManagedBean.cPictureAlbum.name}" required="true" requiredMessage="#{bundle.PICTUREALBUM_NAME_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputText>
                <h:message for="name" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.PICTUREALBUM_REPOSITORY}"/>
            <h:selectOneMenu id="repository" value="#{PictureAlbumManagedBean.cPictureAlbum.repository}">
                <f:selectItems value="#{PictureAlbumManagedBean.repositories}" />
                <h:message for="repository" errorClass="errorMessage"/>
            </h:selectOneMenu>

            <h:outputText value="#{bundle.PICTUREALBUM_USER}"/>
            <h:panelGroup>
                <h:inputText id="user" value="#{PictureAlbumManagedBean.cPictureAlbum.user}" required="true" requiredMessage="#{bundle.PICTUREALBUM_USER_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputText>
                <h:message for="user" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.PICTUREALBUM_ALBUM}"/>
            <h:panelGroup>
                <h:inputText id="album" value="#{PictureAlbumManagedBean.cPictureAlbum.album}" required="true" requiredMessage="#{bundle.PICTUREALBUM_ALBUM_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputText>
                <h:message for="album" errorClass="errorMessage"/>
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
                <h:inputText id="duration" value="#{PictureAlbumManagedBean.cPictureAlbum.duration}" required="true" requiredMessage="#{bundle.PICTUREALBUM_DURATION_REQUIRED}" />
                <h:message for="duration" errorClass="errorMessage"/>
            </h:panelGroup>
        </h:panelGrid>

        <h:commandButton value="#{bundle.FORM_OK}" action="#{PictureAlbumManagedBean.create}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{PictureAlbumManagedBean.cancel}" immediate="true"/>
    </h:form>
</f:subview>

