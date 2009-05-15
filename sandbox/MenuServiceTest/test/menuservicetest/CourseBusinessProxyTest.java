package menuservicetest;

import org.junit.Test;
import sanbox.restaurant.Course;
import static org.junit.Assert.*;
import java.util.Random;

public class CourseBusinessProxyTest {

    private Course buildUnique() {
        Course c = new Course();
        Random bag = new Random();
        bag.setSeed(System.currentTimeMillis());
        c.setKind("kind_" + Math.abs(bag.nextInt()));
        c.setName("name_" + Math.abs(bag.nextInt()));
        return c;
    }

    @Test
    public void testGetAvailableKinds() throws Exception {
        CourseBusinessProxy proxy = new CourseBusinessProxy();
        int size = proxy.getAvailableKinds().length;
        Course c1 = buildUnique();
        CourseCRUDProxy crud = new CourseCRUDProxy();
        crud.create(c1);
        int newSize = proxy.getAvailableKinds().length;
        assertTrue("Bad kind cardinality", size + 1 == newSize);
    }
}