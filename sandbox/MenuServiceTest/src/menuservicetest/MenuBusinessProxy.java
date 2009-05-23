package menuservicetest;
import sanbox.restaurant.*;

public class MenuBusinessProxy {

    public Menu getTodayMenu() throws Exception {
        MenuBusinessService service = new MenuBusinessService();
        MenuBusiness port = service.getMenuBusinessPort();
       return port.getTodayMenu();
    }

}
