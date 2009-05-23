package menuservicetest;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import sanbox.restaurant.*;

public class MenuFinderProxy {

    public Menu findByDate(Date d) throws Exception {
        MenuFinderService service = new MenuFinderService();
        MenuFinder port = service.getMenuFinderPort();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(d);
        DatatypeFactory factory = DatatypeFactory.newInstance();
        return port.findMenuByDate(factory.newXMLGregorianCalendar(calendar));
    }

}
