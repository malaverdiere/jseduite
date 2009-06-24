package breakingnewstest;

import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakNew;
import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakingNewsFinder;
import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakingNewsFinderService;
import java.util.List;

/**
 *
 * @author Steve Colombié
 */
public class BreakingNewsFinderProxy {
    public BreakNew findBreakNewById (int id) throws Exception {
        BreakingNewsFinderService service = new BreakingNewsFinderService();
        BreakingNewsFinder port = service.getBreakingNewsFinderPort();
        return port.findBreakNewById(id);
    }

    public List<Integer> getAllBreakingNewsReferences () throws Exception {
        BreakingNewsFinderService service = new BreakingNewsFinderService();
        BreakingNewsFinder port = service.getBreakingNewsFinderPort();
        return port.getAllBreakingNewsReferences();
    }
}