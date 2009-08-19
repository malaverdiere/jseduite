package webadmin.picturealbum;

import fr.unice.i3s.modalis.jseduite.technical.image.registry.PictureAlbum;
import fr.unice.i3s.modalis.jseduite.technical.image.registry.PictureAlbumRegistryCRUD;
import fr.unice.i3s.modalis.jseduite.technical.image.registry.PictureAlbumRegistryCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.image.registry.PictureAlbumRegistryFinder;
import fr.unice.i3s.modalis.jseduite.technical.image.registry.PictureAlbumRegistryFinderService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.xml.ws.WebServiceRef;
import webadmin.picturealbum.comparators.*;
import webadmin.util.DateFormat;
import webadmin.util.SQLProtection;

/**
 *
 * @author Steve Colombié
 */

public class PictureAlbumManagedBean {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/PictureAlbumRegistry/PictureAlbumRegistryFinderService?wsdl")
    PictureAlbumRegistryFinderService finderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/PictureAlbumRegistry/PictureAlbumRegistryCRUDService?wsdl")
    PictureAlbumRegistryCRUDService crudService;

    //The list of the picture albums
    private ArrayList<PictureAlbum> pictureAlbums;

    // The list cardinality
    private int pictureAlbumsCard;

    //The transient picture album
    private PictureAlbum cPictureAlbum = new PictureAlbum();
    private PictureAlbum uPictureAlbum = new PictureAlbum();

    //The start date
    private Date date = new Date();

    //The current ID
    private int id = 0;

    //The sorting method
    private int sort;

    // The repositories
    private List<SelectItem> repositories;


    /**
     * Constructor
     */
    public PictureAlbumManagedBean () {

    }

    /**
     * Get the picture albums cardinality
     * @return the picture albums cardinality
     */
    public int getPictureAlbumsCard() {
        return pictureAlbumsCard;
    }

    /**
     * Get the created picture album
     * @return the created picture album
     */
    public PictureAlbum getcPictureAlbum() {
        return cPictureAlbum;
    }

    /**
     * Set the created picture album
     * @param p the picture album to create
     */
    public void setcPictureAlbum(PictureAlbum p) {
        this.cPictureAlbum = p;
    }

    /**
     * Get the picture album to update
     * @return the picture album to update
     */
    public PictureAlbum getuPictureAlbum() {
        return uPictureAlbum;
    }

    /**
     * Set the picture album to update
     * @param p the picture album to update
     */
    public void setuPictureAlbum(PictureAlbum p) {
        this.uPictureAlbum = p;
    }


    /**
     * Get the start date
     * @return the start date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the start date
     * @param date the start date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get the identifier
     * @return the identifer
     */
    public int getId() {
        return this.id;

    }

