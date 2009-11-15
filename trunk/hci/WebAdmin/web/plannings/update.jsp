<%--
    Document   : create
    Created on : 26 août 2009
    Author     : Steve Colombié
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
            <script type='text/javascript' language = 'Javascript'>
                function setNull()
                {
                    var inputs = document.getElementsByTagName("input" );

                    for(var i=0, n=inputs.length; i<n; i++) {
                        if(inputs[i].value == "") {
                            inputs[i].value = "__null";
                        }
                    }
                }

                function removeNull()
                {
                    var inputs = document.getElementsByTagName("input");

                    for(var i=0, n=inputs.length; i<n; i++) {
                        if(inputs[i].value == "__null") {
                            inputs[i].value = "";
                        }
                    }
                }
            </script>
            <title><h:outputText value="#{bundle.PLANNING}" /></title>
        </head>
        <body onload="removeNull()">
            <div class="body">
                <div class="header">
                    <%@include file="../layout/header.jsp" %>
                </div>
                <div class="menu">
                    <%@include file="../layout/menu.jsp" %>
                </div>
                <div class="content">
                    <%@include file="./update-content.jsp" %>
                </div>
                <div class="footer">
                    <%@include file="../layout/footer.jsp" %>
                </div>
            </div>
        </body>
    </html>
</f:view>
