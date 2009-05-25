package menuservicetest;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.*;
import java.util.List;

public class CourseBusinessProxy {

    public String[] getAvailableKinds() throws Exception {
        CourseBusinessService service = new CourseBusinessService();
        CourseBusiness port = service.getCourseBusinessPort();
        List<String> result = port.getAvailableCourseKinds();
        return result.toArray(new String[result.size()]);
    }
    
}
