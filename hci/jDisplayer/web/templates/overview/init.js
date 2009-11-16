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
var overview = new jSeduiteTemplate("overview");

// declaring info handlers
overview.declare("twitter",        new scrollingTwitter(),  0);
overview.declare("tv_shows",       new tv_shows_array(6),   1);
overview.declare("weather",        new weatherTransfo(),    1);
overview.declare("image_scraper",  new imageScrapTransfo(), 1);
overview.declare("apal",           new apal_array(5),       1);
overview.declare("picture_albums", new pictAlbumTransfo(),  1);
overview.declare("internal_news",  new internalNews(),      1);
overview.declare("breaking_news",  new breakingNews(),      0);
overview.declare("feed_reader",    new feedHandler(),       1);
overview.declare("stud_summon",    new studSummon(),        1);
overview.declare("absences_profs", new absenceHandler(),    1);
overview.declare("calendar",       new iCalHandler(),       1);
overview.declare("timetable",      new edtHandler(),        1);

// dispatching the information kinds in the display loops
overview.dispatch = function(k) {
    if ("alarm" == k)
        return "timer";
    else if ("breaking_news" == k || "twitter" == k)
        return "scroll";
    else
        return "main";
};

// setting the template
engine.setTemplate(overview);