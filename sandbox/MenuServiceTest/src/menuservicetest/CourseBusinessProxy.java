package menuservicetest;
import sanbox.restaurant.*;

public class CourseBusinessProxy {

    public String[] getAvailableKinds() throws Exception {
        CourseBusinessService service = new CourseBusinessService();
        CourseBusiness port = service.getCourseBusinessPort();
        java.util.List<java.lang.String> result = port.getAvailableKinds();
        return result.toArray(new String[result.size()]);
    }
    
}
