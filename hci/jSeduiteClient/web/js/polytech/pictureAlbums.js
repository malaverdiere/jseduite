/**
 * This file is part of jSeduite::pictureAlbums
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::pictureAlbums is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::pictureAlbums is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::pictureAlbums; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Celine Auzias          [celine.auzias@gmail.com]
 * @contributor 2009     Sebastien Mosser       [mosser@polytech.unice.fr]
 **/
 
 function displayPicturesAlbums(anItem){
	var content = "";
	var isPicasa = false;
	
	content += "<img class=\"logo\" src=\"templates/"+template+"/img/img_logo.png\" alt=\"\" align=\"left\">";
	
	content += "<p class=\"content_title\">";
	content += getTag("ns0:name", anItem);
	content += "</p>";

	content += "<center><div id=\"current_img\"></div></center>";
	content += "<div id=\"loadingImg\"><img src=\"templates/"+template+"/img/ajax-loader.gif\" alt=\"...\" width=\"60\"></div>";
	
	$('main_content').update(content);
	
	if(getTag("ns0:repository", anItem) == "picasa")
		isPicasa = true;
	
	var pictures_albums = new Array();
	var pictureStart = getNode("ns0:picture_star", anItem)[0];
	for(var l=0; l<getNode("ns0:item", pictureStart).length; l++){
		var imgName = getNode("ns0:item", pictureStart)[l].textContent;
		var slash = imgName.lastIndexOf('/', imgName.length);
		pictures_albums.push(imgName.substring(0, slash)+(isPicasa?"/s800":"")+imgName.substring(slash, imgName.length));
	}

	affiche_next(pictures_albums, 0); 	

}