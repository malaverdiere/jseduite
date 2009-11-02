package webadmin.util;

/**
 *
 * @author Steve Colombié
 */
public class DataTablePager {
    // The number of item to display in a page
    private int itemByPage = 20;

    // The current page
    private int currentPage = 1;

    // The first item to display number
    private int firstItem = 0;

    // List cardinality
    private int listCard = 0;

    /**
     * Get the number of item to display in a page
     * @return the number of item
     */
    public int getItemByPage () {
        return itemByPage;
    }

    /**
     * Set the number of item to display in a page
     * @param i the number of item
     */
    public void setItemByPage(int i) {
        this.itemByPage = i;
    }

    /**
     * Get the current page
     * @return the current page
     */
    public int getCurrentPage () {
        return currentPage;
    }

    /**
     * Set the current page
     * @param i the current page
     */
    public void setCurrentPage(int i) {
        this.currentPage = i;
        this.firstItem = (currentPage-1)*itemByPage;
    }

    /**
     * Get the first item to display number
     * @return the first item number
     */
    public int getFirstItem() {
        return firstItem;
    }

    /**
     * Set the first item to display number
     * @param f the first item to display number
     */
    public void setFirstItem(int f) {
        this.firstItem = f;
    }

    /**
     * Get the list cardinality
     * @return the list cardinality
     */
    public int getListCard() {
        return listCard;
    }

    /**
     * Set the list cardinality
     * @param card the list cardinality
     */
    public void setListCard(int card) {
        this.listCard = card;
    }

    /**
     * Go to the previous page
     */
    public void prevPage() {
        if (currentPage > 1) {
            setCurrentPage(currentPage-1);
        }
    }

    /**
     * Go to the next page
     */
    public void nextPage() {
        if(listCard > (currentPage*itemByPage)) {
            setCurrentPage(currentPage+1);
        }
    }

    /**
     * Go to the first page
     */
    public void firstPage() {
        setCurrentPage(1);
    }

    /**
     * Go to the last page
     */
    public void lastPage() {
        setCurrentPage((listCard-1)/itemByPage+1);
    }
}
