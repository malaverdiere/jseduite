package webadmin.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.xml.datatype.XMLGregorianCalendar;
import webadmin.util.Bundle;

/**
 *
 * @author Steve Colombié
 */
public class XMLGregorianCalendarDateConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        XMLGregorianCalendar date = (XMLGregorianCalendar) value;

        String result = String.format(Bundle.get("BEAN_SIMPLE_DATE_PATTERN"), date.toGregorianCalendar());

        return result;
    }

}
