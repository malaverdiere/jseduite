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

var breakingNews = Class.create(jSeduiteTransformation, {
    perform: function(xml) {
        var content = "";
        var now = new Date();
        var stamp = getTag("stamp", xml);
        var dayBN = stamp.substring(8,10);
        var monthBN = stamp.substring(5,7);
        var stampBN = "";
        if(now.getDate() == dayBN && now.getMonth() == monthBN)
            stampBN = stamp.substring(11,13)+"h"+stamp.substring(14,16);
        else
            stampBN = dayBN+"/"+monthBN;
        content += "<span class=\"break_news_date\" id=\"bn_date\">"+stampBN+"</span>";
        content += "<span class=\"break_news_content\" id=\"bn_content\">: "+getTag("content", xml)+" </span>";
        content += "<span class=\"break_news_author\" id=\"bn_author\">("+getTag("author", xml)+") - </span>";
        return [content];
    }
}); 