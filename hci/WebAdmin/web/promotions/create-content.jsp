<%-- 
    Document   : create-content
    Created on : 17 juillet 2009, 11:02:00
    Author     : Steve Colombié
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.PROMOTION_CREATE}" /></h1>

    <h:form>
        <h:panelGrid columns="2" >
            <h:outputText value="#{bundle.PROMOTION_CODE}"/>
            <h:panelGroup>
                <h:inputText id="code" value="#{PromotionManagedBean.cPromo.code}" required="true" requiredMessage="#{bundle.PROMOTION_CODE_REQUIRED}" size="10">
                    <f:validateLength maximum="10" />
                </h:inputText>
                <h:message for="code" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.PROMOTION_NAME}"/>
            <h:panelGroup>
                <h:inputText id="name" value="#{PromotionManagedBean.cPromo.name}" required="true" requiredMessage="#{bundle.PROMOTION_NAME_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputText>
                <h:message for="name" errorClass="errorMessage"/>
            </h:panelGroup>
        </h:panelGrid>

        <h:commandButton value="#{bundle.FORM_OK}" action="#{PromotionManagedBean.create}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{PromotionManagedBean.cancel}" immediate="true"/>
    </h:form>
</f:subview>

