package webadmin.breakingnews;


import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakNew;
import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakingNewsCRUD;
import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakingNewsCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakingNewsFinder;
import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakingNewsFinderService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;
import webadmin.breakingnews.comparators.BreakingNewsAuthorComparator;
import webadmin.breakingnews.comparators.BreakingNewsAuthorComparatorDesc;
import webadmin.breakingnews.comparators.BreakingNewsDateComparator;
import webadmin.breakingnews.comparators.BreakingNewsDateComparatorDesc;

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

    // The list cardinality
    private int breakingNewsCard;

    //The transient break new
    private BreakNew cBreakNew = new BreakNew();
    private BreakNew uBreakNew = new BreakNew();

    //The date
    private Date date = new Date();

    //The time
    private Date time = new Date();

    //The current ID
    private int id = 0;

    //The sorting method
    private int sort = BreakingNewsSorter.sortByDateDesc;

    /**
     * Date -> XMLCalendar converter
     * @param d the date
     * @return the date into an XMLGregorianCalendar format
     */
    public static XMLGregorianCalendar toXmlCalendar(Date d) {
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(d);
            DatatypeFactory factory = DatatypeFactory.newInstance();
            return factory.newXMLGregorianCalendar(calendar);
        }
        catch(Exception e) {
            return null;
        }
    }

    /**
     * Constructor
     */
    public BreakingNewsManagedBean () {

    }

    /**
     * Get the breaking news cardinality
     * @return the breaking news cardinality
     */
    public int getBreakingNewsCard() {
        return breakingNewsCard;
    }

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
    public Date getDate() {
        return date;
    }

    /**
     * Set the date
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get the time
     * @return the time
     */
    public Date getTime() {
        return time;
    }

    /**
     * Set the time
     * @param time the time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Get the identifier
     * @return the identifer
     */
    public int getId() {
        return this.id;

    }

    /**
     * Set the identifier
     * @param i the identifier
     */
    public void setId(int i) {
        this.id = i;
    }

    /**
     * Get the sort method
     * @return the sort method
     */
    public int getSort() {
        return this.sort;

    }

    /**
     * Set the sort method
     * @param i the sort method
     */
    public void setSort(int s) {
        this.sort = s;
    }

    /**
     * Get the breaking news
     * @return a list of the breaking news
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

        // Sorting the values
        switch(sort) {
            case BreakingNewsSorter.sortByDate:
                Collections.sort(breakingNews, new BreakingNewsDateComparator());
                break;

            case BreakingNewsSorter.sortByAuthor:
                Collections.sort(breakingNews, new BreakingNewsAuthorComparator());
                break;

            case BreakingNewsSorter.sortByAuthorDesc:
                Collections.sort(breakingNews, new BreakingNewsAuthorComparatorDesc());
                break;
                
            case BreakingNewsSorter.sortByDateDesc:
            default:
                Collections.sort(breakingNews, new BreakingNewsDateComparatorDesc());
                break;
        }

        breakingNewsCard = breakingNews.size();

        return breakingNews;
    }


    /**
     * Create a break new
     * @return a string indicating the break news is created
     */
    public String create() {
        try {

            this.crudService = new BreakingNewsCRUDService();
            BreakingNewsCRUD crud = crudService.getBreakingNewsCRUDPort();

            date.setHours(time.getHours());
            date.setMinutes(time.getMinutes());
            cBreakNew.setStamp(toXmlCalendar(date));

            crud.createBreakingNews(cBreakNew);

            cBreakNew = new BreakNew();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "created";
    }

    /**
     * Cancel modifications
     * @return a string indicating modification/creation is canceled
     */
    public String cancel() {
        return "cancel";
    }


    /**
     * Delete the break new corresponding with the identifier
     */
    public void delete() {
        try {

            this.crudService = new BreakingNewsCRUDService();
            BreakingNewsCRUD crud = crudService.getBreakingNewsCRUDPort();

            BreakNew breakNewToDelete = crud.readBreakingNews(id);
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

            uBreakNew = crud.readBreakingNews(id);

            date = uBreakNew.getStamp().toGregorianCalendar().getTime();
            time = date;
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

            date.setHours(time.getHours());
            date.setMinutes(time.getMinutes());

            uBreakNew.setStamp(toXmlCalendar(date));
            crud.updateBreakingNews(uBreakNew);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "updated";
    }

    /**
     * Set the sort method
     * @return a string indicating the breaking news are ready to be sorted
     */
    public String sortBy() {
        return "sorted";
    }
}
