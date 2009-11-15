/**
 * This file is part of jSeduite::Displayer
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::BreakingNews is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::Displayer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::Displayer; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Celine Auzias          [celine.auzias@gmail.com]
 * @contributor 2009     Sebastien Mosser       [mosser@polytech.unice.fr]
 **/


/* initTemplate() */
function initTemplate(template){
	this.template = template;
}



/** displayLogin() 
	first screen to enter login **/
function displayLogin(){
	$("main_content").style.display="none";	
	$("breaking_news").className="breaking_news_empty";	
	
	$("login_content").style.display="block";	
}

/** connexion()
	stock username and call initInformation.
	Called when clicking on button 'login' on login screen **/
function connexion(){
    user_logged = window.location.search.split("=")[1];
	displayInformations();
	initInformations();	
}

/** displayInformations() 
	display layout for all types of information **/
function displayInformations(){
	$("main_content").style.display="block";	
	$("breaking_news").className="breaking_news";		
	
	$("login_content").style.display="none";	
}

/** initInformations 
    get the xml file **/
function initInformations(){
	
	new Ajax.Request(xml_to_read,
	  {
	    method:'get',
	    parameters: {display: user_logged},
	    onSuccess: function(transport){
	      information_xml = transport.responseXML || "no response text";
	   //   alert("Success! \n\n" + information_xml);
	  	  dispatchInfo();
	    },
	    onFailure: function(){ 
	    	alert('Something went wrong...');
	    	$("main_content").innerHTML = "<span class=\"error\">Can not retrieve information!</span>"; 
	    }
	  });
  
}

	
	
/*--------------------------------------------------------------------------
 
   	              dispatchInfo()
   	              
   	              Constructs 3 tables from xml info.
					- 1 for breaking news + twit
					- 1 for all others info
					- 1 for alerts

--------------------------------------------------------------------------*/	

var infoArray = new Array();
var alertsArray = new Array();
var cur_info = 0;

function dispatchInfo(){
	var scrollingArray = new Array();
	
	var allResults = getNode("ns1:result", information_xml);
	var root = Element.extend(allResults[0]);
	var allItems = root.children;
	                                 
	// loop on all items
	for(var i=0; i<allItems.length; i++){
		myItem = allItems[i];
		
		if("breaking_news" == getSource(myItem) || "twitter" == getSource(myItem))
			scrollingArray.push(myItem);
		else
			infoArray.push(myItem);	
	}

	/** scrolling info **/
	displayScrollingInfo(scrollingArray);	
	
	/** content info **/
	cur_info = 0;
	startDisplay();
}



/*--------------------------------------------------------------------------
 
   	              startDisplay()
   	              
   	              Info types handled:
	   	             - internal_news
	   	             - image_scraper
	   	             - picture_albums
	   	             - stud_summon
	   	             - absences_profs
	   	             - tv_shows
	   	             - weather

--------------------------------------------------------------------------*/
function startDisplay(){

	if(cur_info < infoArray.length){
		anItem = infoArray[cur_info];

		switch(getSource(anItem)){
			case "internal_news" : 	displayInternalNews(anItem);
									break;
			case "image_scraper" : 	displayImageScraper(anItem);
									break;
			case "picture_albums" : displayPicturesAlbums(anItem);
									break;
			case "stud_summon" : 	displayStudSummon(anItem);
									break;
			case "absences_profs" : displayAbsencesProfs(anItem);
									break;
			case "tv_shows" : 		displayTVShows(anItem, 0);
									break;
			case "weather" : 		displayWeatherLive(anItem);
									break;						
			default :				break;	
		}
	}
	else{
		initInformations();
	}


}

/*--------------------------------------------------------------------------*/



/** displayScrollingInfo **/ 
function displayScrollingInfo(scrollingArray){
	var content_scrolling = "";
	
	for(var j=0; j<scrollingArray.length; j++){
	
		anItem = scrollingArray[j];
		
		if("breaking_news" == getSource(anItem))
			result = displayBreakingNews(anItem);
		if("twitter" == getSource(anItem))
			result = displayTwitter(anItem);
			
		content_scrolling += result;	
	}

	$('defile').update(content_scrolling);
	scrolling($('defile'), 900, 900);
}





