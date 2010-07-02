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
irsam.declare("twitter",        new scrollingTwitter(),   0);
irsam.declare("breaking_news",  new breakingNews(),       0);
irsam.declare("absences_profs", new absenceHandler(),     5);
irsam.declare("picture_albums", new pictAlbumTransfo(),   5);
irsam.declare("weather",        new closeWeatherTransfo(), 5);
irsam.declare("image_scraper",  new imageScrapTransfo(),  5);
irsam.declare("internal_news",  new internalNews(),       6);
irsam.declare("feed_reader",    new feedHandler(),        7);
irsam.declare("calendar",       new iCalHandler(),        6);
//irsam.declare("clock",          new clock(),              5);
// dispatching the information kinds in the display loops
irsam.dispatch = function(k) {
    if ("breaking_news" == k || "twitter" == k)
        return "scroll";
    else
        return "main";
};
// setting the template
engine.setTemplate(irsam);

//we add new ccs styles for turning CSS
engine.addCSS("templates/irsam/style2.css");
//engine.addCSS("templates/irsam/style3.css");
//engine.addCSS("templates/irsam/style4.css");


