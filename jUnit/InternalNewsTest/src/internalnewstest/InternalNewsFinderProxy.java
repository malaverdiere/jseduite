package internalnewstest;

import fr.unice.i3s.modalis.jseduite.technical.news.internal.InternalNewsFinder;
import fr.unice.i3s.modalis.jseduite.technical.news.internal.InternalNewsFinderService;
import fr.unice.i3s.modalis.jseduite.technical.news.internal.News;
import java.util.List;

/**
 *
 * @author Steve Colombié
 */
public class InternalNewsFinderProxy {
    public News findInternalNewsById (int id) throws Exception {
        InternalNewsFinderService service = new InternalNewsFinderService();
        InternalNewsFinder port = service.getInternalNewsFinderPort();
        return port.findInternalNewsById(id);
    }

    public List<Integer> getAllInternalNewsReferences () throws Exception {
        InternalNewsFinderService service = new InternalNewsFinderService();
        InternalNewsFinder port = service.getInternalNewsFinderPort();
        return port.getAllInternalNewsReferences();
    }
}
