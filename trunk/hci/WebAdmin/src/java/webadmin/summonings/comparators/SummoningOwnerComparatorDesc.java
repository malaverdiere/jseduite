package webadmin.summonings.comparators;

import fr.unice.i3s.modalis.jseduite.technical.news.summon.Summoning;
import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class SummoningOwnerComparatorDesc  implements Comparator<Summoning>{

    public int compare(Summoning o1, Summoning o2) {
        return o2.getOwner().toUpperCase().compareTo(o1.getOwner().toUpperCase());
    }

}