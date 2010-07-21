<%--
    Document   : update-content
    Created on : 10 août 2009
    Author     : Steve Colombié
    Edit       : Christophe Desclaux (2010)
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.MENU_UPDATE}" /></h1>

    <h:form id="form">
        <h:panelGrid columns="2" >
            <h:outputText value="#{bundle.MENU_DATE}"/>
            <h:panelGroup>
                <h:inputText id="date" size="15" value="#{MenuManagedBean.date}" required="true" requiredMessage="#{bundle.MENU_DATE_REQUIRED}">
                    <f:convertDateTime type="date" pattern="#{bundle.FORM_DATE_TIME_PATTERN}"/>
               </h:inputText>
                <h:message for="date" errorClass="errorMessage"/>
            </h:panelGroup>
       </h:panelGrid>


        <h:panelGrid columns="2" >
            <h:outputText value="#{bundle.MENU_TYPE}"/>
            <h:panelGroup>
                <h:selectOneMenu id="typeMenus" value="#{MenuManagedBean.uMenu.typeMenu}" onchange="otherDetection()">
                    <f:selectItems value="#{MenuManagedBean.typeMenu}" />
                    <h:message for="typeMenus" errorClass="errorMessage"/>
                </h:selectOneMenu>
                <h:outputText value=" "/>
                <h:inputText id="typeMenu" value="#{MenuManagedBean.alterTypeMenu}" required="true" requiredMessage="#{bundle.MENU_TYPE_REQUIRED}">
                    <f:validateLength maximum="50" />
                </h:inputText>
                <h:message for="typeMenu" errorClass="errorMessage"/>
            </h:panelGroup>
        </h:panelGrid>

        <h:dataTable value="#{MenuManagedBean.coursesDataToUpdate}" var="iterator">
            <h:column>
                <h:outputText value="#{iterator.kind}"/>
            </h:column>

            <h:column>
                <h:selectManyCheckbox layout="pageDirection" value="#{iterator.selectedCourses}" requiredMessage="#{bundle.MENU_COURSES_REQUIRED}" styleClass="checkboxes">
                    <f:selectItems value="#{iterator.courses}"/>
                </h:selectManyCheckbox>
            </h:column>
        </h:dataTable>

        <h:commandButton value="#{bundle.FORM_OK}" action="#{MenuManagedBean.update}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{MenuManagedBean.cancel}" immediate="true"/>

    </h:form>
</f:subview>


