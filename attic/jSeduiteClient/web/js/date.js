/**
 * C.line
 * celine DOT auzias AT gmail DOT com */



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
    //var s = now.getSeconds();
    //var j = now.getDate();
    //var mo = now.getMonth()+1;
    //var y = now.getFullYear();

    if(h<10) h = '0'+h;
    if(m<10) m = '0'+m;
    //if(s<10) s = '0'+s;
    //if(j<10) j = '0'+j;
    //if(mo<10) mo = '0'+mo;

    var date = h+'h'+m+'';
    document.getElementById("date_time").innerHTML = date;
}


