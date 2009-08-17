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
 * @contributor 2009 Steve Colombié            [colombie@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.news.summon;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;

/** A Web service to handle students summons
 * @author mosser
 */
@WebService()
public class StudentSummon {

    /** Extract all valid summons from the database
     * @return a set of valid summons
     * @throws StudentsSummonException
     */
    @WebMethod(operationName = "getSummoned")
    public ValidSummoning[] getSummoned() throws StudentsSummonException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * from `valid_summonings`;";
        try {
            ArrayList<ValidSummoning> result = new ArrayList<ValidSummoning>();
            DalResultSet rset = dal.extractDataSet(sql);
            for(int i = 0; i < rset.size(); i++) {
                result.add(new ValidSummoning(rset));
                rset.next();
            }
            return result.toArray(new ValidSummoning[result.size()]);
        }catch(Exception e){
            throw new StudentsSummonException(e.getMessage());
        }
    }

    /** Extract all summons for a given promo
     * @param code expected promo code
     * @return a set of valid summons for the promo identified by 'code'
     * @throws StudentsSummonException
     */
    @WebMethod(operationName = "getSummonedByCode")
    public ValidSummoning[] getSummonedbyCode(@WebParam(name="code") String code)
            throws StudentsSummonException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * from `valid_summonings` WHERE `p_code` = '"
                + code + "';";
        try {
            ArrayList<ValidSummoning> result = new ArrayList<ValidSummoning>();
            DalResultSet rset = dal.extractDataSet(sql);
            for(int i = 0; i < rset.size(); i++) {
                result.add(new ValidSummoning(rset));
            }
            return result.toArray(new ValidSummoning[result.size()]);
        }catch(Exception e){
            throw new StudentsSummonException(e.getMessage());
        }
    }
}
