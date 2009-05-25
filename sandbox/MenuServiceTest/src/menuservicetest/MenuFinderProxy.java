package menuservicetest;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

public class MenuFinderProxy {

    public Menu findByDate(Date d) throws Exception {
        MenuFinderService service = new MenuFinderService();
        MenuFinder port = service.getMenuFinderPort();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(d);
        DatatypeFactory factory = DatatypeFactory.newInstance();
        return port.findMenuByDate(factory.newXMLGregorianCalendar(calendar));
    }

    public Date[] getAllMenuReferences() throws Exception {
        MenuFinderService service = new MenuFinderService();
        MenuFinder port = service.getMenuFinderPort();
        ArrayList<Date> result = new ArrayList<Date>();
        for(XMLGregorianCalendar c: port.getAllMenuReferences()) {
            result.add(c.toGregorianCalendar().getTime());
        }
        return result.toArray(new Date[result.size()]);
    }
}
