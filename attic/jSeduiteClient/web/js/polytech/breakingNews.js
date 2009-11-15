/**
 * This file is part of jSeduite::breakingNews
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::breakingNews is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::breakingNews is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::breakingNews; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Celine Auzias          [celine.auzias@gmail.com]
 * @contributor 2009     Sebastien Mosser       [mosser@polytech.unice.fr]
 **/
 
 function displayBreakingNews(anItem){
	var content_breaking_news = "";
	
	var now = new Date();
	var stamp = getTag("stamp", anItem);
	var dayBN = stamp.substring(8,10);
	var monthBN = stamp.substring(5,7);
	
	if(now.getDate() == dayBN && now.getMonth() == monthBN)
		var stampBN = stamp.substring(11,13)+"h"+stamp.substring(14,16);
	else
		var stampBN = dayBN+"/"+monthBN;
	
	content_breaking_news += "<span class=\"break_news_date\" id=\"bn_date\">"+stampBN+"</span>";
	content_breaking_news += "<span class=\"break_news_content\" id=\"bn_content\">: "+getTag("content", anItem)+" </span>";
	content_breaking_news += "<span class=\"break_news_author\" id=\"bn_author\">("+getTag("author", anItem)+") - </span>";

	return content_breaking_news;
} 