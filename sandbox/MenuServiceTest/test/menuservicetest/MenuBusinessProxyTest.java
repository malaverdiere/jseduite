package menuservicetest;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sanbox.restaurant.Course;
import sanbox.restaurant.Menu;


public class MenuBusinessProxyTest {

    private static MenuCRUDProxy menuCrud = new MenuCRUDProxy();
    private static CourseCRUDProxy courseCrud = new CourseCRUDProxy();
    private static MenuBusinessProxy proxy = new MenuBusinessProxy();
    private static Random bag = new Random();
    
    private static XMLGregorianCalendar toXmlCalendar(Date d) {
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(d);
            DatatypeFactory factory = DatatypeFactory.newInstance();
            return factory.newXMLGregorianCalendar(calendar);
         } catch(Exception e) {
             throw new RuntimeException(e.getMessage());
         }
    }

    private static Menu m;
    @BeforeClass
    public static void fill() throws Exception {
        m = new Menu();
        m.setDate(toXmlCalendar(new Date()));
        Course c = new Course();
        c.setKind("today_starter_" + Math.abs(bag.nextInt()));
        c.setName("today_name_" + Math.abs(bag.nextInt()));
        courseCrud.create(c);
        m.getCourses().add(c);
        menuCrud.create(m);
    }

    @AfterClass
    public static void release() throws Exception{
        menuCrud.delete(m);
    }

    @Test
    public void testGetTodayMenu() throws Exception {
        Menu today = proxy.getTodayMenu();
        assertTrue("must be equals !",MenuCRUDProxyTest.isEqualsTo(m, today));
    }

}