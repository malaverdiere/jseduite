package webadmin.breakscreen.comparators;

import webadmin.breakscreen.comparators.*;
import fr.unice.i3s.modalis.jseduite.technical.breaks.BreakScreen;
import java.util.Comparator;

/**
 *
 * @author Christophe Desclaux
 */
public class BreakScreenBuildingComparator implements Comparator<BreakScreen> {

    public int compare(BreakScreen o1, BreakScreen o2) {
        return o1.getBuilding().toUpperCase().compareTo(o2.getBuilding().toUpperCase());
    }

}
