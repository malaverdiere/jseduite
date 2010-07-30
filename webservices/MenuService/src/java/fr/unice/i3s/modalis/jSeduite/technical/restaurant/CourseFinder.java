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

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;
import java.util.ArrayList;

@WebService()
public class CourseFinder {

    /** Retrieves all courses for a given kind
     * @param kind the kind you're looking for
     * @return the associated set of courses
     * @throws RestaurantException when sql exception occurs
     */
    @WebMethod(operationName="findCoursesByKind")
    public Course[] findCoursesByKind(@WebParam(name = "kind") String kind)
            throws RestaurantException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `course` WHERE `kind`= '"+kind+"' ORDER BY name ASC;";
            DalResultSet rSet = dal.extractDataSet(sql);
            ArrayList<Course> result = new ArrayList<Course>(rSet.size());
            for(int i = 0; i < rSet.size(); i++){
                result.add(new Course(rSet));
                rSet.next();
            }
            return result.toArray(new Course[result.size()]);
        } catch (Exception e) {
            throw new RestaurantException("SQL Exception: " + e.getMessage());
        }
    }

    /** Retrieves a course for a given name
     * @param name the name you're looking for
     * @return The associated course, null if not exists
     * @throws RestaurantException when SQL exception occurs
     */
    @WebMethod(operationName="findCourseByName")
    public Course findCourseByName(@WebParam(name="name") String name)
            throws RestaurantException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `course` WHERE `name`= '"+name+"';";
            DalResultSet rSet = dal.extractDataSet(sql);
            if (rSet.size() == 0)
                return null;
            return new Course(rSet);
        } catch (Exception e) {
            throw new RestaurantException("SQL Exception: " + e.getMessage());
        }
    }

        /** Retrieves a course for a given name
     * @param id the course you're looking for
     * @return The associated course, null if not exists
     * @throws RestaurantException when SQL exception occurs
     */
    @WebMethod(operationName="findCourseById")
    public Course findCourseById(@WebParam(name="id") int id)
            throws RestaurantException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `course` WHERE `id`= '"+id+"';";
            DalResultSet rSet = dal.extractDataSet(sql);
            if (rSet.size() == 0)
                return null;
            return new Course(rSet);
        } catch (Exception e) {
            throw new RestaurantException("SQL Exception: " + e.getMessage());
        }
    }
    
    /** Extract all persistent references for the Course business object
     * @return all existing course references
     * @throws RestaurantException
     */
    @WebMethod(operationName="getAllCoursesReferences")
    public int[] getAllCoursesReferences() throws RestaurantException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT `id` FROM `course`;";
            String[] refsString  = dal.extractScalarSet(sql, "id");
            int [] refs = new int[refsString.length];
            for(int i=0; i<refs.length;i++)
                refs[i] = Integer.parseInt(refsString[i]);

            return refs;
        } catch (Exception e) {
            throw new RestaurantException("SQL Exception: " + e.getMessage());
        }
    }

}
