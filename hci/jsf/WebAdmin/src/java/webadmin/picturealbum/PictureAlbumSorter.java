package webadmin.picturealbum;

/**
 *
 * @author Steve Colombié
 */
public class PictureAlbumSorter {
    public static final int sortByRepository = 0;
    public static final int sortByName = 1;
    public static final int sortByStart = 2;
    public static final int sortByRepositoryDesc = 3;
    public static final int sortByNameDesc = 4;
    public static final int sortByStartDesc = 5;

    public int getSortByRepository () { return sortByRepository; }
    public int getSortByName () { return sortByName; }
    public int getSortByStart () { return sortByStart; }
    public int getSortByRepositoryDesc () { return sortByRepositoryDesc; }
    public int getSortByNameDesc () { return sortByNameDesc; }
    public int getSortByStartDesc () { return sortByStartDesc; }
}
