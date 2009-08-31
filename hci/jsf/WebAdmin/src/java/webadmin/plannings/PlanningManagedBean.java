package webadmin.plannings;

import javax.xml.ws.WebServiceRef;
import fr.unice.i3s.modalis.jseduite.technical.plannings.Planning;
import fr.unice.i3s.modalis.jseduite.technical.plannings.PlanningFinder;
import fr.unice.i3s.modalis.jseduite.technical.plannings.PlanningFinderService;
import fr.unice.i3s.modalis.jseduite.technical.plannings.PlanningCRUD;
import fr.unice.i3s.modalis.jseduite.technical.plannings.PlanningCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.plannings.PlanningGroup;
import fr.unice.i3s.modalis.jseduite.technical.promos.PromoFinder;
import fr.unice.i3s.modalis.jseduite.technical.promos.PromoFinderService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.model.SelectItem;
import webadmin.plannings.comparators.PlanningPromoComparator;
import webadmin.plannings.comparators.PlanningPromoComparatorDesc;
import webadmin.util.SQLProtection;


/**
 *
 * @author Steve Colombié
 */

public class PlanningManagedBean {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/PlanningFinderService?wsdl")
    PlanningFinderService finderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/PlanningCRUDService?wsdl")
    PlanningCRUDService crudService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/PromoFinderService?wsdl")
    PromoFinderService promoFinderService;


    //The list of the plannings
    private ArrayList<Planning> plannings;

    // The list cardinality
    private int planningsCard;

    //The transient plannings
    private Planning planning = new Planning();

    // The promotions
    private List<SelectItem> promos;

    //The current ID
    private int id = 0;

    //The group to delete
    private int groupDeletion;

    //The sorting method
    private int sort = PlanningSorter.sortByPromo;


    /**
     * Constructor
     */
    public PlanningManagedBean () {

    }

    /**
     * Get the plannings cardinality
     * @return the plannings cardinality
     */
    public int getPlanningCard() {
        return planningsCard;
    }

    /**
     * Get the planning to update
     * @return the planning to update
     */
    public Planning getPlanning() {
        return planning;
    }

    /**
     * Set the planning to update
     * @param p the planning to update
     */
    public void setPlanning(Planning p) {
        this.planning = p;
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
     * @param id the identifier
     */
    public void setId(int id) {
        this.id = id;
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
     * Get the group to delete
     * @return the group to delete
     */
    public int getGroupDeletion() {
        return groupDeletion;
    }

    /**
     * Set the group to delete
     * @param callDeletion the group to delete
     */
    public void setGroupDeletion(int groupDeletion) {
        this.groupDeletion = groupDeletion;
    }

    /**
     * Get the promotions
     * @return the list of promotions
     */
    public List<SelectItem> getPromos() {
        List<Integer> promosBuf;
        List<Integer> existingPromos;
        promos = new ArrayList<SelectItem>();

        try {
            this.promoFinderService = new PromoFinderService();
            PromoFinder finderPort = promoFinderService.getPromoFinderPort();

            this.finderService = new PlanningFinderService();
            PlanningFinder planningPort = finderService.getPlanningFinderPort();

            promosBuf = finderPort.getAllPromosReferences();
            existingPromos = planningPort.getAllPlanningReferences();

            for (Integer pid : promosBuf) {
                if(!existingPromos.contains(pid)) {
                    SelectItem item = new SelectItem(pid, finderPort.findPromoById(pid).getCode() + " - " + finderPort.findPromoById(pid).getName());
                    promos.add(item);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return promos;
    }

    /**
     * Get the plannings
     * @return a list of the plannings
     */
    public ArrayList<Planning> getPlannings() {
        plannings = new ArrayList<Planning>();

        try {
            //Get the plannings ids
            this.finderService = new PlanningFinderService();
            PlanningFinder finderPort = finderService.getPlanningFinderPort();
            List<Integer> planningIds = finderPort.getAllPlanningReferences();

            //Get the plannings
            this.crudService = new PlanningCRUDService();
            PlanningCRUD crudPort = crudService.getPlanningCRUDPort();

            for(int i=0; i<planningIds.size(); i++) {
                plannings.add(crudPort.readPlanning(planningIds.get(i)));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Sorting the values
        switch(sort) {
            case PlanningSorter.sortByPromoDesc:
                Collections.sort(plannings, new PlanningPromoComparatorDesc());
                break;

            case PlanningSorter.sortByPromo:
            default:
                Collections.sort(plannings, new PlanningPromoComparator());
                break;
        }

        planningsCard = plannings.size();

        return plannings;
    }

    /**
     * Initiate the creation process
     * @return a string indicating the creation is ready to be done
     */
    public String goCreate()
    {
        planning = new Planning();

        return "create";
    }

    /**
     * Create a planning
     * @return a string indicating the planning is created
     */
    public String create() {
        try {
            this.crudService = new PlanningCRUDService();
            PlanningCRUD crud = crudService.getPlanningCRUDPort();

            // Escape characters traitement
            planning.setPlanning(SQLProtection.format(planning.getPlanning()));
            for(int i=0; i<planning.getGroups().size(); i++) {
                planning.getGroups().get(i).setName(SQLProtection.format(planning.getGroups().get(i).getName()));
                planning.getGroups().get(i).setPlanning(SQLProtection.format(planning.getGroups().get(i).getPlanning()));
            }

            crud.createPlanning(planning);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        planning = new Planning();

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
     * Delete the planning corresponding with the identifier
     */
    public void delete() {
        try {

            this.crudService = new PlanningCRUDService();
            PlanningCRUD crud = crudService.getPlanningCRUDPort();

            Planning PlanningToDelete = crud.readPlanning(id);
            crud.deletePlanning(PlanningToDelete);

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
            this.crudService = new PlanningCRUDService();
            PlanningCRUD crud = crudService.getPlanningCRUDPort();

            // Read the planning to update
            planning = crud.readPlanning(id);

            //Change the group ids
            int i = 1;
            for(PlanningGroup group : planning.getGroups()) {
                group.setId(i);
                i++;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "update";
    }

    /**
     * Update the planning planning
     * @return a string indicating the planning is updated
     */
    public String update() {
        try {
            this.crudService = new PlanningCRUDService();
            PlanningCRUD crud = crudService.getPlanningCRUDPort();

            // Escape characters traitement
            planning.setPlanning(SQLProtection.format(planning.getPlanning()));
            for(int i=0; i<planning.getGroups().size(); i++) {
                planning.getGroups().get(i).setName(SQLProtection.format(planning.getGroups().get(i).getName()));
                planning.getGroups().get(i).setPlanning(SQLProtection.format(planning.getGroups().get(i).getPlanning()));
            }

            crud.updatePlanning(planning);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        planning = new Planning();

        return "updated";
    }

    /**
     * Set the sort method
     * @return a string indicating the planning are ready to be sorted
     */
    public String sortBy() {
        return "sorted";
    }

    /**
     * Add the new group
     * @return a string indicating the addition of group is done
     */
    public String addition() {
        PlanningGroup group = new PlanningGroup();
        group.setId(planning.getGroups().size()+1);
        group.setName("");
        group.setPlanning("");

        planning.getGroups().add(group);

        return "add";
    }

    /**
     * Delete a group
     * @return a string indicating the group is deleted
     */
    public String deletion() {
        planning.getGroups().remove(groupDeletion-1);

        for(int i=1; i<=planning.getGroups().size(); i++) {
            planning.getGroups().get(i-1).setId(i);
        }

        return "del";
    }
}
