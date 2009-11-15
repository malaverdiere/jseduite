package webadmin.courses;

/**
 *
 * @author Steve Colombié
 */
public class CourseSorter {
    public static final int sortByName = 0;
    public static final int sortByKind = 1;
    public static final int sortByNameDesc = 2;
    public static final int sortByKindDesc = 3;

    public int getSortByName () { return sortByName; }
    public int getSortByKind () { return sortByKind; }
    public int getSortByNameDesc () { return sortByNameDesc; }
    public int getSortByKindDesc () { return sortByKindDesc; }
}
