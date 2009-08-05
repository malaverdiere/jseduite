<%--
    Document   : listing-content
    Created on : 24 juillet 2009
    Author     : Steve Colombié
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.ALARM_LISTING}" /></h1>
    <h:dataTable value="#{AlarmManagedBean.alarms}" var="iterator" rowClasses="list-row-even,list-row-odd"
    headerClass="list-header" rows="#{DataTablePager.itemByPage}" first="#{DataTablePager.firstItem}">
        <h:column>
            <f:facet name="header">
                <h:outputText value="#{bundle.BREAKTIME_BUILDING}"/>
            </f:facet>
            <h:outputText value="#{iterator.breakTime.building}">
                <f:converter converterId="BuildingConverter"/>
            </h:outputText>
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:outputText value="#{bundle.BREAKTIME_TIME}"/>
            </f:facet>
            <h:panelGroup styleClass="alignCenter">
                <h:outputText value="#{iterator.breakTime.start}">
                    <f:converter converterId="XMLGregorianCalendarTimeConverter" />
                </h:outputText>
                <h:outputText value=" - "/>
                <h:outputText value="#{iterator.breakTime.end}">
                    <f:converter converterId="XMLGregorianCalendarTimeConverter" />
                </h:outputText>
            </h:panelGroup>
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:outputText value="#{bundle.BREAKTIME_DETAILS}"/>
            </f:facet>
            <h:panelGrid columns="1">
                <h:outputText value="#{iterator.breakTime.days}" escape="false" styleClass="bold">
                    <f:converter converterId="DayListConverter" />
                </h:outputText>
                <h:outputText value="#{iterator.breakTime.promos}" styleClass="italic">
                    <f:converter converterId="PromoListConverter" />
                </h:outputText>
            </h:panelGrid>
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:outputText value="#{bundle.ALARM_EXISTENCE}"/>
            </f:facet>
            <h:panelGrid columns="3" styleClass="alarmsTab">
                <h:outputText value="#{iterator.alarmStart}" escape="false">
                    <f:converter converterId="AlarmStartConverter" />
                </h:outputText>
                <h:outputText value="#{iterator.alarmAlmostEnd}" escape="false">
                    <f:converter converterId="AlarmAlmostEndConverter" />
                </h:outputText>
                <h:outputText value="#{iterator.alarmEnd}" escape="false">
                    <f:converter converterId="AlarmEndConverter" />
                </h:outputText>
            </h:panelGrid>
        </h:column>

        <h:column>
            <h:form>
                <h:commandLink action="#{AlarmManagedBean.goUpdate}" title="#{bundle.UPDATE}" styleClass="update">
                    <f:setPropertyActionListener target="#{AlarmManagedBean.id}" value="#{iterator.breakTime.id}" />
                </h:commandLink>
            </h:form>
        </h:column>
        <h:column>
            <h:form>
                <h:commandLink action="#{AlarmManagedBean.delete}" title="#{bundle.DELETE}" styleClass="delete">
                    <f:setPropertyActionListener target="#{AlarmManagedBean.id}" value="#{iterator.breakTime.id}" />
                </h:commandLink>
            </h:form>
        </h:column>

        <f:facet name="footer">
            <h:form>
                <h:commandLink title="#{bundle.PAGE_FIRST}" action="#{DataTablePager.firstPage}" styleClass="pager">
                    <h:graphicImage value="../resources/images/first.png" alt="#{bundle.PAGE_FIRST}"/>
                </h:commandLink>
                <h:commandLink title="#{bundle.PAGE_PREV}" action="#{DataTablePager.prevPage}" styleClass="pager">
                    <h:graphicImage value="../resources/images/previous.png" alt="#{bundle.PAGE_PREV}"/>
                </h:commandLink>
                <h:outputText value=" #{DataTablePager.currentPage} " />
                <h:commandLink title="#{bundle.PAGE_NEXT}" action="#{DataTablePager.nextPage}" styleClass="pager">
                    <h:graphicImage value="../resources/images/next.png" alt="#{bundle.PAGE_NEXT}"/>
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{AlarmManagedBean.alarmsCard}" />
                </h:commandLink>
                <h:commandLink title="#{bundle.PAGE_LAST}" action="#{DataTablePager.lastPage}" styleClass="pager">
                    <h:graphicImage value="../resources/images/last.png" alt="#{bundle.PAGE_LAST}"/>
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{AlarmManagedBean.alarmsCard}" />
                </h:commandLink>
                <h:outputText value="#{bundle.FORM_ITEM_BY_PAGE} " />
                <h:inputText value="#{DataTablePager.itemByPage}" size="2"/>
            </h:form>
        </f:facet>
    </h:dataTable>
</f:subview>
