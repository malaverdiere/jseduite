/**
 * This file is part of jSeduite::absencesProfs
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::absencesProfs is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::absencesProfs is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::absencesProfs; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Celine Auzias          [celine.auzias@gmail.com]
 * @contributor 2009     Sebastien Mosser       [mosser@polytech.unice.fr]
 **/
 
 function displayAbsencesProfs(anItem){
	var content = "";
	
	content += "<img class=\"logo\" src=\"templates/"+template+"/img/abs_logo.png\" alt=\"\" align=\"left\">";

	content += "<p class=\"content_title\">";
	content += "Absence du personnel";
	content += "</p>";
	
	content += "<div class=\"clearDiv\">&nbsp;</div>";
	
	content += "<p class=\"abs_teacher\">";
	content += getTag("teacher", anItem);
	content += "<br></p>";
	
	content += "<p class=\"abs_teacher\">";
	content += "<span class=\"blue\">sera absent(e)</span>";
	content += "</p>";
	
	var stampFrom = getTag("from", anItem);
	var stampTo = getTag("until", anItem);
	content += "<p class=\"abs_dates\">";
	content += "<span class=\"blue\">du</span> "+stampFrom.substring(8,10)+"/" + stampFrom.substring(5,7) + " " + stampFrom.substring(11,13) +"h"+ stampFrom.substring(14,16);
	content += "<span class=\"blue\"> au</span> "+stampTo.substring(8,10)+"/" + stampTo.substring(5,7) + " " + stampTo.substring(11,13) +"h"+ stampTo.substring(14,16);
	content += "<br></p>";
		
	content += "<p class=\"abs_reason\">";
	content += "("+getTag("reason", anItem)+")";
	content += "</p>";
	
	content += "</center>";

	$('main_content').update(content);	
	
	cur_info++;
	setTimeout("startDisplay()", 4000);

}
