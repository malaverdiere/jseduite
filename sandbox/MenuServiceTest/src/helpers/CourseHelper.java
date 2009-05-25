package helpers;

import fr.unice.i3s.modalis.jseduite.technical.restaurant.Course;

public class CourseHelper {

    public static boolean courseEquality(Course c1, Course c2) {
        boolean nameEquality = c1.getName().equals(c2.getName());
        boolean kindEquality = c1.getKind().equals(c2.getKind());
        return nameEquality && kindEquality ;
    }
}
