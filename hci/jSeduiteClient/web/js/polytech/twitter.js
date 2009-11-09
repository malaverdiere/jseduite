/**
 * This file is part of jSeduite::twitter
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::twitter is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::twitter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::twitter; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Celine Auzias          [celine.auzias@gmail.com]
 * @contributor 2009     Sebastien Mosser       [mosser@polytech.unice.fr]
 **/
 
 function displayTwitter(anItem){
	var content_twit = "";
	
	var stamp = getTag("date", anItem);
	var stampTwit = stamp.substring(11,13)+"h"+stamp.substring(14,16);
	
	content_twit += "<img src=\"templates/"+template+"/img/twitter_logo.png\" alt=\"\" align=\"top\" height=\"70\">";
	content_twit += "<span class=\"break_news_date\" id=\"bn_date\">"+stampTwit+"</span>";
	content_twit += "<span class=\"break_news_content\" id=\"bn_content\">: "+getTag("content", anItem)+" </span>";
	content_twit += "<span class=\"break_news_author\" id=\"bn_author\">("+getTag("author", anItem)+") - </span>";

	return content_twit;
} 