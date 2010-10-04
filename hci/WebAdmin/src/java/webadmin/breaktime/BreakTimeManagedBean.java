package webadmin.breaktime;

import fr.unice.i3s.modalis.jseduite.technical.breaktime.BreakTime;
import fr.unice.i3s.modalis.jseduite.technical.breaktime.BreakTimeCRUD;
import fr.unice.i3s.modalis.jseduite.technical.breaktime.BreakTimeCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.breaktime.BreakTimeFinder;
import fr.unice.i3s.modalis.jseduite.technical.breaktime.BreakTimeFinderService;

import fr.unice.i3s.modalis.jseduite.technical.breaktime.Promo;
import fr.unice.i3s.modalis.jseduite.technical.promos.PromoFinder;
import fr.unice.i3s.modalis.jseduite.technical.promos.PromoFinderService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.xml.ws.WebServiceRef;
import webadmin.breaktime.comparators.BreakTimeBuildingComparator;
import webadmin.breaktime.comparators.BreakTimeStartComparator;
import webadmin.util.Bundle;
import webadmin.util.DateFormat;
import webadmin.util.SQLProtection;


/**
 *
 * @author Steve Colombié
 */

public class BreakTimeManagedBean {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/BreakTimeFinderService?wsdl")
    BreakTimeFinderService finderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/BreakTimeCRUDService?wsdl")
    BreakTimeCRUDService crudService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/PromoFinderService?wsdl")
    PromoFinderService promoFinderService;

    //The list of the break times
    private ArrayList<BreakTime> breakTimes;

    // The list cardinality
    private int breakTimeCard;

    //The transient break time
    private BreakTime cBreakTime = new BreakTime();
    private BreakTime uBreakTime = new BreakTime();

    //The start date
    private Date startDate = new Date(0, 0, 0, 8, 0);

    //The end date
    private Date endDate = new Date(0, 0, 0, 18, 0);

    //The current ID
    private int id = 0;

    // Days
    private List<SelectItem> days;

    // Selected days
    private String[] selectedDays;

    // The promotions
    private List<SelectItem> promos;

    // Selected promotions
    private Integer[] selectedPromos;

    // The buildings
    private List<SelectItem> buildings;

    // Alternative building
    private String alterBuilding;

    // The kinds
    private List<SelectItem> kinds;


    /**
     * Constructor
     */
    public BreakTimeManagedBean () {

    }

    /**
     * Get the break times cardinality
     * @return the break times cardinality
     */
    public int getBreakTimeCard() {
        return breakTimeCard;
    }

    /**
     * Get the created break time
     * @return the created break time
     */
    public BreakTime getcBreakTime() {
        return cBreakTime;
    }

    /**
     * Set the created break time
     * @param b the break time to create
     */
    public void setcBreakTime(BreakTime b) {
        this.cBreakTime = b;
    }

    /**
     * Get the break time to update
     * @return the break time to update
     */
    public BreakTime getuBreakTime() {
        return uBreakTime;
    }

