package menuservicetest;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.*;

public class CourseFinderProxy {

    public Course[] findByKind(String kind) throws Exception {
        CourseFinderService service = new CourseFinderService();
        CourseFinder port = service.getCourseFinderPort();
        java.util.List<Course> result = port.findCoursesByKind(kind);
        return result.toArray(new Course[result.size()]);
    }

    public Course findByName(String name) throws Exception {
        CourseFinderService service = new CourseFinderService();
        CourseFinder port = service.getCourseFinderPort();
        return port.findCourseByName(name);
    }

    public String[] getAllCoursesReferences() throws Exception {
        CourseFinderService service = new CourseFinderService();
        CourseFinder port = service.getCourseFinderPort();
        java.util.List<String> result = port.getAllCoursesReferences();
        return result.toArray(new String[result.size()]);
    }
}
