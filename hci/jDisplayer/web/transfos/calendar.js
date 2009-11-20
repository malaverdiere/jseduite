6/**
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
 * @author      Main     Sebastien Mosser       [mosser@polytech.unice.fr]
 * @contributor 2009     Celine Auzias          [celine.auzias@gmail.com]
 **/


var iCalHandler = Class.create(jSeduiteTransformation, {
    perform: function(xml) {
        var start = buildDateFromStamp(getTag("start", xml));
        var end = buildDateFromStamp(getTag("end", xml));

        var now = new Date();

        if ( end <= now)
            return [];
        
       	var content = "";
        content += "<div id=\"info_logo\" class=\"calendar_logo\"></div>";
        content += "<p class=\"title\"> &nbsp; ";
        content += truncate(getTag("summary", xml),25);
        content += "</p>";
        content += "<div class=\"clearDiv\">&nbsp;</div>";
        
        content += "<p class=\"huge\">";
        var dateCl = ((start < now && now < end)? "error": "emphasize");
        content += "<span class=\""+dateCl+"\">" + dateToString(start);
        content += " &rarr; "+ dateToString(end) + "</span>";
        content += "<br></p>";
        content += "<p class=\"large\">";
        if ("" != getTag("location", xml)) {
            content += "("+getTag("location", xml)+")"
        } else { content += "Lieu indeterminé !"; }
        content += "<br></p>";
        content += "<p class=\"huge\">";
        if ("" != getTag("description", xml)) {
            content += truncate(getTag("description", xml),75);
        } else { content += "Pas de description !"; }
        content += "<br></p>";
        content += "</p>";
        content += "</center>";
        return [content]; 
    }
}); 