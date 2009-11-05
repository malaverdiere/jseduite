/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;
import java.io.*;
import java.net.URL;
import net.fortuna.ical4j.data.*;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.property.*;
import net.fortuna.ical4j.filter.*;

/**
 *
 * @author mosser
 */
public class HyperHelper {


    public static ComponentList restrict(ComponentList cList, Period p) {
        Filter filter = new Filter(new PeriodRule(p));
        return (ComponentList) filter.filter(cList);
    }

    public static  ComponentList retrieveComponents(URL ical)
            throws Exception {
        StringReader sin = new StringReader(getResourceContent(ical));
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

    public static Date readDate(String p, Component c) {
        try {
            DateProperty dp =  (DateProperty) c.getProperty(p);
            return dp.getDate();
        }catch(Exception e) {
            return new Date();
        }
    }


    public static String readString(String p, Component c) {
        try {
            return c.getProperty(p).getValue();
        }catch(Exception e) {
            return "";
        }
    }

}
