package ManagedBean;

import java.util.List;
import javax.xml.ws.WebServiceRef;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.*;
import java.util.ArrayList;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;



/**
 *
 * @author ARNOUX Pierre & GENTILE Xavier
 *
 * Managed Bean where we can get all courses name, all courses kind, search courses by kind, by name
 */

public class FinderControl {

   @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/sandbox/MenuService/CourseFinderService?wsdl")
   CourseFinderService service;
    
   
   private List<String> courses;

   private String platByName;

   private String platByKind;

   private Course courseByName = new Course();

   private List<Course> courseByKind;

   private List<SelectItem> coursesKinds;

   private List<SelectItem> coursesNames;


    public List<SelectItem> getCoursesNames() {
         try{
         this.service = new CourseFinderService();
            CourseFinder port = service.getCourseFinderPort();
            courses = port.getAllCoursesReferences();
            coursesNames = new ArrayList();
            for(int i=0; i<courses.size(); i++){
                SelectItem selectItem = new SelectItem(courses.get(i),courses.get(i));
                coursesNames.add(selectItem);

            }
         }catch (Exception e) {
            e.printStackTrace();
        }

        return coursesNames;
    }

    public void setCoursesNames(List<SelectItem> coursesNames) {
        this.coursesNames = coursesNames;
    }
   

    public List<SelectItem> getCoursesKinds() {
        try{
         this.service = new CourseFinderService();
            CourseFinder port = service.getCourseFinderPort();
            courses = port.getAllCoursesReferences();
            List<String> kinds = new ArrayList();

            for(int j=0; j<courses.size(); j++){
                    if(!kinds.contains(port.findCourseByName(courses.get(j)).getKind())){
                        // If the list of kinds doesn't contain that kind so add it
                        kinds.add(port.findCourseByName(courses.get(j)).getKind());
                    }
            }
            coursesKinds = new ArrayList();
            for(int i=0; i<kinds.size(); i++){
                SelectItem selectItem = new SelectItem(kinds.get(i),kinds.get(i));
                coursesKinds.add(selectItem);

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return coursesKinds;

    }

    public void setCoursesKinds(List<SelectItem> coursesKinds) {
        this.coursesKinds = coursesKinds;
    }



    public void searchByName(){
           try {
           this.service = new CourseFinderService();
           CourseFinder port = service.getCourseFinderPort();
           courseByName = port.findCourseByName(platByName);

       } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void searchByKind(){
           try {
           this.service = new CourseFinderService();
           CourseFinder port = service.getCourseFinderPort();
           courseByKind = port.findCoursesByKind(platByKind);

       } catch (Exception e) {
            e.printStackTrace();
        }

    }

   public List<String> getCourses() {
        try  {
            this.service = new CourseFinderService();
            CourseFinder port = service.getCourseFinderPort();
            courses = port.getAllCoursesReferences();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
   }

    public String getPlatByName() {
        return platByName;
    }

    public void setPlatByName(String platByName) {
        this.platByName = platByName;
    }

    public Course getCourseByName() {
       return courseByName;
    }

    public void setCourseByName(Course courseByName) {
        this.courseByName = courseByName;
    }


    public String getPlatByKind() {
        return platByKind;
    }

    public void setPlatByKind(String platByKind) {
        this.platByKind = platByKind;
    }

     public List<Course> getCourseByKind() {
        return courseByKind;
    }

    public void setCourseByKind(List<Course> courseByKind) {
        this.courseByKind = courseByKind;
    }

}
