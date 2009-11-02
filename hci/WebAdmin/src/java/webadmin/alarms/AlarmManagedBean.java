package webadmin.alarms;

import fr.unice.i3s.modalis.jseduite.technical.alarms.Alarm;
import fr.unice.i3s.modalis.jseduite.technical.alarms.AlarmCRUD;
import fr.unice.i3s.modalis.jseduite.technical.alarms.AlarmCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.alarms.AlarmFinder;
import fr.unice.i3s.modalis.jseduite.technical.alarms.AlarmFinderService;
import fr.unice.i3s.modalis.jseduite.technical.alarms.BreakTime;
import fr.unice.i3s.modalis.jseduite.technical.breaktime.BreakTimeCRUD;
import fr.unice.i3s.modalis.jseduite.technical.breaktime.BreakTimeCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.breaktime.BreakTimeFinder;
import fr.unice.i3s.modalis.jseduite.technical.breaktime.BreakTimeFinderService;
import fr.unice.i3s.modalis.jseduite.upload.files.FileUploader;
import fr.unice.i3s.modalis.jseduite.upload.files.FileUploaderService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.xml.ws.WebServiceRef;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import webadmin.alarms.comparators.AlarmBuildingComparator;
import webadmin.alarms.comparators.AlarmStartComparator;
import webadmin.util.Bundle;
import webadmin.util.SQLProtection;


/**
 *
 * @author Steve Colombié
 */

public class AlarmManagedBean {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/BreakTimeFinderService?wsdl")
    BreakTimeFinderService breakTimeFinderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/BreakTimeCRUDService?wsdl")
    BreakTimeCRUDService breakTimeCrudService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/AlarmFinderService?wsdl")
    AlarmFinderService alarmFinderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/AlarmCRUDService?wsdl")
    AlarmCRUDService alarmCrudService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/FileUploader/FileUploaderService?wsdl")
    FileUploaderService fileUploaderService;

    //The list of the alarms
    private ArrayList<Alarms> alarms;

    // The list cardinality
    private int alarmsCard;

    //The transient alarms
    private Alarms uAlarm = new Alarms();

    //The current ID
    private int id = 0;

    // Booleans indicating if alarms are activated
    private boolean start = false;
    private boolean almostEnd = false;
    private boolean end = false;

    // The uploaded file
    private UploadedFile file;

    // List of the files
    private List<SelectItem> files;
    private List<SelectItem> files2;

    // The file to delete
    private String fileToDelete;

    /**
     * Constructor
     */
    public AlarmManagedBean () {}

    /**
     * Get the alarms cardinality
     * @return the alarms cardinality
     */
    public int getAlarmsCard() {
        return alarmsCard;
    }

    /**
     * Get the alarm to update
     * @return the alarm to update
     */
    public Alarms getuAlarm() {
        return uAlarm;
    }

