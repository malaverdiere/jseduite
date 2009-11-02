package webadmin.converters;

import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import webadmin.util.Bundle;

/**
 *
 * @author Steve Colombié
 */
public class DayListConverter implements Converter {


    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        List<String> list = (List<String>) value;

        String days = "<table><tr>";

        days += "<td";
        if(!list.contains("monday")) {
            days += " class=\"invisible\"";
        }
        days += ">"+Bundle.get("FORM_MONDAY")+"</td>";

        days += "<td";
        if(!list.contains("tuesday")) {
            days += " class=\"invisible\"";
        }
        days += ">"+Bundle.get("FORM_TUESDAY")+"</td>";

        days += "<td";
        if(!list.contains("wednesday")) {
            days += " class=\"invisible\"";
        }
        days += ">"+Bundle.get("FORM_WEDNESDAY")+"</td>";

        days += "<td";
        if(!list.contains("thursday")) {
            days += " class=\"invisible\"";
        }
        days += ">"+Bundle.get("FORM_THURSDAY")+"</td>";

        days += "<td";
        if(!list.contains("friday")) {
            days += " class=\"invisible\"";
        }
        days += ">"+Bundle.get("FORM_FRIDAY")+"</td>";

        days += "<td";
        if(!list.contains("saturday")) {
            days += " class=\"invisible\"";
        }
        days += ">"+Bundle.get("FORM_SATURDAY")+"</td>";

        days += "</tr></table>";
        return days;
    }

}
