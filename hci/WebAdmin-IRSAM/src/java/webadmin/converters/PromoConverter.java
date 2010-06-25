package webadmin.converters;


import fr.unice.i3s.modalis.jseduite.technical.promos.PromoFinder;
import fr.unice.i3s.modalis.jseduite.technical.promos.PromoFinderService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Steve Colombié
 */
public class PromoConverter implements Converter {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/PromoFinderService?wsdl")
    PromoFinderService service;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            service = new PromoFinderService();
            PromoFinder port = service.getPromoFinderPort();
            return port.findPromoById((Integer)value).getCode();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}