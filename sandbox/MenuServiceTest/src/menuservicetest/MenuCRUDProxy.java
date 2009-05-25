package menuservicetest;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.*;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.*;
import helpers.MenuHelper;

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
        return port.readMenu(MenuHelper.toXmlCalendar(d));
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


