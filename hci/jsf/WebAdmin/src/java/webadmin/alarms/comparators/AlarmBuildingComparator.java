package webadmin.alarms.comparators;

import java.util.Comparator;
import webadmin.alarms.Alarms;

/**
 *
 * @author Steve Colombié
 */
public class AlarmBuildingComparator implements Comparator<Alarms> {

    public int compare(Alarms o1, Alarms o2) {
        return o1.getBreakTime().getBuilding().toUpperCase().compareTo(o2.getBreakTime().getBuilding().toUpperCase());
    }

}
