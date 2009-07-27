package webadmin.alarms.comparators;

import java.util.Comparator;
import webadmin.alarms.Alarms;

/**
 *
 * @author Steve Colombié
 */
public class AlarmStartComparator implements Comparator<Alarms> {

    public int compare(Alarms o1, Alarms o2) {
        return o1.getBreakTime().getStart().compare(o2.getBreakTime().getStart());
    }

}
