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
 * @author 2020     Christophe Desclaux          [desclaux@polytech.unice.fr]
 **/


var ephemerides = Class.create(jSeduiteTransformation, {
    initialize: function() {
        this.clock = new Array();
        this.clock["day"]    = get_day();
        this.clock["time"]   = get_time();
        this.clock["month"]  = get_month();
        this.clock["year"]   = get_year();
        this.clock["UTCDate"]= get_UTCDate();
    },
    perform: function(xml) {
        var content = "";
        content += "<div id=\"clock_logo\" class=\"clock_logo\"></div>";
        content += "<p class=\"title\"> &nbsp; Date </p>";
        content += "<p class=\"clock_day\">";
        content += "<span class=\"emphasize\">Nous sommes le " + this.clock["day"] + " " + this.clock["UTCDate"] + " " + this.clock["month"] + " " + this.clock["year"] + "</span>";
        content += "</p>";
        content += "<p class=\"clock_time\">";
        content += "Il est " + this.clock["time"];
        content += "</p>";
        content += "<p class=\"clock_time\">";

        //TODO: add random choose of a name inephemerides
        items = getTags("item",xml);
        
        content += "Le saint du jour est " + items;
        content += "</p>";
        return [content];
    }
});