package webadmin.summonings;


import fr.unice.i3s.modalis.jseduite.technical.news.summon.Promo;
import fr.unice.i3s.modalis.jseduite.technical.news.summon.Summoning;
import fr.unice.i3s.modalis.jseduite.technical.news.summon.SummoningCRUD;
import fr.unice.i3s.modalis.jseduite.technical.news.summon.SummoningCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.news.summon.SummoningFinder;
import fr.unice.i3s.modalis.jseduite.technical.news.summon.SummoningFinderService;
import fr.unice.i3s.modalis.jseduite.technical.promos.PromoFinder;
import fr.unice.i3s.modalis.jseduite.technical.promos.PromoFinderService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.xml.ws.WebServiceRef;
import webadmin.summonings.comparators.*;
import webadmin.util.DateFormat;
import webadmin.util.SQLProtection;


/**
 *
 * @author Steve Colombié
 */

public class SummoningManagedBean {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/SummoningFinderService?wsdl")
    SummoningFinderService finderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/SummoningCRUDService?wsdl")
    SummoningCRUDService crudService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/PromoFinderService?wsdl")
    PromoFinderService promoFinderService;

    //The list of the summonings
    private ArrayList<Summoning> summonings;

    // The list cardinality
    private int summoningsCard;

    //The transient summoning
    private Summoning cSummoning = new Summoning();
    private Summoning uSummoning = new Summoning();

    //The date
    private Date date = new Date();

    //The time
    private Date time = new Date();

    //The current ID
    private int id = 0;

    // The promotions
    private List<SelectItem> promos;

    // The selected promotion
    private int selectedPromo;

    // The levels
    private List<SelectItem> levels;

    //The sorting method
    private int sort= SummoningSorter.sortByDateDesc;

    /**
     * Constructor
     */
    public SummoningManagedBean () {

    }

    /**
     * Get the summonings cardinality
     * @return the summonings cardinality
     */
    public int getSummoningCard() {
        return summoningsCard;
    }

    /**
     * Get the created summoning
     * @return the created summoning
     */
    public Summoning getcSummoning() {
        cSummoning.setValid(true);
        return cSummoning;
    }

    /**
     * Set the created summoning
     * @param s the summoning to create
     */
    public void setcSummoning(Summoning s) {
        this.cSummoning = s;
    }

    /**
     * Get the summoning to update
     * @return the summoning to update
     */
    public Summoning getuSummoning() {
        return uSummoning;
    }

