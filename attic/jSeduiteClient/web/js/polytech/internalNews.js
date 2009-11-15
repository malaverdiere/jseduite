/**
 * This file is part of jSeduite::InternalNews
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::InternalNews is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::InternalNews is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::InternalNews; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Celine Auzias          [celine.auzias@gmail.com]
 * @contributor 2009     Sebastien Mosser       [mosser@polytech.unice.fr]
 **/
 
function displayInternalNews(anItem){

	var content = "";
	
	content += "<img class=\"logo\" src=\"templates/"+template+"/img/in_logo.png\" alt=\"\" align=\"left\">";
	
	content += "<p class=\"content_title\">";
	content += getTag("title", anItem);
	content += "</p>";
	
	content += "<div class=\"clearDiv\">&nbsp;</div>";
	
	content += "<p class=\"in_content\">";
	content += getTag("content", anItem);
	content += "</p>";
	
	content += "<span class=\"in_author\">";
	content += getTag("author", anItem);
	content += "</span>";
	
	var stampIN = getTag("start", anItem);
	content += "<span class=\"in_date\">";
	content += " ("+stampIN.substring(8,10)+"/"+stampIN.substring(5,7)+" "+stampIN.substring(11,13)+"h"+stampIN.substring(14,16)+")";
	content += "</span>";
	
	$('main_content').update(content);
	
	cur_info++;
	setTimeout("startDisplay()", 4000);
}