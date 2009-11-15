/**
 * This file is part of jSeduite::log
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::log is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::log is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::log; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Celine Auzias          [celine.auzias@gmail.com]
 * @contributor 2009     Sebastien Mosser       [mosser@polytech.unice.fr]
 **/
 
function showLogs(){
		if ($("logs").style.display == 'block')
			$("logs").style.display = 'none';
		else
			$("logs").style.display = 'block';
}


function addLog(aLog){
	var stamp = buildStamp();
	$("logUL").innerHTML += "<li>["+stamp+"] "+aLog+"</li>";
}

function clearLogs() {
    $("logUL").update("");
}

function buildStamp() {

    var now = new Date();
    var stamp = now.getFullYear() + "/";
    stamp += (now.getMonth() < 9 ? "0" + (now.getMonth()+1): (now.getMonth()+1)) + "/";
    stamp += (now.getDate() < 10 ? "0" + now.getDate(): now.getDate()) + " ";
    stamp += (now.getHours() < 10 ? "0" + now.getHours(): now.getHours()) + ":";
    stamp += (now.getMinutes() < 10 ? "0" + now.getMinutes(): now.getMinutes()) + ":";
    stamp += (now.getSeconds() < 10 ? "0" + now.getSeconds(): now.getSeconds());
    return stamp;
}
