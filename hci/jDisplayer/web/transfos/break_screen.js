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
var repeatSoundNumber = 5;

var breakScreen = Class.create(jSeduiteTransformation, {
    isSelfHandled: function() { return true; },

    /**
     * getType()
     * used to get the type of the alert
     * return the type: notSelfManaged if the alert is just a text to print
     */
    getType: function() {
        var content = getTag("content",this.xml);
        if(content[0] == '#'){
            return content.substr(1,content.length);
        }
        else
            return "notSelfManaged";
    },
    
    handle: function(aDiv, xml, delta, callback) {
        this.aDiv = aDiv;
        this.callback = callback;
        this.xml = xml;
        this.delta  = delta;
        if(this.getType(xml) == "notSelfManaged"){
            this.handleNotSelfManaged();
        }
        else{
            this.handleSelfManaged();
        }
    },

    handleSelfManaged: function(){
        if ((new Date()).getTime() > this.endDate(this.xml)) {
            this.callback();
            return;
        }

        //we get the item to print
        var anItem = null;
        for(var i=0;i< engine.loops["main"].content.length;i++){
            if(getSource(engine.loops["main"].content[i]) == this.getType()){
                anItem = engine.loops["main"].content[i];
            }
        }
        
        if(anItem != null){
            var transfo = engine.tpl.getTransformation(getSource(anItem));
            var delta = engine.tpl.getDelta(getSource(anItem));
            if (transfo.isSelfHandled()) {
                var self = this;
                transfo.handle($('main'), anItem, delta, function() { self.handleSelfManaged(); });
            } else {
                //don't work
                var screens = transfo.perform(anItem);
                this.displayElements(screens.reverse(),delta);
            }
        }else{
            //item not found
            this.callback();
        }
    },

    displayElements: function(elements,delta) {
        if (0 == elements.length) {
            this.handleSelfManaged();
        } else {
            var elem = elements.pop();
            $('main').update(elem);
            this.changeTurningCSS(elements,delta,1);
        }
    },
    
    changeTurningCSS: function(elements,delta,curCSS){
        var self = this;
        
        window.setTimeout(function(loop) {
                if(curCSS < engine.getAmountCSS()){
                    replaceTurningCss(engine.getTurningCSS(curCSS));
                    loop.changeTurningCSS(elements,delta,curCSS+1);
                }
                else{
                    replaceTurningCss(engine.getTurningCSS(0));
                    loop.displayElements(elements, delta);
                }
            },delta,self);
    },

    handleNotSelfManaged: function(){
        var content = "<span class=\"alarm\">";
        content += getTag("content",this.xml);
        content += "</span>";

        var urlSound = getTag("sound",this.xml);
        var self;
        if(urlSound != "null"){
            if(urlSound.search(/http/) == -1){
                urlSound = "http://" + location.host + "" + urlSound;
            }
            content += "<span class=\"alarm_player\"><audio width=\"1024\" controls=\"\" id=\"audio \" ";
            content += "src=\"" + urlSound + "\" autoplay style=\"width: 480; height: 360;\">";
            content += "You need a HTML5 compliant web browser. (eg Firefox 3.5...)";
            content += "</audio></span>";
            self = this;
            window.setTimeout(function(end,self){self.initSound(end,self)}, 1000, this.endDate(this.xml) - 1000,self);
        }
        this.aDiv.update(content);
        self = this;
        window.setTimeout(function(self){self.next(0);},self.delta,self);
    },
    
    next: function(curCSS){
        replaceTurningCss(engine.getTurningCSS(curCSS++));
        if ((new Date()).getTime() > this.endDate(this.xml)) {
            this.callback();
            return;
        }
       if(curCSS >= engine.getAmountCSS()){
           curCSS = 0;
       }
       var self = this;
       if(this.endDate(this.xml)  < ((new Date()).getTime() + this.delta)){
           var timeBreak = this.endDate(this.xml) - (new Date()).getTime();
           window.setTimeout(function(obj) { obj.callback();}, timeBreak,  self);
       }
       else{
           window.setTimeout(function(obj) { obj.next(curCSS);}, this.delta,  self);
       }
    },

    initSound: function(end) {
        var audio = document.getElementsByTagName("audio")[0];
        var duree = audio.duration * 1000;
        var timeToEnd = end - (new Date()).getTime() - 3000;
        if(timeToEnd < repeatSoundNumber*duree){
            repeatSoundNumber = Math.floor(timeToEnd / duree);
        }
        var timeBreakSound = (timeToEnd - repeatSoundNumber*duree)/(repeatSoundNumber - 1);
        alert(repeatSoundNumber);
        for(var i=0; i < repeatSoundNumber;i++){
            var delayToPlay = (timeBreakSound + duree)*i;
            window.setTimeout(function(){var audio = document.getElementsByTagName("audio")[0]; audio.play();}, delayToPlay);
        }
    },

    startDate: function(xml) {
        return (buildDateFromStamp(getTag("start",xml))).getTime();
    },

    endDate: function(xml) {
        return (buildDateFromStamp(getTag("end",xml))).getTime();
    },

    duration: function(xml) {
         return this.endDate(xml)- this.startDate(xml);
    }
});