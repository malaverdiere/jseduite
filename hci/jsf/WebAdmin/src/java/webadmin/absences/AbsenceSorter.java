package webadmin.absences;


/**
 *
 * @author Steve Colombié
 */
public class AbsenceSorter {
    public static final int sortByFrom = 0;
    public static final int sortByTeacher = 1;
    public static final int sortByUntil = 2;
    public static final int sortByFromDesc = 3;
    public static final int sortByTeacherDesc = 4;
    public static final int sortByUntilDesc = 5;

    public int getSortByFrom () { return sortByFrom; }
    public int getSortByTeacher () { return sortByTeacher; }
    public int getSortByUntil () { return sortByUntil; }
    public int getSortByFromDesc () { return sortByFromDesc; }
    public int getSortByTeacherDesc () { return sortByTeacherDesc; }
    public int getSortByUntilDesc () { return sortByUntilDesc; }
}
