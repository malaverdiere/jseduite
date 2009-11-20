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
        for(var i = 0; i < lectures.length; i = i+3) {
            screens.push(this.buildScreen(lectures[i], lectures[i+1], lectures[i+2]));
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
    buildScreen: function(first, second, third) {
        var content = "";
        content += "<div id=\"info_logo\" class=\"timetable_logo\"></div>";
        content += "<p class=\"title\"> &nbsp; ";
        content += getTag("promo", first);
        content += "</p>";
        content += this.build(first);
        content += this.build(second);
        content += this.build(third);
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
        var startedCl = ((from <= now && now < to)? "error": "emphasize");
		content +="<td><span class=\""+startedCl+"\">"+dateToString(from)+" &rarr; "+dateToString(to)+"</span></td>";
		content +="<td class=\"tdCenter\"> </td>";
        var raw_rooms = getTags("rooms", elem);
        var rooms = raw_rooms.map(function(e) { 
            var id = e.split(" ")[0] + " ";
            if (e.indexOf("Templ", 0) != -1) {
                return id + "Tpl.";
            } else {
                return id + "Luc.";
            }
        });
		content +="<td class=\"tdRight\"><span class=\""+startedCl+"\">"+rooms +"</span></td>";
		content +="</tr>";
		content += "</table>";
        return content;
    }
});


/**
 	var content = "";

	content += "<img class=\"logo\" src=\"templates/"+template+"/img/edt_logo.png\" alt=\"\" align=\"left\">";

	allCourses = getNode("ns0:item", anItem);

	// fait pour Cycle Prépa 1A uniquement

	content += "<p class=\"content_title\">";
	content += getTag("promo", allCourses[0]);
	content += "</p>";

	content += "<div class=\"clearDiv\">&nbsp;</div>";


	for(var c=0; c<3; c++){
		content += "<table class=\"timetable\">";
		content +="<tr class=\"odd\">";
		content +="<td colspan=\"2\">";
		content +=getTag("course", allCourses[c]).substring(0,20);
		if (getTag("course", allCourses[c]).length>20) content += "...";
		content +="</td>";
		content +="<td class=\"tdRight\">";
		content +=getTag("groups", allCourses[c]);
		content +="</td>";
		content +="</tr>";

		var stampB = getTag("start", allCourses[c]);
		var stampE = getTag("start", allCourses[c]);
		var stampCB = stampB.substring(11,13)+"h"+stampB.substring(14,16);
		var stampCE = stampE.substring(11,13)+"h"+stampE.substring(14,16);
		content +="<tr class=\"even\">";
		content +="<td><span class=\"blue\">"+stampCB+"</span></td>";
		content +="<td class=\"tdCenter\"><span class=\"blue\">"+stampCE+"</span></td>";
		content +="<td class=\"tdRight\"><span class=\"blue\">"+getTag("rooms", allCourses[c])+"</span></td>";
		content +="</tr>";

		content += "</table>";
	}
 */