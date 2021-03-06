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

var Diaporama = Class.create({
   initialize: function(imgs, delta, cb, fullScreen) {
       this.delta = delta;
       this.images = imgs;
       this.callback = cb;
       this.fullScreen = fullScreen;
   },
   prepare: function() {
       var content = "";
       content += "<center><div id=\"diapo_content\"></div></center>";
       content += "<center><div id=\"diapo_loading\"></div></center>";
       return content;
   },
   start: function() {
       // this function MUST be externalized, as window.setTimeout drop the
       // lambda context and use "window" itself instead (real crap +o().
       this.idx = 0;
       this.next(0);
   },
   next: function(curCSS) {
       replaceTurningCss(engine.getTurningCSS(curCSS++));
       if ( this.idx >= this.images.length) { this.callback(); return; }
       var img = this.images[this.idx];
       if(curCSS >= engine.getAmountCSS()){
           this.idx++;
           curCSS = 0;
       }
       $('diapo_content').style.visibility = 'hidden';
       $('diapo_loading').style.display = 'block';
       $('diapo_content').update("<img id=\"diapo_img\" src=\""
                                  + img + "\" alt=\"\"> ");
       // What to do when an img error happens (404, ...)
       $('diapo_img').onerror = function(){
           addLog("diaporama error: ["+ img +"]");
           this.next(curCSS);
       }
       var delta = this.delta;
       var fullScreen = this.fullScreen;
        var self = this;
       // What to do when the image is successfully loaded
       $('diapo_img').onload = function() {
            $('diapo_loading').style.display = 'none';
            $('diapo_content').style.visibility = 'visible';

            if(fullScreen == "true"){
                var screenHeight = window.innerHeight - 22;
                var screenWidth = window.innerWidth - 22;
                var newHeight, newWidth;
                var ratioScreen = screenHeight / screenWidth;
                var ratioPicture = $('diapo_img').height / $('diapo_img').width;
                if(ratioScreen > ratioPicture){
                    newWidth = screenWidth;
                    newHeight = screenWidth * ratioPicture;
                    if(newHeight > screenHeight)newHeight = screenHeight;
                }
                else{
                    newHeight = screenHeight;
                    newWidth  = screenHeight / ratioPicture;
                    if(newWidth > screenWidth)newWidth = screenWidth;
                }
                $('diapo_img').style.marginLeft = (screenWidth - newWidth)/ 2 +"px";
                $('diapo_img').style.marginTop = (screenHeight - newHeight)/ 2 +"px";
                $('diapo_img').height = newHeight;
                $('diapo_img').width = newWidth;
            }

            setDelay(function(obj) { obj.next(curCSS);}, delta, self);
       }
   }
});

