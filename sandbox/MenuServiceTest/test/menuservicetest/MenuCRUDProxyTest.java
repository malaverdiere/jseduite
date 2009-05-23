package menuservicetest;

import java.util.Calendar;
import java.util.Random;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.*;
import org.junit.Test;
import static org.junit.Assert.*;
import sanbox.restaurant.Menu;
import sanbox.restaurant.Course;

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

    private static Date toDate(XMLGregorianCalendar xml) {
        return xml.toGregorianCalendar().getTime();
    }


    private static boolean dateEquality(Date d1, Date d2) {
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
                if (CourseCRUDProxyTest.courseEquality(c1[i], c2[j])) {
                    ok = true; break;
                }
            }
            if (!ok)
                return false;
        }
        return true;
    }

    private Menu quasiClone(Menu m) {
        Menu mPrime = new Menu();
        mPrime.setDate(toXmlCalendar(getRandomDate()));
        for(Course c: m.getCourses())
            mPrime.getCourses().add(c);
        return mPrime;
    }

    /** Test Implementation **/

    @Test(expected=RuntimeException.class)
    public void testNullCreation() throws Exception {
        proxy.create(null);
    }

    @Test(expected=RuntimeException.class)
    public void testNullList()  throws Exception {
        Menu m = new Menu();
        m.setDate(toXmlCalendar(getRandomDate()));
        proxy.create(m); 
    }

    @Test(expected=RuntimeException.class)
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
        assertTrue("Creation is not effective !", isEqualsTo(m, mPrime));
    }

    @Test(expected=RuntimeException.class)
    public void testNullRead() throws Exception {
        this.proxy.read(null);
    }

    @Test(expected=RuntimeException.class)
    public void testUnExistingRefRead() throws Exception {
        Menu m = this.quasiClone(typical);
        Date r = proxy.create(m);
        proxy.read(getRandomDate()); // should me more deterministic ...
    }

    @Test(expected=RuntimeException.class)
    public void testNullUpdate() throws Exception {
        this.proxy.update(null);
    }

    @Test(expected=RuntimeException.class)
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
        assertTrue("Update is not effective !", isEqualsTo(m, mPrime));
    }

    @Test(expected=RuntimeException.class)
    public void testNullDelete() throws Exception {
        this.proxy.delete(null);
    }

    @Test(expected=RuntimeException.class)
    public void testUnreferencedtDelete() throws Exception {
        Menu m = this.quasiClone(typical);
        m.setDate(null);
        this.proxy.delete(m);
    }

    @Test(expected=RuntimeException.class)
    public void testEffectiveDelete() throws Exception {
        Menu m = this.quasiClone(typical);
        Date r = proxy.create(m);
        Menu mPrime = proxy.read(r);
        proxy.delete(mPrime);
        proxy.read(r);
    }


}