<%-- 
    Document   : listing-content
    Created on : 2 août 2010, 11:30:57
    Author     : Desclaux Christophe
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.BREAKSCREEN_LISTING}" /></h1>
    <h:outputLink value="create.jsf" styleClass="create">
        <h:outputText value="#{bundle.BREAKSCREEN_CREATE}"/>
    </h:outputLink>
    <h:dataTable value="#{BreakScreenManagedBean.breakScreens}" var="iterator" rowClasses="list-row-even,list-row-odd"
    headerClass="list-header" rows="#{DataTablePager.itemByPage}" first="#{DataTablePager.firstItem}">
        <h:column>
            <f:facet name="header">
                <h:outputText value="#{bundle.BREAKSCREEN_BUILDING}"/>
            </f:facet>
            <h:outputText value="#{iterator.building}">
                <f:converter converterId="BuildingConverter"/>
            </h:outputText>
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:outputText value="#{bundle.BREAKSCREEN_TIME}"/>
            </f:facet>
            <h:panelGroup styleClass="alignCenter">
                <h:outputText value="#{iterator.start}">
                    <f:converter converterId="XMLGregorianCalendarTimeConverter" />
                </h:outputText>
                <h:outputText value=" - "/>
                <h:outputText value="#{iterator.end}">
                    <f:converter converterId="XMLGregorianCalendarTimeConverter" />
                </h:outputText>
            </h:panelGroup>
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:outputText value="#{bundle.BREAKSCREEN_DETAILS}"/>
            </f:facet>
            <h:panelGrid columns="1">
                <h:outputText value="#{iterator.days}" escape="false" styleClass="bold">
                    <f:converter converterId="DayListConverter" />
                </h:outputText>
                <h:outputText value="#{iterator.content}">
                    <f:converter converterId="ContentConverter" />
                </h:outputText>
            </h:panelGrid>
        </h:column>

        <h:column>
            <h:form>
                <h:commandLink action="#{BreakScreenManagedBean.goUpdate}" title="#{bundle.UPDATE}" styleClass="update">
                    <f:setPropertyActionListener target="#{BreakScreenManagedBean.id}" value="#{iterator.id}" />
                </h:commandLink>
            </h:form>
        </h:column>
        <h:column>
            <h:form>
                <h:commandLink action="#{BreakScreenManagedBean.delete}" title="#{bundle.DELETE}" styleClass="delete">
                    <f:setPropertyActionListener target="#{BreakScreenManagedBean.id}" value="#{iterator.id}" />
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
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{BreakScreenManagedBean.breakScreenCard}" />
                </h:commandLink>
                <h:commandLink title="#{bundle.PAGE_LAST}" action="#{DataTablePager.lastPage}" styleClass="pager">
                    <h:graphicImage value="../resources/images/last.png" alt="#{bundle.PAGE_LAST}"/>
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{BreakScreenManagedBean.breakScreenCard}" />
                </h:commandLink>
                <h:outputText value="#{bundle.FORM_ITEM_BY_PAGE} " />
                <h:inputText value="#{DataTablePager.itemByPage}" size="2"/>
            </h:form>
        </f:facet>
    </h:dataTable>
</f:subview>
