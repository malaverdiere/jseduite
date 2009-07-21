package webadmin.breaktime.comparators;

import fr.unice.i3s.modalis.jseduite.technical.breaktime.BreakTime;
import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class BreakTimeBuildingComparator implements Comparator<BreakTime> {

    public int compare(BreakTime o1, BreakTime o2) {
        return o1.getBuilding().compareTo(o2.getBuilding());
    }

}
