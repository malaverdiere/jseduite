package webadmin.converters;

import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Steve Colombié
 */
public class XMLGregorianCalendarDateConverter implements Converter {

	public static String get(String key) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = ResourceBundle.getBundle( "webadmin.ApplicationMessages", context.getViewRoot().getLocale());
		return bundle.getString(key);
	}


    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        XMLGregorianCalendar date = (XMLGregorianCalendar) value;

        String result = String.format(get("BEAN_SIMPLE_DATE_PATTERN"), date.toGregorianCalendar());

        return result;
    }

}
