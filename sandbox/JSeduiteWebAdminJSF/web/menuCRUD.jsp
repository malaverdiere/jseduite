<%--
    Document   : menuCRUD
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
        <title>Menu CRUD</title>
    </head>
    <body>

         <h2>Create Menu : </h2>


    <h:form>

            <h:selectManyCheckbox id="courses" value="#{MenuCRUDControl.createdNameCourses}">
                <f:selectItems value="#{Finder.coursesNames}"/>
            </h:selectManyCheckbox>
            
            <br>

            <h:outputText value="Year"/>
            <h:inputText id="Year" value="#{MenuCRUDControl.createdDate.year}" required="true" requiredMessage="Année obligatoire !"/>
            <h:message for="Year"/>
            <br>
            <h:outputText value="Month"/>
            <h:inputText id="Month" value="#{MenuCRUDControl.createdDate.month}" required="true" requiredMessage="Mois obligatoire !"/>
            <h:message for="Month"/>
            <br>
            <h:outputText value="Day"/>
            <h:inputText id="Day" value="#{MenuCRUDControl.createdDate.day}" required="true" requiredMessage="Jour obligatoire !"/>
            <h:message for="Day"/>
            <br>
            <h:commandButton value="CREATE" action="#{MenuCRUDControl.createMenu}"/>

       </h:form>



            <h2>Read Menu : </h2>

       <h:form>
            <h:outputText value="Year"/>
            <h:inputText id="Year" value="#{MenuCRUDControl.readDate.year}" required="true" requiredMessage="Année obligatoire !"/>
            <h:message for="Year"/>
            <br>
            <h:outputText value="Month"/>
            <h:inputText id="Month" value="#{MenuCRUDControl.readDate.month}" required="true" requiredMessage="Mois obligatoire !"/>
            <h:message for="Month"/>
            <br>
            <h:outputText value="Day"/>
            <h:inputText id="Day" value="#{MenuCRUDControl.readDate.day}" required="true" requiredMessage="Jour obligatoire !"/>
            <h:message for="Day"/>
            <br>
            <h:commandButton value="READ" action="#{MenuCRUDControl.readMenu}"/>
            <br>
            <h2>Result :</h2>

            <h:dataTable border="1" rules="all" value="#{MenuCRUDControl.readMenu.courses}" var="iterator">

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
            <h2> Delete Menu : </h2>

   <br>
   <h:form>
       <h:selectOneMenu id="name" value="#{MenuCRUDControl.deleteDate}">
           <f:selectItems value="#{MenuFinderControl.allDates}"/>
            </h:selectOneMenu>

            <br>
            <h:commandButton value="DELETE" action="#{MenuCRUDControl.deleteMenu}"/>
            <br>
                
    </h:form>
<h:form>
       <h:panelGrid columns="2" cellpadding="10">
        <h:outputLink value="list.jsf">
            <h:outputText value="List"/>
            </h:outputLink>
        <h:outputLink value="add.jsf">
            <h:outputText value="Add"/>
            </h:outputLink>
            <h:outputLink value="courseBusiness.jsf">
            <h:outputText value="Course Business"/>
            </h:outputLink>
    </h:panelGrid>
    </h:form>

<br>
    <br>

<a href="index.jsp">Back</a>

    </body>
</html>

</f:view>