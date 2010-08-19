package webadmin.pictogram;

import fr.unice.i3s.modalis.jseduite.technical.pictogram.Pictogram;
import fr.unice.i3s.modalis.jseduite.technical.pictogram.PictogramCRUD;
import fr.unice.i3s.modalis.jseduite.technical.pictogram.PictogramCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.pictogram.PictogramFinder;
import fr.unice.i3s.modalis.jseduite.technical.pictogram.PictogramFinderService;
import fr.unice.i3s.modalis.jseduite.upload.files.FileUploader;
import fr.unice.i3s.modalis.jseduite.upload.files.FileUploaderService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.xml.ws.WebServiceRef;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import webadmin.pictogram.comparators.*;
import webadmin.util.Bundle;
import webadmin.util.DateFormat;
import webadmin.util.SQLProtection;

/**
 *
 * @author Christophe Desclaux
 */

public class PictogramManagedBean {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/Pictogram/PictogramFinderService?wsdl")
    PictogramFinderService finderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/Pictogram/PictogramCRUDService?wsdl")
    PictogramCRUDService crudService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/FileUploader/FileUploaderService?wsdl")
    FileUploaderService fileUploaderService;
    
    //The list of the pictograms
    private ArrayList<Pictogram> pictograms;

    // The list cardinality
    private int pictogramsCard;

    //The transient pictograms
    private Pictogram cPictogram = new Pictogram();
    private Pictogram uPictogram = new Pictogram();

    //The start date
    private Date startDate = new Date();

    //The end date
    private Date endDate = new Date();

    //The current ID
    private int id = 0;

    //The sorting method
    private int sort = PictogramSorter.sortByStartDesc;

    // The uploaded file
    private UploadedFile file;

    //the folder where are pictograms
    static final String FOLDER = "pictograms/";
    // List of the files
    private List<SelectItem> files;
    private List<SelectItem> files2;

    // The file to delete
    private String fileToDelete;

    
    /**
     * Constructor
     */
    public PictogramManagedBean () {}

    /**
     * Get the pictograms cardinality
     * @return the pictograms cardinality
     */
    public int getPictogramCard() {
        return pictogramsCard;
    }

    /**
     * Get the created pictograms
     * @return the created pictograms
     */
    public Pictogram getcPictogram() {
        return cPictogram;
    }

    /**
     * Set the created pictograms
     * @param n the pictograms to create
     */
    public void setcPictogram(Pictogram n) {
        this.cPictogram = n;
    }

    /**
     * Get the pictograms to update
     * @return the pictograms to update
     */
    public Pictogram getuPictogram() {
        return uPictogram;
    }

    /**
     * Set the pictograms to update
     * @param n the pictograms to update
     */
    public void setuPictogram(Pictogram n) {
        this.uPictogram = n;
    }


    /**
     * Get the start date
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Set the start date
     * @param date the start date
     */
    public void setStartDate(Date date) {
        this.startDate = date;
    }

