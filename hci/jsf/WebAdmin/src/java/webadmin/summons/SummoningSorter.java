package webadmin.summons;

/**
 *
 * @author Steve Colombié
 */
public class SummoningSorter {
    public static final int sortByStudent = 0;
    public static final int sortByPromo = 1;
    public static final int sortByOwner = 2;
    public static final int sortByDate = 3;
    public static final int sortByLevel = 4;

    public static final int sortByStudentDesc = 5;
    public static final int sortByPromoDesc = 6;
    public static final int sortByOwnerDesc = 7;
    public static final int sortByDateDesc = 8;
    public static final int sortByLevelDesc = 9;


    public int getSortByStudent () { return sortByStudent; }
    public int getSortByPromo () { return sortByPromo; }
    public int getSortByOwner () { return sortByOwner; }
    public int getSortByDate () { return sortByDate; }
    public int getSortByLevel () { return sortByLevel; }

    public int getSortByStudentDesc () { return sortByStudentDesc; }
    public int getSortByPromoDesc () { return sortByPromoDesc; }
    public int getSortByOwnerDesc () { return sortByOwnerDesc; }
    public int getSortByDateDesc () { return sortByDateDesc; }
    public int getSortByLevelDesc () { return sortByLevelDesc; }
}
