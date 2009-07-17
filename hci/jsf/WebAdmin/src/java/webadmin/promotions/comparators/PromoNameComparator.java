package webadmin.promotions.comparators;

import fr.unice.i3s.modalis.jseduite.technical.promos.Promo;
import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class PromoNameComparator  implements Comparator<Promo>{

    public int compare(Promo o1, Promo o2) {
        return o1.getName().compareTo(o2.getName());
    }

}
