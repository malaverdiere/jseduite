package webadmin.converters;

import fr.unice.i3s.modalis.jseduite.technical.breaktime.Promo;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class PromoListConverter implements Converter {
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        List<Promo> list = (List<Promo>) value;

        if(list.size() == 0) {
            return "";
        }

        String promos = "";
        
        for(Promo promo : list) {
            promos += promo.getCode() + ", ";
        }

        return promos.substring(0, promos.length()-2);
    }
}
