
package webadmin.partnerkeys;

import fr.unice.i3s.modalis.jseduite.technical.registry.partners.PartnerKey;
import fr.unice.i3s.modalis.jseduite.technical.registry.partners.PartnerKeysCRUD;
import fr.unice.i3s.modalis.jseduite.technical.registry.partners.PartnerKeysCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.registry.partners.PartnerKeysFinder;
import fr.unice.i3s.modalis.jseduite.technical.registry.partners.PartnerKeysFinderService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.ws.WebServiceRef;
import webadmin.partnerkeys.comparators.PartnerKeysKeyComparator;
import webadmin.partnerkeys.comparators.PartnerKeysKeyComparatorDesc;

/**
 *
 * @author Steve Colombié
 */
public class PartnerKeysManagedBean {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/PartnerKeys/PartnerKeysFinderService?wsdl")
    PartnerKeysFinderService finderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/PartnerKeys/PartnerKeysCRUDService?wsdl")
    PartnerKeysCRUDService crudService;

    //The list of the partner keys
    private ArrayList<PartnerKey> partnerKeys;

    // The list cardinality
    private int partnerKeysCard;

    //The transient partner key
    private PartnerKey cPartnerKey = new PartnerKey();
    private PartnerKey uPartnerKey = new PartnerKey();

    //The current ID
    private String id = "";

    //The sorting method
    private int sort = PartnerKeysSorter.sortByKey;

    /**
     * Constructor
     */
    public PartnerKeysManagedBean () {

    }

    /**
     * Get the partner keys cardinality
     * @return the partner keys cardinality
     */
    public int getPartnerKeysCard() {
        return partnerKeysCard;
    }

    /**
     * Get the created partner key
     * @return the created partner key
     */
    public PartnerKey getcPartnerKey() {
        return cPartnerKey;
    }

    /**
     * Set the created partner key
     * @param p the partner key to create
     */
    public void setcPartnerKey(PartnerKey p) {
        this.cPartnerKey = p;
    }

    /**
     * Get the partner key to update
     * @return the partner key to update
     */
    public PartnerKey getuPartnerKey() {
        return uPartnerKey;
    }

    /**
     * Set the partner key to update
     * @param p the partner key to update
     */
    public void setuPartnerKey(PartnerKey p) {
        this.uPartnerKey = p;
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
     * Get the partner keys
     * @return a list of the partner keys
     */
    public ArrayList<PartnerKey> getPartnerKeys() {
        partnerKeys = new ArrayList<PartnerKey>();

        try {
            //Get the partner keys ids
            this.finderService = new PartnerKeysFinderService();
            PartnerKeysFinder finderPort = finderService.getPartnerKeysFinderPort();
            List<String> partnerKeysIds = finderPort.getAllPartnerKeysReferences();

            //Get the partner keys
            this.crudService = new PartnerKeysCRUDService();
            PartnerKeysCRUD crudPort = crudService.getPartnerKeysCRUDPort();

            for(int i=0; i<partnerKeysIds.size(); i++) {
                partnerKeys.add(crudPort.readPartnerKey(partnerKeysIds.get(i)));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Sorting the values
        switch(sort) {
            case PartnerKeysSorter.sortByKeyDesc:
            Collections.sort(partnerKeys, new PartnerKeysKeyComparatorDesc());
            break;

            case PartnerKeysSorter.sortByKey:
            default:
            Collections.sort(partnerKeys, new PartnerKeysKeyComparator());
            break;
        }
        partnerKeysCard = partnerKeys.size();

        return partnerKeys;
    }


    /**
     * Create a partner key
     * @return a string indicating the partner key is created
     */
    public String create() {
        try {

            this.crudService = new PartnerKeysCRUDService();
            PartnerKeysCRUD crud = crudService.getPartnerKeysCRUDPort();

            crud.createPartnerKey(cPartnerKey);

            cPartnerKey = new PartnerKey();

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
     * Delete the partner key corresponding with the identifier
     */
    public void delete() {
        try {

            this.crudService = new PartnerKeysCRUDService();
            PartnerKeysCRUD crud = crudService.getPartnerKeysCRUDPort();

            PartnerKey partnerKeyToDelete = crud.readPartnerKey(id);
            crud.deletePartnerKey(partnerKeyToDelete);

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
            this.crudService = new PartnerKeysCRUDService();
            PartnerKeysCRUD crud = crudService.getPartnerKeysCRUDPort();

            uPartnerKey = crud.readPartnerKey(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "update";
    }

    /**
     * Update the uPartnerKey partner key
     * @return a string indicating the partner key is updated
     */
    public String update() {
        try {
            this.crudService = new PartnerKeysCRUDService();
            PartnerKeysCRUD crud = crudService.getPartnerKeysCRUDPort();

            crud.updatePartnerKey(uPartnerKey);

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
