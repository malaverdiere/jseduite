package menuservicetest;


import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import sanbox.restaurant.Course;

public class CourseCRUDProxyTest {

    /** Global Attributes for this test suite **/
    private CourseCRUDProxy proxy = new CourseCRUDProxy();
    private Course typical;
    private Random bag = new Random();

    public CourseCRUDProxyTest() {
        bag.setSeed(System.currentTimeMillis());
        this.typical = new Course();
        this.typical.setKind("starter");
        this.typical.setName("salad");
    }

    public static boolean courseEquality(Course c1, Course c2) {
        boolean nameEquality = c1.getName().equals(c2.getName());
        boolean kindEquality = c1.getKind().equals(c2.getKind());
        return nameEquality && kindEquality ;
    }

    /** Helper Code about 'Course' **/

    private boolean isEqualsTo(Course c1, Course c2) {
       return courseEquality(c1, c2);
    }
    
    private void makeSlightlyDifferent(Course c) {
        c.setName(nextRef(c.getName()));
    }

    private Course quasiClone(Course c) {
        Course cPrime = new Course();
        cPrime.setKind(c.getKind());
        cPrime.setName(c.getName());
        makeSlightlyDifferent(cPrime);
        return cPrime;
    }

    private String nextRef(String ref) {
        return ref + "_" + Math.abs(bag.nextInt());
    }

    /** Test Implementation **/

    @Test(expected=RuntimeException.class)
    public void testNullCreation() throws Exception {
        proxy.create(null);
    }

    @Test(expected=RuntimeException.class)
    public void testReCreation()  throws Exception {
        Course c = this.quasiClone(typical);
        String ref = proxy.create(c);
        Course cPrime = proxy.read(ref);
        proxy.create(cPrime);
    }

    @Test
    public void testEffectiveCreation() throws Exception {
        Course c = this.quasiClone(typical);
        String ref = proxy.create(c);
        Course cPrime = proxy.read(ref);
        assertTrue("Creation is not effective !", isEqualsTo(c, cPrime));
    }

    @Test(expected=RuntimeException.class)
    public void testNullRead() throws Exception {
        this.proxy.read(null);
    }

    @Test(expected=RuntimeException.class)
    public void testUnExistingRefRead() throws Exception {
        Course c = this.quasiClone(typical);
        String r = proxy.create(c);
        proxy.read(nextRef(r));
    }

    @Test(expected=RuntimeException.class)
    public void testNullUpdate() throws Exception {
        this.proxy.update(null);
    }

    @Test(expected=RuntimeException.class)
    public void testUnreferencedUpdate() throws Exception {
        Course c = this.quasiClone(typical);
        c.setName(null);
        this.proxy.update(c);
    }

    @Test
    public void testEffectiveUpdate() throws Exception {
        Course original = this.quasiClone(typical);
        String r = proxy.create(original);
        Course c1 = proxy.read(r);
        c1.setKind("main");
        proxy.update(c1);
        Course c2 = proxy.read(r);
        assertTrue("Update is not effective !", isEqualsTo(c1, c2));
    }

    @Test(expected=RuntimeException.class)
    public void testNullDelete() throws Exception {
        this.proxy.delete(null);
    }

    @Test(expected=RuntimeException.class)
    public void testUnreferencedtDelete() throws Exception {
        Course c = this.quasiClone(typical);
        c.setName(null);
        this.proxy.delete(c);
    }

    @Test(expected=RuntimeException.class)
    public void testEffectiveDelete() throws Exception {
        Course c = this.quasiClone(typical);
        String r = proxy.create(c);
        Course cPrime = proxy.read(r);
        proxy.delete(cPrime);
        proxy.read(r);
    }
}