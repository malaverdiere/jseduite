package webadmin.absences;


import fr.unice.i3s.modalis.jseduite.technical.news.absence.Absence;
import fr.unice.i3s.modalis.jseduite.technical.news.absence.AbsenceCRUD;
import fr.unice.i3s.modalis.jseduite.technical.news.absence.AbsenceCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.news.absence.AbsenceFinder;
import fr.unice.i3s.modalis.jseduite.technical.news.absence.AbsenceFinderService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.xml.ws.WebServiceRef;
import webadmin.absences.comparators.*;
import webadmin.util.DateFormat;
import webadmin.util.SQLProtection;

/**
 *
 * @author Steve Colombié
 */

public class AbsenceManagedBean {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/AbsenceFinderService?wsdl")
    AbsenceFinderService finderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/SchoolLife/AbsenceCRUDService?wsdl")
    AbsenceCRUDService crudService;

    //The list of the absence
    private ArrayList<Absence> absences;

    // The list cardinality
    private int absencesCard;

    //The transient absence
    private Absence cAbsence = new Absence();
    private Absence uAbsence = new Absence();

    //The "from" date
    private Date from = new Date();
    private Date fromTime = new Date(0, 0, 0, 8, 0);

    //The "until" date
    private Date until = new Date();
    private Date untilTime = new Date(0, 0, 0, 19, 0);

    //The current ID
    private int id = 0;

    //The sorting method
    private int sort= AbsenceSorter.sortByFromDesc;

    /**
     * Constructor
     */
    public AbsenceManagedBean () {

    }

    /**
     * Get the absence cardinality
     * @return the absence cardinality
     */
    public int getAbsencesCard() {
        return absencesCard;
    }

    /**
     * Get the created absence
     * @return the created absence
     */
    public Absence getcAbsence() {
        return cAbsence;
    }

    /**
     * Set the created absence
     * @param a the absence to create
     */
    public void setcAbsence(Absence a) {
        this.cAbsence = a;
    }

    /**
     * Get the absence to update
     * @return the absence to update
     */
    public Absence getuAbsence() {
        return uAbsence;
    }

    /**
     * Set the absence to update
     * @param a the absence to update
     */
    public void setuAbsence(Absence a) {
        this.uAbsence = a;
    }


    /**
     * Get the "from" date
     * @return the "from" date
     */
    public Date getFrom() {
        return from;
    }
    public Date getFromTime() {
        return fromTime;
    }

    /**
     * Set the "from" date
     * @param from  "from" date
     */
    public void setFrom(Date from) {
        this.from = from;
    }
    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    /**
     * Get the "until" date
     * @return the "until" date
     */
    public Date getUntil() {
        return until;
    }
    public Date getUntilTime() {
        return untilTime;
    }

