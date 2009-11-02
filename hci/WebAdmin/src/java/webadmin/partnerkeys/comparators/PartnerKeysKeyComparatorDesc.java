package webadmin.partnerkeys.comparators;

import fr.unice.i3s.modalis.jseduite.technical.registry.partners.PartnerKey;
import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class PartnerKeysKeyComparatorDesc implements Comparator<PartnerKey> {

    public int compare(PartnerKey o1, PartnerKey o2) {
        return o2.getKey().toUpperCase().compareTo(o1.getKey().toUpperCase());
    }

}
