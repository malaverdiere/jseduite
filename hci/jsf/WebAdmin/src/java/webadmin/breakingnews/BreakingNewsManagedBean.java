package webadmin.breakingnews;


import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakNew;
import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakingNewsCRUD;
import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakingNewsCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakingNewsFinder;
import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakingNewsFinderService;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Steve Colombié
 */

public class BreakingNewsManagedBean {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/BreakingNews/BreakingNewsFinderService?wsdl")
    BreakingNewsFinderService finderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/BreakingNews/BreakingNewsCRUDService?wsdl")
    BreakingNewsCRUDService crudService;

    //The list of the breaking news
    private ArrayList<BreakNew> breakingNews;

    //The transient break new
    private BreakNew cBreakNew = new BreakNew();
    private BreakNew uBreakNew = new BreakNew();

    //The date
    public static XMLGregorianCalendar toXmlCalendar() {
        try {
            Date d = new Date();
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(d);
            DatatypeFactory factory = DatatypeFactory.newInstance();
            return factory.newXMLGregorianCalendar(calendar);
        }
        catch(Exception e) {
            return null;
        }
    }
    private XMLGregorianCalendar date = toXmlCalendar();

    //The current ID
    String id = "";

    /**
     * Get the created break new
     * @return the created break new
     */
    public BreakNew getcBreakNew() {
        return cBreakNew;
    }

    /**
     * Set the created break new
     * @param b the break new to create
     */
    public void setcBreakNew(BreakNew b) {
        this.cBreakNew = b;
    }

    /**
     * Get the break new to update
     * @return the break new to update
     */
    public BreakNew getuBreakNew() {
        return uBreakNew;
    }

    /**
     * Set the break new to update
     * @param b the break new to update
     */
    public void setuBreakNew(BreakNew b) {
        this.uBreakNew = b;
    }

    /**
     * Get the date
     * @return the date
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Set the date
     * @param date the date
     */
    public void setDate(XMLGregorianCalendar date) {
        this.date = date;
    }
    
    /**
     * Get the identifier
     * @return the identifer
     */
    public String getId() {
        return this.id;

    }

    /**
     * Set the identifier
     * @param i the identifier
     */
    public void setId(String i) {
        this.id = i;
    }

    /**
     * Get the breaking news
     * @return a list of the breakingfg news
     */
    public ArrayList<BreakNew> getBreakingNews() {
        breakingNews = new ArrayList<BreakNew>();

        try {
            //Get the breaking news ids
            this.finderService = new BreakingNewsFinderService();
            BreakingNewsFinder finderPort = finderService.getBreakingNewsFinderPort();
            List<Integer> breakingNewsIds = finderPort.getAllBreakingNewsReferences();

            //Get the breaking news
            this.crudService = new BreakingNewsCRUDService();
            BreakingNewsCRUD crudPort = crudService.getBreakingNewsCRUDPort();

            for(int i=0; i<breakingNewsIds.size(); i++) {
                breakingNews.add(crudPort.readBreakingNews(breakingNewsIds.get(i)));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return breakingNews;
    }


    /**
     * Create a break new
     * @return a string indicating the break news is created
     */
    public String create() {
        date = toXmlCalendar();

        try {

            this.crudService = new BreakingNewsCRUDService();
            BreakingNewsCRUD crud = crudService.getBreakingNewsCRUDPort();

            date.setTime(1,1,1);
            cBreakNew.setStamp(date);

            crud.createBreakingNews(cBreakNew);

            cBreakNew = new BreakNew();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "created";
    }


    /**
     * Delete the break new corresponding with the identifier
     */
    public void delete() {
        try {

            this.crudService = new BreakingNewsCRUDService();
            BreakingNewsCRUD crud = crudService.getBreakingNewsCRUDPort();

            BreakNew breakNewToDelete = crud.readBreakingNews(Integer.parseInt(id));
            crud.deleteBreakingNews(breakNewToDelete);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initiate the update process
     * @return a string indicating the update is ready to be done
     */
    public String goUpdate()
    {
        try {
            this.crudService = new BreakingNewsCRUDService();
            BreakingNewsCRUD crud = crudService.getBreakingNewsCRUDPort();

            uBreakNew = crud.readBreakingNews(Integer.parseInt(id));

            date = uBreakNew.getStamp();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "update";
    }

    /**
     * Update the uBreakNew new break
     * @return a string indicating the break new is updated
     */
    public String update() {
        try {

            this.crudService = new BreakingNewsCRUDService();
            BreakingNewsCRUD crud = crudService.getBreakingNewsCRUDPort();

            uBreakNew.setStamp(date);
            crud.updateBreakingNews(uBreakNew);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "updated";
    }
}
