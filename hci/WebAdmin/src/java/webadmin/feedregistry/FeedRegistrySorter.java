package webadmin.feedregistry;


/**
 *
 * @author Steve Colombié
 */
public class FeedRegistrySorter {
    public static final int sortByNickname = 0;
    public static final int sortByClass = 1;
    public static final int sortByNicknameDesc = 2;
    public static final int sortByClassDesc = 3;


    public int getSortByNickname () { return sortByNickname; }
    public int getSortByClass () { return sortByClass; }
    public int getSortByNicknameDesc () { return sortByNicknameDesc; }
    public int getSortByClassDesc () { return sortByClassDesc; }
}
