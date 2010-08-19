package webadmin.breakscreen;

import fr.unice.i3s.modalis.jseduite.technical.breaks.BreakScreen;
import fr.unice.i3s.modalis.jseduite.technical.breaks.BreakScreenCRUD;
import fr.unice.i3s.modalis.jseduite.technical.breaks.BreakScreenCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.breaks.BreakScreenFinder;
import fr.unice.i3s.modalis.jseduite.technical.breaks.BreakScreenFinderService;
import fr.unice.i3s.modalis.jseduite.upload.files.FileUploader;
import fr.unice.i3s.modalis.jseduite.upload.files.FileUploaderService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.xml.ws.WebServiceRef;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import webadmin.breakscreen.comparators.*;
import webadmin.util.Bundle;
import webadmin.util.DateFormat;
import webadmin.util.SQLProtection;


/**
 *
 * @author Christophe Desclaux
 */

public class BreakScreenManagedBean {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/breakScreen/BreakScreenFinderService?wsdl")
    BreakScreenFinderService finderService;
    
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/breakScreen/BreakScreenCRUDService?wsdl")
    BreakScreenCRUDService crudService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/FileUploader/FileUploaderService?wsdl")
    FileUploaderService fileUploaderService;
    
    //The list of the break screens
    private ArrayList<BreakScreen> breakScreens;

    // The list cardinality
    private int breakScreenCard;

    //The transient break screen
    private BreakScreen cBreakScreen = new BreakScreen();
    private BreakScreen uBreakScreen = new BreakScreen();

    //The start date
    private Date startDate = new Date(0, 0, 0, 8, 0);

    //The end date
    private Date endDate = new Date(0, 0, 0, 18, 0);

    //The current ID
    private int id = 0;

    // Days
    private List<SelectItem> days;

    // Selected days
    private String[] selectedDays;

    // The buildings
    private List<SelectItem> buildings;

    // Alternative building
    private String alterBuilding;

    // The kinds
    private String content;

    //The sound
    private String sound;
    
    // The uploaded file
    private UploadedFile file;

    //the folder where are sounds
    static final String FOLDER = "sound/";

    // List of the files
    private List<SelectItem> files;
    private List<SelectItem> files2;

    // The file to delete
    private String fileToDelete;

    /**
     * Constructor
     */
    public BreakScreenManagedBean () {

    }

    /**
     * Get the break screens cardinality
     * @return the break screens cardinality
     */
    public int getBreakScreenCard() {
        return breakScreenCard;
    }

    /**
     * Get the created break screen
     * @return the created break screen
     */
    public BreakScreen getcBreakScreen() {
        return cBreakScreen;
    }

    /**
     * Set the created break screen
     * @param b the break screen to create
     */
    public void setcBreakScreen(BreakScreen b) {
        this.cBreakScreen = b;
    }

    /**
     * Get the break screen to update
     * @return the break screen to update
     */
    public BreakScreen getuBreakScreen() {
        return uBreakScreen;
    }

