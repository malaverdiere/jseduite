/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.i3s.modalis.jSeduite.technical.calendar;
import java.io.*;
import java.net.URL;
import net.fortuna.ical4j.data.*;
import net.fortuna.ical4j.model.*;

/**
 *
 * @author mosser
 */
public class ICalHelper {


    public static ComponentList buildComponents(URL url) throws Exception {
        StringReader sin = new StringReader(getResourceContent(url));
        CalendarBuilder builder = new CalendarBuilder();
        Calendar c = builder.build(sin);
        return c.getComponents(Component.VEVENT);
    }
    
     private static String getResourceContent(URL url) throws Exception  {
       InputStream is = url.openStream();
       BufferedReader bread = new BufferedReader(new InputStreamReader(is));
       String line = "";
       StringBuilder buff = new StringBuilder();
       while((line = bread.readLine()) != null)
           buff.append(line+"\n");
        return new String(buff.toString().getBytes(),"UTF-8");
    }
}
