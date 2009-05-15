package menuservicetest;


import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import sanbox.restaurant.Course;

public class CourseCRUDProxyTest {

    /** Helper Code about 'Course' **/

    private boolean isEqualsTo(Course c1, Course c2) {
        return c1.getId() == c2.getId()
               && c1.getKind().equals(c2.getKind())
               && c1.getName().equals(c2.getName());
    }
    
    private void makeSlightlyDifferent(Course c) {
        Random bag = new Random();
        bag.setSeed(System.currentTimeMillis());
        c.setName(c.getName() + "_" + bag.nextInt());
    }

    private Course quasiClone(Course c) {
        Course cPrime = new Course();
        cPrime.setId(c.getId());
        cPrime.setKind(c.getKind());
        cPrime.setName(c.getName());
        makeSlightlyDifferent(cPrime);
        return cPrime;
    }

    private int nextRef(int ref) {
        return ref + 1;
    }

    /** Global Attributes for this test suite **/
    private CourseCRUDProxy proxy = new CourseCRUDProxy();
    private Course typical;
    
    public CourseCRUDProxyTest() {
        this.typical = new Course();
        this.typical.setKind("starter");
        this.typical.setName("salad");
    }


    /** Test Implementation **/

    @Test(expected=RuntimeException.class)
    public void testNullCreation() throws Exception {
        proxy.create(null);
    }

    @Test(expected=RuntimeException.class)
    public void testReCreation()  throws Exception {
        Course c = this.quasiClone(typical);
        int ref = proxy.create(c);
        Course cPrime = proxy.read(ref);
        proxy.create(cPrime);
    }

    @Test
    public void testEffectiveCreation() throws Exception {
        Course c = this.quasiClone(typical);
        int ref = proxy.create(c);
        Course cPrime = proxy.read(ref);
        c.setId(cPrime.getId());
        assertTrue("Creation is not effective !", isEqualsTo(c, cPrime));
    }

    @Test(expected=RuntimeException.class)
    public void testNullRead() throws Exception {
        this.proxy.read(null);
    }

    @Test(expected=RuntimeException.class)
    public void testUnExistingRefRead() throws Exception {
        Course c = this.quasiClone(typical);
        int r = proxy.create(c);
        proxy.read(nextRef(r));
    }

    @Test(expected=RuntimeException.class)
    public void testNullUpdate() throws Exception {
        this.proxy.update(null);
    }

    @Test(expected=RuntimeException.class)
    public void testTransientUpdate() throws Exception {
        Course c = this.quasiClone(typical);
        this.proxy.update(c);
    }

    @Test
    public void testEffectiveUpdate() throws Exception {
        Course original = this.quasiClone(typical);
        int r = proxy.create(original);
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
    public void testTransientDelete() throws Exception {
        Course c = this.quasiClone(typical);
        this.proxy.delete(c);
    }

    @Test(expected=RuntimeException.class)
    public void testEffectiveDelete() throws Exception {
        Course c = this.quasiClone(typical);
        int r = proxy.create(c);
        Course cPrime = proxy.read(r);
        proxy.delete(cPrime);
        proxy.read(r);
    }

}