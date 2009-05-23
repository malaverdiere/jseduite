package sanbox.restaurant;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService()
public class MenuBusiness {

    @WebMethod(operationName = "getTodayMenu")
    public Menu getTodayMenu() {
        MenuFinder finder = new MenuFinder();
        return finder.findMenuByDate(new java.util.Date());
    }

}
