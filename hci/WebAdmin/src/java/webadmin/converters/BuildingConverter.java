

package webadmin.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class BuildingConverter implements Converter {


    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String f = ((String) value).substring(0, 1).toUpperCase();
        String e = ((String) value).substring(1, ((String) value).length());

        return f+e;
    }
}
