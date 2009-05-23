package menuservicetest;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;
import sanbox.restaurant.Menu;
import sanbox.restaurant.Course;

public class MenuFinderProxyTest {

   CourseCRUDProxy courseCrud = new CourseCRUDProxy();
   MenuCRUDProxy menuCrud = new MenuCRUDProxy();
   
   MenuFinderProxy proxy = new MenuFinderProxy();
   Random bag = new Random();

   public MenuFinderProxyTest() {
       bag.setSeed(System.currentTimeMillis());;
   }

   private Date gensym() {
       // Should be more deterministic
        Date d = new Date(bag.nextLong());
        d.setYear(2000 + bag.nextInt(200));
        return d;
    }

    private XMLGregorianCalendar toXmlCalendar(Date d) {
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(d);
            DatatypeFactory factory = DatatypeFactory.newInstance();
            return factory.newXMLGregorianCalendar(calendar);
         } catch(Exception e) {
             throw new RuntimeException(e.getMessage());
         }
    }

    /** Test implementation **/
   @Test
   public void testFindByDateEmptySet() throws Exception {
       Date d = gensym();
       Menu m = proxy.findByDate(d);
       assertNull("Should not return something",m);
   }

   @Test(expected=RuntimeException.class)
   public void testFindByDateNull() throws Exception {
      proxy.findByDate(null);
   }

   @Test
   public void testFindByDateRegular() throws Exception {
       Date d = gensym();
       Menu m = new Menu();
       Course c1 = new Course();
       c1.setKind("findByDate_" + Math.abs(bag.nextInt()));
       c1.setName("findByDate_name_" + Math.abs(bag.nextInt()));
       courseCrud.create(c1);
       m.setDate(toXmlCalendar(d));
       m.getCourses().add(c1);
       menuCrud.create(m);

       Menu mPrime = proxy.findByDate(d);
       assertNotNull("Should return something",mPrime);
   }
}