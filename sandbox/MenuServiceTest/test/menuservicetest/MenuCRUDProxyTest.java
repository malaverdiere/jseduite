package menuservicetest;

import helpers.*;
import java.util.Random;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.Menu;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.Course;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.RestaurantException_Exception;


public class MenuCRUDProxyTest {

    /** Global Attributes for this test suite **/
    private MenuCRUDProxy proxy = new MenuCRUDProxy();
    private CourseCRUDProxy courseProxy = new CourseCRUDProxy();
    private Menu typical;
    private Random bag = new Random();

    public MenuCRUDProxyTest() {
        bag.setSeed(System.currentTimeMillis());
        this.typical = new Menu();
        Course c1 = new Course();
        c1.setKind("starter_" + Math.abs(bag.nextInt()));
        c1.setName("starter_name_" + Math.abs(bag.nextInt()));
        Course c2 = new Course();
        c2.setKind("main_" + Math.abs(bag.nextInt()));
        c2.setName("main_name_" + Math.abs(bag.nextInt()));
        Course c3 = new Course();
        c3.setKind("desert_" + Math.abs(bag.nextInt()));
        c3.setName("desert_name_" + Math.abs(bag.nextInt()));
        try {
            courseProxy.create(c1);
            courseProxy.create(c2);
            courseProxy.create(c3);
        }catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        this.typical.getCourses().add(c1);
        this.typical.getCourses().add(c2);
        this.typical.getCourses().add(c3);
    }

    /** Helper Code about 'Course' **/

    private Date getRandomDate() {
        Date d = new Date(bag.nextLong());
        /// TODO: use another method
        d.setYear(2000 + bag.nextInt(200));
        return d;
    }
    
    private Menu quasiClone(Menu m) {
        Menu mPrime = new Menu();
        mPrime.setDate(MenuHelper.toXmlCalendar(getRandomDate()));
        for(Course c: m.getCourses())
            mPrime.getCourses().add(c);
        return mPrime;
    }

    /** Test Implementation **/

    @Test(expected=RestaurantException_Exception.class)
    public void testNullCreation() throws Exception {
        proxy.create(null);
    }

    @Test(expected=RestaurantException_Exception.class)
    public void testNullList()  throws Exception {
        Menu m = new Menu();
        m.setDate(MenuHelper.toXmlCalendar(getRandomDate()));
        proxy.create(m); 
    }

    @Test(expected=RestaurantException_Exception.class)
    public void testReCreation()  throws Exception {
        Menu m = this.quasiClone(typical);
        Date ref = proxy.create(m);
        Menu mPrime = proxy.read(ref);
        proxy.create(mPrime);
    }
    
    @Test
    public void testEffectiveCreation() throws Exception {
        Menu m = this.quasiClone(typical);
        Date ref = proxy.create(m);
        Menu mPrime = proxy.read(ref);
        assertTrue("Creation is not effective !", MenuHelper.isEqualsTo(m, mPrime));
    }

    @Test(expected=RestaurantException_Exception.class)
    public void testNullRead() throws Exception {
        this.proxy.read(null);
    }

    @Test(expected=RestaurantException_Exception.class)
    public void testUnExistingRefRead() throws Exception {
        Menu m = this.quasiClone(typical);
        Date r = proxy.create(m);
        proxy.read(getRandomDate()); // should me more deterministic ...
    }

    @Test(expected=RestaurantException_Exception.class)
    public void testNullUpdate() throws Exception {
        this.proxy.update(null);
    }

    @Test(expected=RestaurantException_Exception.class)
    public void testUnreferencedUpdate() throws Exception {
        Menu c = this.quasiClone(typical);
        c.setDate(null);
        this.proxy.update(c);
    }

    @Test
    public void testEffectiveUpdate() throws Exception {
        Menu original = this.quasiClone(typical);
        Date r = proxy.create(original);
        Menu m = proxy.read(r);

        Course c = new Course();
        String name = "update_name_"+Math.abs(bag.nextInt());
        c.setKind("update_main_"+Math.abs(bag.nextInt()));
        c.setName(name);
        courseProxy.create(c);
        
        m.getCourses().add(c);
        proxy.update(m);
        Menu mPrime = proxy.read(r);
        assertTrue("Update is not effective !", MenuHelper.isEqualsTo(m, mPrime));
    }

    @Test(expected=RestaurantException_Exception.class)
    public void testNullDelete() throws Exception {
        this.proxy.delete(null);
    }

    @Test(expected=RestaurantException_Exception.class)
    public void testUnreferencedDelete() throws Exception {
        Menu m = this.quasiClone(typical);
        m.setDate(null);
        this.proxy.delete(m);
    }

    @Test(expected=RestaurantException_Exception.class)
    public void testEffectiveDelete() throws Exception {
        Menu m = this.quasiClone(typical);
        Date r = proxy.create(m);
        Menu mPrime = proxy.read(r);
        proxy.delete(mPrime);
        proxy.read(r);
    }


}