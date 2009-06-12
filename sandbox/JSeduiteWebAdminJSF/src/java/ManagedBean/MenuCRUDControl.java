/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean;


import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.util.List;
import javax.xml.ws.WebServiceRef;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.*;
import java.util.ArrayList;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 *
 * @author ARNOUX Pierre & GENTILE Xavier
 *
 * Managed Bean where we can create, read and delete a menu
 */

public class MenuCRUDControl {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/sandbox/MenuService/MenuCRUDService?wsdl")
    MenuCRUDService service;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/sandbox/MenuService/CourseFinderService?wsdl")
    CourseFinderService service2;

    private Menu createdMenu = new Menu();

    private XMLGregorianCalendar createdDate = new XMLGregorianCalendarImpl();

    private List<String> createdNameCourses = new ArrayList();

    private List<Course> createdCourses = new ArrayList();

    private Menu readMenu = new Menu();

    private XMLGregorianCalendar readDate = new XMLGregorianCalendarImpl();

    private Menu updateMenu = new Menu();

    private String deleteDate;

    private String[] TEST;

    private XMLGregorianCalendar deletedDate = new XMLGregorianCalendarImpl();

    private Menu deleteMenu = new Menu();

    /** Creates a new instance of MenuCRUDControl */
    public MenuCRUDControl() {
    }


     public List<Course> getCreatedCourses() {
        return createdCourses;
    }

    public void setCreatedCourses(List<Course> createdCourses) {
        this.createdCourses = createdCourses;
    }

    public List<String> getCreatedNameCourses() {
        return createdNameCourses;
    }

    public void setCreatedNameCourses(List<String> createdNameCourses) {
        this.createdNameCourses = createdNameCourses;
    }
    
    public XMLGregorianCalendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(XMLGregorianCalendar createdDate) {
        this.createdDate = createdDate;
    }

    public Menu getCreatedMenu() {
        return createdMenu;
    }

    public void setCreatedMenu(Menu createdMenu) {
        this.createdMenu = createdMenu;
    }

    public void createMenu(){
        try{

         this.service = new MenuCRUDService();
         MenuCRUD port = service.getMenuCRUDPort();

         this.service2 = new CourseFinderService();
         CourseFinder port2 = service2.getCourseFinderPort();

         for(int i=0; i<createdNameCourses.size(); i++){
                createdCourses.add(port2.findCourseByName(createdNameCourses.get(i)));
         }

         createdDate.setTime(1,1,1);
         createdMenu.setDate(createdDate);
         createdMenu.getCourses().addAll(createdCourses);
         port.createMenu(createdMenu);
         this.createdMenu = new Menu();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Menu getReadMenu() {
        return readMenu;
    }

    public void setReadCourse(Menu readMenu) {
        this.readMenu = readMenu;
    }

    public XMLGregorianCalendar getReadDate() {
        return readDate;
    }

    public void setReadDate(XMLGregorianCalendar readDate) {
        this.readDate = readDate;
    }


    public void readMenu() {
         try {
           this.service = new MenuCRUDService();
           MenuCRUD port = service.getMenuCRUDPort();
           readDate.setTime(1,1,1);
           readMenu = port.readMenu(readDate);

       } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Menu getUpdateMenu() {
        return updateMenu;
    }

    public void setUpdateMenu(Menu updateMenu) {
        this.updateMenu = updateMenu;
    }

    public void updateMenu() {
        try {
           this.service = new MenuCRUDService();
           MenuCRUD port = service.getMenuCRUDPort();
           port.updateMenu(updateMenu);

       } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Menu getDeleteMenu() {
        return deleteMenu;
    }

    public void setDeleteMenu(Menu deleteMenu) {
        this.deleteMenu = deleteMenu;
    }

    public String getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }


    public XMLGregorianCalendar getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(XMLGregorianCalendar deletedDate) {
        this.deletedDate = deletedDate;
    }


    public void deleteMenu() {
          try {
           this.service = new MenuCRUDService();
           MenuCRUD port = service.getMenuCRUDPort();

           String[] date;
           date=deleteDate.split(" ", 3);
           TEST=deleteDate.split(" ", 3);

           deletedDate.setYear(Integer.parseInt(date[0]));
           deletedDate.setMonth(Integer.parseInt(date[1]));
           deletedDate.setDay(Integer.parseInt(date[2]));
           deletedDate.setTime(1, 1, 1);

           deleteMenu = port.readMenu(deletedDate);
           port.deleteMenu(deleteMenu);

       } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
