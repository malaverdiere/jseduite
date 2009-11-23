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
 * @author      Main     Sebastien Mosser       [mosser@polytech.unice.fr]
 * @contributor 2009     Celine Auzias          [celine.auzias@gmail.com]
 **/


var hyperLocHandler = Class.create(jSeduiteTransformation, {
    initialize: function (delta) {
      this.delta = delta;
    },
    perform: function(xml) {
        var raws = $A(getNode("item", xml));
        var filtered = raws.filter(function(e) {return "??" != getTag("teacher",e);});
        var items = filtered.sort(function(a,b) {
            return getTag("start",a) < getTag("start",b) ;
        }); 
        return this.buildScreens(items);
    },
    buildScreens: function(items) {  
        var slices = items.eachSlice(this.delta);
        var result = new Array();
        for (var i = 0; i < slices.length; i++) {
            result.push(this.buildAScreen(slices[i]));
        }
        return result;
    },
    buildAScreen: function(elements) {
        var content = "";
        content += "<div id=\"info_logo\" class=\"hyperloc_logo\"></div>";
        content += "<p class=\"title\"> &nbsp; Localisation des Enseignants";
        content += "</p>";
        content += "<table class=\"hyperloc\">"
        for(var i = 0; i < elements.length; i++) {
            content += this.transformAnElement(elements[i],(i%2==0?"odd":"even"));
        }
        content += "</table>";
        return content;
    },
    transformAnElement: function(e, css) {
        var result = "<tr class=\""+css+"\">";
        var start = buildDateFromStamp(getTag("start",e));
        var end = buildDateFromStamp(getTag("end",e));
        var now = new Date();
        result += "<td>" + getTag("teacher",e) + "</td>";
        var startedCl = (now <= start? "error": "");
        result += "<td class=\"tdCenter\"><span class=\""+startedCl+"\">" + dateToString(start) + " ";
        result += " &rarr; " + dateToString(end) + "</span></td>";
        var raw_rooms = getTags("rooms", e);
        var rooms = raw_rooms.map(function(elem) {
            return elem.split(" ")[0];
        });
        result += "<td class=\"tdCenter\">" + rooms + "</td>";
        result += "</tr>";
        return result;
    }
});
