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


var apal_array = Class.create(jSeduiteTransformation, {
    initialize: function(treshold) { 
        this.treshold = treshold;
    },
    perform: function(xml) {
        var dispatch = this.dispatch(getNode("ns0:item", xml));
        var promos = dispatch["keys"].sort(function (a,b){ return a > b; });
        var values = dispatch["values"];
        var result = new Array();
        for(var i = 0; i < promos.length; i++)
            result.push(this.buildAPromo(promos[i], values[promos[i]]));
        return result;
    },
    buildAPromo: function(name, elements) {
    	var screen = "";
        screen += "<img class=\"logo\" src=\"templates/_logos/apal.png\" alt=\"\" align=\"left\">";
        var items = elements.sort(function (a,b) {
            return  new Number(getTag("counter",a)) < new Number(getTag("counter",b));
        });
        screen += "<p class=\"content_title\"> &nbsp; Absences: Top " + this.treshold + " (" + name + ")";
        screen += "</p>";
        screen += "<div class=\"clearDiv\">&nbsp;</div>";
        screen += "<table class=\"apal\">";
        for (var i = 0; i < this.treshold && i < items.length; i++) {
            screen +=(i%2==0)?"<tr class=\"odd\">":"<tr class=\"even\">";
            screen += "<td> " + (i+1) + ".</td>"
            screen +="<td>";
            screen +=getTag("firstname",items[i])+" ";
            screen +=getTag("lastname", items[i]);
            screen +="</td>";
            screen +="<td class=\"tdRight\">";
            screen +="("+getTag("counter", items[i])+")";
            screen +="</td>";
            screen +="</tr>";
        }
        screen += "</table>";
        return screen;
    },
    dispatch: function(nodes) {
        var promos = new Array();
        var keys = new Array();
        for(var i = 0; i < nodes.length; i++) {
            var aPromo = getTag("promo", nodes[i]);
            if ( null == promos[aPromo]) {
                keys.push(aPromo);
                promos[aPromo] = new Array();
            }
            promos[aPromo].push(nodes[i]);
        }
        var result = new Array();
        result["values"] = promos;
        result["keys"] = keys;
        return result;
    }
});