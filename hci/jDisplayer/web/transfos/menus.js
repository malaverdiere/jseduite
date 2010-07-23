/**
 * This file is part of jSeduite::util
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::util is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::util is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::util; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Christophe Desclaux    [desclaux@polytech.unice.fr]
 **/

var menus = Class.create(jSeduiteTransformation, {
        perform: function(xml) {

        var date = getTag("date", xml);
        var content = "<span class=\"menus\">";
        content += "<span class=\"menus_title\">";
        content += getTag("typeMenu", xml)+ " à ";
        content +=  date.substring(11,13) + " heures</span>";

        content += "<div id=\"menus_content\" class=\"menus_content\">";
        var coursesNode = getNode("course",xml);
        
        var courses = new Array();
        for(var i=0; i <coursesNode.length;i++){
            var kind = getTag("kind",coursesNode[i]);
            var name = getTag("name",coursesNode[i]);
            if(courses[kind] == null) courses[kind] = name;
            else courses[kind] +="<br/> " + name;
        }
        content += courses["entree"] + "<br/>~~~<br/>";
        content += courses["plat"] + "<br/>~~~<br/>";
        content += courses["dessert"];


        content += "</div></span>";
        return [content];
    }
});