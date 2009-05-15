package menuservicetest;
import sanbox.restaurant.*;


public class CourseCRUDProxy {

    public Integer create(Course c) throws Exception {
        try {
            CourseCRUDService service = new CourseCRUDService();
            CourseCRUD port = service.getCourseCRUDPort();
            java.lang.Integer result = port.create(c);
            return result;
        } catch (Exception ex) {
           throw new RuntimeException(ex.getMessage());
        }
    }

    public Course read(Integer ref) {
        try { 
            CourseCRUDService service = new CourseCRUDService();
            CourseCRUD port = service.getCourseCRUDPort();
            return port.read(ref);
        } catch (Exception ex) {
           throw new RuntimeException(ex.getMessage());
        }
    }

   
    public void update(Course c) {
        try {
            CourseCRUDService service = new CourseCRUDService();
            CourseCRUD port = service.getCourseCRUDPort();
            port.update(c);
        } catch (Exception ex) {
           throw new RuntimeException(ex.getMessage());
        }
    }

    
    public void delete(Course c) {
        try {
            CourseCRUDService service = new CourseCRUDService();
            CourseCRUD port = service.getCourseCRUDPort();
            port.delete(c);
        } catch (Exception ex) {
           throw new RuntimeException(ex.getMessage());
        }
    }

}
