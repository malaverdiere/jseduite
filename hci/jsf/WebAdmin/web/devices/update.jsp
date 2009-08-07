<%--
    Document   : update
    Created on : 3 août 2009
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
                function defaultActivation(val)
                {
                    if(document.getElementById(val.id.substring(0, val.id.indexOf("image", 0))+"inputtext").disabled == false) {
                        document.getElementById(val.id.substring(0, val.id.indexOf("image", 0))+"inputtext").disabled=true;
                        document.getElementById(val.id.substring(0, val.id.indexOf("image", 0))+"inputtext").value=document.getElementById(val.id.substring(0, val.id.indexOf("image", 0))+"defaultvalue").value;
                        val.src = "../resources/images/true.png"
                    }
                    else {
                        document.getElementById(val.id.substring(0, val.id.indexOf("image", 0))+"inputtext").disabled=false;
                        val.src = "../resources/images/false.png"
                    }
                }

                function setDefault()
                {
                    var inputs = document.getElementsByTagName("img" );

                    for(var i=0, n=inputs.length; i<n; i++) {
                        if(inputs[i].src.substring(inputs[i].src.length-8, inputs[i].src.length) == "true.png") {
                            document.getElementById(inputs[i].id.substring(0, inputs[i].id.indexOf("image", 0))+"inputtext").disabled=true;
                            document.getElementById(inputs[i].id.substring(0, inputs[i].id.indexOf("image", 0))+"inputtext").value=document.getElementById(inputs[i].id.substring(0, inputs[i].id.indexOf("image", 0))+"defaultvalue").value;
                        }
                    }
                }

                function removeDisabled()
                {
                    var inputs = document.getElementsByTagName("img");

                    for(var i=0, n=inputs.length; i<n; i++) {
                       document.getElementById(inputs[i].id.substring(0, inputs[i].id.indexOf("image", 0))+"inputtext").disabled=false;
                    }
                }

                function goAnchor()
                {
                    var inputs = document.getElementsByTagName("input");

                    for(var i=0, n=inputs.length; i<n; i++) {
                        if(inputs[i].value == "anchor") {
                            window.location='#'+inputs[i].name.substring(0,inputs[i].name.indexOf("hiddenAnchor", 0))+'source';
                        }
                    }
                }
        </script>
            <title><h:outputText value="#{bundle.DEVICES}" /></title>
        </head>
        <body onload="setDefault(); goAnchor()">
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

