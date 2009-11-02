<%-- 
    Document   : listing-content
    Created on : 8 juil. 2009, 10:37:57
    Author     : Steve Colombié
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.INTERNALNEWS_LISTING}" /></h1>
    <h:outputLink value="create.jsf" styleClass="create">
        <h:outputText value="#{bundle.INTERNALNEWS_CREATE}"/>
    </h:outputLink>
    <h:dataTable value="#{InternalNewsManagedBean.internalNews}" var="iterator" rowClasses="list-row-even,list-row-odd"
    headerClass="list-header" rows="#{DataTablePager.itemByPage}" first="#{DataTablePager.firstItem}">
        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.INTERNALNEWS_TARGET}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{InternalNewsManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByTarget}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{InternalNewsManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_DESC}"/>
                            <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByTargetDesc}" />
                        </h:commandLink>
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:outputText value="#{iterator.target}">
                <f:converter converterId="TargetConverter" />
            </h:outputText>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.INTERNALNEWS_AUTHOR}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{InternalNewsManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByAuthor}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{InternalNewsManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_DESC}"/>
                            <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByAuthorDesc}" />
                        </h:commandLink>
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:outputText value="#{iterator.author}"/>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.INTERNALNEWS_START}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{InternalNewsManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByStart}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{InternalNewsManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_DESC}"/>
                            <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByStartDesc}" />
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
                        <h:outputText value="#{bundle.INTERNALNEWS_END}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{InternalNewsManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByEnd}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{InternalNewsManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_DESC}"/>
                            <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByEndDesc}" />
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
                        <h:outputText value="#{bundle.INTERNALNEWS_TITLE}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{InternalNewsManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByTitle}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{InternalNewsManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_DESC}"/>
                            <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByTitleDesc}" />
                        </h:commandLink>
                        <h:outputText value=" & #{bundle.INTERNALNEWS_CONTENT}" />
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:panelGrid columns="1">
                <h:outputText value="#{iterator.title}" styleClass="bold"/>
                <h:outputText value="#{iterator.content}"/>
            </h:panelGrid>
        </h:column>
        <h:column>
            <h:form>
                <h:commandLink action="#{InternalNewsManagedBean.goUpdate}" title="#{bundle.UPDATE}" styleClass="update">
                    <f:setPropertyActionListener target="#{InternalNewsManagedBean.id}" value="#{iterator.id}" />
                </h:commandLink>
            </h:form>
        </h:column>
        <h:column>
            <h:form>
                <h:commandLink action="#{InternalNewsManagedBean.delete}" title="#{bundle.DELETE}" styleClass="delete">
                    <f:setPropertyActionListener target="#{InternalNewsManagedBean.id}" value="#{iterator.id}" />
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
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{InternalNewsManagedBean.internalNewsCard}" />
                </h:commandLink>
                <h:commandLink title="#{bundle.PAGE_LAST}" action="#{DataTablePager.lastPage}" styleClass="pager">
                    <h:graphicImage value="../resources/images/last.png" alt="#{bundle.PAGE_LAST}"/>
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{InternalNewsManagedBean.internalNewsCard}" />
                </h:commandLink>
                <h:outputText value="#{bundle.FORM_ITEM_BY_PAGE} " />
                <h:inputText value="#{DataTablePager.itemByPage}" size="2"/>
            </h:form>
        </f:facet>
    </h:dataTable>
</f:subview>
