<%-- 
    Document   : create-content
    Created on : 10 août 2009
    Author     : Steve Colombié
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.COURSE_CREATE}" /></h1>

    <h:form id="form">
        <h:panelGrid columns="2" >
            <h:outputText value="#{bundle.COURSE_NAME}"/>
            <h:panelGroup>
                <h:inputText id="name" value="#{CourseManagedBean.cCourse.name}" required="true" requiredMessage="#{bundle.COURSE_NAME_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputText>
                <h:message for="name" errorClass="errorMessage"/>
            </h:panelGroup>

            <h:outputText value="#{bundle.COURSE_KIND}"/>
            <h:panelGroup>
                <h:selectOneMenu id="kinds" value="#{CourseManagedBean.cCourse.kind}" onchange="otherDetection()">
                    <f:selectItems value="#{CourseManagedBean.kinds}" />
                    <h:message for="kinds" errorClass="errorMessage"/>
                </h:selectOneMenu>
                <h:outputText value=" "/>
                <h:inputText id="kind" value="#{CourseManagedBean.alterKind}" required="true" requiredMessage="#{bundle.COURSE_KIND_REQUIRED}">
                    <f:validateLength maximum="50" />
                </h:inputText>
                <h:message for="kind" errorClass="errorMessage"/>
            </h:panelGroup>
        </h:panelGrid>

        <h:commandButton value="#{bundle.FORM_OK}" action="#{CourseManagedBean.create}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{CourseManagedBean.cancel}" immediate="true"/>
    </h:form>
</f:subview>
