<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%-- 
    Document   : breakAdd
    Created on : 2009-5-7, 10:15:00
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <f:view>
            <h:form>


                <h1>BreakAdd</h1>
                <h:panelGrid  columns="2">
                    <f:facet name="footer">
                        <h:panelGroup>
                            <h:commandButton value="OK" action="#{break.insert}" />
                        </h:panelGroup>
                    </f:facet>
                    <h:outputText value="Promo:" />
                    <h:selectOneMenu value="#{break.promo}">
                        <f:selectItems value="#{break.plist}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Start Time:" /><h:inputText id="start" value="#{break.start}" />
                    <h:outputText value="End Time:" /><h:inputText id="end" value="#{break.end}" />
                    <h:outputText value="Break Type:" /><h:inputText id="kind" value="#{break.kind}" />
                    <h:outputText value="Which Day?:" /><h:inputText id="day" value="#{break.day}" />



                </h:panelGrid>
                     <h:commandButton action="back" value="back" />
            </h:form>
        </f:view>
    </body>
</html>
