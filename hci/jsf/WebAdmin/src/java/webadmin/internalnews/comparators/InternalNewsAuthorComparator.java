package webadmin.internalnews.comparators;

import fr.unice.i3s.modalis.jseduite.technical.news.internal.News;
import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class InternalNewsAuthorComparator  implements Comparator<News>{

    public int compare(News o1, News o2) {
        return o1.getAuthor().compareTo(o2.getAuthor());
    }

}
