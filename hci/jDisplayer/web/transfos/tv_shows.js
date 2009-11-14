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


var tv_shows_array = Class.create(jSeduiteTransformation, {
    initialize: function(treshold) { this.treshold = treshold; },
    perform: function(xml) {
        var items = getNode("ns0:item", xml);
        var raw = new Array(); 
        var rawCpt = 0;
        for(var i = 0; i < items.length; i = i+2)
            raw[rawCpt++] = this.transformItems(items[i],items[i+1]);
        var resultCpt = 0;
        var result = new Array();
        for(var j = 0; (j < this.treshold && j < raw.length); j = j+2)
            result[resultCpt++] = this.buildAScreen(raw[j], raw[j+1]);
        return result;
    },
    transformItems: function(first,second) {
        var img = "<img src=\"templates/_img/tv/" + 
            getAttribute("channel", first)+
            ".png\" align=\"bottom\" class=\"channel_logo\">";
        var r = buildSpan(null, "tv_channel", img);
        return r + this.buildAnItem(first) + this.buildAnItem(second);
    },
    buildAnItem: function(item) {
        var r = buildSpan(null, "tv_hour", getTag("ns0:start", item));
        r += buildSpan(null,"tv_title", truncate(getTag("ns0:title",item),29));
        r += "<br />";
        return r;
    },
    buildAScreen: function(ch1, ch2) {
        var result = "";
        result += "<img class=\"logo\" src=\"templates/_logos/tv.png\" align=\"left\">";
        result += "<p class=\"content_title\"> &nbsp; Programme TV</p>";
        result += "<br /> <br />";
        result += ch1 + ch2;
        return result;
    }
});