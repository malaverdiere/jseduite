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
 * @author      Main     Christophe Desclaux        [desclaux@polytech.unice.fr]
 **/


var pictogramHelper = Class.create({
    get: function(xml) {
        if(getTag("picture2", xml) == "null"){
            return this.printOnePicture(getTag("picture1", xml));
        }
        else if(getTag("picture1", xml) == "null"){
            return this.printOnePicture(getTag("picture2", xml));
        }
        else {
            var pictureHeight = window.innerHeight - 22;
            var pictureWidth = (window.innerWidth - 35) / 2;
            var startPictureRight = pictureWidth + 10 + 10;
            var content = "<span id='pictogram'>";
            content += "<span class='pictograms_picture' style='left:5px;' id='pictograms_picture1'>";
            content += "<img height='"+pictureHeight+"' width='"+pictureWidth+"' src='" + getTag("picture1", xml) + "'/></span>";
            content += "<span class='pictograms_picture' style='left:"+startPictureRight+"px;' id='pictograms_picture2'>";
            content += "<img height='"+pictureHeight+"' width='"+pictureWidth+"' src='" + getTag("picture2", xml) + "'/></span>";
            content += "</span>";
            return [content];
        }
    },
    printOnePicture: function(picture) {
            var pictureHeight = window.innerHeight - 22;
            var pictureWidth = (window.innerWidth - 20);
            var content = "<span id='pictogram'>";
            content += "<span class='pictograms_picture' style='left:5px;' id='pictograms_picture1'>";
            content += "<img height='"+pictureHeight+"' width='"+pictureWidth+"' src='" + picture + "'/></span>";
            content += "</span>";
            return [content];
        

    }
});

var pictogramBlink = Class.create(jSeduiteTransformation, {
    perform: function(xml) {
        var timeBlinking = 200;
        var timeBeforeBlink = 7000;
        var delta = engine.tpl.getDelta(getSource(xml))*engine.getAmountCSS();
        var id = window.setInterval(function(){
                var saveScreen = $('main').innerHTML;
                if($('pictogram') == null) return;
                $('main').update("");
                window.setTimeout(function(saveScreen){
                    if($('main').innerHTML == ""){$('main').update(saveScreen);}
                }, timeBlinking ,saveScreen);}
            , timeBeforeBlink);
        window.setTimeout(window.clearInterval, delta, id);

        var helper = new pictogramHelper();
        return helper.get(xml);
    }
});

var pictogram = Class.create(jSeduiteTransformation, {
    perform: function(xml) {
        var helper = new pictogramHelper();
        return helper.get(xml);
    }
});