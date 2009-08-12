package webadmin.converters;

import fr.unice.i3s.modalis.jseduite.technical.restaurant.Course;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class CoursesConverter implements Converter {


    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        List<Course> courses = (List<Course>) value;
        String result = "";
        
        for(Course course : courses) {
            result += course.getName()+"<br/>";
        }

        return result.substring(0, result.length()-5);
    }
}
