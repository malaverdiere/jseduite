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
                function directLink()
                {
                    var flickr = "http://www.flickr.com/";
                    var picasa = "http://picasaweb.google.fr/";

                    var link = document.getElementById('content:directLink').value

                    if(link.substring(0, picasa.length) == picasa){
                        document.getElementById("content:form:repository").selectedIndex = 1;
                        flickrDetection();
                        document.getElementById('content:form:user').value=link.substring(picasa.length, link.indexOf("/", picasa.length));
                        document.getElementById('content:form:album').value=link.substring(link.indexOf("/", picasa.length)+1, link.indexOf("?", link.indexOf("/", picasa.length)));
                        if (link.indexOf("authkey=", link.indexOf("/", picasa.length)) != -1) {
                            document.getElementById('content:form:key').value=link.substring(link.indexOf("authkey=", link.indexOf("/", picasa.length))+8, link.indexOf("&", picasa.length));
                        }
                    }
                    else if (link.substring(0, flickr.length) == flickr) {
                        document.getElementById("content:form:repository").selectedIndex = 0;
                        flickrDetection();
                        document.getElementById('content:form:album').value=link.substring(link.lastIndexOf("/", link.length-2)+1, link.length-1);
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

