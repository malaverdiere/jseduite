package webadmin.converters;

import fr.unice.i3s.modalis.jseduite.technical.restaurant.Course;
import java.util.ArrayList;
import java.util.HashMap;
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
        HashMap<String, ArrayList<String>> mappedCourses = new HashMap<String, ArrayList<String>>();
        String result = "";
        
        for(Course course : courses) {
            mappedCourses.put(course.getKind(), new ArrayList<String>());
        }

        for(Course course : courses) {
            mappedCourses.get(course.getKind()).add(course.getName());
        }

        for(String kind : mappedCourses.keySet()) {
            result += "<b>"+kind+" : </b>";

            for(String name : mappedCourses.get(kind)) {
                result += name+", ";
            }

            result = result.substring(0, result.length()-2) + "<br/>";
        }

        return result.substring(0, result.length()-5);
    }
}
