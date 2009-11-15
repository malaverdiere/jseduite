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


var absenceHandler = Class.create(jSeduiteTransformation, {
    perform: function(xml) {
        var content = "";
        content += "<img class=\"logo\" src=\"templates/_logos/absence.png\" alt=\"\" align=\"left\">";
        content += "<p class=\"content_title\"> &nbsp; Absence du personnel </p>";
        content += "<div class=\"clearDiv\">&nbsp;</div>";
        content += "<p class=\"abs_teacher\">";
        content += getTag("teacher", xml);
        content += "<br></p>";
        content += "<p class=\"abs_teacher\">";
        content += "<span class=\"blue\">sera absent(e)</span>";
        content += "</p>";
        var stampFrom = getTag("from", xml);
        var stampTo = getTag("until", xml);
        content += "<p class=\"abs_dates\">";
        content += "<span class=\"blue\">du</span> "+stampFrom.substring(8,10)+"/" + stampFrom.substring(5,7) + " " + stampFrom.substring(11,13) +"h"+ stampFrom.substring(14,16);
        content += "<span class=\"blue\"> au</span> "+stampTo.substring(8,10)+"/" + stampTo.substring(5,7) + " " + stampTo.substring(11,13) +"h"+ stampTo.substring(14,16);
        content += "<br></p>";
        content += "<p class=\"abs_reason\">";
        content += "("+getTag("reason", xml)+")";
        content += "</p>";
        content += "</center>";
        return [content]; 
    }
});