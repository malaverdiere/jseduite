<%--
    Document   : menuFinder
    Author     : ARNOUX Pierre & GENTILE Xavier

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
        <title>Menu Finder</title>
    </head>
    <body>
        <h1>
	<h:outputText value="Find Menu By Date" />
	</h1>

    <h:form>
    <h:panelGrid border="0" columns="3" cellpadding="5">
            <h:outputText value="Year"/>
            <h:inputText id="Year" value="#{MenuFinderControl.date.year}" required="true" requiredMessage="Année obligatoire !"/>
            <h:message for="Year"/>

            <h:outputText value="Month"/>
            <h:inputText id="Month" value="#{MenuFinderControl.date.month}" required="true" requiredMessage="Mois obligatoire !"/>
            <h:message for="Month"/>

            <h:outputText value="Day"/>
            <h:inputText id="Day" value="#{MenuFinderControl.date.day}" required="true" requiredMessage="Jour obligatoire !"/>
            <h:message for="Day"/>

            <h:commandButton value="Find" action="#{MenuFinderControl.searchMenuByDate}"/>
            <br>
                <h2> Result :</h2>
                <h:dataTable border="1" rules="all" value="#{MenuFinderControl.foundByDate.courses}" var="iterator">
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
       </h:panelGrid>

</h:form>

<h1>
	<h:outputText value="Get all Menu References :" />
	</h1>

 <h:form>
<h:panelGrid border="0" columns="3" cellpadding="5">
    <h:commandButton value="Find" action="#{MenuFinderControl.getAllMenuReferences}"/>
    <br>
    <h2> Result :</h2>
                <h:dataTable border="1" rules="all" value="#{MenuFinderControl.allReferences}" var="iterator">
        <h:column>
                 <f:facet name="header">
                     <h:outputText value="Year" />
                  </f:facet>
                  <h:outputText value=" #{iterator.year} "/>
                  </h:column>

         <h:column>
                 <f:facet name="header">
                     <h:outputText value="Month" />
                  </f:facet>
                  <h:outputText value=" #{iterator.month} "/>
                  </h:column>

           <h:column>
                 <f:facet name="header">
                     <h:outputText value="Day" />
                  </f:facet>
                  <h:outputText value=" #{iterator.day} "/>
                  </h:column>
             </h:dataTable>
</h:panelGrid>
</h:form>

<br>
    <br>

<a href="index.jsp">Back</a>
    </body>
</html>
</f:view>