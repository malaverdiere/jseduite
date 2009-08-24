<%-- 
    Document   : update-content
    Created on : 21 août 2009
    Author     : Steve Colombié
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.FEEDREGISTRY_UPDATE}" /></h1>

    <h:form id="form">
        <h:panelGrid columns="2" >
            <h:outputText value="#{bundle.FEEDREGISTRY_NICKNAME}"/>
            <h:panelGroup>
                <h:inputText id="nickname" value="#{FeedRegistryManagedBean.uFeed.nickname}" required="true" requiredMessage="#{bundle.FEEDREGISTRY_NICKNAME_REQUIRED}">
                    <f:validateLength maximum="255"/>
                </h:inputText>
                <h:message for="nickname" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.FEEDREGISTRY_URL}"/>
            <h:panelGroup>
                <h:inputText id="url" value="#{FeedRegistryManagedBean.url}" required="true" disabled="true"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.FEEDREGISTRY_CLASS}"/>
            <h:panelGroup>
                <h:selectOneMenu id="classes" value="#{FeedRegistryManagedBean.classId}" onchange="otherDetection()">
                    <f:selectItems value="#{FeedRegistryManagedBean.classes}" />
                    <h:message for="classes" errorClass="errorMessage"/>
                </h:selectOneMenu>
                <h:outputText value=" "/>
                <h:inputText id="class" value="#{FeedRegistryManagedBean.alterClass}" required="true" requiredMessage="#{bundle.FEEDREGISTRY_CLASS_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputText>
                <h:message for="class" errorClass="errorMessage"/>
            </h:panelGroup>
        </h:panelGrid>

        <h:commandButton value="#{bundle.FORM_OK}" action="#{FeedRegistryManagedBean.update}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{FeedRegistryManagedBean.cancel}" immediate="true"/>
    </h:form>
</f:subview>
