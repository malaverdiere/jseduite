package menuservicetest;
import sanbox.restaurant.*;

public class CourseFinderProxy {

    public Course[] findByKind(String kind) throws Exception {
        CourseFinderService service = new CourseFinderService();
        CourseFinder port = service.getCourseFinderPort();
        java.util.List<Course> result = port.findByKind(kind);
        return result.toArray(new Course[result.size()]);
    }

    public Course[] findByName(String name) throws Exception {
        CourseFinderService service = new CourseFinderService();
        CourseFinder port = service.getCourseFinderPort();
        java.util.List<Course> result = port.findByName(name);
        return result.toArray(new Course[result.size()]);
    }
    
}
