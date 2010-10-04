package webadmin.breakscreen.comparators;

import webadmin.breakscreen.comparators.*;
import fr.unice.i3s.modalis.jseduite.technical.breaks.BreakScreen;
import java.util.Comparator;

/**
 *
 * @author Christophe Desclaux
 */
public class BreakScreenStartComparator implements Comparator<BreakScreen> {

    public int compare(BreakScreen o1, BreakScreen o2) {
        return o1.getStart().compare(o2.getStart());
    }

}
