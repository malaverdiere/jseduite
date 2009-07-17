<%-- 
    Document   : listing-content
    Created on : 17 juillet 2009, 10:56:00
    Author     : Steve Colombié
--%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.PROMOTION_LISTING}" /></h1>
    <h:outputLink value="create.jsf" styleClass="create">
        <h:outputText value="#{bundle.PROMOTION_CREATE}"/>
    </h:outputLink>

    <h:dataTable value="#{PromotionManagedBean.promos}" var="iterator" rowClasses="list-row-even,list-row-odd"
    headerClass="list-header" rows="#{DataTablePager.itemByPage}" first="#{DataTablePager.firstItem}">
        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.PROMOTION_CODE}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{PromotionManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{PromotionManagedBean.sort}" value="#{PromotionSorter.sortByCode}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{PictureAlbumManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{PromotionManagedBean.sort}" value="#{PromotionSorter.sortByCodeDesc}" />
                        </h:commandLink>
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:outputText value="#{iterator.code}"/>
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.PROMOTION_NAME}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{PromotionManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{PromotionManagedBean.sort}" value="#{PromotionSorter.sortByName}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{PictureAlbumManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{PromotionManagedBean.sort}" value="#{PromotionSorter.sortByNameDesc}" />
                        </h:commandLink>
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:outputText value="#{iterator.name}"/>
        </h:column>

        <h:column>
            <h:form>
                <h:commandLink action="#{PromotionManagedBean.goUpdate}" title="#{bundle.UPDATE}" styleClass="update">
                    <f:setPropertyActionListener target="#{PromotionManagedBean.id}" value="#{iterator.id}" />
                </h:commandLink>
            </h:form>
        </h:column>
        <h:column>
            <h:form>
                <h:commandLink action="#{PromotionManagedBean.delete}" title="#{bundle.DELETE}" styleClass="delete">
                    <f:setPropertyActionListener target="#{PromotionManagedBean.id}" value="#{iterator.id}" />
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
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{PromotionManagedBean.pictureAlbumsCard}" />
                </h:commandLink>
                <h:commandLink title="#{bundle.PAGE_LAST}" action="#{DataTablePager.lastPage}" styleClass="pager">
                    <h:graphicImage value="../resources/images/last.png" alt="#{bundle.PAGE_LAST}"/>
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{PromotionManagedBean.pictureAlbumsCard}" />
                </h:commandLink>
                <h:outputText value="#{bundle.FORM_ITEM_BY_PAGE} " />
                <h:inputText value="#{DataTablePager.itemByPage}" size="2"/>
            </h:form>
        </f:facet>

    </h:dataTable>

</f:subview>

