package webadmin.plannings.comparators;

import fr.unice.i3s.modalis.jseduite.technical.plannings.Planning;
import fr.unice.i3s.modalis.jseduite.technical.promos.Promo;
import fr.unice.i3s.modalis.jseduite.technical.promos.PromoCRUD;
import fr.unice.i3s.modalis.jseduite.technical.promos.PromoCRUDService;
import java.util.Comparator;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Steve Colombié
 */
public class PlanningPromoComparator  implements Comparator<Planning>{


    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/PromoCRUDService?wsdl")
    PromoCRUDService promoCRUDService;

    public int compare(Planning o1, Planning o2) {
        Promo p1 = new Promo();
        Promo p2 = new Promo();


        try {
            this.promoCRUDService = new PromoCRUDService();
            PromoCRUD port = promoCRUDService.getPromoCRUDPort();

            p1 = port.readPromo(o1.getId());
            p2 = port.readPromo(o2.getId());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return p1.getCode().toLowerCase().compareTo(p2.getCode().toLowerCase());
    }

}
