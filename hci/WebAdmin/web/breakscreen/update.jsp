<%-- 
    Document   : update
    Created on : 2 août 2010, 11:31:29
    Author     : Desclaux Christophe
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

            <title><h:outputText value="#{bundle.BREAKTIME}" /></title>

            <script type='text/javascript' language = 'Javascript'>
                function otherDetectionBuilding()
                {
                    if (document.getElementById("content:form:buildings").options[document.getElementById("content:form:buildings").selectedIndex].value == "__other")
                    {
                        document.getElementById('content:form:building').disabled=false;
                        document.getElementById('content:form:building').style.display="inherit";
                    }
                    else
                    {
                        document.getElementById('content:form:building').disabled=true;
                        document.getElementById('content:form:building').style.display="none";
                    }
                }
                var activatedContents = new Array("#calendar", "#calendarNsource","#ephemeride","#internal_news","#menu","#PictogramsSource","#weather");
                function otherDetectionContent(action)
                {
                    var selectedItem = document.getElementById("content:form:contents").options[document.getElementById("content:form:contents").selectedIndex].value;
                    if(activatedContents.lastIndexOf(selectedItem) < 0 && selectedItem != "__other"){
                        alert("Il est fortement déconseillé d'utiliser cet élément pour source de contenu.");
                    }
                    if (selectedItem == "__other")
                    {
                        document.getElementById('content:form:content').disabled=false;
                        document.getElementById('content:form:content').style.display="inherit";
                        document.getElementById('content:form:sound').disabled=false;
                        document.getElementById('content:form:sound').style.display="inherit";
                        document.getElementById('content:form:sound_title').style.display="inherit";
                    }
                    else
                    {
                        document.getElementById('content:form:content').disabled=true;
                        document.getElementById('content:form:content').style.display="none";
                        document.getElementById('content:form:sound').disabled=true;
                        document.getElementById('content:form:sound').style.display="none";
                        document.getElementById('content:form:sound_title').style.display="none";
                    }
                }
            </script>
        </head>
        <body onload="otherDetectionBuilding();otherDetectionContent('start')">
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

