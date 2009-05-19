<%-- 
    Document   : addBreak
    Created on : 2009-5-13, 22:46:27
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
        <title>Add a break</title>
    </head>
    <body>
        <h1>add a new break</h1>
          <f:view>
            <h:form>
                <h:panelGrid  columns="2">
                    <f:facet name="footer">
                        <h:panelGroup>
                            <h:commandButton value="OK" action="#{BreakAdminBean.addNewBreak}" />
                        </h:panelGroup>
                    </f:facet>
                    <h:outputText value="Promo:"/>
                    <h:selectManyListbox value="#{BreakAdminBean.promo}">
                         <f:selectItems value="#{BreakAdminBean.plist}"/>
                     </h:selectManyListbox>
                    <h:outputText value="Start Time:" /><h:inputText id="start" value="#{BreakAdminBean.start}" />
                    <h:outputText value="End Time:" /><h:inputText id="end" value="#{BreakAdminBean.end}" />
                    <h:outputText value="Break Type:" />
                    <h:selectOneMenu value="#{BreakAdminBean.kind}">
                        <f:selectItem itemValue="Short" itemLabel="Short"/>
                        <f:selectItem itemValue="Long" itemLabel="Long"/>
                    </h:selectOneMenu>
                    <h:outputText value="Which Day?:" />
                    <h:selectOneMenu value="#{BreakAdminBean.day}">
                        <f:selectItem itemValue="Monday" itemLabel="Monday"/>
                        <f:selectItem itemValue="Tuesday" itemLabel="Tuesday"/>
                        <f:selectItem itemValue="Wednesday" itemLabel="Wednesday"/>
                        <f:selectItem itemValue="Thursday" itemLabel="Thursday"/>
                        <f:selectItem itemValue="Friday" itemLabel="Friday"/>     
                    </h:selectOneMenu>
                </h:panelGrid>
                <h:commandButton action="back" value="back" />
            </h:form>
        </f:view>
    </body>
</html>
