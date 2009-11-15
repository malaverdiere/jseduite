package webadmin.util;

/**
 *
 * @author Steve Colombié
 */
public abstract class Thumbnail {

    public static String picasa(String image) {
        int pos = image.lastIndexOf("/", image.length());

        return image.substring(0, pos)+"/s72/"+image.substring(pos+1);
    }

    public static String flickr(String image) {
        int pos = image.lastIndexOf(".", image.length());

        return image.substring(0, pos)+"_s."+image.substring(pos+1);
    }
}
