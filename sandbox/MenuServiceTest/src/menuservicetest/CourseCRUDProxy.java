package menuservicetest;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.*;


public class CourseCRUDProxy {

    public String create(Course c) throws Exception {
        CourseCRUDService service = new CourseCRUDService();
        CourseCRUD port = service.getCourseCRUDPort();
        java.lang.String result = port.createCourse(c);
        return result;
    }

    public Course read(String ref) throws Exception {
        CourseCRUDService service = new CourseCRUDService();
        CourseCRUD port = service.getCourseCRUDPort();
        return port.readCourse(ref);
    }

   
    public void update(Course c) throws Exception {
        CourseCRUDService service = new CourseCRUDService();
        CourseCRUD port = service.getCourseCRUDPort();
        port.updateCourse(c);
    }

    
    public void delete(Course c) throws Exception {
        CourseCRUDService service = new CourseCRUDService();
        CourseCRUD port = service.getCourseCRUDPort();
        port.deleteCourse(c);
    }

}
