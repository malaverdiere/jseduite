/**
 * This file is part of jSeduite::Pictogram
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::Pictogram is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::Pictogram is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::Pictogram; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Christophe Desclaux        [desclaux@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.pictogram;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService()
public class PictogramBusiness {

    /**
     * Web service which return all pictograms in use
     */
    @WebMethod(operationName = "getAllPictograms")
    public Pictogram[] getAllPictograms() throws PictogramException {
        String nowSQL = toSql(new Date());
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * FROM `pictograms` WHERE `start` <= '" + nowSQL + "' AND `end` >= '" + nowSQL + "';";
        try {
            ArrayList<Pictogram> result = new ArrayList<Pictogram>();
            DalResultSet rset = dal.extractDataSet(sql);
            for(int i = 0; i < rset.size(); i++){
                result.add(new Pictogram(rset));
                rset.next();
            }
            return result.toArray(new Pictogram[result.size()]);

        } catch(Exception e) {
            throw new PictogramException(e.getMessage());
        }
    }
    
    /** A static method to transform a java Date into a valid SQL entry
     * @param date the date to transform
     * @return a string formatted as YYYY-MM-DD HH:MM:SS
     */
    public static String toSql(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        String d = "" + cal.get(Calendar.YEAR);
        d += "-" + (cal.get(Calendar.MONTH) + 1);
        d += "-" + cal.get(Calendar.DAY_OF_MONTH);
        d += " " + cal.get(Calendar.HOUR_OF_DAY);
        d += ":" + cal.get(Calendar.MINUTE);
        d += ":00";
        return d;
    }
}
