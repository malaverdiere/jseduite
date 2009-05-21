<%-- 
    Document   : editBreak
    Created on : 2009-5-15, 11:24:25
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>edit a break</title>
    </head>
    <body>
        <h1>you can edit the link between break and promo!</h1>
        <f:view>
          <h:form>
              <h:messages/>
              <table>
               <tr><td><h:outputText value="promo: "/></td>
               <td> <h:selectManyListbox value="#{BreakAdminBean.promo}">
                   <f:selectItems value="#{BreakAdminBean.plist}"/>
                </h:selectManyListbox></td></tr>
                <tr><td>
                <h:outputText value="start: "/></td>
                <td><h:inputText value="#{BreakAdminBean.editBreak.start}"
                required="true" requiredMessage="the end can't not be null"/>
                </td></tr>
                <tr>
                 <td><h:outputText value="end: "/></td>
                 <td><h:inputText value="#{BreakAdminBean.editBreak.end}"
                 required="true" requiredMessage="the end can't not be null"/>
                 <td></tr>
                <tr>
                 <td><h:outputText value="kind: "/></td>
                 <td> <h:selectOneMenu value="#{BreakAdminBean.editBreak.kind}">
                      <f:selectItem itemValue="short" itemLabel="short"/>
                      <f:selectItem itemValue="long" itemLabel="long"/>
                      </h:selectOneMenu></td></tr>
                 <tr>
                 <td><h:outputText value="day: "/></td>
                 <td><h:selectOneMenu value="#{BreakAdminBean.editBreak.day}">
                       <f:selectItem itemValue="Monday" itemLabel="Monday"/>
                        <f:selectItem itemValue="Tuesday" itemLabel="Tuesday"/>
                        <f:selectItem itemValue="Wednesday" itemLabel="Wednesday"/>
                        <f:selectItem itemValue="Thursday" itemLabel="Thursday"/>
                        <f:selectItem itemValue="Friday" itemLabel="Friday"/>
                 </h:selectOneMenu></td></tr>
                 </table>
                <h:commandButton action="#{BreakAdminBean.editABreak}" value="ok"/>
                <br/>
                <h:commandButton action="back" value="back"/>
          </h:form>
       </f:view>
    </body>
</html>
