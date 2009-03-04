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
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

/** A Simple data class to represent a picture album
 * @author mosser
 */
public class PictureAlbum {
    
    private String repository;
    private String user;
    private String album;
    private Date validFrom;
    private int duration;

    public PictureAlbum(){};

    public PictureAlbum(String repository, String user, String album,
            Date validFrom, int duration) {
        this.repository = repository;
        this.user = user;
        this.album = album;
        this.validFrom = validFrom;
        this.duration = duration;
    }

    public PictureAlbum(DalResultSet dataSet) throws Exception {
        this.repository = dataSet.getValue("repository");
        this.user = dataSet.getValue("repository_user_id");
        this.album  = dataSet.getValue("repository_album_name");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.validFrom = format.parse(dataSet.getValue("start"));
        this.duration = Integer.parseInt(dataSet.getValue("duration"));
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    

}
