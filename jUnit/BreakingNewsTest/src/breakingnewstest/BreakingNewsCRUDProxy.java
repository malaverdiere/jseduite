package breakingnewstest;

import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakNew;
import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakingNewsCRUD;
import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakingNewsCRUDService;

/**
 *
 * @author Steve Colombié
 */
public class BreakingNewsCRUDProxy {
    public int create(BreakNew b) throws Exception {
        BreakingNewsCRUDService service = new BreakingNewsCRUDService();
        BreakingNewsCRUD port = service.getBreakingNewsCRUDPort();
        int result = port.createBreakingNews(b);
        return result;
    }

    public BreakNew read(int id) throws Exception {
        BreakingNewsCRUDService service = new BreakingNewsCRUDService();
        BreakingNewsCRUD port = service.getBreakingNewsCRUDPort();
        return port.readBreakingNews(id);
    }


    public void update(BreakNew b) throws Exception {
        BreakingNewsCRUDService service = new BreakingNewsCRUDService();
        BreakingNewsCRUD port = service.getBreakingNewsCRUDPort();
        port.updateBreakingNews(b);
    }


    public void delete(BreakNew b) throws Exception {
        BreakingNewsCRUDService service = new BreakingNewsCRUDService();
        BreakingNewsCRUD port = service.getBreakingNewsCRUDPort();
        port.deleteBreakingNews(b);
    }
}