    /**
     * Set the summoning to update
     * @param s the summoning to update
     */
    public void setuSummoning(Summoning s) {
        this.uSummoning = s;
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
     * Get the selected promotion
     * @return the selected promotion
     */
    public int getSelectedPromo() {
        return selectedPromo;
    }

    /**
     * Set the selected promotion
     * @param selectedPromo the selected promotion
     */
    public void setSelectedPromo(int selectedPromo) {
        this.selectedPromo = selectedPromo;
    }



    /**
     * Get the promotions
     * @return the list of promotions
     */
    public List<SelectItem> getPromos() {
        List<Integer> promosBuf;
        promos = new ArrayList<SelectItem>();

        try {
            this.promoFinderService = new PromoFinderService();
            PromoFinder finderPort = promoFinderService.getPromoFinderPort();
            promosBuf = finderPort.getAllPromosReferences();

            for (Integer pid : promosBuf) {
                SelectItem item = new SelectItem(pid, finderPort.findPromoById(pid).getCode() + " - " + finderPort.findPromoById(pid).getName());
                promos.add(item);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return promos;
    }

    /**
     * Get the levels
     * @return the list of levels
     */
    public List<SelectItem> getLevels() {
        List<Integer> levelsBuf;
        levels = new ArrayList<SelectItem>();

        try {
            this.finderService = new SummoningFinderService();
            SummoningFinder finderPort = finderService.getSummoningFinderPort();
            levelsBuf = finderPort.getLevelIds();

            for (Integer tid : levelsBuf) {
                SelectItem item = new SelectItem(tid, finderPort.findLevelById(tid));
                levels.add(item);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return levels;
    }


    /**
     * Get the summonings
     * @return a list of the summonings
     */
    public ArrayList<Summoning> getSummonings() {
        summonings = new ArrayList<Summoning>();

        try {
            //Get the summonings ids
            this.finderService = new SummoningFinderService();
            SummoningFinder finderPort = finderService.getSummoningFinderPort();
            List<Integer> summoningsIds = finderPort.getAllSummoningReferences();

            //Get the summonings
            this.crudService = new SummoningCRUDService();
            SummoningCRUD crudPort = crudService.getSummoningCRUDPort();

            for(int i=0; i<summoningsIds.size(); i++) {
                summonings.add(crudPort.readSummoning(summoningsIds.get(i)));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Sorting the values
        switch(sort) {
            case SummoningSorter.sortByDate:
                Collections.sort(summonings, new SummoningDateComparator());
                break;

            case SummoningSorter.sortByStudent:
                Collections.sort(summonings, new SummoningStudentComparator());
                break;

            case SummoningSorter.sortByPromo:
                Collections.sort(summonings, new SummoningPromoComparator());
                break;

            case SummoningSorter.sortByOwner:
                Collections.sort(summonings, new SummoningOwnerComparator());
                break;

            case SummoningSorter.sortByLevel:
                Collections.sort(summonings, new SummoningLevelComparator());
                break;

            case SummoningSorter.sortByStudentDesc:
                Collections.sort(summonings, new SummoningStudentComparatorDesc());
                break;

            case SummoningSorter.sortByPromoDesc:
                Collections.sort(summonings, new SummoningPromoComparatorDesc());
                break;

            case SummoningSorter.sortByOwnerDesc:
                Collections.sort(summonings, new SummoningOwnerComparatorDesc());
                break;

            case SummoningSorter.sortByLevelDesc:
                Collections.sort(summonings, new SummoningOwnerComparator());
                break;

            case SummoningSorter.sortByDateDesc:
            default:
                Collections.sort(summonings, new SummoningDateComparatorDesc());
                break;
        }

        summoningsCard = summonings.size();

        return summonings;
    }


    /**
     * Create a summoning
     * @return a string indicating the summonings is created
     */
    public String create() {
        try {

            this.crudService = new SummoningCRUDService();
            SummoningCRUD crud = crudService.getSummoningCRUDPort();

            this.promoFinderService = new PromoFinderService();
            PromoFinder promoFinder = promoFinderService.getPromoFinderPort();

            date.setHours(time.getHours());
            date.setMinutes(time.getMinutes());
            cSummoning.setDate(DateFormat.toXmlCalendar(date));

            Promo promotion = new Promo();
            promotion.setId(selectedPromo);
            promotion.setCode(promoFinder.findPromoById(selectedPromo).getCode());
            promotion.setName(promoFinder.findPromoById(selectedPromo).getName());

            cSummoning.setPromo(promotion);

            // Escape characters traitement
            cSummoning.setStudent(SQLProtection.format(cSummoning.getStudent()));
            cSummoning.setOwner(SQLProtection.format(cSummoning.getOwner()));

            crud.createSummoning(cSummoning);

            cSummoning = new Summoning();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        date = new Date();
        time = new Date();

        return "created";
    }

    /**
     * Cancel modifications
     * @return a string indicating modification/creation is canceled
     */
    public String cancel() {
        date = new Date();
        time = new Date();

        return "cancel";
    }


    /**
     * Delete the summoning corresponding with the identifier
     */
    public void delete() {
        try {

            this.crudService = new SummoningCRUDService();
            SummoningCRUD crud = crudService.getSummoningCRUDPort();

            Summoning summoningToDelete = crud.readSummoning(id);
            crud.deleteSummoning(summoningToDelete);

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
            this.crudService = new SummoningCRUDService();
            SummoningCRUD crud = crudService.getSummoningCRUDPort();

            uSummoning = crud.readSummoning(id);

            date = uSummoning.getDate().toGregorianCalendar().getTime();
            time = date;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "update";
    }

    /**
     * Update the uSummoning new break
     * @return a string indicating the summoning is updated
     */
    public String update() {
        try {
            this.crudService = new SummoningCRUDService();
            SummoningCRUD crud = crudService.getSummoningCRUDPort();

            date.setHours(time.getHours());
            date.setMinutes(time.getMinutes());

            uSummoning.setDate(DateFormat.toXmlCalendar(date));

            // Escape characters traitement
            uSummoning.setStudent(SQLProtection.format(uSummoning.getStudent()));
            uSummoning.setOwner(SQLProtection.format(uSummoning.getOwner()));

            crud.updateSummoning(uSummoning);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        date = new Date();
        time = new Date();

        return "updated";
    }

    /**
     * Set the sort method
     * @return a string indicating the summonings are ready to be sorted
     */
    public String sortBy() {
        return "sorted";
    }
}
