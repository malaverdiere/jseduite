package helpers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.Menu;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.Course;

public class MenuHelper {

    public static XMLGregorianCalendar toXmlCalendar(Date d) {
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(d);
            DatatypeFactory factory = DatatypeFactory.newInstance();
            return factory.newXMLGregorianCalendar(calendar);
        } catch(Exception e) {
            return null;
        }
   }

    public static Date toDate(XMLGregorianCalendar xml) {
        return xml.toGregorianCalendar().getTime();
    }

    public static boolean dateEquality(Date d1, Date d2) {
        GregorianCalendar c1 = new GregorianCalendar();
        c1.setTime(d1);
        GregorianCalendar c2 = new GregorianCalendar();
        c2.setTime(d2);
        boolean years = c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
        boolean months = c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH);
        boolean days = c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
        return years && months && days;
    }

       public static boolean isEqualsTo(Menu m1, Menu m2) {

        if (! dateEquality(toDate(m1.getDate()),toDate(m2.getDate())))
            return false;
        if (m1.getCourses().size() != m2.getCourses().size())
            return false;
        Course[] c1 = m1.getCourses().toArray(new Course[m1.getCourses().size()]);
        Course[] c2 = m2.getCourses().toArray(new Course[m2.getCourses().size()]);
        for(int i = 0; i < c1.length; i++) { // Order is undefined
            boolean ok = false;
            for(int j = 0; j < c2.length; j++) {
                if (CourseHelper.courseEquality(c1[i], c2[j])) {
                    ok = true; break;
                }
            }
            if (!ok)
                return false;
        }
        return true;
    }

}
