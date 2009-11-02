package webadmin.converters;

import fr.unice.i3s.modalis.jseduite.technical.alarms.Alarm;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import webadmin.util.Bundle;


public class AlarmStartConverter implements Converter {


    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (((Alarm)value).getBreakTime() == null) {
            return  "<img src=\"../resources/images/alarm_none.png\" alt=\""+Bundle.get("ALARM_START_NOK")+"\"/>";
        }
        else {
            return  "<img src=\"../resources/images/alarm_start.png\" alt=\""+Bundle.get("ALARM_START_OK")+"\"/>";
        }
    }

}
