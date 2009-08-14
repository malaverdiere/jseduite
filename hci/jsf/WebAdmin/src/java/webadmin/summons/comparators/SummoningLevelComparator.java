package webadmin.summons.comparators;

import fr.unice.i3s.modalis.jseduite.technical.news.summon.Summoning;
import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class SummoningLevelComparator  implements Comparator<Summoning>{

    public int compare(Summoning o1, Summoning o2) {
        if(o1.getLevel() == o2.getLevel()) {
            return 0;
        }
        else if (o1.getLevel() > o2.getLevel()) {
            return 1;
        }
        else {
            return -1;
        }
    }

}