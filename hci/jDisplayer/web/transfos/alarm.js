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
 * @author Main     Christophe Desclaux          [desclaux@polytech.unice.fr]
 **/


var alarm = Class.create(jSeduiteTransformation, {
    isSelfHandled: function() { return true; },
    handle: function(aDiv, xml, delta, callback) {
        this.callback = callback;
        this.delta = delta;
        this.xml = xml;
        var content = "<span class=\"alarm\">";
        content += getTag("message",xml);
        content += "</span>";
        var urlSound = getTag("sound",xml);
        if(urlSound.search(/http/) == -1){
            urlSound = "http://" + location.host + "" + urlSound;
        }
        content += "<span class=\"alarm_player\"><audio width=\"1024\" controls=\"\" id=\"audio \" ";
        content += "src=\"" + urlSound + "\" autoplay style=\"width: 480; height: 360;\">";
        content += "You need a HTML5 compliant web browser. (eg Firefox 3.5...)";
        content += "</audio></span>";
        aDiv.update(content);

        var self = this;
        window.setTimeout(function(self){self.next(0);},delta,self);
    },
    next: function(curCSS){
       replaceTurningCss(engine.getTurningCSS(curCSS++));
       if(curCSS >= engine.getAmountCSS()){
            var audio = document.getElementsByTagName("audio")[0];
            if(audio.currentTime >= audio.duration){
                this.callback();
                return;
            }
            else{
                curCSS = 0;
            }
       }
       var self = this;
       window.setTimeout(function(obj) { obj.next(curCSS);}, this.delta,  self);
    },

    startDate: function(xml) {
        return (buildDateFromStamp(getTag("dateRing",xml))).getTime();
    },
    endDate: function(xml) {
        return 1;
    }
});