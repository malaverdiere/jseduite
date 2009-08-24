package webadmin.courses.comparators;

import fr.unice.i3s.modalis.jseduite.technical.restaurant.Course;
import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class CourseNameComparatorDesc implements Comparator<Course> {

    public int compare(Course o1, Course o2) {
        return o2.getName().toUpperCase().compareTo(o1.getName().toUpperCase());
    }

}
