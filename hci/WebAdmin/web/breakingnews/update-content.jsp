<%-- 
    Document   : update-content
    Created on : 8 juil. 2009, 10:32:16
    Author     : Steve Colombi�
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.BREAKINGNEWS_UPDATE}" /></h1>

    <h:form>
        <h:panelGrid columns="2" >
            <h:outputText value="#{bundle.BREAKINGNEWS_AUTHOR}"/>
            <h:panelGroup>
                <h:inputText id="author" value="#{BreakingNewsManagedBean.uBreakNew.author}" required="true" requiredMessage="#{bundle.BREAKINGNEWS_AUTHOR_REQUIRED}">
                    <f:validateLength maximum="50" />
                </h:inputText>
                <h:message for="author" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.BREAKINGNEWS_DATE}"/>
            <h:panelGroup>
                <h:inputText id="date" value="#{BreakingNewsManagedBean.date}" required="true" requiredMessage="#{bundle.BREAKINGNEWS_DATE_REQUIRED}">
                    <f:convertDateTime type="date" pattern="#{bundle.FORM_DATE_PATTERN}"/>
               </h:inputText>
                <h:message for="date" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.BREAKINGNEWS_TIME}"/>
            <h:panelGroup>
                <h:inputText id="time" value="#{BreakingNewsManagedBean.time}" required="true" requiredMessage="#{bundle.BREAKINGNEWS_TIME_REQUIRED}">
                    <f:convertDateTime type="time" pattern="HH:mm"/>
               </h:inputText>
                <h:message for="time" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.BREAKINGNEWS_CONTENT}"/>
            <h:panelGroup>
                <h:inputTextarea id="content" rows="5" value="#{BreakingNewsManagedBean.uBreakNew.content}" required="true" requiredMessage="#{bundle.BREAKINGNEWS_CONTENT_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputTextarea>
                <h:message for="content" errorClass="errorMessage"/>
            </h:panelGroup>
        </h:panelGrid>

        <h:commandButton value="#{bundle.FORM_OK}" action="#{BreakingNewsManagedBean.update}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{BreakingNewsManagedBean.cancel}" immediate="true"/>

    </h:form>
</f:subview>