    /**
     * Set the break screen to update
     * @param b the break screen to update
     */
    public void setuBreakScreen(BreakScreen b) {
        this.uBreakScreen = b;
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
     * Get the alternative building
     * @return the alternative building
     */
    public String getAlterBuilding() {
        return alterBuilding;
    }

    /**
     * Set the alternative building
     * @param alterKind the alternative building
     */
    public void setAlterBuilding(String alterBuilding) {
        this.alterBuilding = alterBuilding;
    }

    /**
     * Get the break screens
     * @return a list of the break screens
     */
    public ArrayList<BreakScreen> getBreakScreens() {
        breakScreens = new ArrayList<BreakScreen>();

        try {
            //Get the break screens ids
            this.finderService = new BreakScreenFinderService();
            BreakScreenFinder finderPort = finderService.getBreakScreenFinderPort();
            List<Integer> breakScreenIds = finderPort.getAllBreakScreenReferences();

            //Get the break screens
            this.crudService = new BreakScreenCRUDService();
            BreakScreenCRUD crudPort = crudService.getBreakScreenCRUDPort();

            for(int i=0; i<breakScreenIds.size(); i++) {
                breakScreens.add(crudPort.readBreakScreen(breakScreenIds.get(i)));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Sort break screens by date then by building
        Collections.sort(breakScreens, new BreakScreenStartComparator());
        Collections.sort(breakScreens, new BreakScreenBuildingComparator());


        breakScreenCard = breakScreens.size();

        return breakScreens;
    }

    /**
     * Get the days
     * @return the list of days
     */
    public List<SelectItem> getDays() {
        days = new ArrayList<SelectItem>();

        SelectItem item = new SelectItem("monday", Bundle.get("FORM_MONDAY"));
        days.add(item);
        item = new SelectItem("tuesday", Bundle.get("FORM_TUESDAY"));
        days.add(item);
        item = new SelectItem("wednesday", Bundle.get("FORM_WEDNESDAY"));
        days.add(item);
        item = new SelectItem("thursday", Bundle.get("FORM_THURSDAY"));
        days.add(item);
        item = new SelectItem("friday", Bundle.get("FORM_FRIDAY"));
        days.add(item);
        item = new SelectItem("saturday", Bundle.get("FORM_SATURDAY"));
        days.add(item);
        item = new SelectItem("sunday", Bundle.get("FORM_SUNDAY"));
        days.add(item);

        return days;
    }

    /**
     * Get the selected days
     * @return the selected days
     */
    public String[] getSelectedDays() {
        return this.selectedDays;
    }

    /**
     * Set the selected days
     * @param d the selected days
     */
    public void setSelectedDays(String[] d) {
        selectedDays = d;
    }

    /**
     * Get the buildings
     * @return the list of buildings
     */
    public List<SelectItem> getBuildings() {
        List<String> buildingsBuf;
        buildings = new ArrayList<SelectItem>();

        try {
            this.finderService = new BreakScreenFinderService();
            BreakScreenFinder port = finderService.getBreakScreenFinderPort();
            buildingsBuf = port.getAllBuildings();

            for (String building : buildingsBuf) {
                SelectItem item = new SelectItem(building, building);
                buildings.add(item);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        buildings.add(new SelectItem("__other", Bundle.get("FORM_OTHER")));

        return buildings;
    }

    /**
     * Get the content
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Set the content
     * @param d the content to set
     */
    public void setContent(String d) {
        this.content = d;
    }

    /**
     * get the sound
     * @return the sound
     */
    public String getSound() {
        return sound;
    }

    /**
     * Get the sound
     * @param sound the sound
     */
    public void setSound(String sound) {
        this.sound = sound;
    }

    /**
     * Create a break screen
     * @return a string indicating the break screen is created
     */
    public String create() {
        try {

            this.crudService = new BreakScreenCRUDService();
            BreakScreenCRUD crud = crudService.getBreakScreenCRUDPort();

            cBreakScreen.setStart(DateFormat.toXmlCalendar(startDate));
            cBreakScreen.setEnd(DateFormat.toXmlCalendar(endDate));

            for(String day : selectedDays) {
                cBreakScreen.getDays().add(day);
            }

            if(cBreakScreen.getBuilding().equals("__other")) {
                cBreakScreen.setBuilding(alterBuilding);
            }

            cBreakScreen.setContent(content);
            cBreakScreen.setSound(sound);
            
            // Escape characters traitement
            cBreakScreen.setBuilding(SQLProtection.format(cBreakScreen.getBuilding()));
            cBreakScreen.setContent(SQLProtection.format(cBreakScreen.getContent()));
            if(cBreakScreen.getSound() != null) {
                cBreakScreen.setSound(SQLProtection.format(cBreakScreen.getSound()));
            }
            crud.createBreakScreen(cBreakScreen);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        cBreakScreen = new BreakScreen();
        selectedDays = null;
        startDate = new Date(0, 0, 0, 8, 0);
        endDate = new Date(0, 0, 0, 18, 0);
        alterBuilding = "";
        content = "";
        sound = "";

        return "created";
    }

    /**
     * Cancel modifications
     * @return a string indicating modification/creation is canceled
     */
    public String cancel() {
        selectedDays = null;
        content = "";
        sound = "";
        startDate = new Date(0, 0, 0, 8, 0);
        endDate = new Date(0, 0, 0, 18, 0);

        return "cancel";
    }


    /**
     * Delete the break screen corresponding with the identifier
     */
    public void delete() {
        try {

            this.crudService = new BreakScreenCRUDService();
            BreakScreenCRUD crud = crudService.getBreakScreenCRUDPort();

            BreakScreen BreakScreenToDelete = crud.readBreakScreen(id);
            crud.deleteBreakScreen(BreakScreenToDelete);

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
            this.crudService = new BreakScreenCRUDService();
            BreakScreenCRUD crud = crudService.getBreakScreenCRUDPort();

            uBreakScreen = crud.readBreakScreen(id);

            startDate = uBreakScreen.getStart().toGregorianCalendar().getTime();
            endDate = uBreakScreen.getEnd().toGregorianCalendar().getTime();

            selectedDays = new String[uBreakScreen.getDays().size()];
            for(int i=0; i< uBreakScreen.getDays().size(); i++) {
                selectedDays[i] = uBreakScreen.getDays().get(i);
            }
            content = uBreakScreen.getContent();
            sound = uBreakScreen.getSound();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "update";
    }

    /**
     * Update the uBreakScreen break screen
     * @return a string indicating the break screen is updated
     */
    public String update() {
        try {
            this.crudService = new BreakScreenCRUDService();
            BreakScreenCRUD crud = crudService.getBreakScreenCRUDPort();

            uBreakScreen.setStart(DateFormat.toXmlCalendar(startDate));
            uBreakScreen.setEnd(DateFormat.toXmlCalendar(endDate));

            uBreakScreen.getDays().clear();
            for(String day : selectedDays) {
                uBreakScreen.getDays().add(day);
            }

            if(uBreakScreen.getBuilding().equals("__other")) {
                uBreakScreen.setBuilding(alterBuilding);
            }

            uBreakScreen.setContent(content);
            uBreakScreen.setSound(sound);
            // Escape characters traitement
            uBreakScreen.setBuilding(SQLProtection.format(uBreakScreen.getBuilding()));
            uBreakScreen.setContent(SQLProtection.format(uBreakScreen.getContent()));
            if(uBreakScreen.getSound() != null) {
                uBreakScreen.setSound(SQLProtection.format(uBreakScreen.getSound()));
            }
            crud.updateBreakScreen(uBreakScreen);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        selectedDays = null;
        startDate = new Date(0, 0, 0, 8, 0);
        endDate = new Date(0, 0, 0, 18, 0);

        return "updated";
    }

    /**
     * Set the sort method
     * @return a string indicating the break screen are ready to be sorted
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
            fileUploaderPort.uploadNewFile(file.getName(), file.getBytes(), FOLDER);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "uploaded";
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
