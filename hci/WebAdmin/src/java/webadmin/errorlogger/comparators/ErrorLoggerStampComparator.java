package webadmin.errorlogger.comparators;

import fr.unice.i3s.modalis.jseduite.technical.logger.error.ErrorLog;

import java.util.Comparator;

/**
 *
 * @author Steve Colombié
 */
public class ErrorLoggerStampComparator implements Comparator<ErrorLog> {

    public int compare(ErrorLog o1, ErrorLog o2) {
        return o1.getStamp().compare(o2.getStamp());
    }

}
