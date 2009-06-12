package ManagedBean;


import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.util.List;
import javax.xml.ws.WebServiceRef;
import fr.unice.i3s.modalis.jseduite.technical.restaurant.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.faces.model.SelectItem;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 *
 * @author ARNOUX Pierre & GENTILE Xavier
 *
 * Managed Bean where we can get all references of menu , search menu by Date , get all dates of menus
 */

public class MenuFinderControl {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/sandbox/MenuService/MenuFinderService?wsdl")
    MenuFinderService service;

    private Menu foundByDate = new Menu();

    private XMLGregorianCalendar date = new XMLGregorianCalendarImpl();

    
    private List<XMLGregorianCalendar> allReferences;

    private List<SelectItem> allDates;

   /** Creates a new instance of MenuFinderControl */
    public MenuFinderControl() {
        date.clear();
    }

    public void searchMenuByDate(){
        try {
           this.service = new MenuFinderService();
           MenuFinder port = service.getMenuFinderPort();
           date.setTime(1,1,1);
          foundByDate = port.findMenuByDate(date);
       } catch (Exception e) {
            e.printStackTrace();
        }
    }

     public Menu getFoundByDate() {
        return foundByDate;
    }

    public void setFoundByDate(Menu foundByDate) {
        this.foundByDate = foundByDate;
    }

    public XMLGregorianCalendar getDate() {
        return date;
    }

    public void setDate(XMLGregorianCalendar date) {
          this.date = date;
    }

    

    public List<XMLGregorianCalendar> getAllReferences() {
        return allReferences;
    }

    public void setAllReferences(List<XMLGregorianCalendar> allReferences) {
        this.allReferences = allReferences;
    }

    public void getAllMenuReferences(){
        try {
           this.service = new MenuFinderService();
           MenuFinder port = service.getMenuFinderPort();
           allReferences = port.getAllMenuReferences();
       } catch (Exception e) {
            e.printStackTrace();
        }
    }

     public List<SelectItem> getAllDates() {
        try{
         this.service = new MenuFinderService();
            MenuFinder port = service.getMenuFinderPort();
            allReferences = port.getAllMenuReferences();

            allDates = new ArrayList();
            String date="";
            for(int i=0; i<allReferences.size(); i++){
                date=String.valueOf(allReferences.get(i).getYear()) + " " +String.valueOf(allReferences.get(i).getMonth()) + " " + String.valueOf(allReferences.get(i).getDay());
                SelectItem selectItem = new SelectItem(date,allReferences.get(i).toString());
                allDates.add(selectItem);

            }
         }catch (Exception e) {
            e.printStackTrace();
        }

         return allDates;
    }

    public void setAllDates(List<SelectItem> allDates) {
        this.allDates = allDates;
    }

}
