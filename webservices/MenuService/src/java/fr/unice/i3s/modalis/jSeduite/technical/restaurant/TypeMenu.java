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
 * @author 2010 Desclaux Christophe[desclaux@polytech.unice.fr]
**/
package fr.unice.i3s.modalis.jSeduite.technical.restaurant;


//TODO: poubelle

public class TypeMenu {
    private String time;
    private String typeMenu;

    public TypeMenu() {}

    public TypeMenu(String time,String type) {
        this.time = time;
        this.typeMenu = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTypeMenu(){
        return typeMenu;
    }

    public void setTypeMenu(String type){
        this.typeMenu = type;
    }
}
