package webadmin.internalnews.comparators;

import fr.unice.i3s.modalis.jseduite.technical.news.internal.News;
import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class InternalNewsAuthorComparatorDesc  implements Comparator<News>{

    public int compare(News o1, News o2) {
        return o2.getAuthor().toUpperCase().compareTo(o1.getAuthor().toUpperCase());
    }

}
