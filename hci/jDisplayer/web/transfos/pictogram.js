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


var pictogram = Class.create(jSeduiteTransformation, {
    perform: function(xml) {
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
            var content = "";
            content += "<span class='pictograms_picture' style='left:5px;' id='pictograms_picture1'>";
            content += "<img height='"+pictureHeight+"' width='"+pictureWidth+"' src='" + getTag("picture1", xml) + "'/></span>";
            content += "<span class='pictograms_picture' style='left:"+startPictureRight+"px;' id='pictograms_picture2'>";
            content += "<img height='"+pictureHeight+"' width='"+pictureWidth+"' src='" + getTag("picture2", xml) + "'/></span>";
            return [content];
        }
    },
    printOnePicture: function(picture) {
            var pictureHeight = window.innerHeight - 22;
            var pictureWidth = (window.innerWidth - 20);
            content = "";
            content += "<span class='pictograms_picture' style='left:5px;' id='pictograms_picture1'>";
            content += "<img height='"+pictureHeight+"' width='"+pictureWidth+"' src='" + picture + "'/></span>";
            return [content];
        

    }
});