
package webadmin.promotions;

import fr.unice.i3s.modalis.jseduite.technical.promos.Promo;
import fr.unice.i3s.modalis.jseduite.technical.promos.PromoCRUD;
import fr.unice.i3s.modalis.jseduite.technical.promos.PromoCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.promos.PromoFinder;
import fr.unice.i3s.modalis.jseduite.technical.promos.PromoFinderService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.ws.WebServiceRef;
import webadmin.promotions.comparators.PromoCodeComparator;
import webadmin.promotions.comparators.PromoCodeComparatorDesc;
import webadmin.promotions.comparators.PromoNameComparator;
import webadmin.promotions.comparators.PromoNameComparatorDesc;
import webadmin.util.SQLProtection;

/**
 *
 * @author Steve Colombié
 */
public class PromotionManagedBean {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/PromoFinderService?wsdl")
    PromoFinderService finderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/PromoCRUDService?wsdl")
    PromoCRUDService crudService;

    //The list of the promotions
    private ArrayList<Promo> promos;

    // The list cardinality
    private int promosCard;

    //The transient promotion
    private Promo cPromo = new Promo();
    private Promo uPromo = new Promo();

    //The current ID
    private int id = 0;

    //The sorting method
    private int sort = PromotionSorter.sortByCode;

    /**
     * Constructor
     */
    public PromotionManagedBean () {

    }

    /**
     * Get the promotions cardinality
     * @return the promotions cardinality
     */
    public int getPromosCard() {
        return promosCard;
    }

    /**
     * Get the created promotion
     * @return the created promotion
     */
    public Promo getcPromo() {
        return cPromo;
    }

    /**
     * Set the created promotion
     * @param p the promotion to create
     */
    public void setcPromo(Promo p) {
        this.cPromo = p;
    }

    /**
     * Get the promotion to update
     * @return the promotion to update
     */
    public Promo getuPromo() {
        return uPromo;
    }

    /**
     * Set the promotion to update
     * @param p the promotion to update
     */
    public void setuPromo(Promo p) {
        this.uPromo = p;
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
     * Get the promotions
     * @return a list of the promotions
     */
    public ArrayList<Promo> getPromos() {
        promos = new ArrayList<Promo>();

        try {
            //Get the promotions ids
            this.finderService = new PromoFinderService();
            PromoFinder finderPort = finderService.getPromoFinderPort();
            List<Integer> promosIds = finderPort.getAllPromosReferences();

            //Get the promotions
            this.crudService = new PromoCRUDService();
            PromoCRUD crudPort = crudService.getPromoCRUDPort();

            for(int i=0; i<promosIds.size(); i++) {
                promos.add(crudPort.readPromo(promosIds.get(i)));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Sorting the values
        switch(sort) {
            case PromotionSorter.sortByName:
                Collections.sort(promos, new PromoNameComparator());
                break;

            case PromotionSorter.sortByCodeDesc:
                Collections.sort(promos, new PromoCodeComparatorDesc());
                break;

            case PromotionSorter.sortByNameDesc:
                Collections.sort(promos, new PromoNameComparatorDesc());
                break;

            case PromotionSorter.sortByCode:
            default:
                Collections.sort(promos, new PromoCodeComparator());
                break;
        }

        promosCard = promos.size();

        return promos;
    }


    /**
     * Create a promotion
     * @return a string indicating the promotion is created
     */
    public String create() {
        try {

            this.crudService = new PromoCRUDService();
            PromoCRUD crud = crudService.getPromoCRUDPort();

            // Escape characters traitement
            cPromo.setCode(SQLProtection.format(cPromo.getCode()));
            cPromo.setName(SQLProtection.format(cPromo.getName()));

            crud.createPromo(cPromo);

            cPromo = new Promo();

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
     * Delete the promotion corresponding with the identifier
     */
    public void delete() {
        try {

            this.crudService = new PromoCRUDService();
            PromoCRUD crud = crudService.getPromoCRUDPort();

            Promo promoToDelete = crud.readPromo(id);
            crud.deletePromo(promoToDelete);

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
            this.crudService = new PromoCRUDService();
            PromoCRUD crud = crudService.getPromoCRUDPort();

            uPromo = crud.readPromo(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "update";
    }

    /**
     * Update the uPromo promotion
     * @return a string indicating the promotion is updated
     */
    public String update() {
        try {
            this.crudService = new PromoCRUDService();
            PromoCRUD crud = crudService.getPromoCRUDPort();

            // Escape characters traitement
            uPromo.setCode(SQLProtection.format(uPromo.getCode()));
            uPromo.setName(SQLProtection.format(uPromo.getName()));

            crud.updatePromo(uPromo);

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
