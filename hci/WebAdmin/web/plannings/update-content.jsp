<%--
    Document   : create-content
    Created on : 26 août 2009
    Author     : Steve Colombié
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.PLANNING_UPDATE}" /></h1>

    <h:form id="update">
        <h:panelGrid columns="2" >
            <h:outputText value="#{bundle.PLANNING_PROMO}"/>
            <h:inputText id="promo" value="#{PlanningManagedBean.planning.id}" disabled="true">
                <f:converter converterId="PromoConverter"/>
            </h:inputText>


            <h:outputText value="#{bundle.PLANNING_PLANNING}"/>
            <h:panelGroup>
                <h:inputText id="planning" value="#{PlanningManagedBean.planning.planning}" required="true" requiredMessage="#{bundle.PLANNING_PLANNING_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputText>
                <h:message for="planning" errorClass="errorMessage"/>
            </h:panelGroup>
        </h:panelGrid>

        <h:dataTable value="#{PlanningManagedBean.planning.groups}" var="group">
            <h:column>
                <h:panelGrid columns="1" styleClass="call">
                    <h:panelGroup styleClass="head">
                        <h:outputText value="#{bundle.PLANNING_GROUP}" styleClass="title"/>
                        <h:commandLink action="#{PlanningManagedBean.deletion}" title="#{bundle.DELETE}" styleClass="delete"  onmousedown="setNull()">
                            <f:setPropertyActionListener target="#{PlanningManagedBean.groupDeletion}" value="#{group.id}" />
                        </h:commandLink>
                    </h:panelGroup>

                    <h:panelGrid columns="2">
                        <h:outputText value="#{bundle.PLANNING_GROUPNAME}"/>
                        <h:panelGroup>
                            <h:inputText id="groupname" value="#{group.name}" required="true" requiredMessage="#{bundle.PLANNING_GROUPNAME_REQUIRED}">
                                <f:validateLength maximum="255" />
                            </h:inputText>
                            <h:message for="groupname" errorClass="errorMessage"/>
                        </h:panelGroup>

                        <h:outputText value="#{bundle.PLANNING_GROUPPLANNING}"/>
                        <h:panelGroup>
                            <h:inputText id="groupplanning" value="#{group.planning}" required="true" requiredMessage="#{bundle.PLANNING_GROUPPLANNING_REQUIRED}">
                                <f:validateLength maximum="255" />
                            </h:inputText>
                            <h:message for="groupplanning" errorClass="errorMessage"/>
                        </h:panelGroup>
                    </h:panelGrid>
                </h:panelGrid>
            </h:column>
        </h:dataTable>

        <h:panelGrid columns="1"  styleClass="callAddition">
            <h:commandLink action="#{PlanningManagedBean.addition}" value="#{bundle.PLANNING_NEW_GROUP}" styleClass="title"  onmousedown="setNull()"/>
        </h:panelGrid>

        <h:commandButton value="#{bundle.FORM_OK}" action="#{PlanningManagedBean.update}"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{PlanningManagedBean.cancel}" immediate="true"/>

    </h:form>
</f:subview>
