/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.i3s.modails.jSeduite.technical.helpers.time;

import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author mosser
 */
@WebService()
public class TimeMachine {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "compare")
    public int compare(@WebParam(name = "d1") Date d1,
            @WebParam(name = "d2") Date d2) {
        //TODO write your implementation code here:
        return d1.compareTo(d2);
    }

}
