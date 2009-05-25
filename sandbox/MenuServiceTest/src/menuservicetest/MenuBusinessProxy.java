package menuservicetest;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.*;

public class MenuBusinessProxy {

    public Menu getTodayMenu() throws Exception {
        MenuBusinessService service = new MenuBusinessService();
        MenuBusiness port = service.getMenuBusinessPort();
       return port.getTodayMenu();
    }

}
