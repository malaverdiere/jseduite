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

var SPEED_DATE = 1000*60;
var timer_date;

function init_date(){
    day_date();
    timer_date = setInterval("day_date()",SPEED_DATE);
}

function clearTimer(){
    clearInterval(timer_date);
}


function day_date(){
    var now = new Date();
    var h = now.getHours();
    var m = now.getMinutes();
    if(h<10) h = '0'+h;
    if(m<10) m = '0'+m;
    var date = h+'h'+m+'';
    document.getElementById("date_time").innerHTML = date;
}


