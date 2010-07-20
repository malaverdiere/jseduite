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

import java.util.Date;

public class Menu {
    private Date date;
    private Course[] courses;
    private String typeMenu;

    public Menu() {}

    public Menu(Date date, Course[] courses) {
        this(date,courses,"déjeuner");
    }

    public Menu(Date date, Course[] courses,String type) {
        this.date = date;
        this.courses = courses;
        this.typeMenu = type;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTypeMenu(){
        return typeMenu;
    }

    public void setTypeMenu(String type){
        this.typeMenu = type;
    }
}
