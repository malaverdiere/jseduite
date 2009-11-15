<%-- 
    Document   : create-content
    Created on : 3 août 2009
    Author     : Steve Colombié
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.DEVICES_CREATE}" /></h1>

    <h:form>
        <h:panelGrid columns="2" >
            <h:outputText value="#{bundle.DEVICES_NAME}"/>
            <h:panelGroup>
                <h:inputText id="name" value="#{DeviceManagedBean.cDevice.name}" required="true" requiredMessage="#{bundle.DEVICES_NAME_REQUIRED}">
                    <f:validateLength maximum="50" />
                </h:inputText>
                <h:message for="name" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.DEVICES_LOCATION}"/>
            <h:panelGroup>
                <h:inputTextarea id="location" value="#{DeviceManagedBean.cDevice.location}" required="true" requiredMessage="#{bundle.DEVICES_LOCATION_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputTextarea>
                <h:message for="location" errorClass="errorMessage"/>
            </h:panelGroup>
        </h:panelGrid>

        <h:commandButton value="#{bundle.FORM_OK}" action="#{DeviceManagedBean.create}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{DeviceManagedBean.cancel}" immediate="true"/>
    </h:form>
</f:subview>

