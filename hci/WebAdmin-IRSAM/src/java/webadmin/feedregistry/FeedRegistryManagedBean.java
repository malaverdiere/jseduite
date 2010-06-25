package webadmin.feedregistry;

import fr.unice.i3s.modalis.jseduite.technical.registry.Feed;
import fr.unice.i3s.modalis.jseduite.technical.registry.FeedFinder;
import fr.unice.i3s.modalis.jseduite.technical.registry.FeedFinderService;
import fr.unice.i3s.modalis.jseduite.technical.registry.FeedCRUD;
import fr.unice.i3s.modalis.jseduite.technical.registry.FeedCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.registry.FeedClass;
import fr.unice.i3s.modalis.jseduite.technical.registry.FeedClassFinder;
import fr.unice.i3s.modalis.jseduite.technical.registry.FeedClassFinderService;
import fr.unice.i3s.modalis.jseduite.technical.registry.FeedClassCRUD;
import fr.unice.i3s.modalis.jseduite.technical.registry.FeedClassCRUDService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.xml.ws.WebServiceRef;
import webadmin.feedregistry.comparators.*;
import webadmin.util.Bundle;
import webadmin.util.SQLProtection;
import webadmin.util.URLParser;

/**
 *
 * @author Steve Colombié
 */

public class FeedRegistryManagedBean {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/FeedRegistry/FeedFinderService?wsdl")
    FeedFinderService feedFinderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/FeedRegistry/FeedCRUDService?wsdl")
    FeedCRUDService feedCRUDService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/FeedRegistry/FeedClassFinderService?wsdl")
    FeedClassFinderService feedClassFinderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/FeedRegistry/FeedClassCRUDService?wsdl")
    FeedClassCRUDService feedClassCRUDService;

    //The list of the feeds
    private ArrayList<Feed> feeds;

    // The list cardinality
    private int feedsCard;

    //The transient feed
    private Feed cFeed = new Feed();
    private Feed uFeed = new Feed();

    //The current IDs
    private String feedURL = "";
    private String providerDNS = "";

    // The feed url
    private String url;

    //The sorting method
    private int sort = FeedRegistrySorter.sortByNickname;

    // The classes
    private List<SelectItem> classes;

    // Alternative class
    private String alterClass;

    // Class id
    private int classId = 1;

    /**
     * Constructor
     */
    public FeedRegistryManagedBean () {

    }

    /**
     * Get the feeds cardinality
     * @return the feeds cardinality
     */
    public int getFeedsCard() {
        return feedsCard;
    }

    /**
     * Get the created feed
     * @return the created feed
     */
    public Feed getcFeed() {
        return cFeed;
    }

    /**
     * Set the created feed
     * @param f the feed to create
     */
    public void setcFeed(Feed f) {
        this.cFeed = f;
    }

    /**
     * Get the feed to update
     * @return the feed to update
     */
    public Feed getuFeed() {
        return uFeed;
    }

    /**
     * Set the feed to update
     * @param f the feed to update
     */
    public void setuFeed(Feed f) {
        this.uFeed = f;
    }

    /**
     * Get the feed url
     * @return the feed url
     */
    public String getFeedURL() {
        return feedURL;
    }

    /**
     * Set the feed url
     * @param feedURL the feed url
     */
    public void setFeedURL(String feedURL) {
        this.feedURL = feedURL;
    }

    /**
     * Get the provider dns
     * @return the provider dns
     */
    public String getProviderDNS() {
        return providerDNS;
    }

    /**
     * Set the provider dns
     * @param providerDNS the provider dns
     */
    public void setProviderDNS(String providerDNS) {
        this.providerDNS = providerDNS;
    }


    /**
     * Get the sort method
     * @return the sort method
     */
    public int getSort() {
        return this.sort;

    }

    /**
     * Set the sort method
     * @param i the sort method
     */
    public void setSort(int s) {
        this.sort = s;
    }

    /**
     * Get the URL
     * @return the URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the URL
     * @param url the URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get the class id
     * @return the class id
     */
    public int getClassId() {
        return classId;
    }

    /**
     * Set the class id
     * @param classId the class id
     */
    public void setClassId(int classId) {
        this.classId = classId;
    }



    /**
     * Get the alternative class
     * @return the alternative class
     */
    public String getAlterClass() {
        return alterClass;
    }

    /**
     * Set the alternative class
     * @param alterClass the alternative class
     */
    public void setAlterClass(String alterClass) {
        this.alterClass = alterClass;
    }


