package webadmin.promotions;

/**
 *
 * @author Steve Colombié
 */
public class PromotionSorter {
    public static final int sortByCode = 0;
    public static final int sortByName = 1;
    public static final int sortByCodeDesc = 2;
    public static final int sortByNameDesc = 3;


    public int getSortByCode () { return sortByCode; }
    public int getSortByName () { return sortByName; }
    public int getSortByCodeDesc () { return sortByCodeDesc; }
    public int getSortByNameDesc () { return sortByNameDesc; }
}
