
/**
 * This file is part of jSeduite::BreakTimeManager
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::Web Scrapper is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::Twitter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::InternalNews; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
 * @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 **/
package cn.m1.hebut.jSeduite.technical.webscrapper;

import java.io.BufferedReader;


import java.io.InputStreamReader;
import java.net.URL;

import java.net.URLConnection;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;




@WebService()
public class WebScrapper {

 
    @WebMethod(operationName = "fetch")
    public String fetch(@WebParam(name = "url")
    URL url) {
              try {
            URLConnection conn = url.openConnection();
            String encoding = conn.getContentEncoding();
            if (encoding == null) {
                encoding = "ISO-8859-1";
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
            StringBuilder sb = new StringBuilder(16384);
            try {
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append('\n');
                }
            } finally {
                br.close();
            }
            return sb.toString();
        } catch (Exception ex) {
          return "Error URL";
        }
}



    

   @WebMethod(operationName = "scrap")
    public String scrap(@WebParam(name = "url")
    URL url, @WebParam(name = "expression")
    String expr) {
     
try {

    //String expr="<div\\s+id=\"hour\"[^>]*>.*?<div\\s+class=\"result\"[^>]*>"
      //      +".*?</div></div>";
         /*
            String expr = "<td><span\\s+class=\"flagicon\"[^>]*>"
              + ".*?</span><a href=\""
              + "([^\"]+)"      // first piece of data goes up to quote
              + "\"[^>]*>"      // end quote, then skip to end of tag
              + "([^<]+)"       // name is data up to next tag
              + "</a>.*?</td>"; // end a tag, then skip to the td close tag
            /*	try {
            BufferedReader br = new BufferedReader(new InputStreamReader(url
            .openStream()));
            String s = "";
            StringBuffer sb = new StringBuffer("");
            while ((s = br.readLine()) != null) {
            sb.append(s + "\r\n");
            }
            br.close();
            return sb.toString();
            } catch (Exception e) {
            return "error open url:" + url.toString();
            }
             */
            URLConnection conn = url.openConnection();
            String encoding = conn.getContentEncoding();
            if (encoding == null) {
                encoding = "ISO-8859-1";
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
            StringBuilder sb = new StringBuilder(16384);
            try {
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append('\n');
                }
            } finally {
                br.close();
            }


  Pattern patt = Pattern.compile(expr,
  Pattern.DOTALL | Pattern.UNIX_LINES);

 Matcher m = patt.matcher(sb);
String s="";
while (m.find()) {

s = m.group();


        }
        return s;
        }
        catch (Exception ex) {
          return "Error URL";
        }
    }
  
}