    /**
     * Get the feeds
     * @return a list of the feeds
     */
    public ArrayList<Feed> getFeeds() {
        feeds = new ArrayList<Feed>();

        try {
            //Get the feeds
            this.feedFinderService = new FeedFinderService();
            FeedFinder finderPort = feedFinderService.getFeedFinderPort();

            feeds = new ArrayList<Feed>();
            for(Feed feed : finderPort.getAllFeeds()) {
                feeds.add(feed);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Sorting the values
        switch(sort) {
            case FeedRegistrySorter.sortByClass:
            Collections.sort(feeds, new FeedRegistryClassComparator());
            break;

            case FeedRegistrySorter.sortByClassDesc:
            Collections.sort(feeds, new FeedRegistryClassComparatorDesc());
            break;

            case FeedRegistrySorter.sortByNicknameDesc:
            Collections.sort(feeds, new FeedRegistryNicknameComparatorDesc());
            break;

            case FeedRegistrySorter.sortByNickname:
            default:
            Collections.sort(feeds, new FeedRegistryNicknameComparator());
            break;
        }

        feedsCard = feeds.size();

        return feeds;
    }

    /**
     * Get the classes
     * @return the list of classes
     */
    public List<SelectItem> getClasses() {
        List<Integer> classesBuf;
        classes = new ArrayList<SelectItem>();

        try {
            this.feedClassFinderService = new FeedClassFinderService();
            FeedClassFinder finderPort = feedClassFinderService.getFeedClassFinderPort();

            this.feedClassCRUDService = new FeedClassCRUDService();
            FeedClassCRUD crudPort = feedClassCRUDService.getFeedClassCRUDPort();

            classesBuf = finderPort.getAllFeedClassReferences();


            for (Integer feedClass : classesBuf) {
                SelectItem item = new SelectItem(feedClass, crudPort.readFeedClass(feedClass).getName());
                classes.add(item);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        classes.add(new SelectItem(0, Bundle.get("FORM_OTHER")));

        return classes;
    }


    /**
     * Create a feed
     * @return a string indicating the feeds is created
     */
    public String create() {
        try {
            this.feedCRUDService = new FeedCRUDService();
            FeedCRUD feedCRUD = feedCRUDService.getFeedCRUDPort();

            this.feedClassCRUDService = new FeedClassCRUDService();
            FeedClassCRUD feedClassCRUD = feedClassCRUDService.getFeedClassCRUDPort();

            cFeed.setProviderDNS(URLParser.getProvider(url));
            cFeed.setFeedURL(URLParser.getFeed(url));

            FeedClass newClass;
            if(classId == 0) {
                // Escape characters traitement
                alterClass = SQLProtection.format(alterClass);

                // Create the new class
                newClass = new FeedClass();
                newClass.setName(alterClass);
                classId = feedClassCRUD.createFeedClass(newClass);
            }

            cFeed.setFeedClass(feedClassCRUD.readFeedClass(classId));

            // Escape characters traitement
            cFeed.setFeedURL(SQLProtection.format(cFeed.getFeedURL()));
            cFeed.setProviderDNS(SQLProtection.format(cFeed.getProviderDNS()));
            cFeed.setNickname(SQLProtection.format(cFeed.getNickname()));

            feedCRUD.createFeed(cFeed);

            cFeed = new Feed();
            url = "";
            classId = 1;
            alterClass = "";

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "created";
    }

    /**
     * Cancel modifications
     * @return a string indicating modification/creation is canceled
     */
    public String cancel() {
        return "cancel";
    }


    /**
     * Delete the feed corresponding with the identifier
     */
    public void delete() {
        try {
            this.feedCRUDService = new FeedCRUDService();
            FeedCRUD crud = feedCRUDService.getFeedCRUDPort();

            Feed feedToDelete = crud.readFeed(providerDNS, feedURL);
            crud.deleteFeed(feedToDelete);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initiate the update process
     * @return a string indicating the update is ready to be done
     */
    public String goUpdate()
    {
        try {
            this.feedCRUDService = new FeedCRUDService();
            FeedCRUD crud = feedCRUDService.getFeedCRUDPort();

            uFeed = crud.readFeed(providerDNS, feedURL);

            url = providerDNS + feedURL;
            classId = uFeed.getFeedClass().getId();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "update";
    }

    /**
     * Update the uFeed new break
     * @return a string indicating the feed is updated
     */
    public String update() {
        try {
            this.feedCRUDService = new FeedCRUDService();
            FeedCRUD crud = feedCRUDService.getFeedCRUDPort();

            this.feedClassCRUDService = new FeedClassCRUDService();
            FeedClassCRUD feedClassCRUD = feedClassCRUDService.getFeedClassCRUDPort();

            uFeed.setProviderDNS(URLParser.getProvider(url));
            uFeed.setFeedURL(URLParser.getFeed(url));

            FeedClass newClass;
            if(classId == 0) {
                // Escape characters traitement
                alterClass = SQLProtection.format(alterClass);

                // Create the new class
                newClass = new FeedClass();
                newClass.setName(alterClass);
                classId = feedClassCRUD.createFeedClass(newClass);
            }

            uFeed.setFeedClass(feedClassCRUD.readFeedClass(classId));

            // Escape characters traitement
            uFeed.setFeedURL(SQLProtection.format(uFeed.getFeedURL()));
            uFeed.setProviderDNS(SQLProtection.format(uFeed.getProviderDNS()));
            uFeed.setNickname(SQLProtection.format(uFeed.getNickname()));

            crud.updateFeed(uFeed);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        uFeed = new Feed();
        url = "";
        classId = 1;
        alterClass = "";

        return "updated";
    }

    /**
     * Set the sort method
     * @return a string indicating the feeds are ready to be sorted
     */
    public String sortBy() {
        return "sorted";
    }
}
