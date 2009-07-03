package webadmin.converters;

import fr.unice.i3s.modalis.jseduite.technical.news.internal.InternalNewsFinder;
import fr.unice.i3s.modalis.jseduite.technical.news.internal.InternalNewsFinderService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Steve Colombié
 */
public class TargetConverter implements Converter {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/InternalNews/InternalNewsService?wsdl")
    InternalNewsFinderService service;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            service = new InternalNewsFinderService();
            InternalNewsFinder port = service.getInternalNewsFinderPort();
            return port.findTargetById(Integer.parseInt((String)value));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}
