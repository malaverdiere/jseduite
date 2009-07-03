<%--
    Document   : listing
    Created on : 25 juin 2009, 15:13:18
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

            <title><h:outputText value="#{bundle.BREAKINGNEWS}" /></title>
        </head>
        <body>
            <h1><h:outputText value="#{bundle.BREAKINGNEWS_LISTING}" /></h1>
            <h:outputLink value="create.jsf">
                <h:outputText value="#{bundle.BREAKINGNEWS_CREATE}"/>
            </h:outputLink>
            <h:dataTable value="#{BreakingNewsManagedBean.breakingNews}" var="iterator" rowClasses="list-row-even,list-row-odd"
            headerClass="list-header" rows="#{DataTablePager.itemByPage}" first="#{DataTablePager.firstItem}">
                <h:column>
                    <f:facet name="header">
                        <h:form>
                            <h:outputText value="#{bundle.BREAKINGNEWS_AUTHOR} "/>
                            <h:commandLink value="asc" action="#{BreakingNewsManagedBean.sortBy}" >
                                <f:setPropertyActionListener target="#{BreakingNewsManagedBean.sort}" value="#{BreakingNewsSorter.sortByAuthor}" />
                            </h:commandLink>
                            <h:outputText value="/"/>
                            <h:commandLink value="desc" action="#{BreakingNewsManagedBean.sortBy}" >
                                <f:setPropertyActionListener target="#{BreakingNewsManagedBean.sort}" value="#{BreakingNewsSorter.sortByAuthorDesc}" />
                            </h:commandLink>
                        </h:form>
                    </f:facet>
                    <h:outputText value="#{iterator.author}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:form>
                            <h:outputText value="#{bundle.BREAKINGNEWS_DATE} "/>
                            <h:commandLink value="asc" action="#{BreakingNewsManagedBean.sortBy}" >
                                <f:setPropertyActionListener target="#{BreakingNewsManagedBean.sort}" value="#{BreakingNewsSorter.sortByDate}" />
                            </h:commandLink>
                            <h:outputText value="/"/>
                            <h:commandLink value="desc" action="#{BreakingNewsManagedBean.sortBy}" >
                                <f:setPropertyActionListener target="#{BreakingNewsManagedBean.sort}" value="#{BreakingNewsSorter.sortByDateDesc}" />
                            </h:commandLink>
                        </h:form>
                    </f:facet>
                    <h:outputText value="#{iterator.stamp}">
                        <f:converter converterId="XMLGregorianCalendarConverter" />
                    </h:outputText>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="#{bundle.BREAKINGNEWS_CONTENT}" />
                    </f:facet>
                    <h:outputText value="#{iterator.content}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="#{bundle.UPDATE}" />
                    </f:facet>
                    <h:form>
                        <h:commandLink value="up" action="#{BreakingNewsManagedBean.goUpdate}" >
                            <f:setPropertyActionListener target="#{BreakingNewsManagedBean.id}" value="#{iterator.id}" />
                        </h:commandLink>
                    </h:form>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="#{bundle.DELETE}" />
                    </f:facet>
                    <h:form>
                        <h:commandLink value="del" action="#{BreakingNewsManagedBean.delete}" >
                            <f:setPropertyActionListener target="#{BreakingNewsManagedBean.id}" value="#{iterator.id}" />
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
                            <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{BreakingNewsManagedBean.breakingNewsCard}" />
                        </h:commandLink>
                        <h:outputText value=" " />
                        <h:commandLink value=">>" action="#{DataTablePager.lastPage}" >
                            <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{BreakingNewsManagedBean.breakingNewsCard}" />
                        </h:commandLink>
                        <h:outputText value="#{bundle.FORM_ITEM_BY_PAGE} " />
                        <h:inputText value="#{DataTablePager.itemByPage}" size="2"/>
                    </h:form>
                </f:facet>
            </h:dataTable>
        </body>
    </html>
</f:view>

