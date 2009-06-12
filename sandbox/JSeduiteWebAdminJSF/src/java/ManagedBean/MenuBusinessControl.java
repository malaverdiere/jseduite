/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean;


import java.util.List;
import javax.xml.ws.WebServiceRef;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.*;


/**
 *
 * @author ARNOUX Pierre & GENTILE Xavier
 *
 * Managed Bean where we can get the today's menu
 */

public class MenuBusinessControl {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/sandbox/MenuService/MenuBusinessService?wsdl")
    MenuBusinessService service;

    private Menu todayMenu = new Menu();


    /** Creates a new instance of MenuBusinessControl */
    public MenuBusinessControl() {
    }

     public Menu getTodayMenu() {
        try  {
            this.service = new MenuBusinessService();
            MenuBusiness port = service.getMenuBusinessPort();
            todayMenu = port.getTodayMenu();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return todayMenu;
    }

    public void setTodayMenu(Menu todayMenu) {
        this.todayMenu = todayMenu;
    }

}
