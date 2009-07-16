package webadmin.picturealbum.comparators;

import fr.unice.i3s.modalis.jseduite.technical.image.registry.PictureAlbum;
import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class PictureAlbumRepositoryComparatorDesc  implements Comparator<PictureAlbum>{

    public int compare(PictureAlbum o1, PictureAlbum o2) {
        return o2.getRepository().compareTo(o1.getRepository());
    }

}