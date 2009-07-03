package internalnewstest;

import fr.unice.i3s.modalis.jseduite.technical.news.internal.InternalNewsCRUD;
import fr.unice.i3s.modalis.jseduite.technical.news.internal.InternalNewsCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.news.internal.News;

/**
 *
 * @author Steve Colombié
 */
public class InternalNewsCRUDProxy {
    public int create(News i) throws Exception {
        InternalNewsCRUDService service = new InternalNewsCRUDService();
        InternalNewsCRUD port = service.getInternalNewsCRUDPort();
        int result = port.createInternalNews(i);
        return result;
    }

    public News read(int id) throws Exception {
        InternalNewsCRUDService service = new InternalNewsCRUDService();
        InternalNewsCRUD port = service.getInternalNewsCRUDPort();
        return port.readInternalNews(id);
    }


    public void update(News i) throws Exception {
        InternalNewsCRUDService service = new InternalNewsCRUDService();
        InternalNewsCRUD port = service.getInternalNewsCRUDPort();
        port.updateInternalNews(i);
    }


    public void delete(News i) throws Exception {
        InternalNewsCRUDService service = new InternalNewsCRUDService();
        InternalNewsCRUD port = service.getInternalNewsCRUDPort();
        port.deleteInternalNews(i);
    }
}
