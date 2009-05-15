package menuservicetest;

import org.junit.Test;
import sanbox.restaurant.Course;
import static org.junit.Assert.*;
import java.util.Random;

public class CourseFinderProxyTest {

    /** Helper Code **/
    private String genSym(String prefix) {
        return prefix + "_" + Math.abs(bag.nextInt());
    }

    /** Global Arguments **/

    private CourseCRUDProxy crud = new CourseCRUDProxy();
    private CourseFinderProxy proxy = new CourseFinderProxy();
    private Random bag = new Random();

    public CourseFinderProxyTest() {
        bag.setSeed(System.currentTimeMillis());
    }
    
    /** Test Implementation **/
    
    @Test
    public void testFindByKindEmptySet() throws Exception {
       String unknown = genSym("kind");
       int size = this.proxy.findByKind(unknown).length;
       assertTrue("Bad set cardinality",size == 0);
    }

    @Test
    public void testFindByKindRegular() throws Exception {
       String unknown = genSym("kind");
       Course c = new Course();
       c.setKind(unknown); c.setName(genSym("name"));
       crud.create(c);
       Course[] set = this.proxy.findByKind(unknown);
       assertTrue("Bad set cardinality",set.length == 1);
    }

    @Test
    public void testFindByNameEmptySet() throws Exception {
       String unknown = genSym("name");
       int size = this.proxy.findByName(unknown).length;
       assertTrue("Bad set cardinality",size == 0);
    }

    @Test
    public void testFindByNameRegular() throws Exception {
       String unknown = genSym("name");
       Course c = new Course();
       c.setKind(genSym("kind")); c.setName(unknown);
       crud.create(c);
       Course[] set = this.proxy.findByName(unknown);
       assertTrue("Bad set cardinality",set.length == 1);
    }


}