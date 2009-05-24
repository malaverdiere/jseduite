/**
 * This file is part of jSeduite::BreakTimeManager
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::Twitter is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::Twitter is distributed in the hope that it will be useful,
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

package cn.m1.hebut.jSeduite.technical.twitter.admin;

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
import winterwell.jtwitter.Twitter;

/**
 *
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
   @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 */
@WebService()
public class TwitterWrapperAdmin {

    /**
     * make the school follow the student and link student to his id
     * @param schoolUser,schoolPassword...
     * @exception DALException and TwitterWrapperAdminException
     * @return boolean
     */
    @WebMethod(operationName = "register")
    public boolean register(@WebParam(name = "schoolUser")
    String schoolUser, @WebParam(name = "schoolPassword")
    String schoolPassword, @WebParam(name = "studentId")
    String studentId, @WebParam(name = "studentTwitter")
    String studentTwitter, @WebParam(name = "status")
    String status) {
       if(status.equalsIgnoreCase("free")){
            try {
                DataAccessLayer dal = new DataAccessLayer();
                String sql = "insert into `twitter_binding` values('" + studentId + "','" + studentTwitter + "','" + status + "');";
                dal.executeVoid(sql);
                Twitter school = new Twitter(schoolUser, schoolPassword);
                school.follow(studentTwitter);
                return true;
            } catch (DALException ex) {
                Logger.getLogger(TwitterWrapperAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }catch(TwitterWrapperAdminException ex){
                Logger.getLogger(TwitterWrapperAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
        return false;
    }

    /**
     * block the relationship between student and schlool
     * @param studentId,schoolUser and schoolPassword
     * @exception DALException and TwitterWrapperAdminException
     * @return boolean
     */
    @WebMethod(operationName = "block")
    public boolean block(@WebParam(name = "studentId")
    String studentId,@WebParam(name = "schoolUser")
    String schoolUser,@WebParam(name = "schoolPassword")
    String schoolPassword) {
        try {
            DataAccessLayer dal = new DataAccessLayer();
            String sql = "select `twitter_account` from `twitter_binding` where "
                    + "student_id='" + studentId + "';";
            DalResultSet rset = dal.extractDataSet(sql);
            if (rset.size() != 0) {
              String studentTwitter=rset.getValue("twitter_account");
              Twitter school = new Twitter(schoolUser, schoolPassword);
              if(Twitter.getUser(studentTwitter, school.getFriends())!=null)
              {
                  school.breakFriendship(studentTwitter);
                  sql="update `twitter_binding` set `status`='blocked' where student_id='" +
                          studentId+"';";
                  dal.executeVoid(sql);
                  return true;
              } else
              { return false;}
            }
        } catch (DALException ex) {
            Logger.getLogger(TwitterWrapperAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }catch(TwitterWrapperAdminException ex){
            Logger.getLogger(TwitterWrapperAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
          return false;
    }

    /**
     * unlock the relationship
     * @param studentId,schoolUser and schoolPassword
     * @exception DALException and TwitterWrapperAdminException
     * @return boolean
     */
    @WebMethod(operationName = "unlock")
    public boolean unlock(@WebParam(name = "studentId")
    String studentId, @WebParam(name = "schoolUser")
    String schoolUser, @WebParam(name = "schoolPassword")
    String schoolPassword) {
        try {
            DataAccessLayer dal = new DataAccessLayer();
            String sql = "select `twitter_account` from `twitter_binding` where " + "student_id='" + studentId + "';";
            DalResultSet rset = dal.extractDataSet(sql);
            if (rset.size() != 0) {
              String studentTwitter=rset.getValue("twitter_account");
              Twitter school = new Twitter(schoolUser, schoolPassword);
              school.follow(studentTwitter);
              sql="update `twitter_binding` set `status`='free' where student_id='" +
                          studentId+"';";
                  dal.executeVoid(sql);
                  return true;
            }

        } catch (DALException ex) {
            Logger.getLogger(TwitterWrapperAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }catch(TwitterWrapperAdminException ex){
            Logger.getLogger(TwitterWrapperAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
         return false;
    }

    /**
     *get all the users who have registered
     * @exception DALException and TwitterWrapperAdminException
     * @return a list of users
     */
    @WebMethod(operationName = "getAllRegisterUser")
    public List<User> getAllRegisterUser() {
        try {
            DataAccessLayer dal = new DataAccessLayer();
            String sql = "select * from `twitter_binding`;";
            DalResultSet rset = dal.extractDataSet(sql);
            List<User> list=new ArrayList<User>();
            if(rset.size()!=0){
                for(int i=0;i<rset.size();i++){
                    list.add(new User(rset));
                    rset.next();
                }
            }
            return list;
        } catch (DALException ex) {
            Logger.getLogger(TwitterWrapperAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }catch(TwitterWrapperAdminException ex){
            Logger.getLogger(TwitterWrapperAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        return null;
    }
   
}
