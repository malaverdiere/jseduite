<%--
    Document   : courseCRUD
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
        <title>Course CRUD</title>
    </head>
    <body>
        <h2><h:outputText value="Create Course :" /></h2>


    <h:form>
        
            <h:outputText value="Name : "/>
            <h:inputText id="name" value="#{CRUDControl.plat.name}" required="true" requiredMessage="nom obligatoire !"/>
            <h:message for="name"/>
            <br>

            Kind : 
             <h:selectOneMenu id="kind" value="#{CRUDControl.plat.kind}">
                 <f:selectItems value="#{Finder.coursesKinds}"/>
            </h:selectOneMenu>
            <br>
            <h:commandButton value="add" action="#{CRUDControl.createCourse}"/>
        
        </h:form>
        
    <h2> Read Course :</h2>

        <h:form>
        <h:panelGrid border="0" columns="3" cellpadding="5">

            <h:selectOneMenu id="name" value="#{CRUDControl.readName}">
                <f:selectItems value="#{Finder.coursesNames}"/>
            </h:selectOneMenu>

            <h:commandButton value="Read" action="#{CRUDControl.readCourse}"/>
           
           
        </h:panelGrid>
        <br>
            <h3> Result : </h3>
            <h:outputText value="Name : #{CRUDControl.readCourse.name} "/>
            <br>
            <h:outputText value="Kind : #{CRUDControl.readCourse.kind} "/>
        </h:form>

          

        <h2> Delete Course :</h2>

        <h:form>
        <h:panelGrid border="0" columns="3" cellpadding="5">
            
            <h:selectOneMenu id="name" value="#{CRUDControl.deleteName}">
                <f:selectItems value="#{Finder.coursesNames}"/>
            </h:selectOneMenu>

            <h:commandButton value="Delete" action="#{CRUDControl.deleteCourse}"/>

        </h:panelGrid>
        </h:form>

<br>
    <br>

<a href="index.jsp">Back</a>
    </body>
</html>

</f:view>