<%--
    Document   : listing
    Created on : 3 juillet 2009, 17:13:34
    Author     : Steve Colombié
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/resources/stylesheet.css' />

            <title><h:outputText value="#{bundle.INTERNALNEWS}" /></title>
        </head>
        <body>
            <h1><h:outputText value="#{bundle.INTERNALNEWS_LISTING}" /></h1>
            <h:outputLink value="create.jsf">
                <h:outputText value="#{bundle.INTERNALNEWS_CREATE}"/>
            </h:outputLink>
            <h:dataTable value="#{InternalNewsManagedBean.internalNews}" var="iterator" rowClasses="list-row-even,list-row-odd"
            headerClass="list-header" rows="#{DataTablePager.itemByPage}" first="#{DataTablePager.firstItem}">
                <h:column>
                    <f:facet name="header">
                        <h:form>
                            <h:outputText value="#{bundle.INTERNALNEWS_TARGET} "/>
                            <h:commandLink value="asc" action="#{InternalNewsManagedBean.sortBy}" >
                                <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByTarget}" />
                            </h:commandLink>
                            <h:outputText value="/"/>
                            <h:commandLink value="desc" action="#{InternalNewsManagedBean.sortBy}" >
                                <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByTargetDesc}" />
                            </h:commandLink>
                        </h:form>
                    </f:facet>
                    <h:outputText value="#{iterator.target}">
                        <f:converter converterId="TargetConverter" />
                    </h:outputText>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:form>
                            <h:outputText value="#{bundle.INTERNALNEWS_AUTHOR} "/>
                            <h:commandLink value="asc" action="#{InternalNewsManagedBean.sortBy}" >
                                <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByAuthor}" />
                            </h:commandLink>
                            <h:outputText value="/"/>
                            <h:commandLink value="desc" action="#{InternalNewsManagedBean.sortBy}" >
                                <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByAuthorDesc}" />
                            </h:commandLink>
                        </h:form>
                    </f:facet>
                    <h:outputText value="#{iterator.author}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:form>
                            <h:outputText value="#{bundle.INTERNALNEWS_START} "/>
                            <h:commandLink value="asc" action="#{InternalNewsManagedBean.sortBy}" >
                                <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByStart}" />
                            </h:commandLink>
                            <h:outputText value="/"/>
                            <h:commandLink value="desc" action="#{InternalNewsManagedBean.sortBy}" >
                                <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByStartDesc}" />
                            </h:commandLink>
                        </h:form>
                    </f:facet>
                    <h:outputText value="#{iterator.start}">
                        <f:converter converterId="XMLGregorianCalendarDateConverter" />
                    </h:outputText>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:form>
                            <h:outputText value="#{bundle.INTERNALNEWS_END} "/>
                            <h:commandLink value="asc" action="#{InternalNewsManagedBean.sortBy}" >
                                <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByEnd}" />
                            </h:commandLink>
                            <h:outputText value="/"/>
                            <h:commandLink value="desc" action="#{InternalNewsManagedBean.sortBy}" >
                                <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByEndDesc}" />
                            </h:commandLink>
                        </h:form>
                    </f:facet>
                    <h:outputText value="#{iterator.end}">
                        <f:converter converterId="XMLGregorianCalendarDateConverter" />
                    </h:outputText>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:form>
                            <h:outputText value="#{bundle.INTERNALNEWS_TITLE} "/>
                            <h:commandLink value="asc" action="#{InternalNewsManagedBean.sortBy}" >
                                <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByTitle}" />
                            </h:commandLink>
                            <h:outputText value="/"/>
                            <h:commandLink value="desc" action="#{InternalNewsManagedBean.sortBy}" >
                                <f:setPropertyActionListener target="#{InternalNewsManagedBean.sort}" value="#{InternalNewsSorter.sortByTitleDesc}" />
                            </h:commandLink>
                        </h:form>
                    </f:facet>
                    <h:outputText value="#{iterator.title}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="#{bundle.INTERNALNEWS_CONTENT}" />
                    </f:facet>
                    <h:outputText value="#{iterator.content}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="#{bundle.UPDATE}" />
                    </f:facet>
                    <h:form>
                        <h:commandLink value="up" action="#{InternalNewsManagedBean.goUpdate}" >
                            <f:setPropertyActionListener target="#{InternalNewsManagedBean.id}" value="#{iterator.id}" />
                        </h:commandLink>
                    </h:form>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="#{bundle.DELETE}" />
                    </f:facet>
                    <h:form>
                        <h:commandLink value="del" action="#{InternalNewsManagedBean.delete}" >
                            <f:setPropertyActionListener target="#{InternalNewsManagedBean.id}" value="#{iterator.id}" />
                        </h:commandLink>
                    </h:form>
                </h:column>
                <f:facet name="footer">
                    <h:form>
                        <h:commandLink value="<<" action="#{DataTablePager.firstPage}" />
                        <h:outputText value=" " />
                        <h:commandLink value="<" action="#{DataTablePager.prevPage}" />
                        <h:outputText value=" #{DataTablePager.currentPage} " />
                        <h:commandLink value=">" action="#{DataTablePager.nextPage}" >
                            <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{InternalNewsSorter.breakingNewsCard}" />
                        </h:commandLink>
                        <h:outputText value=" " />
                        <h:commandLink value=">>" action="#{DataTablePager.lastPage}" >
                            <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{InternalNewsSorter.breakingNewsCard}" />
                        </h:commandLink>
                        <h:outputText value="#{bundle.FORM_ITEM_BY_PAGE} " />
                        <h:inputText value="#{DataTablePager.itemByPage}" size="2"/>
                    </h:form>
                </f:facet>
            </h:dataTable>
        </body>
    </html>
</f:view>

