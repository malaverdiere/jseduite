<%-- 
    Document   : listing-content
    Created on : 17 août 2010, 12:22:36
    Author     : desclaux
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.PICTOGRAMS_LISTING}" /></h1>
    <h:outputLink value="create.jsf" styleClass="create">
        <h:outputText value="#{bundle.PICTOGRAMS_CREATE}"/>
    </h:outputLink>
    <h:dataTable value="#{PictogramManagedBean.pictograms}" var="iterator" rowClasses="list-row-even,list-row-odd"
    headerClass="list-header" rows="#{DataTablePager.itemByPage}" first="#{DataTablePager.firstItem}">

        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.PICTOGRAMS_START}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{PictogramManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{PictogramManagedBean.sort}" value="#{PictogramSorter.sortByStart}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{PictogramManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_DESC}"/>
                            <f:setPropertyActionListener target="#{PictogramManagedBean.sort}" value="#{PictogramSorter.sortByStartDesc}" />
                        </h:commandLink>
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:outputText value="#{iterator.start}">
                <f:converter converterId="XMLGregorianCalendarDateConverter" />
            </h:outputText>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.PICTOGRAMS_END}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{PictogramManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{PictogramManagedBean.sort}" value="#{PictogramSorter.sortByEnd}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{PictogramManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_DESC}"/>
                            <f:setPropertyActionListener target="#{PictogramManagedBean.sort}" value="#{PictogramSorter.sortByEndDesc}" />
                        </h:commandLink>
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:outputText value="#{iterator.end}">
                <f:converter converterId="XMLGregorianCalendarDateConverter" />
            </h:outputText>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.PICTOGRAMS_TITLE}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{PictogramManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{PictogramManagedBean.sort}" value="#{PictogramSorter.sortByTitle}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{PictogramManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_DESC}"/>
                            <f:setPropertyActionListener target="#{PictogramManagedBean.sort}" value="#{PictogramSorter.sortByTitleDesc}" />
                        </h:commandLink>
                        <h:outputText value=" & #{bundle.PICTOGRAMS_PICTURES}" />
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:panelGrid columns="1">
                <h:outputText value="#{iterator.title}" styleClass="bold"/>
            </h:panelGrid>
        </h:column>
        <h:column>
            <h:form>
                <h:commandLink action="#{PictogramManagedBean.goUpdate}" title="#{bundle.UPDATE}" styleClass="update">
                    <f:setPropertyActionListener target="#{PictogramManagedBean.id}" value="#{iterator.id}" />
                </h:commandLink>
            </h:form>
        </h:column>
        <h:column>
            <h:form>
                <h:commandLink action="#{PictogramManagedBean.delete}" title="#{bundle.DELETE}" styleClass="delete">
                    <f:setPropertyActionListener target="#{PictogramManagedBean.id}" value="#{iterator.id}" />
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
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{PictogramManagedBean.internalNewsCard}" />
                </h:commandLink>
                <h:commandLink title="#{bundle.PAGE_LAST}" action="#{DataTablePager.lastPage}" styleClass="pager">
                    <h:graphicImage value="../resources/images/last.png" alt="#{bundle.PAGE_LAST}"/>
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{PictogramManagedBean.internalNewsCard}" />
                </h:commandLink>
                <h:outputText value="#{bundle.FORM_ITEM_BY_PAGE} " />
                <h:inputText value="#{DataTablePager.itemByPage}" size="2"/>
            </h:form>
        </f:facet>
    </h:dataTable>
</f:subview>

