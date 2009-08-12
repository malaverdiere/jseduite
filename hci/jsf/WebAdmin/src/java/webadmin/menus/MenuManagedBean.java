package webadmin.menus;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.Course;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.CourseBusiness;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.CourseBusinessService;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.CourseFinder;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.CourseFinderService;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.Menu;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.MenuCRUD;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.MenuCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.MenuFinder;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.MenuFinderService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;
import webadmin.menus.comparators.MenuDateComparator;
import webadmin.menus.comparators.MenuDateComparatorDesc;
import webadmin.util.DateFormat;

/**
 *
 * @author Steve Colombié
 */

public class MenuManagedBean {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/MenuService/MenuFinderService?wsdl")
    MenuFinderService finderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/MenuService/MenuCRUDService?wsdl")
    MenuCRUDService crudService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/MenuService/CourseFinderService?wsdl")
    CourseFinderService courseFinderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/MenuService/CourseBusinessService?wsdl")
    CourseBusinessService courseBusinessService;

    //The list of the menus
    private ArrayList<Menu> menus;

    // The list cardinality
    private int menusCard;

    //The transient menu
    private Menu cMenu = new Menu();
    private Menu uMenu = new Menu();

    //The date
    private Date date = new Date();

    //The current ID
    private XMLGregorianCalendar id = new XMLGregorianCalendarImpl();

    //The sorting method
    private int sort = MenuSorter.sortByDateDesc;

    // The courses
    private ArrayList<CoursesData> coursesData;

    // The courses to update
    private ArrayList<CoursesData> coursesDataToUpdate;

    /**
     * Constructor
     */
    public MenuManagedBean () {

    }

    /**
     * Get the menus cardinality
     * @return the menus cardinality
     */
    public int getMenusCard() {
        return menusCard;
    }

    /**
     * Get the created menu
     * @return the created menu
     */
    public Menu getcMenu() {
        return cMenu;
    }

    /**
     * Set the created menu
     * @param m the menu to create
     */
    public void setcMenu(Menu m) {
        this.cMenu = m;
    }

    /**
     * Get the menu to update
     * @return the menu to update
     */
    public Menu getuMenu() {
        return uMenu;
    }

    /**
     * Set the menu to update
     * @param m the menu to update
     */
    public void setuMenu(Menu m) {
        this.uMenu = m;
    }


    /**
     * Get the date
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the date
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get the identifier
     * @return the identifer
     */
    public XMLGregorianCalendar getId() {
        return this.id;

    }