    /**
     * Set the alarm to update
     * @param a the alarm to update
     */
    public void setuAlarm(Alarms a) {
        this.uAlarm = a;
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
     * Get the start alarm status
     * @return the start alarm status
     */
    public boolean getStart() {
        return start;
    }


    /**
     * Set the start alarm status
     * @param start the status
     */
    public void setStart (boolean start) {
        this.start = start;
    }

    /**
     * Get the almost end alarm status
     * @return the almost end alarm status
     */
    public boolean getAlmostEnd() {
        return almostEnd;
    }


    /**
     * Set the almost end alarm status
     * @param aEnd the status
     */
    public void setAlmostEnd (boolean aEnd) {
        this.almostEnd = aEnd;
    }

    /**
     * Get the end alarm status
     * @return the end alarm status
     */
    public boolean getEnd() {
        return end;
    }


    /**
     * Set the end alarm status
     * @param end the status
     */
    public void setEnd (boolean end) {
        this.end = end;
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
            for (String name : fileUploaderPort.getAllFiles()) {
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
            for (String name : fileUploaderPort.getAllFiles()) {
                SelectItem item = new SelectItem(fileUploaderPort.getURL(name), name);
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
     * Get the alarms
     * @return a list of the alarms
     */
    public ArrayList<Alarms> getAlarms() {
        alarms = new ArrayList<Alarms>();

        try {
            this.alarmFinderService = new AlarmFinderService();
            AlarmFinder alarmPort = alarmFinderService.getAlarmFinderPort();

            //Get the break times ids
            this.breakTimeFinderService = new BreakTimeFinderService();
            BreakTimeFinder finderPort = breakTimeFinderService.getBreakTimeFinderPort();
            List<Integer> breakTimeIds = finderPort.getAllBreakTimeReferences();

            //Get the break times and alarms
            this.breakTimeCrudService = new BreakTimeCRUDService();
            BreakTimeCRUD crudPort = breakTimeCrudService.getBreakTimeCRUDPort();

            List<Alarm> referencedAlarms;
            Alarms currentAlarms;
            for(int i=0; i<breakTimeIds.size(); i++) {
                referencedAlarms = alarmPort.getAlarmsByBreakTimeId(breakTimeIds.get(i));

                currentAlarms = new Alarms();
                currentAlarms.setBreakTime(crudPort.readBreakTime(breakTimeIds.get(i)));
                for (Alarm alarm : referencedAlarms) {
                    currentAlarms.addAlarm(alarm);
                }

                alarms.add(currentAlarms);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Sort break times by date then by building
        Collections.sort(alarms, new AlarmStartComparator());
        Collections.sort(alarms, new AlarmBuildingComparator());


        alarmsCard = alarms.size();

        return alarms;
    }


    /**
     * Cancel modifications
     * @return a string indicating modification/creation is canceled
     */
    public String cancel() {
        start = false;
        almostEnd = false;
        end = false;
        uAlarm = new Alarms();

        return "cancel";
    }


    /**
     * Delete the alarms corresponding with the identifier
     */
    public void delete() {
        try {
            this.alarmFinderService = new AlarmFinderService();
            AlarmFinder finderPort = alarmFinderService.getAlarmFinderPort();
            List<Alarm> alarmsToDelete = finderPort.getAlarmsByBreakTimeId(id);

            this.alarmCrudService = new AlarmCRUDService();
            AlarmCRUD crudPort = alarmCrudService.getAlarmCRUDPort();

            for(Alarm alarm : alarmsToDelete) {
                crudPort.deleteAlarm(alarm);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        uAlarm = new Alarms();
    }

    /**
     * Initiate the update process
     * @return a string indicating the update is ready to be done
     */
    public String goUpdate()
    {
        try {
            this.alarmFinderService = new AlarmFinderService();
            AlarmFinder finderPort = alarmFinderService.getAlarmFinderPort();
            List<Alarm> alarmsToUpdate = finderPort.getAlarmsByBreakTimeId(id);

            for(Alarm alarm : alarmsToUpdate) {
                uAlarm.addAlarm(alarm);
            }

            this.breakTimeCrudService = new BreakTimeCRUDService();
            BreakTimeCRUD crud = breakTimeCrudService.getBreakTimeCRUDPort();

            uAlarm.setBreakTime(crud.readBreakTime(id));

            if(uAlarm.getAlarmStart().getBreakTime()!=null) {
                start = true;
            }
            if(uAlarm.getAlarmAlmostEnd().getBreakTime()!=null) {
                almostEnd = true;
            }
            if(uAlarm.getAlarmEnd().getBreakTime()!=null) {
                end = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "update";
    }

    /**
     * Update the uAlarms alarm
     * @return a string indicating the alarm is updated
     */
    public String update() {
        try {
            this.alarmFinderService = new AlarmFinderService();
            AlarmFinder finderPort = alarmFinderService.getAlarmFinderPort();
            List<Integer> existingAlarms = finderPort.getAllAlarmIds();

            this.alarmCrudService = new AlarmCRUDService();
            AlarmCRUD crudPort = alarmCrudService.getAlarmCRUDPort();

            // Start alarm
            if(start) {
                // Escape characters traitement
                uAlarm.getAlarmStart().setMessage(SQLProtection.format(uAlarm.getAlarmStart().getMessage()));
                if(uAlarm.getAlarmStart().getSound() != null) {
                    uAlarm.getAlarmStart().setSound(SQLProtection.format(uAlarm.getAlarmStart().getSound()));
                }

                if(existingAlarms.contains(uAlarm.getAlarmStart().getId())) {
                    crudPort.updateAlarm(uAlarm.getAlarmStart());
                }
                else {
                    uAlarm.getAlarmStart().setKind("start");
                    uAlarm.getAlarmStart().setBreakTime(new BreakTime());       //VERY UGLY!!!!
                    uAlarm.getAlarmStart().getBreakTime().setId(id);            //NEED IMPROVMENT
                    crudPort.createAlarm(uAlarm.getAlarmStart());
                }
            }
            else {
                if(uAlarm.getAlarmStart().getBreakTime() != null) {
                    crudPort.deleteAlarm(uAlarm.getAlarmStart());
                }
            }

            // Almost end alarm
            if(almostEnd) {
                // Escape characters traitement
                uAlarm.getAlarmAlmostEnd().setMessage(SQLProtection.format(uAlarm.getAlarmAlmostEnd().getMessage()));
                if(uAlarm.getAlarmAlmostEnd().getSound() != null) {
                    uAlarm.getAlarmAlmostEnd().setSound(SQLProtection.format(uAlarm.getAlarmAlmostEnd().getSound()));
                }

                if(existingAlarms.contains(uAlarm.getAlarmAlmostEnd().getId())) {
                    crudPort.updateAlarm(uAlarm.getAlarmAlmostEnd());
                }
                else {
                    uAlarm.getAlarmAlmostEnd().setKind("almost_end");
                    uAlarm.getAlarmAlmostEnd().setBreakTime(new BreakTime());   //VERY UGLY!!!!
                    uAlarm.getAlarmAlmostEnd().getBreakTime().setId(id);        //NEED IMPROVMENT
                    crudPort.createAlarm(uAlarm.getAlarmAlmostEnd());
                }
            }
            else {
                if(uAlarm.getAlarmAlmostEnd().getBreakTime() != null) {
                    crudPort.deleteAlarm(uAlarm.getAlarmAlmostEnd());
                }
            }

            // End alarm
            if(end) {
                // Escape characters traitement
                uAlarm.getAlarmEnd().setMessage(SQLProtection.format(uAlarm.getAlarmEnd().getMessage()));
                if(uAlarm.getAlarmEnd().getSound() != null) {
                    uAlarm.getAlarmEnd().setSound(SQLProtection.format(uAlarm.getAlarmEnd().getSound()));
                }

                if(existingAlarms.contains(uAlarm.getAlarmEnd().getId())) {
                    crudPort.updateAlarm(uAlarm.getAlarmEnd());
                }
                else {
                    uAlarm.getAlarmEnd().setKind("end");
                    uAlarm.getAlarmEnd().setBreakTime(new BreakTime());         //VERY UGLY!!!!
                    uAlarm.getAlarmEnd().getBreakTime().setId(id);              //NEED IMPROVMENT
                    crudPort.createAlarm(uAlarm.getAlarmEnd());
                }
            }
            else {
                if(uAlarm.getAlarmEnd().getBreakTime() != null) {
                    crudPort.deleteAlarm(uAlarm.getAlarmEnd());
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        start = false;
        almostEnd = false;
        end = false;
        uAlarm = new Alarms();

        return "updated";
    }

    /**
     * Set the sort method
     * @return a string indicating the alarms are ready to be sorted
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
            // Upload the file
            fileUploaderPort.uploadNewFile(file.getName(), file.getBytes());
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
            fileUploaderPort.deleteFile(fileToDelete);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "file deleted";
    }
}
