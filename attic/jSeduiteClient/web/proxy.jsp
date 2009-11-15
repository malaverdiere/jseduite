<?xml  version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.io.*" %>
<%@page import="java.net.*" %>
<%
String name = request.getParameter("display");
if (null == name || name.equals(""))
    name = "default";
URL url = new URL("http://localhost:9080/InformationProviderService/InformationProviderPort/GetMyInformation/run/"+name);
InputStream is = url.openStream();
BufferedReader bread = new BufferedReader(new InputStreamReader(is));
String line = new String(new byte[0], "UTF-8");
StringBuffer buff = new StringBuffer();
while((line = bread.readLine()) != null){
    buff.append(line);
}
out.print(new String(buff.toString().getBytes(),"UTF-8"));
%>
