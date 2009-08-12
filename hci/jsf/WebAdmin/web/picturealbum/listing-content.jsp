<%-- 
    Document   : listing-content
    Created on : 13 juil. 2009, 13:04:12
    Author     : Steve Colombié
--%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.PICTUREALBUM_LISTING}" /></h1>
    <h:outputLink value="create.jsf" styleClass="create">
        <h:outputText value="#{bundle.PICTUREALBUM_CREATE}"/>
    </h:outputLink>

    <h:dataTable value="#{PictureAlbumManagedBean.pictureAlbums}" var="iterator" rowClasses="list-row-even,list-row-odd"
    headerClass="list-header" rows="#{DataTablePager.itemByPage}" first="#{DataTablePager.firstItem}">
        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.PICTUREALBUM_NAME}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{PictureAlbumManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{PictureAlbumManagedBean.sort}" value="#{PictureAlbumSorter.sortByName}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{PictureAlbumManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{PictureAlbumManagedBean.sort}" value="#{PictureAlbumSorter.sortByNameDesc}" />
                        </h:commandLink>
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:outputText value="#{iterator.name}"/>
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.PICTUREALBUM_START}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{PictureAlbumManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{PictureAlbumManagedBean.sort}" value="#{PictureAlbumSorter.sortByStart}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{PictureAlbumManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_DESC}"/>
                            <f:setPropertyActionListener target="#{PictureAlbumManagedBean.sort}" value="#{PictureAlbumSorter.sortByStartDesc}" />
                        </h:commandLink>
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:panelGroup styleClass="alignCenter">
                <h:outputText value="#{iterator.validFrom}">
                    <f:converter converterId="XMLGregorianCalendarDateConverter" />
                </h:outputText>
            </h:panelGroup>
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:outputText value="#{bundle.PICTUREALBUM_DURATION}"/>
            </f:facet>
            <h:outputText value="#{iterator.duration}"/>
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.PICTUREALBUM_REPOSITORY}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{PictureAlbumManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{PictureAlbumManagedBean.sort}" value="#{PictureAlbumSorter.sortByRepository}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{PictureAlbumManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{PictureAlbumManagedBean.sort}" value="#{PictureAlbumSorter.sortByRepositoryDesc}" />
                        </h:commandLink>
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:outputText value="#{iterator.repository}"/>
        </h:column>

        <h:column>
            <h:form>
                <h:commandLink action="#{PictureAlbumManagedBean.goUpdate}" title="#{bundle.UPDATE}" styleClass="update">
                    <f:setPropertyActionListener target="#{PictureAlbumManagedBean.id}" value="#{iterator.id}" />
                </h:commandLink>
            </h:form>
        </h:column>
        <h:column>
            <h:form>
                <h:commandLink action="#{PictureAlbumManagedBean.delete}" title="#{bundle.DELETE}" styleClass="delete">
                    <f:setPropertyActionListener target="#{PictureAlbumManagedBean.id}" value="#{iterator.id}" />
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
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{PictureAlbumManagedBean.pictureAlbumsCard}" />
                </h:commandLink>
                <h:commandLink title="#{bundle.PAGE_LAST}" action="#{DataTablePager.lastPage}" styleClass="pager">
                    <h:graphicImage value="../resources/images/last.png" alt="#{bundle.PAGE_LAST}"/>
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{PictureAlbumManagedBean.pictureAlbumsCard}" />
                </h:commandLink>
                <h:outputText value="#{bundle.FORM_ITEM_BY_PAGE} " />
                <h:inputText value="#{DataTablePager.itemByPage}" size="2"/>
            </h:form>
        </f:facet>

    </h:dataTable>

</f:subview>

