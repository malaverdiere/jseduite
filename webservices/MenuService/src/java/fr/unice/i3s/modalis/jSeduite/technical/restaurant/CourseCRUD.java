/** This file is part of jSeduite::MenuService
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::MenuService is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::MenuService is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::MenuService; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Mireille Blay-Fornarino [blay@polytech.unice.fr]
 * @contributor 2009 Mosser Sebastien   [mosser@polytech.unice.fr]
 * @contributor 2010 Desclaux Christophe[desclaux@polytech.unice.fr]
**/
package fr.unice.i3s.modalis.jSeduite.technical.restaurant;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;

/** A Web service for course business object data handling
 */
@WebService()
public class CourseCRUD {

    /// A finder, to explore the persistences layer in an OO way
    private CourseFinder finder = new CourseFinder();

    /** Create CRUD pattern operation
     * @param c the trasient course to transform in a persistent one
     * @return the course reference (i.e. its id)
     * @throws RestaurantException: null object, still persistent
     */
    @WebMethod(operationName = "createCourse")
    public int createCourse(@WebParam(name = "c") Course c)
            throws RestaurantException {
        if (null == c)
            throw new RestaurantException("Null creation !");
        if (null != finder.findCourseByName(c.getName()) || c.getId() > 0)
            throw new RestaurantException("Re-Creation !");
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "INSERT INTO `course` (`id`, `kind`, `name`) VALUES (";
            sql += "NULL, '" + c.getKind() + "', '" + c.getName() + "');";
            dal.executeVoid(sql);
        } catch (Exception e) {
            throw new RestaurantException("SQL Exception: " + e.getMessage());
        }
        try{
            String sql2 = "SELECT max(id) as `id` FROM `course`";
            DalResultSet rSet = dal.extractDataSet(sql2);
            c.setId(Integer.parseInt(rSet.getValue("id")));
            return c.getId();
        } catch (Exception e) {
            throw new RestaurantException("SQL Exception: " + e.getMessage());
        }
    }

    /** Read CRUD pattern operation 
     * @param ref an existing reference (i.e. id) to a persistent course
     * @return the expected course
     * @throws RestaurantException: null ref or not binded to persistent object
     */
    @WebMethod(operationName = "readCourse")
    public Course readCourse(@WebParam(name = "id") int id)
            throws RestaurantException {
       if(id < 0)
           throw new RestaurantException("Null read !");
       Course found = finder.findCourseById(id);
       if (null == found)
           throw new RestaurantException("UnexistingRefRead: " + id);
       return found;
    }

    /** Update CRUD pattern operation
     * @param c the persistent course to update
     * @throws RestaurantException null object, non persistent object
     */
    @WebMethod(operationName = "updateCourse")
    public void updateCourse(@WebParam(name = "c") Course c)
            throws RestaurantException {
        if (null == c)
            throw new RestaurantException("Null update !");
        if (c.getId() <= 0)
            throw new RestaurantException("Unreferenced update !");
        String sql = "UPDATE `course` SET `kind` = '"+c.getKind()+"', ";
        sql += "`name` = '" + c.getName()+"'";
        sql += " WHERE `id` = '" + c.getId()+"';";
        try {
            DataAccessLayer dal = new DataAccessLayer();
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new RestaurantException("SQLException: " + e.getMessage());
       }
    }

    /**  Delete CRUD pattern operation
     * @param c the persistent course to delete
     * @throws RestaurantException null object, non persistent object
     */
    @WebMethod(operationName = "deleteCourse")
    public void deleteCourse(@WebParam(name = "c") Course c)
            throws RestaurantException {
        if (null == c)
            throw new RestaurantException("Null delete !");
        if (c.getId() <= 0)
            throw new RestaurantException("Unreferenced delete !");
        String sql = "DELETE FROM `course` WHERE `id` = '" + c.getId()+"';";
        String sql2 = "DELETE FROM `menu` WHERE `courseId` = '" + c.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
            dal.executeVoid(sql2);
        } catch(Exception e) {
           throw new RestaurantException("SQLException: " + e.getMessage());
       }
    }
}
