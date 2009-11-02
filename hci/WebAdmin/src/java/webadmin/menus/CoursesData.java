package webadmin.menus;

import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author Steve Colombié
 */
public class CoursesData {
    private String kind;
    private List<SelectItem> courses;
    private String[] selectedCourses;

    public CoursesData(String kind, List<SelectItem> courses, String[] selectedCourses) {
        this.kind = kind;
        this.courses = courses;
        this.selectedCourses = selectedCourses;
    }

    public List<SelectItem> getCourses() {
        return courses;
    }

    public void setCourses(List<SelectItem> courses) {
        this.courses = courses;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String[] getSelectedCourses() {
        return selectedCourses;
    }

    public void setSelectedCourses(String[] selectedCourses) {
        this.selectedCourses = selectedCourses;
    }
}
