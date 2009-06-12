<%--
    Document   : courseFinder
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
<title> Course Finder </title>
</head>

	<body>
	<h1>
	<h:outputText value="Find Courses" />
	</h1>

    <h2>
        <h:outputText value="All Courses :"/>
    </h2>
    <h:dataTable border="1" rules="all" value="#{Finder.courses}" var="iterator">
        <h:column>
            <f:facet name="header">
                <h:outputText value="Name" />
            </f:facet>
            <h:outputText value="#{iterator}"/>
        </h:column>
    </h:dataTable>

<h2> Search Course by Name :</h2>
<h:form>
    <h:panelGrid border="0" columns="3" cellpadding="5">
            <h:outputText value="name"/>
            <h:inputText id="name" value="#{Finder.platByName}" required="true" requiredMessage="nom obligatoire !"/>
            <h:message for="name"/>

            <h:commandButton value="Find" action="#{Finder.searchByName}"/>
            
       </h:panelGrid>

       <br>
             <h:outputText value="Name : #{Finder.courseByName.name}"/>
             <br>
            <h:outputText value="Kind : #{Finder.courseByName.kind}"/>
</h:form>
<br>
<h2> Search Course by Kind :</h2>
<h:form>
    <h:panelGrid border="0" columns="3" cellpadding="5">
            <h:outputText value="kind"/>
            <h:inputText id="kind" value="#{Finder.platByKind}" required="true" requiredMessage="type obligatoire !"/>
            <h:message for="kind"/>

            <h:commandButton value="Find" action="#{Finder.searchByKind}"/>

        </h:panelGrid>
        <br>
       <h:dataTable border="1" rules="all" value="#{Finder.courseByKind}" var="iterator">
 
        <h:column>
            <f:facet name="header">
                <h:outputText value=" Name " />
            </f:facet>
            <h:outputText value="#{iterator.name} "/>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:outputText value=" Kind " />
            </f:facet>
            <h:outputText value="#{iterator.kind} "/>
       </h:column>

    </h:dataTable>
      

</h:form>


<br>
    <br>

<a href="index.jsp">Back</a>
	</body>
</html>
</f:view>
