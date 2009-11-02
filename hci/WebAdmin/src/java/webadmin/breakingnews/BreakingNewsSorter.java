package webadmin.breakingnews;

/**
 *
 * @author Steve Colombié
 */
public class BreakingNewsSorter {
    public static final int sortByDate = 0;
    public static final int sortByAuthor = 1;
    public static final int sortByDateDesc = 2;
    public static final int sortByAuthorDesc = 3;

    public int getSortByDate () { return sortByDate; }
    public int getSortByAuthor () { return sortByAuthor; }
    public int getSortByDateDesc () { return sortByDateDesc; }
    public int getSortByAuthorDesc () { return sortByAuthorDesc; }
}
