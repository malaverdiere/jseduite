/**
 * This file is part of jSeduite::studSummon
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::studSummon is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::studSummon is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::studSummon; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Celine Auzias          [celine.auzias@gmail.com]
 * @contributor 2009     Sebastien Mosser       [mosser@polytech.unice.fr]
 **/
 
 function displayStudSummon(anItem){
	var content = "";
	
	content += "<img class=\"logo\" src=\"templates/"+template+"/img/sum_logo.png\" alt=\"\" align=\"left\">";
	
	content += "<p class=\"content_title\">";
	content += "Convocation";
	content += "</p>";
	
	content += "<span class=\"sum_target\">";
	content += "<img src=\"templates/"+template+"/"+levels[getTag("level", anItem)]+"\" alt=\"\">";
	content += "</span>";
	
	content += "<br><br>";
	
	content += "<p class=\"sum_content\">";
	content += getTag("student", anItem)+"<br>("+getTag("promo", anItem)+")";
	content += "</p>";
	
	content += "<p class=\"sum_content\">";
	content += "<span class=\"blue\">Convoqué par :</span>";
	content += "</p>";
	
	content += "<p class=\"sum_content\">";
	content += getTag("owner", anItem);
	content += "</p>";
	
	var stampSum = getTag("date", anItem);
	content += "<p class=\"sum_date\">";
	content += "(publi&eacute; le "+ stampSum.substring(8,10) + "/" + stampSum.substring(5,7) + " &agrave; " + stampSum.substring(11,13) +"h"+ stampSum.substring(14,16) + ")";
	content += "</p>";
	
	$('main_content').update(content);
	
	cur_info++;
	setTimeout("startDisplay()", 4000);

}