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
 **/

// creating a template.
var noel = new jSeduiteTemplate("noel");
// declaring info handlers
noel.declare("twitter",        new scrollingTwitter(),   0);
noel.declare("breaking_news",  new breakingNews(),       0);
noel.declare("absences_profs", new absenceHandler(),     5);
noel.declare("picture_albums", new pictAlbumTransfo(),   5);
noel.declare("tv_shows",       new tv_shows_array(6),    5);
noel.declare("weather",        new weatherTransfo(),     5);
noel.declare("image_scraper",  new imageScrapTransfo(),  5);
noel.declare("apal",           new apal_array(5),        5);
noel.declare("hyperloc",       new hyperLocHandler(7),  10);
noel.declare("internal_news",  new internalNews(),       5);
noel.declare("feed_reader",    new feedHandler(),        5);
noel.declare("stud_summon",    new studSummon(),         5);
noel.declare("calendar",       new iCalHandler(),        5);
noel.declare("timetable",      new edtHandler(1),        5);
// dispatching the information kinds in the display loops
noel.dispatch = function(k) {
    if ("breaking_news" == k || "twitter" == k)
        return "scroll";
    else
        return "main";
};
// setting the template
engine.setTemplate(noel);



