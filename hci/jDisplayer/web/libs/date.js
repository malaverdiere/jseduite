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
 * @contributor 2010     Christophe Desclaux    [desclaux@polytech.unice.fr]
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
    var date = get_time();
    document.getElementById("date_time").innerHTML = date;
}

function get_time(){
    var now = new Date();
    var h = now.getHours();
    var m = now.getMinutes();
    if(h<10) h = '0'+h;
    if(m<10) m = '0'+m;
    return h+'h'+m+'';
}

function get_year(){
    return (new Date()).getFullYear();
}

function get_month(){
    var month =  (new Date()).getMonth() + 1;
    switch (month){
        case 1: return "janvier";   break;
        case 2: return "février";   break;
        case 3: return "mars";      break;
        case 4: return "avril";     break;
        case 5: return "mai";       break;
        case 6: return "juin";      break;
        case 7: return "juillet";   break;
        case 8: return "août";      break;
        case 9: return "septembre"; break;
        case 10:return "octobre";   break;
        case 11:return "novembre";  break;
        case 12:return "décembre";  break;
    }
}

function get_day(){
    var d = (new Date()).getDay();
    return get_a_day(d);
}

function get_a_day(d){
    switch (d){
    case 0: return "dimanche";break;
    case 1: return "lundi";break;
    case 2: return "mardi";break;
    case 3: return "mercredi";break;
    case 4: return "jeudi";break;
    case 5: return "vendredi";break;
    case 6: return "samedi";break;
    }
}

function get_UTCDate(){
    return (new Date()).getUTCDate();
}


function buildDateFromStamp(stamp) {
    var year    = stamp.substring(0,4);
    var month   = (stamp.substring(5,7) - 1);
    var day     = stamp.substring(8,10);
    var hours   = stamp.substring(11,13);
    var minutes = stamp.substring(14,16);
    var secondes= stamp.substring(17,19);
    return new Date(year,month,day,hours,minutes,secondes);
}

function dateToString(aDate) {
    var h = "" + aDate.getHours();
    var m = "" + aDate.getMinutes()
    h = (h.length < 2? "0"+h: h);
    m = (m.length < 2? "0"+m: m);
    return h + "h" + m;
}
