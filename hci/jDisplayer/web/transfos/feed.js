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


var feedHandler = Class.create(jSeduiteTransformation, {
    perform: function(xml) {
        var content = "";
        content += "<div id=\"info_logo\" class=\"feed_logo\"></div>";
        content += "<p class=\"title\">";
        content += truncate(getTag("author", xml),35);
        content += "</p>";
        content += "<div class=\"clearDiv\">&nbsp;</div>";
        content += "<span class=\"feed_title\">";
        content += getTag("title", xml);
        content += "</span>";
        content += "<p class=\"feed_content\">";
        content += truncate(unescape(getTag("content", xml)),90);
        content += "</p>";
        return [content];
    }
});

var feedHandlerTitleOnly = Class.create(jSeduiteTransformation, {
    perform: function(xml) {
        var content = "";
        content += "<div id=\"info_logo\" class=\"feed_logo\"></div>";
        content += "<p class=\"title\">";
        content += truncate(getTag("author", xml),35);
        content += "</p><br /><br />";
        content += "<div class=\"clearDiv\">&nbsp;</div>";
        content += "<span class=\"feed_title\">";
        content += getTag("title", xml);
        content += "</span>";
        return [content];
    }
});
