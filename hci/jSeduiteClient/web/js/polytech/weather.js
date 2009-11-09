/**
 * This file is part of jSeduite::weather
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::weather is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::weather is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::weather; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Celine Auzias          [celine.auzias@gmail.com]
 * @contributor 2009     Sebastien Mosser       [mosser@polytech.unice.fr]
 **/
 
 /** Display weather live**/
function displayWeatherLive(anItem){
	var content = "";
	var weatherLive = getNode("ns0:live", anItem)[0];
	
	content += "<img class=\"logo\" src=\"templates/"+template+"/img/weat_logo.png\" alt=\"\" align=\"left\">";

	content += "<p class=\"content_title\">";
	content += "M&eacute;t&eacute;o";
	content += "</p>";
	
	content += "<br><center>";
	
	var reg=new RegExp("(.gif)", "g");
	
	content += "<span class=\"little blue\">";
	content += getTag("ns0:station", weatherLive)+"<br>";
	content += "</span>";
	content += "<span class=\"weat_today\">";
	content += "<img src=\"http://img.weather.weatherbug.com/forecast/icons/localized/250x210/en/trans/"+getTag("ns0:icon", weatherLive).replace(reg, ".png")+"\" alt=\"\" align=\"middle\"> ";
	content += "<b>"+getTag("ns0:temp", weatherLive)+"&deg;C</b><br>";
	content += "</span>";
	
	content += "<span class=\"weat_temp\">";
	content += "<span class=\"t_min\">"+getTag("ns0:minTemp", weatherLive)+"&deg;</span>/<span class=\"t_max\">"+getTag("ns0:maxTemp", weatherLive)+"&deg;</span>";
	content += "</span><br><br>";
	
	content += "<span class=\"weat_infos\">";
	content += "<span class=\"w_wind\"><img src=\"templates/"+template+"/img/vent.png\" alt=\"\" align=\"bottom\"> "+getTag("ns0:windSpeed", weatherLive)+" km/h ("+getTag("ns0:windDirection", weatherLive)+")</span> ";
	//content += "<span class=\"w_press\"><img src=\"img/pression.png\" alt=\"\" align=\"bottom\"> "+getTag("ns0:pressure", weatherLive)+"</span> ";
	content += "<span class=\"w_rain\"><img src=\"templates/"+template+"/img/pluie.png\" alt=\"\" align=\"bottom\"> "+getTag("ns0:rain", weatherLive)+"mm</span>";
	content += "</span>";
	
	content += "</center>";

	$('main_content').update(content);	
	setTimeout(displayWeatherForecast2Days, 4000, anItem);	
}

/** Display weather forecast 2 next days **/
function displayWeatherForecast2Days(anItem){
	var content = "";
	var forecast = getNode("ns0:forecast_star", anItem)[0];
	
	content += "<img class=\"logo\" src=\"templates/"+template+"/img/weat_logo.png\" alt=\"\" align=\"left\">";
	content += "<p class=\"content_title\">";
	content += "M&eacute;t&eacute;o";
	content += "</p>";
	
	content += "<br><br><center>";
	content += "<table class=\"weather\" width=\"100%\" height=\"380\">";
	content += "<tr>";

 	var reg=new RegExp("(.gif)", "g");
 	
	for(var t=0; t<2; t++){
		var curDay = getNode("ns0:item", forecast)[t];
		var iconImg = getTag("ns0:icon", curDay);
		content += "<td>";
		content += "<span class=\"weat_day\">"+days[getAttribute("day", curDay)]+"</span>";
		content += "<img class=\"weat_day_img\" src=\"http://img.weather.weatherbug.com/forecast/icons/localized/120x101/en/opaq/"+(iconImg.substring(iconImg.lastIndexOf('/', iconImg.length)+1, iconImg.length)).replace(reg, ".png")+"\" alt=\"\"><br>";
		content += "<span class=\"weat2_temp\"> "+getTag("ns0:minTemp", curDay)+"&deg;/"+getTag("ns0:maxTemp", curDay)+"&deg;C</span>";
		content += "</td>";
	}
				
	content += "</tr>";	
	content += "</table>";
	content += "</center>";

	$('main_content').update(content);
	
	setTimeout(displayWeatherForecast4Days, 4000, anItem);
}


/** Display weather forecast day+3 - day+7 **/
function displayWeatherForecast4Days(anItem){
	var content = "";
	var forecast = getNode("ns0:forecast_star", anItem)[0];
	
	content += "<img class=\"logo\" src=\"templates/"+template+"/img/weat_logo.png\" alt=\"\" align=\"left\">";

	content += "<p class=\"content_title\">";
	content += "M&eacute;t&eacute;o";
	content += "</p>";
	
	content += "<br><br><center>";
	content += "<table class=\"weather\" width=\"100%\" height=\"380\">";
	
	var reg=new RegExp("(.gif)", "g");
	
	for(var l=0; l<4; l=l+2){
		content += "<tr>";
		
		for(var c=2; c<4; c++){
			var curDay = getNode("ns0:item", forecast)[c+l];
			
			var iconImg = getTag("ns0:icon", curDay); 
			content += "<td>";
			content += "<span class=\"weat_day\">"+days[getAttribute("day", curDay)]+"</span>";
			content += "<span class=\"weat4_temp\"><img src=\"http://img.weather.weatherbug.com/forecast/icons/localized/120x101/en/opaq/"+(iconImg.substring(iconImg.lastIndexOf('/', iconImg.length)+1, iconImg.length)).replace(reg, ".png")+"\" alt=\"\" align=\"center\"> ";
			content += " "+getTag("ns0:minTemp", curDay)+"&deg;/"+getTag("ns0:maxTemp", curDay)+"&deg;</span>";
			content += "</td>";
		}
		
		content += "</tr>";
	}
	

	content += "</table>";
	content += "</center>";

	$('main_content').update(content);
	
	cur_info++;
	setTimeout("startDisplay()", 4000);
}
