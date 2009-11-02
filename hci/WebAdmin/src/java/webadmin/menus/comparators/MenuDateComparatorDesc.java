package webadmin.menus.comparators;

import fr.unice.i3s.modalis.jseduite.technical.restaurant.Menu;
import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class MenuDateComparatorDesc implements Comparator<Menu> {

    public int compare(Menu o1, Menu o2) {
        return o2.getDate().compare(o1.getDate());
    }

}
