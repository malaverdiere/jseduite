package webadmin.picturealbum.comparators;

import fr.unice.i3s.modalis.jseduite.technical.image.registry.PictureAlbum;
import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class PictureAlbumNameComparatorDesc  implements Comparator<PictureAlbum>{

    public int compare(PictureAlbum o1, PictureAlbum o2) {
        return o2.getName().toUpperCase().compareTo(o1.getName().toUpperCase());
    }

}
