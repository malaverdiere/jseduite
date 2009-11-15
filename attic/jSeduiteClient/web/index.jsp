<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!--  Celine AUZIAS 2009 c DOT line AT ymail DOT com  -->


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSeduite</title>

<meta name="Description" content="JSeduite" >

<!-- templates are localized in directory 'templates' -->
<link rel="stylesheet" type="text/css" href="templates/polytech/style.css" >

<script type="text/javascript" src="js/prototype-1.6.0.3.js"></script>
<script type="text/javascript" src="js/initVar.js"></script>
<script type="text/javascript" src="js/jseduite.js"></script>
<script type="text/javascript" src="js/util.js"></script>
<script type="text/javascript" src="js/log.js"></script>
<script type="text/javascript" src="js/date.js"></script>
<!-- js related to template -->
<script type="text/javascript" src="js/polytech/breakingNews.js"></script>
<script type="text/javascript" src="js/polytech/twitter.js"></script>
<script type="text/javascript" src="js/polytech/internalNews.js"></script>
<script type="text/javascript" src="js/polytech/studSummon.js"></script>
<script type="text/javascript" src="js/polytech/absencesProfs.js"></script>
<script type="text/javascript" src="js/polytech/weather.js"></script>
<script type="text/javascript" src="js/polytech/TVShow.js"></script>
<script type="text/javascript" src="js/polytech/imageScraper.js"></script>
<script type="text/javascript" src="js/polytech/pictureAlbums.js"></script>

</head>

<!-- to change template, set it with initTemplate -->
<body onload="initTemplate('polytech'); init_date(); connexion();" onKeypress="dispatch(event);">

<!-- date time -->
<div id="date_time"></div>

<!-- main content -->
<div id="main_content">

</div>

<!-- login content -->
<div id="login_content">
	<span id="error_login"></span>
	
	<div id="login">
	Username : <input type="text" id="username" size="32">
	<input type="button" id="login_btn" value="Login" onclick="connexion();">
	</div>
	
	<img src="img/logos_home.png" alt="">
</div>

<!-- breaking news -->
<div id="breaking_news" class="breaking_news">

	<div id="breaking_news_marquee" class="breaking_news_marquee">
	<span id="defile">&nbsp;</span>	
	</div>

	<div id="logo"></div>

</div>

<!-- logs -->
<div id="logs"><ul id="logUL"></ul></div>

</body>
</html>