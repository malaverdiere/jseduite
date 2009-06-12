<%-- 
    Document   : courseBusiness
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
        <title>Course Business</title>
    </head>
    <body>
        <h1>
	<h:outputText value="All kind of courses :" />
	</h1>

    <h:dataTable border="1" rules="all" value="#{CourseBusinessControl.allCoursesKind}" var="iterator">
        <h:column>
            <f:facet name="header">
                <h:outputText value="Name" />
            </f:facet>
            <h:outputText value="#{iterator}"/>
        </h:column>
    </h:dataTable>
<br>
    <br>

<a href="index.jsp">Back</a>

    </body>
</html>
</f:view>