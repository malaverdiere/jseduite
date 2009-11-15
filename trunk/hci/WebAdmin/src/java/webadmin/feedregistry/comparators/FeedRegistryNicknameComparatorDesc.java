package webadmin.feedregistry.comparators;

import fr.unice.i3s.modalis.jseduite.technical.registry.Feed;
import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class FeedRegistryNicknameComparatorDesc  implements Comparator<Feed> {

    public int compare(Feed o1, Feed o2) {
        return o2.getNickname().toUpperCase().compareTo(o1.getNickname().toUpperCase());
    }

}
