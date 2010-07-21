package webadmin.menus;

import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author Steve Colombié
 * @edit Christophe Desclaux (2010)
 */
public class CoursesData {
    private String kind;
    private List<SelectItem> courses;
    private int[] selectedCourses;

    public CoursesData(String kind, List<SelectItem> courses, int[] selectedCourses) {
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

    public int[] getSelectedCourses() {
        return selectedCourses;
    }

    public void setSelectedCourses(int[] selectedCourses) {
        this.selectedCourses = selectedCourses;
    }
}
