package webadmin.internalnews;

import fr.unice.i3s.modalis.jseduite.technical.news.internal.News;
import fr.unice.i3s.modalis.jseduite.technical.news.internal.InternalNewsCRUD;
import fr.unice.i3s.modalis.jseduite.technical.news.internal.InternalNewsCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.news.internal.InternalNewsFinder;
import fr.unice.i3s.modalis.jseduite.technical.news.internal.InternalNewsFinderService;
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

public class InternalNewsManagedBean {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/InternalNews/InternalNewsFinderService?wsdl")
    InternalNewsFinderService finderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/InternalNews/InternalNewsCRUDService?wsdl")
    InternalNewsCRUDService crudService;

    //The list of the breaking news
    private ArrayList<News> internalNews;

    // The list cardinality
    private int internalNewsCard;

    //The transient break new
    private News cNews = new News();
    private News uNews = new News();

    //The start date
    private Date startDate = new Date();

    //The start time
    private Date startTime = new Date();

    //The end date
    private Date endDate = new Date();

    //The end time
    private Date endTime = new Date();

    //The current ID
    private int id = 0;

    //The sorting method
    private int sort;

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
    public InternalNewsManagedBean () {

    }

    /**
     * Get the internal news cardinality
     * @return the internal news cardinality
     */
    public int getInternalNewsCard() {
        return internalNewsCard;
    }

    /**
     * Get the created internal news
     * @return the created internal news
     */
    public News getcNews() {
        return cNews;
    }

    /**
     * Set the created internal new
     * @param n the internal new to create
     */
    public void setcNews(News n) {
        this.cNews = n;
    }

    /**
     * Get the internal new to update
     * @return the internal new to update
     */
    public News getuNews() {
        return uNews;
    }

    /**
     * Set the internal new to update
     * @param n the internal new to update
     */
    public void setuNews(News n) {
        this.uNews = n;
    }


    /**
     * Get the start date
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Set the start date
     * @param date the start date
     */
    public void setStartDate(Date date) {
        this.startDate = date;
    }

    /**
     * Get the start time
     * @return the start time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Set the start time
     * @param time the start time
     */
    public void setStartTime(Date time) {
        this.startTime = time;
    }

    /**
     * Get the end date
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Set the end date
     * @param date the end date
     */
    public void setEndDate(Date date) {
        this.endDate = date;
    }

    /**
     * Get the end time
     * @return the end time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Set the end time
     * @param time the end time
     */
    public void setEndTime(Date time) {
        this.endTime = time;
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
     * Get the internal news
     * @return a list of the internal news
     */
    public ArrayList<News> getInternalNews() {
        internalNews = new ArrayList<News>();

        try {
            //Get the breaking news ids
            this.finderService = new InternalNewsFinderService();
            InternalNewsFinder finderPort = finderService.getInternalNewsFinderPort();
            List<Integer> internalNewsIds = finderPort.getAllInternalNewsReferences();

            //Get the breaking news
            this.crudService = new InternalNewsCRUDService();
            InternalNewsCRUD crudPort = crudService.getInternalNewsCRUDPort();

            for(int i=0; i<internalNewsIds.size(); i++) {
                internalNews.add(crudPort.readInternalNews(internalNewsIds.get(i)));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Sorting the values
/*        switch(sort) {
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
*/
        internalNewsCard = internalNews.size();

        return internalNews;
    }


    /**
     * Create an internal news
     * @return a string indicating the internal news is created
     */
    public String create() {
        try {

            this.crudService = new InternalNewsCRUDService();
            InternalNewsCRUD crud = crudService.getInternalNewsCRUDPort();

            startDate.setHours(startTime.getHours());
            startDate.setMinutes(startTime.getMinutes());
            endDate.setHours(endTime.getHours());
            endDate.setMinutes(endTime.getMinutes());

            cNews.setStart(toXmlCalendar(startDate));
            cNews.setEnd(toXmlCalendar(endDate));

            crud.createInternalNews(cNews);

            cNews = new News();

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
     * Delete the internal news corresponding with the identifier
     */
    public void delete() {
        try {

            this.crudService = new InternalNewsCRUDService();
            InternalNewsCRUD crud = crudService.getInternalNewsCRUDPort();

            News internalNewsToDelete = crud.readInternalNews(id);
            crud.deleteInternalNews(internalNewsToDelete);

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
            this.crudService = new InternalNewsCRUDService();
            InternalNewsCRUD crud = crudService.getInternalNewsCRUDPort();

            uNews = crud.readInternalNews(id);

            startDate = uNews.getStart().toGregorianCalendar().getTime();
            startTime = startDate;

            endDate = uNews.getEnd().toGregorianCalendar().getTime();
            endTime = endDate;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "update";
    }

    /**
     * Update the uNews internal news
     * @return a string indicating the internal news is updated
     */
    public String update() {
        try {
            this.crudService = new InternalNewsCRUDService();
           InternalNewsCRUD crud = crudService.getInternalNewsCRUDPort();

            startDate.setHours(startTime.getHours());
            startDate.setMinutes(startTime.getMinutes());

            endDate.setHours(endTime.getHours());
            endDate.setMinutes(endTime.getMinutes());

            uNews.setStart(toXmlCalendar(startDate));
            uNews.setEnd(toXmlCalendar(endDate));

            crud.updateInternalNews(uNews);

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
