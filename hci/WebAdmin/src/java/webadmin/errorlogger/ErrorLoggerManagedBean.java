package webadmin.errorlogger;

import fr.unice.i3s.modalis.jseduite.technical.logger.error.ErrorLog;
import fr.unice.i3s.modalis.jseduite.technical.logger.error.ErrorLogger;
import fr.unice.i3s.modalis.jseduite.technical.logger.error.ErrorLoggerService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.xml.ws.WebServiceRef;
import webadmin.errorlogger.comparators.ErrorLoggerStampComparatorDesc;
import webadmin.util.DateFormat;

/**
 *
 * @author Steve Colombié
 */
public class ErrorLoggerManagedBean {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/ErrorLogger/ErrorLoggerService?wsdl")
    ErrorLoggerService service;

    //The list of the errors
    private List<ErrorLog> errors;

    // The list cardinality
    private int errorsCard;

    // The start date
    Date start = new Date(1);

    // The end date
    Date end = new Date();


    /**
     * Constructor
     */
    public ErrorLoggerManagedBean () {

    }

    /**
     * Get the errors cardinality
     * @return the errors cardinality
     */
    public int getErrorsCard() {
        return errorsCard;
    }

    /**
     * Get the errors
     * @return a list of the errors
     */
    public List<ErrorLog> getErrors() {
        end = new Date();
        errors = new ArrayList<ErrorLog>();

        try {
            //Get the errors ids
            this.service = new ErrorLoggerService();
            ErrorLogger port = service.getErrorLoggerPort();

            errors = port.getLogs(DateFormat.toSql(start), DateFormat.toSql(end));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Sort the errors
        Collections.sort(errors, new ErrorLoggerStampComparatorDesc());

        errorsCard = errors.size();

        return errors;
    }
}

