<%-- 
    Document   : update-content
    Created on : 8 juil. 2009, 10:38:31
    Author     : Steve Colombié
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.INTERNALNEWS_UPDATE}" /></h1>

    <h:form>
        <h:panelGrid columns="2" >
            <h:outputText value="#{bundle.INTERNALNEWS_TARGET}"/>
            <h:selectOneMenu id="target" value="#{InternalNewsManagedBean.uNews.target}" >
                <f:selectItems value="#{InternalNewsManagedBean.targets}" />
                <h:message for="target" errorClass="errorMessage"/>
            </h:selectOneMenu>

            <h:outputText value="#{bundle.INTERNALNEWS_AUTHOR}"/>
            <h:panelGroup>
                <h:inputText id="author" value="#{InternalNewsManagedBean.uNews.author}" required="true" requiredMessage="#{bundle.INTERNALNEWS_AUTHOR_REQUIRED}">
                    <f:validateLength maximum="50" />
                </h:inputText>
                <h:message for="author" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.INTERNALNEWS_START}"/>
            <h:panelGroup>
                <h:inputText id="start" value="#{InternalNewsManagedBean.startDate}" required="true" requiredMessage="#{bundle.INTERNALNEWS_START_REQUIRED}">
                    <f:convertDateTime type="date" pattern="#{bundle.FORM_DATE_PATTERN}"/>
               </h:inputText>
                <h:message for="start" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.INTERNALNEWS_END}"/>
            <h:panelGroup>
                <h:inputText id="end" value="#{InternalNewsManagedBean.endDate}" required="true" requiredMessage="#{bundle.INTERNALNEWS_END_REQUIRED}">
                    <f:convertDateTime type="time" pattern="#{bundle.FORM_DATE_PATTERN}"/>
               </h:inputText>
                <h:message for="end" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.INTERNALNEWS_TITLE}"/>
            <h:panelGroup>
                <h:inputText id="title" value="#{InternalNewsManagedBean.uNews.title}" required="true" requiredMessage="#{bundle.INTERNALNEWS_TITLE_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputText>
                <h:message for="title" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.INTERNALNEWS_CONTENT}"/>
            <h:panelGroup>
                <h:inputTextarea id="content" rows="5" value="#{InternalNewsManagedBean.uNews.content}" required="true" requiredMessage="#{bundle.INTERNALNEWS_CONTENT_REQUIRED}"/>
                <h:message for="content" errorClass="errorMessage"/>
            </h:panelGroup>
        </h:panelGrid>

        <h:commandButton value="#{bundle.FORM_OK}" action="#{InternalNewsManagedBean.update}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{InternalNewsManagedBean.cancel}" immediate="true"/>
    </h:form>
</f:subview>


