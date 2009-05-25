package menuservicetest;

import helpers.*;
import java.util.Date;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.Menu;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.Course;


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
       m.setDate(MenuHelper.toXmlCalendar(d));
       m.getCourses().add(c1);
       menuCrud.create(m);

       Menu mPrime = proxy.findByDate(d);
       assertNotNull("Should return something",mPrime);
   }
}