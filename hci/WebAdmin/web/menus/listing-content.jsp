<%--
    Document   : listing-content
    Created on : 11 août 2009
    Author     : Steve Colombié
    Edit       : Christophe Desclaux (2010)
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.MENU_LISTING}" /></h1>
    <h:outputLink value="create.jsf" styleClass="create">
        <h:outputText value="#{bundle.MENU_CREATE}"/>
    </h:outputLink>
    <h:dataTable value="#{MenuManagedBean.menus}" var="iterator" rowClasses="list-row-even,list-row-odd"
    headerClass="list-header" rows="#{DataTablePager.itemByPage}" first="#{DataTablePager.firstItem}">
        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.MENU_DATE}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{MenuManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{MenuManagedBean.sort}" value="#{MenuSorter.sortByDate}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{MenuManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_DESC}"/>
                            <f:setPropertyActionListener target="#{MenuManagedBean.sort}" value="#{MenuSorter.sortByDateDesc}" />
                        </h:commandLink>
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:panelGroup styleClass="alignCenter">
                <h:outputText value="#{iterator.date}">
                    <f:converter converterId="XMLGregorianCalendarConverter" />
                </h:outputText>
            </h:panelGroup>
            <h:panelGroup styleClass="alignCenter">
                <h:outputText value="#{iterator.typeMenu}"/>
            </h:panelGroup>
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:outputText value="#{bundle.MENU_COURSES}"/>
            </f:facet>
            <h:outputText value="#{iterator.courses}" escape="false">
                <f:converter converterId="CoursesConverter" />
            </h:outputText>
        </h:column>

        <h:column>
            <h:form>
                <h:commandLink action="#{MenuManagedBean.goUpdate}" title="#{bundle.UPDATE}" styleClass="update">
                    <f:setPropertyActionListener target="#{MenuManagedBean.id}" value="#{iterator.date}" />
                </h:commandLink>
            </h:form>
        </h:column>
        <h:column>
            <h:form>
                <h:commandLink action="#{MenuManagedBean.delete}" title="#{bundle.DELETE}" styleClass="delete">
                    <f:setPropertyActionListener target="#{MenuManagedBean.id}" value="#{iterator.date}" />
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
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{MenuManagedBean.menusCard}" />
                </h:commandLink>
                <h:commandLink title="#{bundle.PAGE_LAST}" action="#{DataTablePager.lastPage}" styleClass="pager">
                    <h:graphicImage value="../resources/images/last.png" alt="#{bundle.PAGE_LAST}"/>
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{MenuManagedBean.menusCard}" />
                </h:commandLink>
                <h:outputText value="#{bundle.FORM_ITEM_BY_PAGE} " />
                <h:inputText value="#{DataTablePager.itemByPage}" size="2"/>
            </h:form>
        </f:facet>
    </h:dataTable>
</f:subview>
