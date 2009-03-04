/**
 * This file is part of jSeduite::PictureAlbumRegistry
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::PictureAlbumRegistry is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::PictureAlbumRegistry is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::PictureAlbumRegistry; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technicals.image.registry;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;

import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author mosser
 */
@WebService()
public class PictureAlbumRegistry {

    /** Extract valids album from the database
     */
    @WebMethod(operationName = "getValidAlbums")
    public PictureAlbum[] getValidAlbums()
            throws PictureAlbumRegistryException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * FROM `picture_album_registry` WHERE ";
        sql += "TO_DAYS(NOW()) >= TO_DAYS(`picture_album_registry`.`start`) ";
        sql += "AND TO_DAYS(NOW()) <  ";
        sql += "TO_DAYS(`picture_album_registry`.`start`) + `duration` ;";
        ArrayList<PictureAlbum> result = new ArrayList<PictureAlbum>();
        try {
            DalResultSet rset = dal.extractDataSet(sql);
            for (int i = 0; i < rset.size(); i++ ) {
                result.add(new PictureAlbum(rset));
                rset.next();
            }
            return result.toArray(new PictureAlbum[result.size()]);
        }catch(Exception e) {
            throw new PictureAlbumRegistryException(e.getMessage());
        }
    }

}
