/**
 * This file is part of jSeduite::InternalNews
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::InternalNews is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::InternalNews is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::InternalNews; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.news.internal;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;

import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/** A web service to handle internal school news
 * @author mosser
 */
@WebService()
public class InternalNews {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getTargets")
    public String[] getTargets() throws InternalNewsException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * FROM `internal_news_target`;";
        try {
            return dal.extractScalarSet(sql, "name");
        } catch(Exception e) {
            throw new InternalNewsException(e.getMessage());
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllNews")
    public News[] getAllNews() throws InternalNewsException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * FROM `current_internal_news`; ";
        try {
            ArrayList<News> result = new ArrayList<News>();
            DalResultSet rset = dal.extractDataSet(sql);
            for(int i = 0; i < rset.size(); i++){
                result.add(new News(rset));
                rset.next();
            }
            return result.toArray(new News[result.size()]);

        } catch(Exception e) {
            throw new InternalNewsException(e.getMessage());
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getTargetedNews")
    public News[] getTargetedNews(@WebParam(name = "target") String target)
            throws InternalNewsException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * FROM `current_internal_news` WHERE ";
        sql += " target = '"+target+"';";
        try {
            ArrayList<News> result = new ArrayList<News>();
            DalResultSet rset = dal.extractDataSet(sql);
            for(int i = 0; i < rset.size(); i++){
                result.add(new News(rset));
                rset.next();
            }
            return result.toArray(new News[result.size()]);

        } catch(Exception e) {
            throw new InternalNewsException(e.getMessage());
        }
    }

}
