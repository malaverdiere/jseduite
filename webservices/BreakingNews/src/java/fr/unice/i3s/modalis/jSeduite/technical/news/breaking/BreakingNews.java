/**
 * This file is part of jSeduite::BreakingNews
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::BreakingNews is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::BreakingNews is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite:DataCache; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.news.breaking;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;

import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;

/** A data source service to handle breaking news
 * @author mosser
 */
@WebService()
public class BreakingNews {

    /** Extract the 10 lasts BreakingNews from the database
     * @return Today's breaking news
     * @throws BreakingNewsException
     */
    @WebMethod(operationName = "getBreakingNews")
    public BreakNew[] getBreakingNews() throws BreakingNewsException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * FROM `breaking_news` ";
        sql += "ORDER BY `stamp` DESC LIMIT 10";
        try {
            ArrayList<BreakNew> result = new ArrayList<BreakNew>();
            DalResultSet rset = dal.extractDataSet(sql);
             for (int i = 0; i < rset.size(); i++ ) {
                result.add(new BreakNew(rset));
                rset.next();
            }
            return result.toArray(new BreakNew[result.size()]);
        } catch (Exception e) {
            throw new BreakingNewsException(e.getMessage());
        }
    }

}