    /**
     * Get the end date
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Set the end date
     * @param date the end date
     */
    public void setEndDate(Date date) {
        this.endDate = date;
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
     * Get the uploaded file
     * @return the uploaded file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * Set the uploaded file
     * @param file the uploaded file
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /**
     * Get the list of files
     * @return a list of files
     */
    public List<SelectItem> getFiles() {
        this.fileUploaderService = new FileUploaderService();
        FileUploader fileUploaderPort = fileUploaderService.getFileUploaderPort();

        files = new ArrayList<SelectItem>();

        try {
            for (String name : fileUploaderPort.getFolderFiles(FOLDER)) {
                SelectItem item = new SelectItem(name, name);
                files.add(item);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return files;
    }

    /**
     * Get the list of files with "no one"
     * @return a list of files with "no one"
     */
    public List<SelectItem> getFiles2() {
        this.fileUploaderService = new FileUploaderService();
        FileUploader fileUploaderPort = fileUploaderService.getFileUploaderPort();

        files2 = new ArrayList<SelectItem>();

        files2.add(new SelectItem("", Bundle.get("FORM_NOONE")));

        try {
            for (String name : fileUploaderPort.getFolderFiles(FOLDER)) {
                SelectItem item = new SelectItem(fileUploaderPort.getURL(FOLDER,name), name);
                files2.add(item);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return files2;
    }

    /**
     * Get the file to delete name
     * @return the file to delete name
     */
    public String getFileToDelete() {
        return fileToDelete;
    }

    /**
     * Set the file to delete name
     * @param fileToDelete the file to delete name
     */
    public void setFileToDelete(String fileToDelete) {
        this.fileToDelete = fileToDelete;
    }


    /**
     * Get the pictograms
     * @return a list of the pictograms
     */
    public ArrayList<Pictogram> getPictograms() {
        pictograms = new ArrayList<Pictogram>();

        try {
            //Get the pictograms ids
            this.finderService = new PictogramFinderService();
            PictogramFinder finderPort = finderService.getPictogramFinderPort();
            List<Integer> PictogramIds = finderPort.getAllPictogramReferences();

            //Get the pictograms
            this.crudService = new PictogramCRUDService();
            PictogramCRUD crudPort = crudService.getPictogramCRUDPort();

            for(int i=0; i<PictogramIds.size(); i++) {
                pictograms.add(crudPort.readPictogram(PictogramIds.get(i)));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Sorting the values
        switch(sort) {

            case PictogramSorter.sortByStart:
                Collections.sort(pictograms, new PictogramStartComparator());
                break;

            case PictogramSorter.sortByEnd:
                Collections.sort(pictograms, new PictogramEndComparator());
                break;

            case PictogramSorter.sortByTitle:
                Collections.sort(pictograms, new PictogramTitleComparator());
                break;

            case PictogramSorter.sortByEndDesc:
                Collections.sort(pictograms, new PictogramEndComparatorDesc());
                break;

            case PictogramSorter.sortByTitleDesc:
                Collections.sort(pictograms, new PictogramTitleComparatorDesc());
                break;
            case PictogramSorter.sortByStartDesc:
            default:
                Collections.sort(pictograms, new PictogramStartComparatorDesc());
                break;
        }

        pictogramsCard = pictograms.size();

        return pictograms;
    }


    /**
     * Create an pictograms
     * @return a string indicating the pictograms is created
     */
    public String create() {
        try {

            this.crudService = new PictogramCRUDService();
            PictogramCRUD crud = crudService.getPictogramCRUDPort();

            cPictogram.setStart(DateFormat.toXmlCalendar(startDate));
            cPictogram.setEnd(DateFormat.toXmlCalendar(endDate));

            // Escape characters traitement
            if(cPictogram.getPicture1() != null) {
                cPictogram.setPicture1(SQLProtection.format(cPictogram.getPicture1()));
            }
            if(cPictogram.getPicture2() != null) {
                cPictogram.setPicture2(SQLProtection.format(cPictogram.getPicture2()));
            }
            if(cPictogram.getTitle() != null) {
                cPictogram.setTitle(SQLProtection.format(cPictogram.getTitle()));
            }
            crud.createPictogram(cPictogram);

            cPictogram = new Pictogram();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        startDate = new Date();
        endDate = new Date();

        return "created";
    }

    /**
     * Cancel modifications
     * @return a string indicating modification/creation is canceled
     */
    public String cancel() {
        startDate = new Date();
        endDate = new Date();
        return "cancel";
    }


    /**
     * Delete the pictograms corresponding with the identifier
     */
    public void delete() {
        try {

            this.crudService = new PictogramCRUDService();
            PictogramCRUD crud = crudService.getPictogramCRUDPort();

            Pictogram PictogramToDelete = crud.readPictogram(id);
            crud.deletePictogram(PictogramToDelete);

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
            this.crudService = new PictogramCRUDService();
            PictogramCRUD crud = crudService.getPictogramCRUDPort();

            uPictogram = crud.readPictogram(id);

            startDate = uPictogram.getStart().toGregorianCalendar().getTime();
            endDate = uPictogram.getEnd().toGregorianCalendar().getTime();
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "update";
    }

    /**
     * Update the uPictogram internal news
     * @return a string indicating the internal news is updated
     */
    public String update() {
        try {
            this.crudService = new PictogramCRUDService();
           PictogramCRUD crud = crudService.getPictogramCRUDPort();

            uPictogram.setStart(DateFormat.toXmlCalendar(startDate));
            uPictogram.setEnd(DateFormat.toXmlCalendar(endDate));

            // Escape characters traitement
            if(uPictogram.getPicture1() != null) {
                uPictogram.setPicture1(SQLProtection.format(uPictogram.getPicture1()));
            }
            if(uPictogram.getPicture2() != null) {
                uPictogram.setPicture2(SQLProtection.format(uPictogram.getPicture2()));
            }
            if(cPictogram.getTitle() != null) {
                uPictogram.setTitle(SQLProtection.format(uPictogram.getTitle()));
            }
            crud.updatePictogram(uPictogram);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        startDate = new Date();
        endDate = new Date();

        return "updated";
    }

    /**
     * Set the sort method
     * @return a string indicating the breaking news are ready to be sorted
     */
    public String sortBy() {
        return "sorted";
    }
    
    /**
     * Upload the file on the server
     * @return a string indicating the file is uploaded
     */
    public String upload() {
        this.fileUploaderService = new FileUploaderService();
        FileUploader fileUploaderPort = fileUploaderService.getFileUploaderPort();

        try {
            fileUploaderPort.uploadNewFile(file.getName(), file.getBytes(),FOLDER);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "uploaded";
    }

    public Pictogram getCPictogram() {
        return cPictogram;
    }

    public void setCPictogram(Pictogram cPictogram) {
        this.cPictogram = cPictogram;
    }

    public int getPictogramsCard() {
        return pictogramsCard;
    }

    public void setPictogramsCard(int pictogramsCard) {
        this.pictogramsCard = pictogramsCard;
    }

    public Pictogram getUPictogram() {
        return uPictogram;
    }

    public void setUPictogram(Pictogram uPictogram) {
        this.uPictogram = uPictogram;
    }

    /**
     * Delete the file on the server
     * @return a string indicating the file is deleted
     */
    public String deleteFile() {
        this.fileUploaderService = new FileUploaderService();
        FileUploader fileUploaderPort = fileUploaderService.getFileUploaderPort();

        try {
            // Upload the file
            fileUploaderPort.deleteFile(FOLDER+fileToDelete);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "file deleted";
    }
}
