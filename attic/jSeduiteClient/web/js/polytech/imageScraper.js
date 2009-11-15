/**
 * This file is part of jSeduite::imageScraper
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::imageScraper is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::imageScraper is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::imageScraper; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Celine Auzias          [celine.auzias@gmail.com]
 * @contributor 2009     Sebastien Mosser       [mosser@polytech.unice.fr]
 **/
 
 function displayImageScraper(anItem){

	var content = "";
	var isPicasa = false;
	
	content += "<img class=\"logo\" src=\"templates/"+template+"/img/img_logo.png\" alt=\"\" align=\"left\">";
	
	content += "<p class=\"content_title\">";
	content += "Folksonomie";
	content += "</p>";
	
	content += "<center><div id=\"current_img\"></div></center>";
	content += "<div id=\"loadingImg\"><img src=\"templates/"+template+"/img/ajax-loader.gif\" alt=\"...\" width=\"60\"></div>";
	
	$('main_content').update(content);
	
	var images_scraper = new Array();
	for(var k=0; k<getNode("ns0:item", anItem).length; k++){
		var imgName = getNode("ns0:item", anItem)[k].textContent;
		// google 
		if(imgName.indexOf(google_img, 0) != -1)
			isPicasa = true;
		var slash = imgName.lastIndexOf('/', imgName.length);
		images_scraper.push(imgName.substring(0, slash)+(isPicasa?"/s800":"")+imgName.substring(slash, imgName.length));
	}

	affiche_next(images_scraper, 0);

}