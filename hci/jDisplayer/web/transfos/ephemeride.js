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

var ephemerideHelper = Class.create({
     initialize: function() {
        this.clock = new Array();
        this.clock["day"]    = get_day();
        this.clock["time"]   = get_time();
        this.clock["month"]  = get_month();
        this.clock["year"]   = get_year();
        this.clock["UTCDate"]= get_UTCDate();
    },
    getClock: function(){
        return this.clock;
    },
    getAllSaints: function(names) {
        var content = "<p class=\"ephemerides_saints\">Les saints du jour sont:<ul>";
        for(var i=0;i<names.length;i=i+1){
            content += "<li>" + names[i] + "</li>";
        }
        content += "</p></li>";
        return content;
    },
    getOneSaint: function(names) {
        var content = "<div class=\"ephemerides_saints\">";
        idSaint = Math.floor(Math.random() * names.length);
        content += "Bonne fête " + names[idSaint] + "</div>";
        return content;
    }
});
var ephemeride = Class.create(jSeduiteTransformation, {

});
var ephemerideOneSaint = Class.create(jSeduiteTransformation, {
    perform: function(xml) {
        var helper = new ephemerideHelper();
        var content = "<span class=\"ephemerides\">";
        content += "<div class=\"ephemerides_title\">";
        content += helper.clock["day"] + " " + helper.clock["UTCDate"];
        content += " " + helper.clock["month"] + " " + helper.clock["year"];
        content +="</div>";
        content += "<div class=\"ephemerides_clock_time\">" + helper.clock["time"];
        content += "</div>";
        content += helper.getOneSaint(getTags("names",xml));
        content += "</span>";
        return [content];
    }
});

var ephemerideAllSaints = Class.create(jSeduiteTransformation, {
    perform: function(xml) {
        var helper = new ephemerideHelper();
        var content = "";
        content += "<div id=\"clock_logo\" class=\"clock_logo\"></div>";
        content += "<p class=\"title\"> &nbsp; Ephémérides </p>";
        content += "<span class=\"ephemerides\"><p class=\"clock_day\">";
        content += "<span class=\"emphasize\">Nous sommes le ";
        content += helper.clock["day"] + " " + helper.clock["UTCDate"];
        content += " " + helper.clock["month"] + " " + helper.clock["year"];
        content += "<span class=\"ephemerides_clock_time\", il est " + helper.clock["time"];
        content += "</span></p>";
        content += helper.getAllSaints(getTags("names",xml));
        return [content];
    }
});