    /**
     * Set the break time to update
     * @param b the break time to update
     */
    public void setuBreakTime(BreakTime b) {
        this.uBreakTime = b;
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
     * Get the alternative building
     * @return the alternative building
     */
    public String getAlterBuilding() {
        return alterBuilding;
    }

    /**
     * Set the alternative building
     * @param alterKind the alternative building
     */
    public void setAlterBuilding(String alterBuilding) {
        this.alterBuilding = alterBuilding;
    }

    /**
     * Get the break times
     * @return a list of the break times
     */
    public ArrayList<BreakTime> getBreakTimes() {
        breakTimes = new ArrayList<BreakTime>();

        try {
            //Get the break times ids
            this.finderService = new BreakTimeFinderService();
            BreakTimeFinder finderPort = finderService.getBreakTimeFinderPort();
            List<Integer> breakTimeIds = finderPort.getAllBreakTimeReferences();

            //Get the break times
            this.crudService = new BreakTimeCRUDService();
            BreakTimeCRUD crudPort = crudService.getBreakTimeCRUDPort();

            for(int i=0; i<breakTimeIds.size(); i++) {
                breakTimes.add(crudPort.readBreakTime(breakTimeIds.get(i)));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Sort break times by date then by building
        Collections.sort(breakTimes, new BreakTimeStartComparator());
        Collections.sort(breakTimes, new BreakTimeBuildingComparator());


        breakTimeCard = breakTimes.size();

        return breakTimes;
    }

    /**
     * Get the days
     * @return the list of days
     */
    public List<SelectItem> getDays() {
        days = new ArrayList<SelectItem>();

        SelectItem item = new SelectItem("monday", Bundle.get("FORM_MONDAY"));
        days.add(item);
        item = new SelectItem("tuesday", Bundle.get("FORM_TUESDAY"));
        days.add(item);
        item = new SelectItem("wednesday", Bundle.get("FORM_WEDNESDAY"));
        days.add(item);
        item = new SelectItem("thursday", Bundle.get("FORM_THURSDAY"));
        days.add(item);
        item = new SelectItem("friday", Bundle.get("FORM_FRIDAY"));
        days.add(item);
        item = new SelectItem("saturday", Bundle.get("FORM_SATURDAY"));
        days.add(item);
        item = new SelectItem("sunday", Bundle.get("FORM_SUNDAY"));
        days.add(item);

        return days;
    }

    /**
     * Get the selected days
     * @return the selected days
     */
    public String[] getSelectedDays() {
        return this.selectedDays;
    }

    /**
     * Set the selected days
     * @param d the selected days
     */
    public void setSelectedDays(String[] d) {
        selectedDays = d;
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
     * Get the selected promotions
     * @return the selected promotions
     */
    public Integer[] getSelectedPromos() {
        return this.selectedPromos;
    }

    /**
     * Set the selected promotions
     * @param p the selected promotions
     */
    public void setSelectedPromos(Integer[] p) {
        selectedPromos = p;
    }

    /**
     * Get the buildings
     * @return the list of buildings
     */
    public List<SelectItem> getBuildings() {
        List<String> buildingsBuf;
        buildings = new ArrayList<SelectItem>();

        try {
            this.finderService = new BreakTimeFinderService();
            BreakTimeFinder port = finderService.getBreakTimeFinderPort();
            buildingsBuf = port.getAllBuildings();

            for (String building : buildingsBuf) {
                SelectItem item = new SelectItem(building, building);
                buildings.add(item);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        buildings.add(new SelectItem("__other", Bundle.get("FORM_OTHER")));

        return buildings;
    }

    /**
     * Get the kinds
     * @return the list of kinds
     */
    public List<SelectItem> getKinds() {
        kinds = new ArrayList<SelectItem>();

        SelectItem item = new SelectItem("short", Bundle.get("BREAKTIME_KIND_SHORT"));
        kinds.add(item);

        item = new SelectItem("long", Bundle.get("BREAKTIME_KIND_LONG"));
        kinds.add(item);

        return kinds;
    }

    /**
     * Create a break time
     * @return a string indicating the break time is created
     */
    public String create() {
        try {

            this.crudService = new BreakTimeCRUDService();
            BreakTimeCRUD crud = crudService.getBreakTimeCRUDPort();

            this.promoFinderService = new PromoFinderService();
            PromoFinder finderPort = promoFinderService.getPromoFinderPort();

            cBreakTime.setStart(DateFormat.toXmlCalendar(startDate));
            cBreakTime.setEnd(DateFormat.toXmlCalendar(endDate));

            for(String day : selectedDays) {
                cBreakTime.getDays().add(day);
            }

            Promo promotion;
            for(Integer promo : selectedPromos) {
                promotion = new Promo();
                promotion.setId(finderPort.findPromoById(promo).getId());
                promotion.setCode(finderPort.findPromoById(promo).getCode());
                promotion.setName(finderPort.findPromoById(promo).getName());

                cBreakTime.getPromos().add(promotion);
            }

            if(cBreakTime.getBuilding().equals("__other")) {
                cBreakTime.setBuilding(alterBuilding);
            }

            // Escape characters traitement
            cBreakTime.setBuilding(SQLProtection.format(cBreakTime.getBuilding()));

            crud.createBreakTime(cBreakTime);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        cBreakTime = new BreakTime();
        selectedDays = null;
        selectedPromos = null;
        startDate = new Date(0, 0, 0, 8, 0);
        endDate = new Date(0, 0, 0, 18, 0);
        alterBuilding = "";

        return "created";
    }

    /**
     * Cancel modifications
     * @return a string indicating modification/creation is canceled
     */
    public String cancel() {
        selectedDays = null;
        selectedPromos = null;
        startDate = new Date(0, 0, 0, 8, 0);
        endDate = new Date(0, 0, 0, 18, 0);

        return "cancel";
    }


    /**
     * Delete the break time corresponding with the identifier
     */
    public void delete() {
        try {

            this.crudService = new BreakTimeCRUDService();
            BreakTimeCRUD crud = crudService.getBreakTimeCRUDPort();

            BreakTime BreakTimeToDelete = crud.readBreakTime(id);
            crud.deleteBreakTime(BreakTimeToDelete);

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
            this.crudService = new BreakTimeCRUDService();
            BreakTimeCRUD crud = crudService.getBreakTimeCRUDPort();

            uBreakTime = crud.readBreakTime(id);

            startDate = uBreakTime.getStart().toGregorianCalendar().getTime();
            endDate = uBreakTime.getEnd().toGregorianCalendar().getTime();

            selectedDays = new String[uBreakTime.getDays().size()];
            for(int i=0; i< uBreakTime.getDays().size(); i++) {
                selectedDays[i] = uBreakTime.getDays().get(i);
            }

            selectedPromos = new Integer[uBreakTime.getPromos().size()];
            for(int i=0; i< uBreakTime.getPromos().size(); i++) {
                selectedPromos[i] = uBreakTime.getPromos().get(i).getId();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "update";
    }

    /**
     * Update the uBreakTime break time
     * @return a string indicating the break time is updated
     */
    public String update() {
        try {
            this.crudService = new BreakTimeCRUDService();
            BreakTimeCRUD crud = crudService.getBreakTimeCRUDPort();

            this.promoFinderService = new PromoFinderService();
            PromoFinder finderPort = promoFinderService.getPromoFinderPort();

            uBreakTime.setStart(DateFormat.toXmlCalendar(startDate));
            uBreakTime.setEnd(DateFormat.toXmlCalendar(endDate));

            uBreakTime.getDays().clear();
            for(String day : selectedDays) {
                uBreakTime.getDays().add(day);
            }

            uBreakTime.getPromos().clear();
            Promo promotion;
            for(Integer promo : selectedPromos) {
                promotion = new Promo();
                promotion.setId(finderPort.findPromoById(promo).getId());
                promotion.setCode(finderPort.findPromoById(promo).getCode());
                promotion.setName(finderPort.findPromoById(promo).getName());

                uBreakTime.getPromos().add(promotion);
            }

            if(uBreakTime.getBuilding().equals("__other")) {
                uBreakTime.setBuilding(alterBuilding);
            }

            // Escape characters traitement
            uBreakTime.setBuilding(SQLProtection.format(uBreakTime.getBuilding()));

            crud.updateBreakTime(uBreakTime);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        selectedDays = null;
        selectedPromos = null;
        startDate = new Date(0, 0, 0, 8, 0);
        endDate = new Date(0, 0, 0, 18, 0);

        return "updated";
    }

    /**
     * Set the sort method
     * @return a string indicating the break time are ready to be sorted
     */
    public String sortBy() {
        return "sorted";
    }
}
