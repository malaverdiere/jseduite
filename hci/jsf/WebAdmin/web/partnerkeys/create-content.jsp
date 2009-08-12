<%-- 
    Document   : create-content
    Created on : 11 août 2009
    Author     : Steve Colombié
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.PARTNERKEYS_CREATE}" /></h1>

    <h:form>
        <h:panelGrid columns="2" >
            <h:outputText value="#{bundle.PARTNERKEYS_KEY}"/>
            <h:panelGroup>
                <h:inputText id="key" value="#{PartnerKeysManagedBean.cPartnerKey.key}" required="true" requiredMessage="#{bundle.PARTNERKEYS_KEY_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputText>
                <h:message for="key" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.PARTNERKEYS_VALUE}"/>
            <h:panelGroup>
                <h:inputText id="value" value="#{PartnerKeysManagedBean.cPartnerKey.value}" required="true" requiredMessage="#{bundle.PARTNERKEYS_VALUE_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputText>
                <h:message for="value" errorClass="errorMessage"/>
            </h:panelGroup>
        </h:panelGrid>

        <h:commandButton value="#{bundle.FORM_OK}" action="#{PartnerKeysManagedBean.create}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{PartnerKeysManagedBean.cancel}" immediate="true"/>
    </h:form>
</f:subview>

