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
 * @contributor 2009 Steve Colombié            [colombie@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.image.registry;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

/** A Simple data class to represent a picture album
 * @author mosser
 */
public class PictureAlbum {

    private int id;
    private String name;
    private String repository;
    private String user;
    private String album;
    private Date validFrom;
    private int duration;

    public PictureAlbum(){};

    public PictureAlbum(String repository, String user, String album,
            Date validFrom, int duration, String name) {
        this.repository = repository;
        this.user = user;
        this.album = album;
        this.validFrom = validFrom;
        this.duration = duration;
        this.name = name;
        this.id = 0;
    }

    public PictureAlbum(int id, String repository, String user, String album,
            Date validFrom, int duration, String name) {
        this.repository = repository;
        this.user = user;
        this.album = album;
        this.validFrom = validFrom;
        this.duration = duration;
        this.name = name;
        this.id = id;
    }

    public PictureAlbum(DalResultSet dataSet) throws Exception {
        this.name = dataSet.getValue("album_name");
        this.repository = dataSet.getValue("repository");
        this.user = dataSet.getValue("repository_user_id");
        this.album  = dataSet.getValue("repository_album_name");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.validFrom = format.parse(dataSet.getValue("start"));
        this.duration = Integer.parseInt(dataSet.getValue("duration"));
        this.id = Integer.parseInt(dataSet.getValue("id"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
