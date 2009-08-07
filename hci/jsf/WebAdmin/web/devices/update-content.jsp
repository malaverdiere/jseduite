<%--
    Document   : update-content
    Created on : 3 août 2009
    Author     : Steve Colombié
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.DEVICES_UPDATE} : #{DeviceManagedBean.uDevice.name}" /></h1>

    <h:form id="update">
        <h:panelGrid columns="2" >
            <h:outputText value="#{bundle.DEVICES_LOCATION}"/>
            <h:panelGroup>
                <h:inputTextarea id="location" value="#{DeviceManagedBean.uDevice.location}" required="true" requiredMessage="#{bundle.DEVICES_LOCATION_REQUIRED}">
                    <f:validateLength maximum="255" />
                </h:inputTextarea>
                <h:message for="location" errorClass="errorMessage"/>
            </h:panelGroup>
        </h:panelGrid>

        <h:dataTable id="list" value="#{DeviceManagedBean.sources}" var="sourceData">
            <h:column>
                <h:panelGrid columns="1">
                    <h:inputHidden id="hiddenAnchor" value="#{sourceData.anchor}"/>
                    <h:outputText id="source" value="#{sourceData.source.name}" styleClass="subtitle"/>
                    <h:dataTable value="#{sourceData.calls}" var="callData">
                        <h:column>
                            <h:panelGrid columns="1" styleClass="call">
                                <h:panelGroup styleClass="head">
                                    <h:outputText value="#{bundle.DEVICES_CALL} #{callData.setId}" styleClass="title"/>
                                    <h:commandLink action="#{DeviceManagedBean.deletion}" title="#{bundle.DELETE}" styleClass="delete">
                                        <f:setPropertyActionListener target="#{DeviceManagedBean.sourceDeletion}" value="#{sourceData.source.nickname}" />
                                        <f:setPropertyActionListener target="#{DeviceManagedBean.callDeletion}" value="#{callData.setId}" />
                                    </h:commandLink>
                                </h:panelGroup>

                                <h:dataTable value="#{callData.parameters}" var="paramData">
                                    <h:column>
                                        <h:outputText value="#{paramData.param.prettyName}"/>
                                    </h:column>
                                    <h:column>
                                        <h:inputText id="inputtext" value="#{paramData.param.value}"/>
                                    </h:column>
                                    <h:column>
                                        <h:inputHidden id="defaultvalue" value="#{paramData.param.defaultValue}"/>
                                        <h:graphicImage value="../resources/images/#{paramData.isDefault}.png" id="image" onclick="defaultActivation(this)"/>
                                    </h:column>
                                </h:dataTable>
                            </h:panelGrid>
                        </h:column>
                    </h:dataTable>
                    <h:panelGrid columns="1"  styleClass="callAddition">
                        <h:commandLink action="#{DeviceManagedBean.addition}" value="#{bundle.DEVICES_NEW_CALL} #{sourceData.source.name}" styleClass="title"  onmousedown="removeDisabled();">
                            <f:setPropertyActionListener target="#{DeviceManagedBean.sourceAddition}" value="#{sourceData.source.nickname}" />
                        </h:commandLink>
                    </h:panelGrid>
                </h:panelGrid>
            </h:column>
        </h:dataTable>

        <h:commandButton value="#{bundle.FORM_OK}" action="#{DeviceManagedBean.update}" onclick="removeDisabled()"/>
        <h:commandButton value="#{bundle.FORM_CANCEL}" action="#{DeviceManagedBean.cancel}" immediate="true"/>

    </h:form>
</f:subview>


