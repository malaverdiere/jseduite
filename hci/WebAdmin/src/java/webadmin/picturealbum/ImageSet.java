package webadmin.picturealbum;

/**
 *
 * @author Steve Colombié
 */
public class ImageSet {
    private String[] image;

    public ImageSet() {
        image = new String[5];
    }

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }
}
