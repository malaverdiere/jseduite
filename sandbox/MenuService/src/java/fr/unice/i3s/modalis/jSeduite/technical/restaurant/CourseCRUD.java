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
     * @return the course reference (i.e. its name)
     * @throws RestaurantException: null object, still persistent
     */
    @WebMethod(operationName = "createCourse")
    public String createCourse(@WebParam(name = "c") Course c)
            throws RestaurantException {
        if (null == c)
            throw new RestaurantException("Null creation !");
        if (null != finder.findCourseByName(c.getName()))
            throw new RestaurantException("Re-Creation !");
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "INSERT INTO `course` (`kind`, `name`) VALUES (";
            sql += "'"+c.getKind()+"','"+c.getName()+"');";
            dal.executeVoid(sql);
            return c.getName();
        } catch (Exception e) {
            throw new RestaurantException("SQL Exception: " + e.getMessage());
        }
    }

    /** Read CRUD pattern operation 
     * @param ref an existing reference (i.e. name) to a persistent course
     * @return the expected course
     * @throws RestaurantException: null ref or not binded to persistent object
     */
    @WebMethod(operationName = "readCourse")
    public Course readCourse(@WebParam(name = "ref") String ref)
            throws RestaurantException {
       if(null == ref)
           throw new RestaurantException("Null read !");
       Course found = finder.findCourseByName(ref);
       if (null == found)
           throw new RestaurantException("UnexistingRefRead: " + ref);
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
        if (null == c.getName())
            throw new RestaurantException("Unreferenced update !");
        String sql = "UPDATE `course` SET `kind` = '"+c.getKind()+"' ";
        sql += "WHERE `name` = '" + c.getName()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
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
        if (null == c.getName())
            throw new RestaurantException("Unreferenced delete !");
        String sql = "DELETE FROM `course` WHERE `name` = '" + c.getName()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new RestaurantException("SQLException: " + e.getMessage());
       }
    }
}
