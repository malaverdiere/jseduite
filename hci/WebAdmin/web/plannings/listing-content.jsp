<%-- 
    Document   : listing-content
    Created on : 25 août 2009
    Author     : Steve Colombié
--%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.PLANNING_LISTING}" /></h1>
    <h:form>
        <h:commandLink action="#{PlanningManagedBean.goCreate}" title="#{bundle.PLANNING_CREATE}" styleClass="create" >
            <h:outputText value="#{bundle.PLANNING_CREATE}"/>
        </h:commandLink>
    </h:form>
    <h:dataTable value="#{PlanningManagedBean.plannings}" var="iterator" rowClasses="list-row-even,list-row-odd"
    headerClass="list-header" rows="#{DataTablePager.itemByPage}" first="#{DataTablePager.firstItem}">
        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.PLANNING_PROMO}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{PlanningManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{PlanningManagedBean.sort}" value="#{PlanningSorter.sortByPromo}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{PlanningManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{PlanningManagedBean.sort}" value="#{PlanningSorter.sortByPromoDesc}" />
                        </h:commandLink>
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:panelGroup styleClass="alignCenter">
                <h:outputText value="#{iterator.id}">
                    <f:converter converterId="PromoConverter"/>
                </h:outputText>
            </h:panelGroup>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:outputText value="#{bundle.PLANNING_GROUPS}" />
            </f:facet>
            <h:outputText value="#{iterator.groups}" escape="false" styleClass="groupLink">
                <f:converter converterId="PlanningGroupsConverter"/>
            </h:outputText>
        </h:column>
        <h:column>
            <h:outputLink value="#{iterator.planning}" title="#{bundle.PLANNING_PLANNING}" target="_blank" styleClass="planning"/>
        </h:column>
        <h:column>
            <h:form>
                <h:commandLink action="#{PlanningManagedBean.goUpdate}" title="#{bundle.UPDATE}" styleClass="update">
                    <f:setPropertyActionListener target="#{PlanningManagedBean.id}" value="#{iterator.id}" />
                </h:commandLink>
            </h:form>
        </h:column>
        <h:column>
            <h:form>
                <h:commandLink action="#{PlanningManagedBean.delete}" title="#{bundle.DELETE}" styleClass="delete">
                    <f:setPropertyActionListener target="#{PlanningManagedBean.id}" value="#{iterator.id}" />
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
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{PlanningManagedBean.planningsCard}" />
                </h:commandLink>
                <h:commandLink title="#{bundle.PAGE_LAST}" action="#{DataTablePager.lastPage}" styleClass="pager">
                    <h:graphicImage value="../resources/images/last.png" alt="#{bundle.PAGE_LAST}"/>
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{PlanningManagedBean.planningsCard}" />
                </h:commandLink>
                <h:outputText value="#{bundle.FORM_ITEM_BY_PAGE} " />
                <h:inputText value="#{DataTablePager.itemByPage}" size="2"/>
            </h:form>
        </f:facet>
    </h:dataTable>
</f:subview>

