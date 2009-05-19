<%-- 
    Document   : showBreak
    Created on : 2009-5-13, 12:03:31
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
        <title>Get breaks</title>
    </head>
    <body>
        <h1>Get breaks</h1>
        <f:view>
        <h:form>
            <hr/>
           <h2>Get all the breaks</h2>
           <h:commandButton action="getAllBreak" value="show all breaks"/>
           <hr/>
           <br/>
           <h2>Get breaks for today</h2>
            <h:commandButton action="getTodayBreak" value="show breaks for today"/>
            <hr/>
            <br/>
            <h2>Get breaks by day</h2>
             <h:outputText  value="Weekday:" />
             <h:selectOneMenu value="#{BreakUseBean.day}">
                 <f:selectItem itemValue="Monday" itemLabel="Monday"/>
                  <f:selectItem itemValue="Tuesday" itemLabel="Tuesday"/>
                  <f:selectItem itemValue="Wednesday" itemLabel="Wednesday"/>
                  <f:selectItem itemValue="Thursday" itemLabel="Thursday"/>
                  <f:selectItem itemValue="Friday" itemLabel="Friday"/>
             </h:selectOneMenu>
            <h:commandButton action="getBreakByDay" value="show breaks"/>

            <hr/>
            <br/>
            <h2>Get breaks by promo</h2>
            <h:outputText value="promo:"/>
            <h:selectOneMenu value="#{BreakUseBean.promo}">
                <f:selectItems value="#{BreakAdminBean.plist}"/>
            </h:selectOneMenu>
            <h:commandButton action="getBreakByPromo" value="show breaks"/>
            <br/>
            <h:commandButton action="back" value="back"/>
        </h:form>
        </f:view>
    </body>
</html>
