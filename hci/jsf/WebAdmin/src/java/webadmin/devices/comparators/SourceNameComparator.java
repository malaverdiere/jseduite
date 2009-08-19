package webadmin.devices.comparators;

import java.util.Comparator;
import webadmin.devices.SourceData;

/**
 *
 * @author Steve Colombié
 */
public class SourceNameComparator  implements Comparator<SourceData>{

    public int compare(SourceData o1, SourceData o2) {
        return o1.getSource().getName().compareTo(o2.getSource().getName());
    }

}
