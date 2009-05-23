package menuservicetest;
import sanbox.restaurant.*;
import java.util.List;

public class CourseBusinessProxy {

    public String[] getAvailableKinds() throws Exception {
        CourseBusinessService service = new CourseBusinessService();
        CourseBusiness port = service.getCourseBusinessPort();
        List<String> result = port.getAvailableCourseKinds();
        return result.toArray(new String[result.size()]);
    }
    
}
