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
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><h:outputText value="Breaking News" /></title>
        </head>
        <body>
            <h1><h:outputText value="Listing of the breaking news" /></h1>
            <h:outputLink value="create.jsf">
                <h:outputText value="Add a break new"/>
            </h:outputLink>
            <h:dataTable value="#{BreakingNewsManagedBean.breakingNews}" var="iterator">
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Author" />
                    </f:facet>
                    <h:outputText value="#{iterator.author}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Stamp" />
                    </f:facet>
                    <h:outputText value="#{iterator.stamp}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Content" />
                    </f:facet>
                    <h:outputText value="#{iterator.content}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Update" />
                    </f:facet>
                    <h:form>
                        <h:commandLink value="up" action="#{BreakingNewsManagedBean.goUpdate}" >
                            <f:setPropertyActionListener target="#{BreakingNewsManagedBean.id}" value="#{iterator.id}" />
                        </h:commandLink>
                    </h:form>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Delete" />
                    </f:facet>
                    <h:form>
                        <h:commandLink value="del" action="#{BreakingNewsManagedBean.delete}" >
                            <f:setPropertyActionListener target="#{BreakingNewsManagedBean.id}" value="#{iterator.id}" />
                        </h:commandLink>
                    </h:form>
                </h:column>
            </h:dataTable>
        </body>
    </html>
</f:view>

