/**
 * This file is part of jSeduite::TVShow
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::TVShow is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::TVShow is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::TVShow; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Celine Auzias          [celine.auzias@gmail.com]
 * @contributor 2009     Sebastien Mosser       [mosser@polytech.unice.fr]
 **/
 
 function displayTVShows(anItem, cpt_prog){
	var content = "";
	
	content += "<img class=\"logo\" src=\"templates/"+template+"/img/tv_logo.png\" alt=\"\" align=\"left\">";

	content += "<p class=\"content_title\">";
	content += "Programme TV";
	content += "</p>";

	content += "<br><br>";
	
	/**    channel 1
	    1st evening part
	    2nd evening part
	    
	       channel 2
	    1st evening part
	    2nd evening part
	**/
	var curChannel;
	
	for(var ch=0; ch<2; ch++){
		curChannel = getNode("ns0:item", anItem)[cpt_prog++];	
		content += "<span class=\"tv_channel\"> <img src=\"templates/"+template+"/img/tv/"+getAttribute("channel", curChannel)+".png\" align=\"bottom\" class=\"channel_logo\"></span>";
		content += "<span class=\"tv_hour\">"+getTag("ns0:start", curChannel)+"</span>";
		content += "<span class=\"tv_title\">"+getTag("ns0:title", curChannel).substring(0,29);
		if (getTag("ns0:title", curChannel).length>28) content += "...";
		content += "</span><br>";
	
		curChannel = getNode("ns0:item", anItem)[cpt_prog++];
		content += "<span class=\"tv_hour\">"+getTag("ns0:start", curChannel)+"</span>";
		content += "<span class=\"tv_title\">"+getTag("ns0:title", curChannel).substring(0,29);
		if (getTag("ns0:title", curChannel).length>28) content += "...";
		content += "</span>";
		content+="<br><br>";
	}

	
	$('main_content').update(content);	
	

	if(cpt_prog/2 < nb_prog_tv)
		setTimeout(displayTVShows, 4000, anItem, cpt_prog);
	else{
		cur_info++;
		setTimeout("startDisplay()", 4000);
	}
}
