package webadmin.internalnews;

/**
 *
 * @author Steve Colombié
 */
public class InternalNewsSorter {
    public static final int sortByTarget = 0;
    public static final int sortByAuthor = 1;
    public static final int sortByStart = 2;
    public static final int sortByEnd = 3;
    public static final int sortByTitle = 4;
    public static final int sortByTargetDesc = 5;
    public static final int sortByAuthorDesc = 6;
    public static final int sortByStartDesc = 7;
    public static final int sortByEndDesc = 8;
    public static final int sortByTitleDesc = 9;

    public int getSortByTarget () { return sortByTarget; }
    public int getSortByAuthor () { return sortByAuthor; }
    public int getSortByStart () { return sortByStart; }
    public int getSortByEnd () { return sortByEnd; }
    public int getSortByTitle () { return sortByTitle; }
    public int getSortByTargetDesc () { return sortByTargetDesc; }
    public int getSortByAuthorDesc () { return sortByAuthorDesc; }
    public int getSortByStartDesc () { return sortByStartDesc; }
    public int getSortByEndDesc () { return sortByEndDesc; }
    public int getSortByTitleDesc () { return sortByTitleDesc; }
}
