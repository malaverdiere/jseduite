package webadmin.converters;

import fr.unice.i3s.modalis.jseduite.technical.news.summon.SummoningFinder;
import fr.unice.i3s.modalis.jseduite.technical.news.summon.SummoningFinderService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Steve Colombié
 */
public class LevelConverter implements Converter {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/SummoningFinderService?wsdl")
    SummoningFinderService service;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            service = new SummoningFinderService();
            SummoningFinder port = service.getSummoningFinderPort();
            return port.findLevelById((Integer)value);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}