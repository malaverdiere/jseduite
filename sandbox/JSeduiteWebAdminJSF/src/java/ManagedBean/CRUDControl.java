package ManagedBean;

import java.util.List;
import javax.xml.ws.WebServiceRef;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.*;

/**
 *
 * @author ARNOUX Pierre & GENTILE Xavier
 *
 * Managed Bean where we can create, read and delete a course
 */

public class CRUDControl {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/sandbox/MenuService/CourseCRUDService?wsdl")
    CourseCRUDService service;

    private Course plat  = new Course();

    private Course readCourse = new Course();

    private String readName;

    private Course updateCourse = new Course();

    private String deleteName;


    public Course getPlat() {
        return plat;
    }

    public void setPlat(Course plat) {
        this.plat = plat;
    }

    
     public void createCourse() throws RestaurantException_Exception{
   
         CourseCRUD port = service.getCourseCRUDPort();

         port.createCourse(plat);
         this.plat = new Course();
    }

     public Course getReadCourse() {
        return readCourse;
    }

    public void setReadCourse(Course readCourse) {
        this.readCourse = readCourse;
    }

     public String getReadName() {
        return readName;
    }

    public void setReadName(String readName) {
        this.readName = readName;
    }

    public void readCourse() {
         try {
           this.service = new CourseCRUDService();
           CourseCRUD port = service.getCourseCRUDPort();
           readCourse = port.readCourse(readName);

       } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Course getUpdateCourse() {
        return updateCourse;
    }

    public void setUpdateCourse(Course updateCourse) {
        this.updateCourse = updateCourse;
    }

    public void updateCourse() {
        try {
           this.service = new CourseCRUDService();
           CourseCRUD port = service.getCourseCRUDPort();
           port.updateCourse(updateCourse);

       } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDeleteName() {
        return deleteName;
    }

    public void setDeleteName(String deleteName) {
        this.deleteName = deleteName;
    }

    public void deleteCourse() {
          try {
           this.service = new CourseCRUDService();
           CourseCRUD port = service.getCourseCRUDPort();
           Course temp = port.readCourse(deleteName);
           port.deleteCourse(temp);

       } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
