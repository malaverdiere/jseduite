package webadmin.converters;

import fr.unice.i3s.modalis.jseduite.technical.plannings.PlanningGroup;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class PlanningGroupsConverter implements Converter {
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        List<PlanningGroup> list = (List<PlanningGroup>) value;

        if(list.size() == 0) {
            return "";
        }

        String groups = "";

        for(PlanningGroup group : list) {
            groups += "<a href=\""+group.getPlanning()+"\" target=\"_blank\">"+group.getName() + "</a>, ";
        }

        return groups.substring(0, groups.length()-2);
    }
}