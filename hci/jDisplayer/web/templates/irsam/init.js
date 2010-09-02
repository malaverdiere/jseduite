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
 * @author      Main     Sebastien Mosser       [mosser@polytech.unice.fr]
 * @contributor 2009     Celine Auzias          [celine.auzias@gmail.com]
 * @contributor 2010     Christophe Desclaux    [desclaux@polytech.unice.fr]
 **/

// creating a template.
var irsam = new jSeduiteTemplate("template destiné à l'IRSAM");
// declaring info handlers
irsam.declare("twitter",        new scrollingTwitter(),           0);
irsam.declare("breaking_news",  new breakingNews(),               0);
irsam.declare("absences_profs", new absenceHandler(),            15);
irsam.declare("picture_albums", new fullScreenPictAlbumTransfo(), 7);
irsam.declare("weather",        new closeWeatherTransfo(),       15);
irsam.declare("image_scraper",  new imageScrapTransfo(),         15);
irsam.declare("internal_news",  new internalNews(),              16);
irsam.declare("feed_reader",    new feedHandler(),               15);
irsam.declare("calendar",       new iCalHandler(),               15);
irsam.declare("CalendarNSource",new iCalHandler(),               15);
irsam.declare("ephemeride",     new ephemerideOneSaint(),        15);
irsam.declare("menu",          new menusFullScreen(),            15);
irsam.declare("alarm",          new alarm(),                     15);
irsam.declare("break_screen",   new breakScreen(),               15);
irsam.declare("pictograms",      new pictogram(),                 7);
// dispatching the information kinds in the display loops
irsam.dispatch = function(k) {
    if ("alarm" == k || "break_screen"  == k)
        return "timer";
    else if ("breaking_news" == k || "twitter" == k)
        return "scroll";
    else
        return "main";
};
// setting the template
engine.setTemplate(irsam);

//we add new ccs styles for turning CSS
//engine.addCSS("templates/irsam/styleBleu.css");
engine.addCSS("templates/irsam/styleJaune.css");
//engine.addCSS("templates/irsam/styleRouge.css");

