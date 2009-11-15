<%-- 
    Document   : listing-content
    Created on : 20 août 2009
    Author     : Steve Colombié
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.FEEDREGISTRY_LISTING}" /></h1>
    <h:outputLink value="create.jsf" styleClass="create">
        <h:outputText value="#{bundle.FEEDREGISTRY_CREATE}"/>
    </h:outputLink>
    <h:dataTable value="#{FeedRegistryManagedBean.feeds}" var="iterator" rowClasses="list-row-even,list-row-odd"
    headerClass="list-header" rows="#{DataTablePager.itemByPage}" first="#{DataTablePager.firstItem}">
        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.FEEDREGISTRY_NICKNAME}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{FeedRegistryManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{FeedRegistryManagedBean.sort}" value="#{FeedRegistrySorter.sortByNickname}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{FeedRegistryManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_DESC}"/>
                            <f:setPropertyActionListener target="#{FeedRegistryManagedBean.sort}" value="#{FeedRegistrySorter.sortByNicknameDesc}" />
                        </h:commandLink>
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:outputText value="#{iterator.nickname}"/>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.FEEDREGISTRY_CLASS}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{FeedRegistryManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{FeedRegistryManagedBean.sort}" value="#{FeedRegistrySorter.sortByClass}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{FeedRegistryManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_DESC}"/>
                            <f:setPropertyActionListener target="#{FeedRegistryManagedBean.sort}" value="#{FeedRegistrySorter.sortByClassDesc}" />
                        </h:commandLink>
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:outputText value="#{iterator.feedClass.name}"/>
        </h:column>
        <h:column>
            <h:outputLink value="http://#{iterator.providerDNS}#{iterator.feedURL}" title="#{bundle.FEED}" target="_blank" styleClass="feed"/>
        </h:column>
        <h:column>
            <h:form>
                <h:commandLink action="#{FeedRegistryManagedBean.goUpdate}" title="#{bundle.UPDATE}" styleClass="update">
                    <f:setPropertyActionListener target="#{FeedRegistryManagedBean.providerDNS}" value="#{iterator.providerDNS}" />
                    <f:setPropertyActionListener target="#{FeedRegistryManagedBean.feedURL}" value="#{iterator.feedURL}" />
                </h:commandLink>
            </h:form>
        </h:column>
        <h:column>
            <h:form>
                <h:commandLink action="#{FeedRegistryManagedBean.delete}" title="#{bundle.DELETE}" styleClass="delete">
                    <f:setPropertyActionListener target="#{FeedRegistryManagedBean.providerDNS}" value="#{iterator.providerDNS}" />
                    <f:setPropertyActionListener target="#{FeedRegistryManagedBean.feedURL}" value="#{iterator.feedURL}" />
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
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{FeedRegistryManagedBean.feedsCard}" />
                </h:commandLink>
                <h:commandLink title="#{bundle.PAGE_LAST}" action="#{DataTablePager.lastPage}" styleClass="pager">
                    <h:graphicImage value="../resources/images/last.png" alt="#{bundle.PAGE_LAST}"/>
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{FeedRegistryManagedBean.feedsCard}" />
                </h:commandLink>
                <h:outputText value="#{bundle.FORM_ITEM_BY_PAGE} " />
                <h:inputText value="#{DataTablePager.itemByPage}" size="2"/>
            </h:form>
        </f:facet>
    </h:dataTable>
</f:subview>
