package webadmin.pictogram.comparators;

import fr.unice.i3s.modalis.jseduite.technical.pictogram.Pictogram;
import java.util.Comparator;

/**
 *
 * @author Christophe Desclaux
 */
public class PictogramEndComparatorDesc implements Comparator<Pictogram> {

    public int compare(Pictogram o1, Pictogram o2) {
        return o2.getEnd().compare(o1.getEnd());
    }

}
