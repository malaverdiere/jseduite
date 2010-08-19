package webadmin.pictogram;

/**
 *
 * @author Christophe Desclaux
 */
public class PictogramSorter {
    public static final int sortByStart = 1;
    public static final int sortByEnd = 2;
    public static final int sortByTitle = 3;
    public static final int sortByStartDesc = 4;
    public static final int sortByEndDesc = 5;
    public static final int sortByTitleDesc = 6;

    public int getSortByStart () { return sortByStart; }
    public int getSortByEnd () { return sortByEnd; }
    public int getSortByTitle () { return sortByTitle; }
    public int getSortByStartDesc () { return sortByStartDesc; }
    public int getSortByEndDesc () { return sortByEndDesc; }
    public int getSortByTitleDesc () { return sortByTitleDesc; }
}