    /**
     * Set the "until" date
     * @param until the "until" date
     */
    public void setUntil(Date until) {
        this.until = until;
    }
    public void setUntilTime(Date untilTime) {
        this.untilTime = untilTime;
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
     * @param s the sort method
     */
    public void setSort(int s) {
        this.sort = s;
    }

    /**
     * Get the absence
     * @return a list of the absences
     */
    public ArrayList<Absence> getAbsences() {
        absences = new ArrayList<Absence>();

        try {
            //Get the absence ids
            this.finderService = new AbsenceFinderService();
            AbsenceFinder finderPort = finderService.getAbsenceFinderPort();
            List<Integer> absencesIds = finderPort.getAllAbsenceReferences();

            //Get the absence
            this.crudService = new AbsenceCRUDService();
            AbsenceCRUD crudPort = crudService.getAbsenceCRUDPort();

            for(int i=0; i<absencesIds.size(); i++) {
                absences.add(crudPort.readAbsence(absencesIds.get(i)));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Sorting the values
        switch(sort) {
            case AbsenceSorter.sortByFrom:
                Collections.sort(absences, new AbsenceFromComparator());
                break;

            case AbsenceSorter.sortByTeacher:
                Collections.sort(absences, new AbsenceTeacherComparator());
                break;

            case AbsenceSorter.sortByUntil:
                Collections.sort(absences, new AbsenceUntilComparator());
                break;

            case AbsenceSorter.sortByTeacherDesc:
                Collections.sort(absences, new AbsenceTeacherComparatorDesc());
                break;
                
            case AbsenceSorter.sortByUntilDesc:
                Collections.sort(absences, new AbsenceUntilComparatorDesc());
                break;

            case AbsenceSorter.sortByFromDesc:
            default:
                Collections.sort(absences, new AbsenceFromComparatorDesc());
                break;
        }

        absencesCard = absences.size();

        return absences;
    }


    /**
     * Create an absence
     * @return a string indicating the absence is created
     */
    public String create() {
        try {

            this.crudService = new AbsenceCRUDService();
            AbsenceCRUD crud = crudService.getAbsenceCRUDPort();

            from.setHours(fromTime.getHours());
            from.setMinutes(fromTime.getMinutes());

            until.setHours(untilTime.getHours());
            until.setMinutes(untilTime.getMinutes());

            cAbsence.setFrom(DateFormat.toXmlCalendar(from));
            cAbsence.setUntil(DateFormat.toXmlCalendar(until));

            // Escape characters traitement
            cAbsence.setTeacher(SQLProtection.format(cAbsence.getTeacher()));
            cAbsence.setReason(SQLProtection.format(cAbsence.getReason()));

            crud.createAbsence(cAbsence);

            cAbsence = new Absence();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        from = new Date();
        until = new Date();
        fromTime = new Date(0, 0, 0, 8, 0);
        untilTime = new Date(0, 0, 0, 19, 0);

        return "created";
    }

    /**
     * Cancel modifications
     * @return a string indicating modification/creation is canceled
     */
    public String cancel() {
        from = new Date();
        until = new Date();
        fromTime = new Date(0, 0, 0, 8, 0);
        untilTime = new Date(0, 0, 0, 19, 0);

        return "cancel";
    }


    /**
     * Delete the absence corresponding with the identifier
     */
    public void delete() {
        try {

            this.crudService = new AbsenceCRUDService();
            AbsenceCRUD crud = crudService.getAbsenceCRUDPort();

            Absence breakNewToDelete = crud.readAbsence(id);
            crud.deleteAbsence(breakNewToDelete);

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
            this.crudService = new AbsenceCRUDService();
            AbsenceCRUD crud = crudService.getAbsenceCRUDPort();

            uAbsence = crud.readAbsence(id);

            from = uAbsence.getFrom().toGregorianCalendar().getTime();
            until = uAbsence.getUntil().toGregorianCalendar().getTime();

            fromTime.setHours(from.getHours());
            fromTime.setMinutes(fromTime.getMinutes());
            untilTime.setHours(until.getHours());
            untilTime.setMinutes(until.getMinutes());

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "update";
    }

    /**
     * Update the uAbsence absence
     * @return a string indicating the absence is updated
     */
    public String update() {
        try {
            this.crudService = new AbsenceCRUDService();
            AbsenceCRUD crud = crudService.getAbsenceCRUDPort();

            from.setHours(fromTime.getHours());
            from.setMinutes(fromTime.getMinutes());

            until.setHours(untilTime.getHours());
            until.setMinutes(untilTime.getMinutes());

            uAbsence.setFrom(DateFormat.toXmlCalendar(from));
            uAbsence.setUntil(DateFormat.toXmlCalendar(until));

            // Escape characters traitement
            uAbsence.setTeacher(SQLProtection.format(uAbsence.getTeacher()));
            uAbsence.setReason(SQLProtection.format(uAbsence.getReason()));

            crud.updateAbsence(uAbsence);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        from = new Date();
        until = new Date();
        fromTime = new Date(0, 0, 0, 8, 0);
        untilTime = new Date(0, 0, 0, 19, 0);

        return "updated";
    }

    /**
     * Set the sort method
     * @return a string indicating the absence are ready to be sorted
     */
    public String sortBy() {
        return "sorted";
    }
}
