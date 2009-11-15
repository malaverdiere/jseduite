package webadmin.breakingnews.comparators;

import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakNew;
import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class BreakingNewsDateComparatorDesc implements Comparator<BreakNew> {

    public int compare(BreakNew o1, BreakNew o2) {
        return o2.getStamp().compare(o1.getStamp());
    }

}