    /**
     * Set the identifier
     * @param i the identifier
     */
    public void setId(int i) {
        this.id = i;
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
     * Get the picture albums
     * @return a list of the picture albums
     */
    public ArrayList<PictureAlbum> getPictureAlbums() {
        pictureAlbums = new ArrayList<PictureAlbum>();

        try {
            //Get the internal news ids
            this.finderService = new PictureAlbumRegistryFinderService();
            PictureAlbumRegistryFinder finderPort = finderService.getPictureAlbumRegistryFinderPort();
            List<Integer> pictureAlbumIds = finderPort.getAllPictureAlbumReferences();

            //Get the internal news
            this.crudService = new PictureAlbumRegistryCRUDService();
            PictureAlbumRegistryCRUD crudPort = crudService.getPictureAlbumRegistryCRUDPort();

            for(int i=0; i<pictureAlbumIds.size(); i++) {
                pictureAlbums.add(crudPort.readPictureAlbum(pictureAlbumIds.get(i)));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Sorting the values
        switch(sort) {
            case PictureAlbumSorter.sortByRepository:
                Collections.sort(pictureAlbums, new PictureAlbumRepositoryComparator());
                break;

            case PictureAlbumSorter.sortByName:
                Collections.sort(pictureAlbums, new PictureAlbumNameComparator());
                break;

            case PictureAlbumSorter.sortByStart:
                Collections.sort(pictureAlbums, new PictureAlbumStartComparator());
                break;

            case PictureAlbumSorter.sortByRepositoryDesc:
                Collections.sort(pictureAlbums, new PictureAlbumRepositoryComparatorDesc());
                break;

            case PictureAlbumSorter.sortByNameDesc:
                Collections.sort(pictureAlbums, new PictureAlbumRepositoryComparatorDesc());
                break;

            case PictureAlbumSorter.sortByStartDesc:
            default:
                Collections.sort(pictureAlbums, new PictureAlbumRepositoryComparatorDesc());
                break;
        }

        pictureAlbumsCard = pictureAlbums.size();

        return pictureAlbums;
    }

    /**
     * Get the repositories
     * @return the list of repositories
     */
    public List<SelectItem> getRepositories() {
        repositories = new ArrayList<SelectItem>();

        SelectItem item = new SelectItem("flickr", "Flickr");
        repositories.add(item);

        item = new SelectItem("picasa", "Picasa");
        repositories.add(item);

        return repositories;
    }


    /**
     * Create a picture album
     * @return a string indicating the picture album is created
     */
    public String create() {
        try {

            this.crudService = new PictureAlbumRegistryCRUDService();
            PictureAlbumRegistryCRUD crud = crudService.getPictureAlbumRegistryCRUDPort();

            cPictureAlbum.setValidFrom(DateFormat.toXmlCalendar(date));

            // Escape characters traitement
            cPictureAlbum.setUser(SQLProtection.format(cPictureAlbum.getUser()));
            cPictureAlbum.setName(SQLProtection.format(cPictureAlbum.getName()));
            cPictureAlbum.setAlbum(SQLProtection.format(cPictureAlbum.getAlbum()));

            crud.createPictureAlbum(cPictureAlbum);

            cPictureAlbum = new PictureAlbum();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        date = new Date();

        return "created";
    }

    /**
     * Cancel modifications
     * @return a string indicating modification/creation is canceled
     */
    public String cancel() {
        date = new Date();
        return "cancel";
    }


    /**
     * Delete the picture album corresponding with the identifier
     */
    public void delete() {
        try {

            this.crudService = new PictureAlbumRegistryCRUDService();
            PictureAlbumRegistryCRUD crud = crudService.getPictureAlbumRegistryCRUDPort();

            PictureAlbum pictureAlbumToDelete = crud.readPictureAlbum(id);
            crud.deletePictureAlbum(pictureAlbumToDelete);

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
            this.crudService = new PictureAlbumRegistryCRUDService();
            PictureAlbumRegistryCRUD crud = crudService.getPictureAlbumRegistryCRUDPort();

            uPictureAlbum = crud.readPictureAlbum(id);

            date = uPictureAlbum.getValidFrom().toGregorianCalendar().getTime();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "update";
    }

    /**
     * Update the uNews internal news
     * @return a string indicating the internal news is updated
     */
    public String update() {
        try {
            this.crudService = new PictureAlbumRegistryCRUDService();
            PictureAlbumRegistryCRUD crud = crudService.getPictureAlbumRegistryCRUDPort();

            uPictureAlbum.setValidFrom(DateFormat.toXmlCalendar(date));

            // Escape characters traitement
            uPictureAlbum.setUser(SQLProtection.format(uPictureAlbum.getUser()));
            uPictureAlbum.setName(SQLProtection.format(uPictureAlbum.getName()));
            uPictureAlbum.setAlbum(SQLProtection.format(uPictureAlbum.getAlbum()));

            crud.updatePictureAlbum(uPictureAlbum);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        date = new Date();

        return "updated";
    }

    /**
     * Set the sort method
     * @return a string indicating the breaking news are ready to be sorted
     */
    public String sortBy() {
        return "sorted";
    }
}
