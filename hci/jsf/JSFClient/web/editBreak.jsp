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
              <h:selectManyListbox value="#{BreakAdminBean.promo}">
                   <f:selectItems value="#{BreakAdminBean.plist}"/>
                </h:selectManyListbox>
                <br/>
                <h:commandButton action="#{BreakAdminBean.editABreak}" value="ok"/>
                <br/>
                <h:commandButton action="back" value="back"/>
          </h:form>
       </f:view>
    </body>
</html>
