package BreakingNews;



import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakNew;
import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakingNewsCRUD;
import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakingNewsCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakingNewsFinder;
import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakingNewsFinderService;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Steve Colombié
 */

public class BreakingNewsManagedBean {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/BreakingNews/BreakingNewsFinderService?wsdl")
    BreakingNewsFinderService finderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/BreakingNews/BreakingNewsCRUDService?wsdl")
    BreakingNewsCRUDService crudService;

    //The list of the breaking news
    private ArrayList<BreakNew> breakingNews;

    public ArrayList<BreakNew> getBreakingNews() {
        breakingNews = new ArrayList<BreakNew>();

        try {
            //Get the breaking news ids
            this.finderService = new BreakingNewsFinderService();
            BreakingNewsFinder finderPort = finderService.getBreakingNewsFinderPort();
            List<Integer> breakingNewsIds = finderPort.getAllBreakingNewsReferences();

            //Get the breaking news
            this.crudService = new BreakingNewsCRUDService();
            BreakingNewsCRUD crudPort = crudService.getBreakingNewsCRUDPort();
            int id;
            for(int i=0; i<breakingNewsIds.size(); i++) {
                id = breakingNewsIds.get(i);
                breakingNews.add(crudPort.readBreakingNews(id));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return breakingNews;
    }
}
