package webadmin.summonings.comparators;

import fr.unice.i3s.modalis.jseduite.technical.news.summon.Summoning;
import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class SummoningLevelComparatorDesc  implements Comparator<Summoning>{

    public int compare(Summoning o1, Summoning o2) {
        if(o2.getLevel() == o1.getLevel()) {
            return 0;
        }
        else if (o2.getLevel() > o1.getLevel()) {
            return 1;
        }
        else {
            return -1;
        }
    }

}