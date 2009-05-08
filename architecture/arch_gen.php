<?php
/**
 * This file is part of jSeduite::ArchGen
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::ArchGen is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::ArchGen is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::ArchGen; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Sebastien Mosser         [mosser@polytech.unice.fr]
 *
 **/


/******************************
 ** Configuration & Settings **
 ******************************/

define("SWIPL","/opt/local/bin/swipl");
define("NEATO","/sw/bin/neato");
define("FONT_PATH","/System/Library/Fonts");

/*********************************
 ** Do not edit after this line **
 *********************************/

error_reporting(E_STRICT | E_ALL);
$scripts = array("descriptor" => "jSeduite.pl",
		"transformation" => "graphviz.pl");
define("URL_PREFIX", "http://jseduite.googlecode.com/svn/trunk/architecture/");
define("PREFIX", URL_PREFIX ."description/");
define("CACHE_DELTA", 15*60); // => 15 minutes of cached data

/*******************
 ** Main function **
 *******************/
function main() 
{
  $target = (array_key_exists("target",$_GET) ? $_GET["target"] : null);
  $img = null;
  if (null == $target) {
    $img = generateFaultyImage("target ?");
  }
  else {
    synchronizeScripts();
    $img = generateImage($target);
  }
  echoImg($img);
  imagedestroy($img);
}

/**************************
 ** File synchronization ** 
 **************************/ 

// Retrieves all scripts from SVN repository according to cache delta
function synchronizeScripts() 
{
  global $scripts;
  foreach($scripts as $f)
    fileSync($f);
}

// Synchronize a given script file
function fileSync($fileName) 
{
  $exists = file_exists($fileName);
  $obsolete = (time() - filemtime("./".$fileName) > CACHE_DELTA);
  if (! $exists || $obsolete ) {
    file_put_contents($fileName, file_get_contents(PREFIX . $fileName));
  }
}

/************************
 ** Image Manipulation **
 ************************/

// Generate an image (GD resource) containing its $msg argument
function generateFaultyImage($msg) 
{
  $im  = imagecreatetruecolor(150, 30);
  $bgc = imagecolorallocate($im, 255, 0, 0); 
  $tc  = imagecolorallocate($im, 0, 0, 0);  
  imagefilledrectangle($im, 0, 0, 150, 30, $bgc);
  imagestring($im, 10, 5, 5, $msg, $tc);
  return $im;
}

// Generate an graph dependency image (GD resource) for a given target name
function generateImage($target) 
{
  global $scripts;
  $dot = target2dot($target);
  $png = dot2png($dot);
  $gd = imagecreatefrompng($png);
  unlink($png);
  return $gd;
}

// Return a given GD resource as a PNG content to the web browser
function echoImg($resource)
{
  header('Content-type: image/png');
  imagepng($resource);
}


/*********************
 ** Transformations **
 *********************/ 

// Generate dot (graphviz) code for a given target (relies on Prolog script)
function target2dot($t)
{
  $t = escapeshellarg($t);
  $target="dgraph([$t])";
  $target2dot = SWIPL . " -s graphviz.pl -g '[jSeduite], run($target), halt.'";
  $dotLines = array();
  exec($target2dot,$dotLines);
  $result = "";
  foreach($dotLines as $l)
    $result .= $l ."\n";
  return $result;
}

// Generate png file for a given dot code (relies on graphviz utility)
function dot2png($code) {
  $dot = tempnam("/tmp","wag_dot");
  file_put_contents($dot,$code);
  $png = tempnam("/tmp","wag_png");
  $cmd = NEATO . " -Gfontpath=".FONT_PATH." -Tpng -o $png $dot";
  exec($cmd);
  unlink($dot);
  return($png);
}

// Running main as business code.
main();
?>