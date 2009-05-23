package menuservicetest;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.*;
import sanbox.restaurant.*;

public class MenuCRUDProxy {

    public Date create(Menu m) throws Exception  {
        MenuCRUDService service = new MenuCRUDService();
        MenuCRUD port = service.getMenuCRUDPort();
        XMLGregorianCalendar result =  port.createMenu(m);
        return result.toGregorianCalendar().getTime();
    }

    public Menu read(Date d) throws Exception {
        MenuCRUDService service = new MenuCRUDService();
        MenuCRUD port = service.getMenuCRUDPort();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(d);
        DatatypeFactory factory = DatatypeFactory.newInstance();
        return port.readMenu(factory.newXMLGregorianCalendar(calendar));
    }

    public void update(Menu m) throws Exception {
        MenuCRUDService service = new MenuCRUDService();
        MenuCRUD port = service.getMenuCRUDPort();
        port.updateMenu(m);
    }
    
    public void delete(Menu m) throws Exception {
        MenuCRUDService service = new MenuCRUDService();
        MenuCRUD port = service.getMenuCRUDPort();
        port.deleteMenu(m);
    }
}


