/**
 * This file is part of jSeduite::InternalNews
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::InternalNews is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::InternalNews is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::InternalNews; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 * @contributor 2009 Steve Colombié            [colombie@polytech.unice.fr]
 **/
package fr.unice.i3s.modalis.jSeduite.technical.news.internal;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;
import java.util.Date;
import java.text.SimpleDateFormat;


/** A simple data model to reify internal news
 * @author mosser
 */
public class News {

    private int id;
    private String author;
    private String target;
    private String content;
    private Date start;
    private Date end;
    private String title;

    public News() {}

    public News(DalResultSet rset) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        this.author = rset.getValue("author");
        this.target = rset.getValue("target");
        this.content =  rset.getValue("content");
        this.start = format.parse(rset.getValue("start"));
        this.end  = format.parse(rset.getValue("end"));
        this.title  = rset.getValue("title");
        this.id = Integer.parseInt(rset.getValue("id"));
    }
    
    public News(String author, String category, String content, Date start,
            Date end, String title) {
        this.author = author;
        this.target = category;
        this.content = content;
        this.start = start;
        this.end = end;
        this.title = title;
        this.id = 0;
    }

    public News(int id, String author, String category, String content,
            Date start, Date end, String title) {
        this.author = author;
        this.target = category;
        this.content = content;
        this.start = start;
        this.end = end;
        this.title = title;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }



}
