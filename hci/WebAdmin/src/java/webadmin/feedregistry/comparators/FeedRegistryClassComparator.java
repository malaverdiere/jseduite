package webadmin.feedregistry.comparators;

import fr.unice.i3s.modalis.jseduite.technical.registry.Feed;
import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class FeedRegistryClassComparator  implements Comparator<Feed>{

    public int compare(Feed o1, Feed o2) {
        return o1.getFeedClass().getName().toUpperCase().compareTo(o2.getFeedClass().getName().toUpperCase());
    }

}
