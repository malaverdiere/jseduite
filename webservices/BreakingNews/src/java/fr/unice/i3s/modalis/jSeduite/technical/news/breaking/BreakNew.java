/**
 * This file is part of jSeduite::BreakingNews
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::BreakingNews is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::BreakingNews is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite:DataCache; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.news.breaking;

import java.text.SimpleDateFormat;
import java.util.Date;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;

/** A simple data class to handle breaking nexs business objects
 * @author mosser
 */
public class BreakNew {

    private String author;
    private Date stamp;
    private String content;

    public BreakNew() { }

    public BreakNew(DalResultSet rset) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        this.stamp = format.parse(rset.getValue("stamp"));
        this.author = rset.getValue("author");
        this.content = rset.getValue("content");
    }

    public BreakNew(String author, Date stamp, String content) {
        this.author = author;
        this.stamp = stamp;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStamp() {
        return stamp;
    }

    public void setStamp(Date stamp) {
        this.stamp = stamp;
    }

    

}
