<%--
    Document   : update
    Created on : 26 juin 2009, 09:37:46
    Author     : Squallco
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
            <title>Breaking News</title>
        </head>
        <body>
            <h1><h:outputText value="Update a break new" /></h1>

            <h:form>
                <table>
                    <tr>
                        <td>
                    <h:inputHidden id="id" value="#{BreakingNewsManagedBean.uBreakNew.id}" />
                    <h:outputText value="Author"/>
                        </td>
                        <td>
                    <h:inputText id="author" value="#{BreakingNewsManagedBean.uBreakNew.author}" required="true" requiredMessage="author required"/>
                    <h:message for="author"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                    <h:outputText value="Year"/>
                        </td>
                        <td>
                    <h:inputText id="Year" value="#{BreakingNewsManagedBean.date.year}" required="true" requiredMessage="Année obligatoire !"/>
                    <h:message for="Year"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                    <h:outputText value="Month"/>
                        </td>
                        <td>
                    <h:inputText id="Month" value="#{BreakingNewsManagedBean.date.month}" required="true" requiredMessage="Mois obligatoire !"/>
                    <h:message for="Month"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                    <h:outputText value="Day"/>
                        </td>
                        <td>
                    <h:inputText id="Day" value="#{BreakingNewsManagedBean.date.day}" required="true" requiredMessage="Jour obligatoire !"/>
                    <h:message for="Day"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                    <h:outputText value="Content : "/>
                        </td>
                        <td>
                    <h:inputTextarea id="content" rows="5" value="#{BreakingNewsManagedBean.uBreakNew.content}" required="true" requiredMessage="content required"/>
                    <h:message for="author"/>
                        </td>
                    </tr>
                </table>
                    <h:commandButton value="Update" action="#{BreakingNewsManagedBean.update}"/>

            </h:form>
        </body>
    </html>
</f:view>

