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


var edtHandler = Class.create(jSeduiteTransformation, {
    initialize: function (delta) {
      this.delta = delta;
    },
    perform: function(xml) {
        var raws = this.ignore($A(getNode("item", xml)));
        var lectures = raws.sort(function(a,b) { return getTag("start",a) > getTag("start",b); });
        var screens = new Array();
        for(var i = 0; i < lectures.length; i = i+4) {
            screens.push(this.buildScreen(lectures[i], lectures[i+1], 
                                          lectures[i+2], lectures[i+3]));
        }
        return screens;
    },
    ignore: function(raw) {
        var d = this.delta;
        return raw.filter( function(e) {
            var start = buildDateFromStamp(getTag("start",e));
            var end = buildDateFromStamp(getTag("end",e));
            var now = new Date();
            var next = new Date(now);
            next.setHours(now.getHours() + d);
            return ((start <= now && now < end) || (now < start && start <= next));
        });
    },
    buildScreen: function(first, second, third, fourth) {
        var content = "";
        content += "<div id=\"info_logo\" class=\"timetable_logo\"></div>";
        content += "<p class=\"title\"> &nbsp; ";
        content += getTag("promo", first);
        content += "</p>";
        content += this.build(first);
        content += this.build(second);
        content += this.build(third);
        content += this.build(fourth);
        return content;
    },
    build: function(elem) {
        if (null == elem) 
            return "";
        var content = "";
        content += "<table class=\"timetable\">";
		content +="<tr class=\"odd\">";
        var now = new Date();
        var from = buildDateFromStamp(getTag("start",elem));
        var to = buildDateFromStamp(getTag("end",elem));
		content +="<td colspan=\"2\">";
		content += ""+truncate(getTag("course", elem),20)+"";
		content +="</td>";
		content +="<td class=\"tdRight\">";
		content +=getTags("groups", elem);
		content +="</td>";
		content +="</tr>";
		content +="<tr class=\"even\">";
        var startedCl = ((from <= now && now < to)? "alert": "emphasize");
		content +="<td><span class=\""+startedCl+"\">"+dateToString(from)+" &rarr; "+dateToString(to)+"</span></td>";
		content +="<td class=\"tdCenter\"> </td>";
        /** var raw_rooms = getTags("rooms", elem);
        var rooms = raw_rooms.map(function(e) { 
            var id = e.split(" ")[0] + " ";
            if (e.indexOf("Templ", 0) != -1) {
                return id + "Tpl.";
            } else {
                return id + "Luc.";
            }
        }); */
        var building = getTags("building", elem);
        var rooms = getTags("rooms", elem);
		content +="<td class=\"tdRight\"><span class=\""+startedCl+"\">"+ rooms + " " + building +"</span></td>";
		content +="</tr>";
		content += "</table>";
        return content;
    }
});
