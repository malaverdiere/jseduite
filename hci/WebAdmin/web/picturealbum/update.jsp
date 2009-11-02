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
                    var link = document.getElementById('content:directLink').value;

                    if(link.indexOf("picasa") > 0) {
                        document.getElementById("content:form:repository").selectedIndex = 1;
                        flickrDetection();

                        var name = link.indexOf("/", link.indexOf("picasa"))+1;
                        var album = link.indexOf("/", name)+1;

                        var albumEnd = link.indexOf("?", album);
                        if(albumEnd == -1) {
                            albumEnd = link.indexOf("#", album);

                            if(albumEnd == -1) {
                                albumEnd = link.length;
                            }
                        }

                        var key = link.indexOf("authkey=", albumEnd)+8;
                        var keyEnd = link.indexOf("&", key);

                        document.getElementById('content:form:user').value=link.substring(name, album-1);
                        document.getElementById('content:form:album').value=link.substring(album, albumEnd);
                        if (key != 7) {
                            document.getElementById('content:form:key').value=link.substring(key, keyEnd);
                        }
                        else {
                            document.getElementById('content:form:key').value="";
                        }
                    }
                    else if (link.indexOf("flickr") > 0) {
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

