/**
 * This file is part of jSeduite::BreakTimeManager
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::BreakTimeManager is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::BreakTimeManager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::InternalNews; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
   @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 **/

package cn.m1.hebut.jSeduite.technical.breaktime.admin;

import cn.m1.hebut.jSeduite.technical.breaktime.BreakTime;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService()
public class BreakManagerAdmin{

    /**
     *Create a break
     * @param promo_id,start_time,end_time,break type,week day
     * @throws BreakTimeAdminException
     */
    @WebMethod(operationName = "create")
    public void create(@WebParam(name = "promo")
    int promo, @WebParam(name = "start")
    String start, @WebParam(name = "end")
    String end, @WebParam(name = "kind")
    String kind, @WebParam(name = "day")
    String day) throws BreakTimeAdminException{
     DataAccessLayer dal = new DataAccessLayer();
    String sql ="INSERT INTO `break_time` VALUES (NULL,"+promo+",'"+start+"','"+end+"'," +
            "'"+kind+"','"+day+"');";

        try{

            dal.executeVoid(sql);

      }
        catch(Exception e) {

      throw new BreakTimeAdminException(e.getMessage());

        }

    }

    /**
     * Find a break by id
     * @param id expected break_id
     * @return break by id
     * @throws BreakTimeAdminException
     *
     */
    @WebMethod(operationName = "read")
    public BreakTime read(@WebParam(name = "id")
    int id) throws BreakTimeAdminException {
      DataAccessLayer dal=new DataAccessLayer();
        String sql="select * from break_time ";
        sql+="where break_id='"+id+"';";
   try {
          
            DalResultSet rset = dal.extractDataSet(sql);
              return new BreakTime(rset);

        } catch(Exception e) {

      throw new BreakTimeAdminException(e.getMessage());

        }

       
    }

    /**
     *Update a break
     *  @param promo_id,start_time,end_time,break type,week day,id as break_id
     * @throws BreakTimeAdminException
     */
    @WebMethod(operationName = "update")
    public void update(@WebParam(name = "promo")
    int promo, @WebParam(name = "start")
    String start, @WebParam(name = "end")
    String end, @WebParam(name = "kind")
    String kind, @WebParam(name = "day")
    String day,@WebParam(name = "id")
    int id) throws BreakTimeAdminException {
        DataAccessLayer dal=new DataAccessLayer();
         String sql="update break_time set promo='"+promo+"',";
                sql+="start='"+start+"',";
                sql+="end='"+end+"',";
                sql+="kind='"+kind+"',";
                sql+="day='"+day+"'where break_id='"+id+"';";
           try{

            dal.executeVoid(sql);

      }
        catch(Exception e) {

      throw new BreakTimeAdminException(e.getMessage());

        }
    }

    /**
     * delete a break
     * @param id expected break_id
     * @throws BreakTimeAdminException
     */
    @WebMethod(operationName = "delete")
    public void delete(@WebParam(name = "id")
    int id) throws BreakTimeAdminException {
         DataAccessLayer dal=new DataAccessLayer();
        String sql="delete from break_time ";
        sql+="where break_id='"+id+"';";
     try{

            dal.executeVoid(sql);

      }
        catch(Exception e) {

      throw new BreakTimeAdminException(e.getMessage());

        }
    }

}
