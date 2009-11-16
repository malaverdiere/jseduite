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


var studSummon = Class.create(jSeduiteTransformation, {
    initialize: function() {
        this.levels = new Array();
        this.levels["Information"] = "level1.png";
        this.levels["Regularisation"] = "level2.png";
        this.levels["Urgent"] = "level3.png";
        this.levels["Immediatement"] = "level3.png";
    },
    perform: function(xml) {
        var content = "";
        content += "<img class=\"logo\" src=\"templates/_logos/summons.png\" alt=\"\" align=\"left\">";
        content += "<p class=\"content_title\"> &nbsp; Convocation </p>";
        content += "<span class=\"sum_target\">";
        content += "<img src=\"templates/_img/summons/"+this.levels[getTag("level", xml)]+"\" alt=\"\">";
        content += "</span>";
        content += "<br><br>";
        content += "<p class=\"sum_content\">";
        content += getTag("student", xml)+"<br>("+getTag("promo", xml)+")";
        content += "</p>";
        content += "<p class=\"sum_content\">";
        content += "<span class=\"blue\">Convoqué par :</span>";
        content += "</p>";
        content += "<p class=\"sum_content\">";
        content += getTag("owner", xml);
        content += "</p>";
        var stampSum = getTag("date", xml);
        content += "<p class=\"sum_date\">";
        content += "(publi&eacute; le "+ stampSum.substring(8,10) + "/" + stampSum.substring(5,7) + " &agrave; " + stampSum.substring(11,13) +"h"+ stampSum.substring(14,16) + ")";
        content += "</p>";
        return [content];
    }
});