    /**
     * Set the identifier
     * @param i the identifier
     */
    public void setId(XMLGregorianCalendar i) {
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
     * Get the courses
     * @return the list of courses
     */
    public List<CoursesData> getCoursesData() {
        List<String> kinds;
        List<Course> coursesBuf;
        ArrayList<SelectItem> items;

        coursesData = new ArrayList<CoursesData>();

        try {
            this.courseBusinessService = new CourseBusinessService();
            CourseBusiness businessPort = courseBusinessService.getCourseBusinessPort();

            this.courseFinderService = new CourseFinderService();
            CourseFinder finderPort = courseFinderService.getCourseFinderPort();

            kinds = businessPort.getAvailableCourseKinds();
            for(String kind : kinds) {
                coursesBuf = finderPort.findCoursesByKind(kind);

                items = new ArrayList();

                for(Course course : coursesBuf) {
                    items.add(new SelectItem(course.getName(), course.getName()));
                }

                coursesData.add(new CoursesData(kind, items, new String[items.size()]));
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return coursesData;
    }

    /**
     * Get the courses to update
     * @return the list of courses to update
     */
    public List<CoursesData> getCoursesDataToUpdate() {
        List<String> kinds;
        List<Course> coursesBuf;
        ArrayList<SelectItem> items;
        String[] selectedCourses;
        int i;

        coursesDataToUpdate = new ArrayList<CoursesData>();

        try {
            this.courseBusinessService = new CourseBusinessService();
            CourseBusiness businessPort = courseBusinessService.getCourseBusinessPort();

            this.courseFinderService = new CourseFinderService();
            CourseFinder finderPort = courseFinderService.getCourseFinderPort();

            kinds = businessPort.getAvailableCourseKinds();
            for(String kind : kinds) {
                coursesBuf = finderPort.findCoursesByKind(kind);

                items = new ArrayList();
                selectedCourses = new String[coursesBuf.size()];
                i=0;

                for(Course course : coursesBuf) {
                    items.add(new SelectItem(course.getName(), course.getName()));

                    for(Course uCourse : uMenu.getCourses()) {
                        if(uCourse.getName().equals(course.getName())) {
                            selectedCourses[i] = course.getName();
                            break;
                        }
                    }
                    i++;
                }

                coursesDataToUpdate.add(new CoursesData(kind, items, selectedCourses));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return coursesDataToUpdate;
    }

    /**
     * Get the menus
     * @return a list of the menus
     */
    public ArrayList<Menu> getMenus() {
        menus = new ArrayList<Menu>();

        try {
            //Get the menus ids
            this.finderService = new MenuFinderService();
            MenuFinder finderPort = finderService.getMenuFinderPort();
            List<XMLGregorianCalendar> menusIds = finderPort.getAllMenuReferences();

            //Get the menus
            this.crudService = new MenuCRUDService();
            MenuCRUD crudPort = crudService.getMenuCRUDPort();

            for(int i=0; i<menusIds.size(); i++) {
                menus.add(crudPort.readMenu(menusIds.get(i)));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Sorting the values
        switch(sort) {
            case MenuSorter.sortByDateDesc:
            Collections.sort(menus, new MenuDateComparatorDesc());
            break;

            case MenuSorter.sortByDate:
            default:
            Collections.sort(menus, new MenuDateComparator());
            break;
        }

        menusCard = menus.size();

        return menus;
    }


    /**
     * Create a menu
     * @return a string indicating the menus is created
     */
    public String create() {
        try {
            this.crudService = new MenuCRUDService();
            MenuCRUD crud = crudService.getMenuCRUDPort();

            this.courseFinderService = new CourseFinderService();
            CourseFinder finderPort = courseFinderService.getCourseFinderPort();

            cMenu.setDate(DateFormat.toXmlCalendar(date));


            Course course;
            for(CoursesData courses : coursesData) {
                for(String cid : courses.getSelectedCourses()) {
                    course = new Course();
                    course.setName(cid);
                    course.setKind(finderPort.findCourseByName(cid).getKind());

                    cMenu.getCourses().add(course);
                }
            }


            crud.createMenu(cMenu);

            cMenu = new Menu();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        date = new Date();

        return "created";
    }

    /**
     * Cancel modifications
     * @return a string indicating modification/creation is canceled
     */
    public String cancel() {
        date = new Date();

        return "cancel";
    }


    /**
     * Delete the menu corresponding with the identifier
     */
    public void delete() {
        try {

            this.crudService = new MenuCRUDService();
            MenuCRUD crud = crudService.getMenuCRUDPort();

            id.setTime(1,1,1);

            Menu menuToDelete = crud.readMenu(id);
            crud.deleteMenu(menuToDelete);

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
            this.crudService = new MenuCRUDService();
            MenuCRUD crud = crudService.getMenuCRUDPort();

            id.setTime(1,1,1);
            uMenu = crud.readMenu(id);

            date = uMenu.getDate().toGregorianCalendar().getTime();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "update";
    }

    /**
     * Update the uMenu new break
     * @return a string indicating the menu is updated
     */
    public String update() {
        try {
            this.crudService = new MenuCRUDService();
            MenuCRUD crud = crudService.getMenuCRUDPort();

            this.courseFinderService = new CourseFinderService();
            CourseFinder finderPort = courseFinderService.getCourseFinderPort();
            
            uMenu.setDate(DateFormat.toXmlCalendar(date));
            uMenu.getCourses().clear();

            Course course;
            for(CoursesData courses : coursesDataToUpdate) {
                for(String cid : courses.getSelectedCourses()) {
                    course = new Course();
                    course.setName(cid);
                    course.setKind(finderPort.findCourseByName(cid).getKind());

                    uMenu.getCourses().add(course);
                }
            }
            crud.updateMenu(uMenu);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        date = new Date();

        return "updated";
    }

    /**
     * Set the sort method
     * @return a string indicating the menus are ready to be sorted
     */
    public String sortBy() {
        return "sorted";
    }
}
