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
 * GNU General Public License for more detailp.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite:SchoolLife; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Steve Colombié            [colombie@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.plannings;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService()
public class PlanningCRUD {
    /// A finder, to explore the persistences layer in an OO way
    private PlanningFinder finder = new PlanningFinder();


    /**
     * Create CRUD pattern operation
     * @param p the transient planning to transform in a persistent one
     * @return the planning reference (i.e. its id)
     * @throws PlanningException: null object, still persistent
     */
    @WebMethod(operationName = "createPlanning")
    public int createPlanning(@WebParam(name = "p") Planning p)
            throws PlanningException {
        if (null == p)
            throw new PlanningException("Null creation !");

        DataAccessLayer dal = new DataAccessLayer();

        String sql = "INSERT INTO `promo_planning` ";
        sql += "(`id`, `planning`) ";
        sql += "VALUES ('"+p.getId()+"', '"+p.getPlanning()+"');";

        try {
            dal.executeVoid(sql);

            String sql_group;
            for(PlanningGroup group : p.getGroups()) {
                sql_group = "INSERT INTO `promo_groups` ";
                sql_group += "(`name`, `planning`, `promo_id`) ";
                sql_group += "VALUES ('"+group.getName()+"', '";
                sql_group += group.getPlanning()+"', '"+p.getId()+"');";

                dal.executeVoid(sql_group);
            }


            return p.getId();
        } catch (Exception e) {
            throw new PlanningException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Read CRUD pattern operation
     * @param id an existing reference (i.e. id) to a persistent planning
     * @return the expected planning
     * @throws PlanningException: null id or not binded to persistent object
     */
    @WebMethod(operationName = "readPlanning")
    public Planning readPlanning(@WebParam(name = "id") int id)
            throws PlanningException {
        Planning found = finder.findPlanningById(id);
        if (null == found) {
            throw new PlanningException("UnexistingIdRead: " + id);
        }
        return found;
    }

    /**
     * Update CRUD pattern operation
     * @param p the persistent planning to update
     * @throws PlanningException: null object, non persistent object
     */
    @WebMethod(operationName = "updatePlanning")
    public void updatePlanning(@WebParam(name = "p") Planning p)
            throws PlanningException {
        if (null == p) {
            throw new PlanningException("Null update !");
        }
        if (0 == p.getId()) {
            throw new PlanningException("Unreferenced update !");
        }

        String sql = "UPDATE `promo_planning`";
        sql += "SET `planning` = '"+p.getPlanning()+"' ";
        sql += "WHERE `id` = '" + p.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();

        String sql_del = "DELETE FROM `promo_groups` WHERE `promo_id` = '"+p.getId()+"';";

        try {
            dal.executeVoid(sql);
            dal.executeVoid(sql_del);

            String sql_group;
            for(PlanningGroup group : p.getGroups()) {
                sql_group = "INSERT INTO `promo_groups` ";
                sql_group += "(`name`, `planning`, `promo_id`) ";
                sql_group += "VALUES ('"+group.getName()+"', '";
                sql_group += group.getPlanning()+"', '"+p.getId()+"');";

                dal.executeVoid(sql_group);
            }
        } catch(Exception e) {
           throw new PlanningException("SQLException: " + e.getMessage());
       }
    }

    /**
     * Delete CRUD pattern operation
     * @param p the persistent planning to delete
     * @throws PlanningException: null object, non persistent object
     */
    @WebMethod(operationName = "deletePlanning")
    public void deletePlanning(@WebParam(name = "p") Planning p)
            throws PlanningException {
        if (null == p)
            throw new PlanningException("Null delete !");
        if (0 == p.getId())
            throw new PlanningException("Unreferenced delete !");

        String sql = "DELETE FROM `promo_planning`";
        sql += "WHERE `id` = '" + p.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new PlanningException("SQLException: " + e.getMessage());
       }
    }
}
