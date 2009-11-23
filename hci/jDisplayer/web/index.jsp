<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <title>jSeduite 1.0</title>
    <!-- Javascripts Librairies -->
    <script type="text/javascript" src="libs/prototype-1.6.0.3.js"></script>
    <script type="text/javascript" src="libs/log.js"></script>
    <script type="text/javascript" src="libs/date.js"></script>
    <script type="text/javascript" src="libs/helpers.js"></script>
    <script type="text/javascript" src="libs/diaporama.js"></script>
    <!-- jSeduite engine -->
    <script type="text/javascript" src="engine/loops.js"></script>
    <script type="text/javascript" src="engine/engine.js"></script>
    <script type="text/javascript" src="engine/template.js"></script>
    <script type="text/javascript" src="engine/transformation.js"></script>
    <script type="text/javascript" src="engine/init.js"></script>
    <!-- jSeduite transformations -->
    <script type="text/javascript" src="transfos/absence.js"></script>
    <script type="text/javascript" src="transfos/albums.js"></script>
    <script type="text/javascript" src="transfos/apal.js"></script>
    <script type="text/javascript" src="transfos/breaking_news.js"></script>
    <script type="text/javascript" src="transfos/calendar.js"></script>
    <script type="text/javascript" src="transfos/edt.js"></script>
    <script type="text/javascript" src="transfos/feed.js"></script>
    <script type="text/javascript" src="transfos/hyperloc.js"></script>
    <script type="text/javascript" src="transfos/internal.js"></script>
    <script type="text/javascript" src="transfos/scrap.js"></script>
    <script type="text/javascript" src="transfos/summons.js"></script>
    <script type="text/javascript" src="transfos/tv_shows.js"></script>
    <script type="text/javascript" src="transfos/twitter.js"></script>
    <script type="text/javascript" src="transfos/weather.js"></script>
    <!-- StyleSheets -->
    <link rel="stylesheet" type="text/css" href="templates/common.css" >
  </head>
  <body onload="jSeduiteInit(); engine.run();" onKeypress="dispatch(event);">
    <div id="date_time"></div>
    <div id="main">
        
    </div>
    <div id="scrolling" class="scrolling_content">
	  <div id="scrolling_marquee" class="scrolling_marquee">
	    <span id="defile">&nbsp;</span>
	  </div>
	  <div id="mini_logo"></div>
    </div>
    <div id="logs"><ul id="logUL"></ul></div>
  </body>
</html>