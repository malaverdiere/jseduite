/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.m1.hebut.jSeduite.technical.breaktime.admin;

import cn.m1.hebut.jSeduite.technical.breaktime.BreakTime;
import cn.m1.hebut.jSeduite.technical.breaktime.BreakTimeException;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DALException;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Administrator
 */
@WebService()
public class BreakManagerAdmin {
   /**
     *Create a break
     * @param promo_id,start_time,end_time,break type,week day
     * @throws BreakTimeAdminException
     */
    @WebMethod(operationName = "create")
    @SuppressWarnings("empty-statement")
    public void create(@WebParam(name = "promo")
    List<String> promo, @WebParam(name = "start")
    String start, @WebParam(name = "end")
    String end, @WebParam(name = "kind")
    String kind, @WebParam(name = "day")
    String day) throws BreakTimeAdminException, DALException{
        DataAccessLayer dal = new DataAccessLayer();
         DalResultSet rset;
          String sql;
          sql="select * from break_time;";
          rset=dal.extractDataSet(sql);
           sql ="INSERT INTO `break_time` VALUES (NULL,'"+start+"','"+end+"'," +
            "'"+kind+"','"+day+"');";
          for(int i=0;i<rset.size();i++){
              if(rset.getValue("start").equals(start)&&rset.getValue("day").equals(day)){
                  sql=null;
                  break;
              }
              rset.next();
          }
           if(sql!=null){
            dal.executeVoid(sql);}
        sql="select max(break_id) as max from break_time;";
        rset=dal.extractDataSet(sql);
        int breakCount=Integer.parseInt(rset.getValue("max"));
        if(!(promo==null))//if the promo is null not insert into lnk table
        {
        for(int i=0;i<promo.size();i++){
        sql="select promos_id from promos where name='"+promo.get(i)+"';";
        rset= dal.extractDataSet(sql);
        int promoid=Integer.parseInt(rset.getValue("promos_id"));
        String sqlForLnk="INSERT INTO `break_time_promos_lnk` VALUES('"+promoid+
                 "','"+breakCount+"');";
          dal.executeVoid(sqlForLnk);
        }
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
            if(rset.size()==0) return null;
            BreakTime breaktime=new BreakTime(rset);
            return breaktime;

        } catch(Exception e) {
           throw new BreakTimeAdminException(e.getMessage());
        }


    }

    /**
     *Update a break
     *  @param break_id,promo
     * @throws BreakTimeAdminException
     */
    @WebMethod(operationName = "deleteAllLnkByBreak")
    public void deleteAllLnkByBreak(@WebParam(name = "breakId")
    int breakId) throws DALException {
         if(breakId==0) return;
          DataAccessLayer dal=new DataAccessLayer();
           String sql="delete from break_time_promos_lnk where break_id='"+breakId+"';";
           dal.executeVoid(sql);
    }

    @WebMethod(operationName = "update")
    public void update(@WebParam(name = "breakId")
    int breakId, @WebParam(name = "promo")
    List<String> promo,@WebParam(name = "start")
    String start,@WebParam(name = "end")
    String end, @WebParam(name = "kind")
    String kind,@WebParam(name = "day")
    String day) throws DALException {
       DataAccessLayer dal=new DataAccessLayer();
       String sql;
      if(promo!=null)//if the promo is null not insert into lnk table
      {
          for(int i=0;i<promo.size();i++){
          sql="select promos_id from promos where name='"+promo.get(i)+"';";
          DalResultSet rset= dal.extractDataSet(sql);
          int promoid=Integer.parseInt(rset.getValue("promos_id"));
           sql="insert into break_time_promos_lnk values('"+promoid+"','"+
                        breakId+"');";
           dal.executeVoid(sql);}
          
      }
        sql="update break_time set start='"+start+"',end='"+end+"',kind='" +
                  kind+"',day='"+day+"' where break_id='"+breakId+"'";
        dal.executeVoid(sql);
     }


    /**
     * delete a break
     * @param id expected break_id
     * @throws BreakTimeAdminException
     */

    @WebMethod(operationName = "delete")
    public void delete(@WebParam(name = "id")
    int id) throws BreakTimeAdminException, DALException {
         DataAccessLayer dal=new DataAccessLayer();
        String sql="delete from break_time ";
        sql+="where break_id='"+id+"';";
        String sqlForPromoLnk="delete from break_time_promos_lnk where break_id='"+
                id+"';";
        String sqlForAlarmLnk="delete from break_alarm_lnk where break='"+id+"';";
     try{
            dal.executeVoid(sqlForPromoLnk);
            dal.executeVoid(sql);
            dal.executeVoid(sqlForAlarmLnk);
      }
        catch(Exception e) {

      throw new BreakTimeAdminException(e.getMessage());

        }
    }

    /**
     * get all the breaks
     * @return BreakTimeForAdmin[]
     */
    @WebMethod(operationName = "getAllBreaks")
    public List<BreakTime> getAllBreaks() throws BreakTimeException, DALException {
         DataAccessLayer dal=new DataAccessLayer();
        String sql="select * from break_time ";
        ArrayList<BreakTime> result = new ArrayList<BreakTime>();
        DalResultSet rset = dal.extractDataSet(sql);
        for(int i=0;i<rset.size();i++){
            try {
                BreakTime breaktime = new BreakTime(rset);
                result.add(breaktime);
                rset.next();
            } catch (Exception ex) {
                Logger.getLogger(BreakManagerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;

    }
     /**
     * get promos by break
     */
    @WebMethod(operationName = "getPromosByBreak")
    public List getPromosByBreak(@WebParam(name = "id")
    int id) throws DALException {
        DataAccessLayer dal = new DataAccessLayer();
         ArrayList promolist=new ArrayList();
        String sql="SELECT promo_id FROM break_time_promos_lnk where break_id='"+
                id+"';";
        DalResultSet rset = dal.extractDataSet(sql);
        if(rset.size()==0) return null;
        for(int i=0;i<rset.size();i++){
            int promoid=Integer.parseInt(rset.getValue("promo_id"));
            sql="select `name` from `promos` where promos_id='"+promoid+"';";
            DalResultSet rset2=dal.extractDataSet(sql);
            promolist.add(rset2.getValue("name"));
            rset.next();
        }
          return promolist;

    }

}
