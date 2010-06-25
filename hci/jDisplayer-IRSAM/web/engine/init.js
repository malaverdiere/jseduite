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

var speed_scrolling = 15;
/** PRIVATE CONFIG, DO NOT TOUCH **/
//var provider_url = null;
var engine = null;
var debug = null;


function jSeduiteInit() {
    init_date();
    // Reading URL arguments
    var args = new String(window.location).toQueryParams();
    var tpl    = (null == args["tpl"]     ? "default" : args["tpl"]);
    var screen = (null == args["display"] ? null      : args["display"]);
    var debug  = (null == args["dbg"]     ? "F"       : args["dbg"]);
    // start the engine according to such parameters
    startEngine(tpl, screen, debug);
}

function startEngine(template, screen, debug)
{
    var url = null;
    if ("F" != debug) { url = "samples/" + debug + ".xml"; }
    else { url = "proxy.jsp?display=" + screen; }
    engine =  new jSeduiteEngine(url, screen,template);
}
