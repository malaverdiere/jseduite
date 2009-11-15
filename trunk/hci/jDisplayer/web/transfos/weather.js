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


var weatherTransfo = Class.create(jSeduiteTransformation, {
    initialize: function() {
        this.days = new Array();
        this.days["MON"]="Lundi";
        this.days["TUE"]="Mardi";
        this.days["WED"]="Mercredi";
        this.days["THU"]="Jeudi";
        this.days["FRI"]="Vendredi";
        this.days["SAT"]="Samedi";
        this.days["SUN"]="Dimanche";
    },
    perform: function(xml) {
        var live = this.getLive(getNode("ns0:live", xml)[0]);
        var forecast = this.getForecast(getNode("ns0:forecast_star", xml)[0]);
        var result = new Array();
        return result.concat([live], forecast);
    },
    getLive: function(weatherLive) {
        var url = "http://img.weather.weatherbug.com/forecast/icons/localized/250x210/en/trans/";
        var content = "";
        content += "<img class=\"logo\" src=\"templates/_logos/weather.png\" alt=\"\" align=\"left\">";
        content += "<p class=\"content_title\"> &nbsp; M&eacute;t&eacute;o</p>";
	    content += "<br><center>";
	    var reg=new RegExp("(.gif)", "g");
        content += "<span class=\"little blue\">";
        content += getTag("ns0:station", weatherLive)+"<br>";
        content += "</span>";
        content += "<span class=\"weat_today\">";
        content += "<img src=\""+url+getTag("ns0:icon", weatherLive).replace(reg, ".png")+"\" alt=\"\" align=\"middle\"> ";
        content += "<b>"+getTag("ns0:temp", weatherLive)+"&deg;C</b><br>";
        content += "</span>";
        content += "<span class=\"weat_temp\">";
        content += "<span class=\"t_min\">"+getTag("ns0:minTemp", weatherLive)+"&deg;</span>/<span class=\"t_max\">"+getTag("ns0:maxTemp", weatherLive)+"&deg;</span>";
        content += "</span><br><br>";
        content += "<span class=\"weat_infos\">";
        content += "<span class=\"w_wind\"><img src=\"templates/_img/weather/vent.png\" alt=\"\" align=\"bottom\"> "+getTag("ns0:windSpeed", weatherLive)+" km/h ("+getTag("ns0:windDirection", weatherLive)+")</span> ";
        content += "<span class=\"w_rain\"><img src=\"templates/_img/weather/pluie.png\" alt=\"\" align=\"bottom\"> "+getTag("ns0:rain", weatherLive)+"mm</span>";
        content += "</span>";
        content += "</center>";
        return content;
    },
    getForecast: function(fcast) {
        return [ this.getClose(fcast), this.getFar(fcast) ];
    },
    getClose: function(forecast) {
        var content = "<img class=\"logo\" src=\"templates/_logos/weather.png\" alt=\"\" align=\"left\">";
        content += "<p class=\"content_title\"> &nbsp; M&eacute;t&eacute;o</p>";
        content += "<br><br><center>";
        content += "<table class=\"weather\" width=\"100%\" height=\"380\">";
        content += "<tr>";
        var reg=new RegExp("(.gif)", "g");
        for(var t=0; t<2; t++){
            var curDay = getNode("ns0:item", forecast)[t];
            var iconImg = getTag("ns0:icon", curDay);
            content += "<td>";
            content += "<span class=\"weat_day\">"+this.days[getAttribute("day", curDay)]+"</span>";
            content += "<img class=\"weat_day_img\" src=\"http://img.weather.weatherbug.com/forecast/icons/localized/120x101/en/opaq/"+(iconImg.substring(iconImg.lastIndexOf('/', iconImg.length)+1, iconImg.length)).replace(reg, ".png")+"\" alt=\"\"><br>";
            content += "<span class=\"weat2_temp\"> "+getTag("ns0:minTemp", curDay)+"&deg;/"+getTag("ns0:maxTemp", curDay)+"&deg;C</span>";
            content += "</td>";
        }
        content += "</tr>";
        content += "</table>";
        content += "</center>";
        return content;
    },
    getFar: function(forecast) {
        var content = "<img class=\"logo\" src=\"templates/_logos/weather.png\" alt=\"\" align=\"left\">";
        content += "<p class=\"content_title\"> &nbsp; M&eacute;t&eacute;o</p>";
        content += "<br><br><center>";
        content += "<table class=\"weather\" width=\"100%\" height=\"380\">";
        var reg=new RegExp("(.gif)", "g");
        for(var l=0; l<4; l=l+2){
            content += "<tr>";
            for(var c=2; c<4; c++){
                var curDay = getNode("ns0:item", forecast)[c+l];
                var iconImg = getTag("ns0:icon", curDay);
                content += "<td>";
                content += "<span class=\"weat_day\">"+this.days[getAttribute("day", curDay)]+"</span>";
                content += "<span class=\"weat4_temp\"><img src=\"http://img.weather.weatherbug.com/forecast/icons/localized/120x101/en/opaq/"+(iconImg.substring(iconImg.lastIndexOf('/', iconImg.length)+1, iconImg.length)).replace(reg, ".png")+"\" alt=\"\" align=\"center\"> ";
                content += " "+getTag("ns0:minTemp", curDay)+"&deg;/"+getTag("ns0:maxTemp", curDay)+"&deg;</span>";
                content += "</td>";
            }
            content += "</tr>";
        }
        content += "</table>";
        content += "</center>";
        return content;
    }
});