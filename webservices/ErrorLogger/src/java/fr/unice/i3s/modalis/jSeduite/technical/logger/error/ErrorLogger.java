/**
 * This file is part of jSeduite::ErrorLogger
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::ErrorLogger is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::ErrorLogger is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::ErrorLogger; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 * @contributor 2008 Clémentine Delerce-Mauris [delercm@polytech.unice.fr]
 * @contributor 2008 Lionel Palacin            [lionel.palacin@gmail.com]
 * @contributor 2008 Stéphane Martarello       [stephane.martarello@gmail.com]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.logger.error;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;

import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Date;

/**
 *
 * @author mosser
 */
@WebService()
public class ErrorLogger {

    /** Log an error inside the system
     * @param trigger Name of the orchestration where the error was catched
     * @param level log level [eg SEVERE, INFO, WARNING, FATAL, ...)
     * @param message the content of the error message
     */
    @WebMethod(operationName = "log")
    public void log(@WebParam(name = "trigger") String trigger,
            @WebParam(name = "level") String level,
            @WebParam(name = "message") String message)
            throws ErrorLoggerException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "INSERT INTO `errors` VALUES(NULL,NOW(),'"+trigger+"',";
        sql += "'"+level+"','"+message+"');";
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
            throw new ErrorLoggerException(e.getMessage());
        }
    }


    /** Returns all log betwwen two given dates
     * @param start start date in SQL format ('YYYY-mm-dd H:m:s')
     * @param end end date in SQL format ('YYYY-mm-dd H:m:s')
     * @return
     */
    @WebMethod(operationName = "getLogs")
    public ErrorLog[] getLogs(@WebParam(name = "start") String start,
            @WebParam(name = "end") String end) throws ErrorLoggerException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * FROM `errors` WHERE `stamp` >= '" + start+"'";
        sql += " AND `stamp` <= '" + end+"';";
        ArrayList<ErrorLog> result = new ArrayList<ErrorLog>();
        try {
            DalResultSet rset = dal.extractDataSet(sql);
            for (int i = 0; i < rset.size(); i++ ) {
                result.add(new ErrorLog(rset));
                rset.next();
            }
            return result.toArray(new ErrorLog[result.size()]);
        } catch(Exception e) {
            throw new ErrorLoggerException(e.getMessage());
        }
    }

}
