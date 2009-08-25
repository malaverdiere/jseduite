<%--
    Document   : update
    Created on : 26 juin 2009, 09:37:46
    Author     : Squallco
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
            
            <title><h:outputText value="#{bundle.PICTUREALBUM}" /></title>

            <script type='text/javascript' language = 'Javascript'>
                function flickrDetection()
                {
                   if (document.getElementById("content:form:repository").options[document.getElementById("content:form:repository").selectedIndex].value == "flickr")
                   {
                      document.getElementById('content:form:user').disabled=true;
                      document.getElementById('content:form:user').style.display="none";
                      document.getElementById('content:form:userField').style.display="none";

                      document.getElementById('content:form:key').disabled=true;
                      document.getElementById('content:form:key').style.display="none";
                      document.getElementById('content:form:keyField').style.display="none";
                   }
                   else
                   {
                      document.getElementById('content:form:user').disabled=false;
                      document.getElementById('content:form:user').style.display="inherit";
                      document.getElementById('content:form:userField').style.display="inherit";

                      document.getElementById('content:form:key').disabled=false;
                      document.getElementById('content:form:key').style.display="inherit";
                      document.getElementById('content:form:keyField').style.display="inherit";
                   }
                }
            </script>
        </head>
        <body onload="flickrDetection()">
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

