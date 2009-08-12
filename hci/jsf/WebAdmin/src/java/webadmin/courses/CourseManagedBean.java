package webadmin.courses;

import fr.unice.i3s.modalis.jseduite.technical.restaurant.Course;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.CourseFinder;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.CourseFinderService;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.CourseCRUD;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.CourseCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.CourseBusiness;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.CourseBusinessService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.xml.ws.WebServiceRef;
import webadmin.courses.comparators.*;
import webadmin.util.Bundle;

/**
 *
 * @author Steve Colombié
 */

public class CourseManagedBean {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/MenuService/CourseFinderService?wsdl")
    CourseFinderService finderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/MenuService/CourseCRUDService?wsdl")
    CourseCRUDService crudService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/MenuService/CourseBusinessService?wsdl")
    CourseBusinessService businessService;

    //The list of the courses
    private ArrayList<Course> courses;

    // The list cardinality
    private int coursesCard;

    //The transient course
    private Course cCourse = new Course();
    private Course uCourse = new Course();

    //The current ID
    private String id = "";

    //The sorting method
    private int sort = CourseSorter.sortByName;

    // The kinds
    private List<SelectItem> kinds;

    // ALternative kind
    private String alterKind;

    /**
     * Constructor
     */
    public CourseManagedBean () {

    }

    /**
     * Get the courses cardinality
     * @return the courses cardinality
     */
    public int getCoursesCard() {
        return coursesCard;
    }

    /**
     * Get the created course
     * @return the created course
     */
    public Course getcCourse() {
        return cCourse;
    }

    /**
     * Set the created course
     * @param c the course to create
     */
    public void setcCourse(Course c) {
        this.cCourse = c;
    }

    /**
     * Get the course to update
     * @return the course to update
     */
    public Course getuCourse() {
        return uCourse;
    }

    /**
     * Set the course to update
     * @param c the course to update
     */
    public void setuCourse(Course c) {
        this.uCourse = c;
    }

    /**
     * Get the identifier
     * @return the identifer
     */
    public String getId() {
        return this.id;

    }

    /**
     * Set the identifier
     * @param i the identifier
     */
    public void setId(String i) {
        this.id = i;
    }

    /**
     * Get the sort method
     * @return the sort method
     */
    public int getSort() {
        return this.sort;

    }

    /**
     * Set the sort method
     * @param i the sort method
     */
    public void setSort(int s) {
        this.sort = s;
    }

    /**
     * Get the alternative kind
     * @return the alternative kind
     */
    public String getAlterKind() {
        return alterKind;
    }

    /**
     * Set the alternative kind
     * @param alterKind the alternative kind
     */
    public void setAlterKind(String alterKind) {
        this.alterKind = alterKind;
    }





    /**
     * Get the courses
     * @return a list of the courses
     */
    public ArrayList<Course> getCourses() {
        courses = new ArrayList<Course>();

        try {
            //Get the courses ids
            this.finderService = new CourseFinderService();
            CourseFinder finderPort = finderService.getCourseFinderPort();
            List<String> coursesIds = finderPort.getAllCoursesReferences();

            //Get the courses
            this.crudService = new CourseCRUDService();
            CourseCRUD crudPort = crudService.getCourseCRUDPort();

            for(int i=0; i<coursesIds.size(); i++) {
                courses.add(crudPort.readCourse(coursesIds.get(i)));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Sorting the values
        switch(sort) {
            case CourseSorter.sortByKind:
            Collections.sort(courses, new CourseKindComparator());
            break;

            case CourseSorter.sortByNameDesc:
            Collections.sort(courses, new CourseNameComparatorDesc());
            break;

            case CourseSorter.sortByKindDesc:
            Collections.sort(courses, new CourseKindComparatorDesc());
            break;

            case CourseSorter.sortByName:
            default:
            Collections.sort(courses, new CourseNameComparator());
            break;
        }

        coursesCard = courses.size();

        return courses;
    }

    /**
     * Get the kinds
     * @return the list of kinds
     */
    public List<SelectItem> getKinds() {
        List<String> kindsBuf;
        kinds = new ArrayList<SelectItem>();

        try {
            this.businessService = new CourseBusinessService();
            CourseBusiness port = businessService.getCourseBusinessPort();
            kindsBuf = port.getAvailableCourseKinds();

            for (String kind : kindsBuf) {
                SelectItem item = new SelectItem(kind, kind);
                kinds.add(item);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        kinds.add(new SelectItem("other", Bundle.get("FORM_OTHER")));

        return kinds;
    }


    /**
     * Create a course
     * @return a string indicating the courses is created
     */
    public String create() {
        try {

            this.crudService = new CourseCRUDService();
            CourseCRUD crud = crudService.getCourseCRUDPort();

            if(cCourse.getKind().equals("other")) {
                cCourse.setKind(alterKind);
            }

            crud.createCourse(cCourse);

            cCourse = new Course();
            alterKind = "";

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "created";
    }

    /**
     * Cancel modifications
     * @return a string indicating modification/creation is canceled
     */
    public String cancel() {
        return "cancel";
    }


    /**
     * Delete the course corresponding with the identifier
     */
    public void delete() {
        try {
            this.crudService = new CourseCRUDService();
            CourseCRUD crud = crudService.getCourseCRUDPort();

            Course courseToDelete = crud.readCourse(id);
            crud.deleteCourse(courseToDelete);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initiate the update process
     * @return a string indicating the update is ready to be done
     */
    public String goUpdate()
    {
        try {
            this.crudService = new CourseCRUDService();
            CourseCRUD crud = crudService.getCourseCRUDPort();

            uCourse = crud.readCourse(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "update";
    }

    /**
     * Update the uCourse new break
     * @return a string indicating the course is updated
     */
    public String update() {
        try {
            this.crudService = new CourseCRUDService();
            CourseCRUD crud = crudService.getCourseCRUDPort();

            if(uCourse.getKind().equals("other")) {
                uCourse.setKind(alterKind);
            }

            crud.updateCourse(uCourse);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        alterKind = "";

        return "updated";
    }

    /**
     * Set the sort method
     * @return a string indicating the courses are ready to be sorted
     */
    public String sortBy() {
        return "sorted";
    }
}
