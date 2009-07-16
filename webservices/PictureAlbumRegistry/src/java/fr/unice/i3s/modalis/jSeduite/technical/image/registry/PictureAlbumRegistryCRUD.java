/**
 * This file is part of jSeduite::PictureAlbumRegistry
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::BreakingNews is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::PictureAlbumRegistry is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::PictureAlbumRegistry; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Steve Colombié          [colombie@polytech.unice.fr]
 **/


package fr.unice.i3s.modalis.jSeduite.technical.image.registry;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService()
public class PictureAlbumRegistryCRUD {

    /// A finder, to explore the persistences layer in an OO way
    private PictureAlbumRegistryFinder finder = new PictureAlbumRegistryFinder();

    /**
     * A static method to transform a java Date into a valid SQL entry
     * @param date the date to transform
     * @return a string formatted as YYYY-MM-DD HH:MM
     */
    public static String toSql(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        String d = "" + cal.get(Calendar.YEAR);
        d += "-" + (cal.get(Calendar.MONTH)+1);
        d += "-" + cal.get(Calendar.DAY_OF_MONTH);
        d += " " + cal.get(Calendar.HOUR_OF_DAY);
        d += ":" + cal.get(Calendar.MINUTE);
        d += ":" + cal.get(Calendar.SECOND);
        return d;
    }


    /**
     * Create CRUD pattern operation
     * @param p the transient breaking news to transform in a persistent one
     * @return the picture album reference (i.e. its id)
     * @throws PictureAlbumRegistryException: null object, still persistent
     */
    @WebMethod(operationName = "createPictureAlbum")
    public int createPictureAlbum(@WebParam(name = "p") PictureAlbum p)
            throws PictureAlbumRegistryException {
        if (null == p)
            throw new PictureAlbumRegistryException("Null creation !");

        DataAccessLayer dal = new DataAccessLayer();
        String sqlDate = toSql(p.getValidFrom());

        String sql = "INSERT INTO `picture_album_registry`";
        sql += "(`album_name`, `repository`, `repository_user_id`, ";
        sql += "`repository_album_name`, `start`, `duration`) VALUES (";
        sql += "'"+p.getName()+"','"+p.getRepository()+"','"+p.getUser()+"','";
        sql += p.getAlbum()+"','"+sqlDate+"','"+p.getDuration()+"');";

        String idGetter = "SELECT max(`id`) AS `id` FROM `picture_album_registry`";

        try {
            dal.executeVoid(sql);
            return Integer.parseInt(dal.extractScalar(idGetter, "id"));
        } catch (Exception e) {
            throw new PictureAlbumRegistryException("SQL Exception: " + e.getMessage());
        }
    }


    /**
     * Read CRUD pattern operation
     * @param id an existing reference (i.e. id) to a persistent picture album
     * @return the expected picture album
     * @throws PictureAlbumRegistryException: null id or not binded to persistent object
     */
    @WebMethod(operationName = "readPictureAlbum")
    public PictureAlbum readPictureAlbum(@WebParam(name = "id") int id)
            throws PictureAlbumRegistryException {
        PictureAlbum found = finder.findPictureAlbumById(id);
        if (null == found) {
            throw new PictureAlbumRegistryException("UnexistingIdRead: " + id);
        }
        return found;
    }


    /**
     * Update CRUD pattern operation
     * @param p the persistent picture album to update
     * @throws PictureAlbumRegistryException: null object, non persistent object
     */
    @WebMethod(operationName = "updatePictureAlbum")
    public void updatePictureAlbum(@WebParam(name = "p") PictureAlbum p)
            throws PictureAlbumRegistryException {
        if (null == p) {
            throw new PictureAlbumRegistryException("Null update !");
        }
        if (0 == p.getId()) {
            throw new PictureAlbumRegistryException("Unreferenced update !");
        }

        String sql = "UPDATE `picture_album_registry`";
        sql += "SET `album_name` = '"+p.getName()+"', ";
        sql += "`repository` = '"+p.getRepository()+"', ";
        sql += "`repository_user_id` = '"+p.getUser()+"', ";
        sql += "`repository_album_name` = '"+p.getAlbum()+"', ";
        sql += "`start` = '"+toSql(p.getValidFrom())+"', ";
        sql += "`duration` = '"+p.getDuration()+"' ";
        sql += "WHERE `id` = '" + p.getId()+"';";
        
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new PictureAlbumRegistryException("SQLException: " + e.getMessage());
       }
    }

    /**
     * Delete CRUD pattern operation
     * @param p the persistent picture album to delete
     * @throws PictureAlbumRegistryException: null object, non persistent object
     */
    @WebMethod(operationName = "deletePictureAlbum")
    public void deletePictureAlbum(@WebParam(name = "p") PictureAlbum p)
            throws PictureAlbumRegistryException {
        if (null == p)
            throw new PictureAlbumRegistryException("Null delete !");
        if (0 == p.getId())
            throw new PictureAlbumRegistryException("Unreferenced delete !");

        String sql = "DELETE FROM `picture_album_registry`";
        sql += "WHERE `id` = '" + p.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new PictureAlbumRegistryException("SQLException: " + e.getMessage());
       }
    }
}
