package menuservicetest;
import sanbox.restaurant.*;


public class CourseCRUDProxy {

    public Integer create(Course c) throws Exception {
        CourseCRUDService service = new CourseCRUDService();
        CourseCRUD port = service.getCourseCRUDPort();
        java.lang.Integer result = port.create(c);
        return result;
    }

    public Course read(Integer ref) {
        CourseCRUDService service = new CourseCRUDService();
        CourseCRUD port = service.getCourseCRUDPort();
        return port.read(ref);
    }

   
    public void update(Course c) {
        CourseCRUDService service = new CourseCRUDService();
        CourseCRUD port = service.getCourseCRUDPort();
        port.update(c);
    }

    
    public void delete(Course c) {
        CourseCRUDService service = new CourseCRUDService();
        CourseCRUD port = service.getCourseCRUDPort();
        port.delete(c);
    }

}
