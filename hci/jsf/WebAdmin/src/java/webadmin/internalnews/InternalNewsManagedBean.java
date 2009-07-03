package webadmin.internalnews;

import fr.unice.i3s.modalis.jseduite.technical.news.internal.News;
import fr.unice.i3s.modalis.jseduite.technical.news.internal.InternalNewsCRUD;
import fr.unice.i3s.modalis.jseduite.technical.news.internal.InternalNewsCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.news.internal.InternalNewsFinder;
import fr.unice.i3s.modalis.jseduite.technical.news.internal.InternalNewsFinderService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;
import webadmin.internalnews.comparators.*;

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

    //The end date
    private Date endDate = new Date();

    //The current ID
    private int id = 0;

    //The sorting method
    private int sort;

    // The targets
    private List<SelectItem> targets;

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
            //Get the internal news ids
            this.finderService = new InternalNewsFinderService();
            InternalNewsFinder finderPort = finderService.getInternalNewsFinderPort();
            List<Integer> internalNewsIds = finderPort.getAllInternalNewsReferences();

            //Get the internal news
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
        switch(sort) {
            case InternalNewsSorter.sortByTarget:
                Collections.sort(internalNews, new InternalNewsTargetComparator());
                break;

            case InternalNewsSorter.sortByAuthor:
                Collections.sort(internalNews, new InternalNewsAuthorComparator());
                break;

            case InternalNewsSorter.sortByStart:
                Collections.sort(internalNews, new InternalNewsStartComparator());
                break;

            case InternalNewsSorter.sortByEnd:
                Collections.sort(internalNews, new InternalNewsEndComparator());
                break;

            case InternalNewsSorter.sortByTitle:
                Collections.sort(internalNews, new InternalNewsTitleComparator());
                break;

            case InternalNewsSorter.sortByTargetDesc:
                Collections.sort(internalNews, new InternalNewsTargetComparatorDesc());
                break;

            case InternalNewsSorter.sortByAuthorDesc:
                Collections.sort(internalNews, new InternalNewsAuthorComparatorDesc());
                break;

            case InternalNewsSorter.sortByEndDesc:
                Collections.sort(internalNews, new InternalNewsEndComparatorDesc());
                break;

            case InternalNewsSorter.sortByTitleDesc:
                Collections.sort(internalNews, new InternalNewsTitleComparatorDesc());
                break;
            case InternalNewsSorter.sortByStartDesc:
            default:
                Collections.sort(internalNews, new InternalNewsStartComparatorDesc());
                break;
        }

        internalNewsCard = internalNews.size();

        return internalNews;
    }

    /**
     * Get the targets
     * @return the list of targets
     */
    public List<SelectItem> getTargets() {
        List<String> targetsBuf;
        targets = new ArrayList<SelectItem>();

        try {
            this.finderService = new InternalNewsFinderService();
            InternalNewsFinder finderPort = finderService.getInternalNewsFinderPort();
            targetsBuf = finderPort.getTargetsIds();

            for (String tid : targetsBuf) {
                SelectItem item = new SelectItem(tid, finderPort.findTargetById(Integer.parseInt(tid)));
                targets.add(item);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return targets;
    }


    /**
     * Create an internal news
     * @return a string indicating the internal news is created
     */
    public String create() {
        try {

            this.crudService = new InternalNewsCRUDService();
            InternalNewsCRUD crud = crudService.getInternalNewsCRUDPort();

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
            endDate = uNews.getEnd().toGregorianCalendar().getTime();
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
