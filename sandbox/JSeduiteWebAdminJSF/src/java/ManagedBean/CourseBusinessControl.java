
package ManagedBean;


import java.util.List;
import javax.xml.ws.WebServiceRef;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.*;


/**
 *
 * @author ARNOUX Pierre & GENTILE Xavier
 *
 * Managed Bean where we can get all the courses kind
 */

public class CourseBusinessControl {


    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/sandbox/MenuService/CourseBusinessService?wsdl")
    CourseBusinessService service;

    private List<String> allCoursesKind;

   /** Creates a new instance of CourseBusinessControl */
    public CourseBusinessControl() {
    }

     public List<String> getAllCoursesKind() {
        try  {
            this.service = new CourseBusinessService();
            CourseBusiness port = service.getCourseBusinessPort();
            allCoursesKind = port.getAvailableCourseKinds();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allCoursesKind;
    }

    public void setAllCoursesKind(List<String> allCoursesKind) {
        this.allCoursesKind = allCoursesKind;
    }
}
