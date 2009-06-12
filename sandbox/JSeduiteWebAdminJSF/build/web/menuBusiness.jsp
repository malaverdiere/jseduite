<%--
    Document   : menuBusiness
    Author     : ARNOUX Pierre & GENTILE Xavier

--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Business</title>
    </head>
    <body>
        <h1>
	<h:outputText value="Today's menu :" />
	</h1>

                <h:dataTable border="1" rules="all" value="#{MenuBusinessControl.todayMenu.courses}" var="iterator">
        <h:column>
                 <f:facet name="header">
                     <h:outputText value="Name" />
                  </f:facet>
                  <h:outputText value="#{iterator.name}"/>
        </h:column>
        <h:column>
                  <f:facet name="header">
                     <h:outputText value="Kind" />
                  </f:facet>
                  <h:outputText value="#{iterator.kind}"/>

        </h:column>

            </h:dataTable>

<br>
    <br>

<a href="index.jsp">Back</a>
    </body>
</html>
</f:view>