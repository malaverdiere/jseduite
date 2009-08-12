package webadmin.partnerkeys.comparators;

import fr.unice.i3s.modalis.jseduite.technical.registry.partners.PartnerKey;
import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class PartnerKeysKeyComparator implements Comparator<PartnerKey> {

    public int compare(PartnerKey o1, PartnerKey o2) {
        return o1.getKey().compareTo(o2.getKey());
    }

}
