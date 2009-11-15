/**
 * This file is part of jSeduite::Twitter
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::Twitter is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::Twitter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::Twitter; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 **/
package fr.unice.i3s.modalis.jSeduite.technical.messaging.twitter;

/** DataStructure to represent a Tweet
 * @author mosser
 */
public class Tweet {

    private String author;
    private String date;
    private String content;

    public Tweet() {}
    
    public Tweet(String author, String date, String content) {
        this.author = author;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
