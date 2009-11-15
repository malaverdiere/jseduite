/**
 * This file is part of jSeduite::initVar
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::initVar is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::initVar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::initVar; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Celine Auzias          [celine.auzias@gmail.com]
 * @contributor 2009     Sebastien Mosser       [mosser@polytech.unice.fr]
 **/
 
var template = 'polytech';
 
var xml_to_read = 'proxy.jsp';

var user_logged;
var information_xml;

var delay_img = 3000;
var speed_scrolling = 15;
var nb_prog_tv = 6;
var google_img = ".ggpht.com";

var days = new Array();
days["MON"]="Lundi";
days["TUE"]="Mardi";
days["WED"]="Mercredi";
days["THU"]="Jeudi";
days["FRI"]="Vendredi";
days["SAT"]="Samedi";
days["SUN"]="Dimanche";

var levels = new Array();
levels["Régularisation"] = "img/level2.png";
levels["Urgent"] = "img/level3.png";
