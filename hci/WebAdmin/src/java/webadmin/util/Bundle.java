package webadmin.util;

import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

public abstract class Bundle {
	public static String get(String key) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = ResourceBundle.getBundle( "webadmin.ApplicationMessages", context.getViewRoot().getLocale());
		return bundle.getString(key);
	}
}
