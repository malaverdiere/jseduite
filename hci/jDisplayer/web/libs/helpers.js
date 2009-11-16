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
        case 32: clearLogs(); break;
  		default: break;
  	}
}

function setLoadingState(){
    var loading = "<center><img src=\"templates/_img/loading-polytech.gif\" alt=\"\" /></center>";
    $('main').update("<br/> <br/> <br/><br/><br/>" + loading);
}

function removeLoadingState() {
    $('main').update("");
}

function loadCss(file)
{
    var css = new Element('link', { rel: "stylesheet", type: "text/css",
                          href: file });
    $$("head")[0].appendChild(css);
}

function loadJs(file)
{
    var js = new Element('script', { type: 'text/javascript',
                         src: file });
    $$("head")[0].appendChild(js);
}

function buildSpan(i, c, txt) {
    var anId = (null == i ? "" : "id=\""+i+"\"");
    var aClass = (null == c ? "": "class=\""+c+"\"");
    return "<span "+anId+" "+aClass+">"+txt+"</span>";
}


function truncate(str, length) {
    if (str.length > length)
        return str.substring(0,length) + "...";
    return str;
}

function imageHacking(imgName) {
    var google_img = ".ggpht.com";
    var isPicasa = false;
    if(imgName.indexOf(google_img, 0) != -1)
			isPicasa = true;
	var slash = imgName.lastIndexOf('/', imgName.length);
    return imgName.substring(0, slash) + (isPicasa? "/s800": "")
           + imgName.substring(slash, imgName.length);
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

function getTags(tag, element){
	return $A(element.getElementsByTagName(tag)).map(function(e) { 
        return e.textContent ;
    });
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

var scroll_timer = null;
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
   	
   	scroll_timer = setTimeout(scrolling, speed_scrolling, scrolled, psinit, pscrnt);
}

function stopScroll() {
    window.clearTimeout(scroll_timer);
} 
