package menuservicetest;

import org.junit.Test;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.Course;
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
    public void testFindByKindOne() throws Exception {
       String unknown = genSym("kind");
       Course c = new Course();
       c.setKind(unknown); c.setName(genSym("name"));
       crud.create(c);
       Course[] set = this.proxy.findByKind(unknown);
       assertTrue("Bad set cardinality",set.length == 1);
    }
    
    @Test
    public void testFindByKindRegular() throws Exception {
       String unknown = genSym("kind");
       Course c = new Course();
       c.setKind(unknown);
       int quantity = bag.nextInt(10)+2;
       for(int i = 0; i < quantity ; i++) {
         c.setName(genSym("findByKindRegular_name"));
         crud.create(c);
       }
       Course[] set = this.proxy.findByKind(unknown);
       assertTrue("Bad set cardinality",set.length == quantity);
    }

    @Test
    public void testFindByNameNull() throws Exception {
       String unknown = genSym("name");
       Course c =  this.proxy.findByName(unknown);
       assertNull("Should not return something",c);
    }

    @Test
    public void testFindByNameRegular() throws Exception {
       String unknown = genSym("name");
       Course c = new Course();
       c.setKind(genSym("kind")); c.setName(unknown);
       crud.create(c);
       Course cPrime = this.proxy.findByName(unknown);
       assertNotNull("Should retrieve something",cPrime);
    }


}