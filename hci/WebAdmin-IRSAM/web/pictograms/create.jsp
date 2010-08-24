<%-- 
    Document   : create
    Created on : 17 août 2010, 12:22:08
    Author     : desclaux
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

            <title><h:outputText value="#{bundle.PICTOGRAMS}" /></title>
            <script type='text/javascript' language = 'Javascript'>
                function onChangePicture1()
                {
                    picture = document.getElementById("content:form:picture1").value;
                    document.getElementById("content:form:picture1preview").setAttribute("src", picture);
                }
                function onChangePicture2()
                {
                    picture = document.getElementById("content:form:picture2").value;
                    document.getElementById("content:form:picture2preview").setAttribute("src", picture);
                }
            </script>
        </head>
        <body  onload="onChangePicture1(), onChangePicture2()">
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
