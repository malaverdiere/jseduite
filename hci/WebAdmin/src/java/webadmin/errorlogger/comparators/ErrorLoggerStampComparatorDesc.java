package webadmin.errorlogger.comparators;

import fr.unice.i3s.modalis.jseduite.technical.logger.error.ErrorLog;
import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class ErrorLoggerStampComparatorDesc implements Comparator<ErrorLog> {

    public int compare(ErrorLog o1, ErrorLog o2) {
        return o2.getStamp().compare(o1.getStamp());
    }

}
