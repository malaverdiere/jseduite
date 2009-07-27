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
 * along with jSeduite::SchoolLife; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Steve Colombié         [colombie@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.promos;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService()
public class PromoCRUD {

    /// A finder, to explore the persistences layer in an OO way
    private PromoFinder finder = new PromoFinder();

    /**
     * Create CRUD pattern operation
     * @param p the transient promo to transform in a persistent one
     * @return the promo reference (i.e. its id)
     * @throws PromoException: null object, still persistent
     */
    @WebMethod(operationName = "createPromo")
    public int createPromo(@WebParam(name = "p") Promo p)
            throws PromoException {
        if (null == p)
            throw new PromoException("Null creation !");

        DataAccessLayer dal = new DataAccessLayer();

        String sql = "INSERT INTO `promos` ";
        sql += "(`id`, `code`, `name`) VALUES (";
        sql += "'"+p.getId()+"','"+p.getCode()+"','"+p.getName()+"');";

        String idGetter = "SELECT max(`id`) AS `id` FROM `promos`";

        try {
            dal.executeVoid(sql);
            return Integer.parseInt(dal.extractScalar(idGetter, "id"));
        } catch (Exception e) {
            throw new PromoException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Read CRUD pattern operation
     * @param id an existing reference (i.e. id) to a persistent promo
     * @return the expected promo
     * @throws PromoException: null id or not binded to persistent object
     */
    @WebMethod(operationName = "readPromo")
    public Promo readPromo(@WebParam(name = "id") int id)
            throws PromoException {
        Promo found = finder.findPromoById(id);
        if (null == found) {
            throw new PromoException("UnexistingIdRead: " + id);
        }
        return found;
    }

    /**
     * Update CRUD pattern operation
     * @param p the persistent promo to update
     * @throws PromoException: null object, non persistent object
     */
    @WebMethod(operationName = "updatePromo")
    public void updatePromo(@WebParam(name = "p") Promo p)
            throws PromoException {
        if (null == p) {
            throw new PromoException("Null update !");
        }
        if (0 == p.getId()) {
            throw new PromoException("Unreferenced update !");
        }

        String sql = "UPDATE `promos`";
        sql += "SET `code` = '"+p.getCode()+"', ";
        sql += "`name` = '"+p.getName()+"' ";
        sql += "WHERE `id` = '" + p.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new PromoException("SQLException: " + e.getMessage());
       }
    }

    /**
     * Delete CRUD pattern operation
     * @param p the persistent promo to delete
     * @throws PromoException: null object, non persistent object
     */
    @WebMethod(operationName = "deletePromo")
    public void deletePromo(@WebParam(name = "p") Promo p)
            throws PromoException {
        if (null == p)
            throw new PromoException("Null delete !");
        if (0 == p.getId())
            throw new PromoException("Unreferenced delete !");

        String sql = "DELETE FROM `promos`";
        sql += "WHERE `id` = '" + p.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new PromoException("SQLException: " + e.getMessage());
       }
    }
}
