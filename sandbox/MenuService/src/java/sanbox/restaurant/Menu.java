package sanbox.restaurant;

import java.util.Date;

public class Menu {
    private Date date;
    private Course[] courses;

    public Menu() {}

    public Menu(Date date, Course[] courses) {
        this.date = date;
        this.courses = courses;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
