package webadmin.absences.comparators;

import fr.unice.i3s.modalis.jseduite.technical.news.absence.Absence;
import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class AbsenceTeacherComparator implements Comparator<Absence> {

    public int compare(Absence o1, Absence o2) {
        return o1.getTeacher().toUpperCase().compareTo(o2.getTeacher().toUpperCase());
    }

}
