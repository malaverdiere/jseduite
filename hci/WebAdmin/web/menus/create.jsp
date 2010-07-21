<%--
    Document   : create
    Created on : 11 août 2009
    Author     : Steve Colombié
    Edit       : Christophe Desclaux (2010)
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
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/resources/stylesheet.css' />

            <title><h:outputText value="#{bundle.MENU}" /></title>

            <script type='text/javascript' language = 'Javascript'>
                function otherDetection()
                {
                    if (document.getElementById("content:form:typeMenus").options[document.getElementById("content:form:typeMenus").selectedIndex].value == "__other")
                    {
                        document.getElementById('content:form:typeMenu').disabled=false;
                        document.getElementById('content:form:typeMenu').style.display="inherit";
                    }
                    else
                    {
                        document.getElementById('content:form:typeMenu').disabled=true;
                        document.getElementById('content:form:typeMenu').style.display="none";
                    }
                }
            </script>
        </head>
        <body onload="otherDetection()">
            <div class="body">
                <div class="header">
                    <%@include file="../layout/header.jsp" %>
                </div>
                <div class="menu">
                    <%@include file="../layout/menu.jsp" %>
                </div>
                <div class="content">
                    <%@include file="./create-content.jsp" %>
                </div>
                <div class="footer">
                    <%@include file="../layout/footer.jsp" %>
                </div>
            </div>
        </body>
    </html>
</f:view>
