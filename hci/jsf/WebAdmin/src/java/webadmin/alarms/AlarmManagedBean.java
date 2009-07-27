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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.ws.WebServiceRef;
import webadmin.alarms.comparators.AlarmBuildingComparator;
import webadmin.alarms.comparators.AlarmStartComparator;


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
            List<Alarm> existingAlarms = finderPort.getAlarmsByBreakTimeId(id);

            this.alarmCrudService = new AlarmCRUDService();
            AlarmCRUD crudPort = alarmCrudService.getAlarmCRUDPort();

            // Start alarm
            if(start) {
                if(existingAlarms.contains(uAlarm.getAlarmStart())) {
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
                if(existingAlarms.contains(uAlarm.getAlarmAlmostEnd())) {
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
                if(existingAlarms.contains(uAlarm.getAlarmEnd())) {
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
}
