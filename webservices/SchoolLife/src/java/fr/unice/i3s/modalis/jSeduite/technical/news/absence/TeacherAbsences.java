/**
 * This file is part of jSeduite::SchoolLife
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::SchoolLife is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::SchoolLife is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite:SchoolLife; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 *
 **/
package fr.unice.i3s.modalis.jSeduite.technical.news.absence;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;

/** A Web service to handle teachers' absences
 * @author mosser
 */
@WebService()
public class TeacherAbsences {

    /** Retrieve all teachers who are declares as missing for NOW
     * @return a list of Absence
     */
    @WebMethod(operationName = "getAbsences")
    public Absence[] getAbsences() throws TeacherAbsenceException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * FROM `teacher_absences` WHERE `from` < NOW()";
        sql += " AND NOW() < `until`;";
        try {
            ArrayList<Absence> result = new ArrayList<Absence>();
            DalResultSet rset = dal.extractDataSet(sql);
            for(int i = 0; i < rset.size(); i++)
                result.add(new Absence(rset));
            return result.toArray(new Absence[result.size()]);
        } catch(Exception e) {
            throw new TeacherAbsenceException(e.getMessage());
        }
    }

}
