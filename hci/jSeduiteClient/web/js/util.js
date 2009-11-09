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
 * @author      Main     Celine Auzias          [celine.auzias@gmail.com]
 * @contributor 2009     Sebastien Mosser       [mosser@polytech.unice.fr]
 **/
 
 
function dispatch(e) {
  	switch (e.which) {
  		case 108: showLogs(); break;
  		default: break;
  	}
}

/*--------------------------------------------------------------------------
 
   	              UTIL. FUNCTIONS
   	              
   	              getSource()
   	              getAttribute()
   	              getTag()
   	              getNode()

--------------------------------------------------------------------------*/
/** getSource() 
	return type of info of 'element' **/
function getSource(element) {
	for(var i = 0; i < element.attributes.length; i++) {
		if ("source" == element.attributes[i].name)
			return element.attributes[i].nodeValue;
	}
	return null;	
}

/** getAttribute()
	return value of 'attribute' of 'element' **/
function getAttribute(attribute, element) {
	for(var i = 0; i < element.attributes.length; i++) {
		if (attribute == element.attributes[i].name)
			return element.attributes[i].nodeValue;
	}
	return null;	
}
	
/** getTag()
	return value of 'tag' in 'element' **/	
function getTag(tag, element){
	return element.getElementsByTagName(tag)[0].textContent;
}
	
/** getNode()
	return 'node' in 'element' **/	
function getNode(node, element){
	return element.getElementsByTagName(node);
}	

/*--------------------------------------------------------------------------*/

/** scrolling()
 	from Mr N. de developpez.net
 	scrolled : element to scroll
 	psinit : initial position
 	pscrnt : current position
**/
function scrolling(scrolled, psinit, pscrnt) {
	
   	if (!scrolled) scrolled = $('defile');
	if (scrolled) {
      	if(pscrnt < ( - scrolled.offsetWidth) ){
        	pscrnt = psinit;
      	} 
      	else {
        	pscrnt+= -2; 
		}
   		scrolled.style.left = pscrnt+"px";
   	}
   	
   	setTimeout(scrolling, speed_scrolling, scrolled, psinit, pscrnt); 
} 


/** For diaporama display **/ 
function affiche_next(album, cpt_image){
	
	$("current_img").style.visibility = 'hidden';
	$("loadingImg").style.display='block';
	
	if (cpt_image>=album.length) {
		cur_info++;
		startDisplay();
	}
	else{
		$("current_img").style.visibility = 'hidden';
		$("current_img").innerHTML = "<img id=\"imgAlbum\" src=\""+album[cpt_image++]+"\" alt=\"\"> ";
		$("imgAlbum").onerror = function() { 
			addLog("img error: \n["+album[cpt_image-1]+"]");
			setTimeout(affiche_next,delay_img, album, cpt_image); }
		
		$("imgAlbum").onload = function() {
			$('loadingImg').style.display='none';
			$('current_img').style.visibility = 'visible';
			setTimeout(affiche_next,delay_img, album, cpt_image);
		}
	}
	
}