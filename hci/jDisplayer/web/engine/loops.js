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
 * @author      Main     Sebastien Mosser       [mosser@polytech.unice.fr]
 * @contributor 2009     Celine Auzias          [celine.auzias@gmail.com]
 * @contributor 2010     Christophe Desclaux    [desclaux@polytech.unice.fr]
 **/

var loop = Class.create({
   initialize: function(tpl) {
       this.tpl = tpl;
       this.clear();
   },
   feed: function(elem) { this.content.push(elem); },
   start: function() { return; },
   setCallBack: function(f) { this.callback = f;},
   clear: function() { this.content = new Array(); }
});

/******************
 ** SCROLL LOOPS **
 ******************/
 
var scrollLoop = Class.create(loop,{
    start: function() {
        stopScroll();
        $('defile').update("");
        var txt = "";
        for(var j=0; j < this.content.length; j++){
            var anItem = this.content[j];
            var transfo = this.tpl.getTransformation(getSource(anItem));
            var result = transfo.perform(anItem)
            txt += result[0];
        }
        $('defile').update(txt);
        scrolling($('defile'), 900, 900);
    }
});

/***************
 ** MAIN LOOP **
 ***************/

var mainLoop = Class.create(loop, {
    start: function() {
        initDelays();
        this.idx = 0;
        this.next();
    },
    setCallback: function(f) { this.callback = f; },
    next: function () {
        if (this.idx >= this.content.length) {
            this.callback();
        } else {
            var anItem = this.content[this.idx];
            var transfo = this.tpl.getTransformation(getSource(anItem));
            var delta = this.tpl.getDelta(getSource(anItem));
            this.idx = this.idx + 1;
            if (transfo.isSelfHandled()) {
                var self = this;
                transfo.handle($('main'), anItem, delta, function() { self.next(); });
            } else {
                var screens = transfo.perform(anItem);
                this.displayElements(screens.reverse(),delta);
            }            
        }
    },
    displayElements: function(elements,delta) {
        if (0 == elements.length) {
            this.next();
        } else {
            var elem = elements.pop();
            $('main').update(elem);
            this.changeTurningCSS(elements,delta,1);
        }
    },
    changeTurningCSS: function(elements,delta,curCSS){
        setDelay(function(loop) {
                if(curCSS < engine.getAmountCSS()){
                    replaceTurningCss(engine.getTurningCSS(curCSS));
                    loop.changeTurningCSS(elements,delta,curCSS+1);
                }
                else{
                    replaceTurningCss(engine.getTurningCSS(0));
                    loop.displayElements(elements, delta);
                }
            },delta,this);
    }
});

/***************
 ** TIMER LOOP **
 ***************/
var timers  = new Array();
var timerLoop = Class.create(loop, {
    start: function() {
        for(var i=0; i < timers.length;i++){
            var timeoutID = timers[i];
            window.clearTimeout(timeoutID);
        }
        timers = new Array();
        for(i=0; i < this.content.length;i++){
            var id = this.setTimer(this.content[i]);
            timers.push(id);
        }
    },
    
    setTimer: function(item) {
        //we start loarding the transformation witch will be print
        var transfo = this.tpl.getTransformation(getSource(item));
        var delta = this.tpl.getDelta(getSource(item));
        var timer  = transfo.startDate(item) - (new Date()).getTime();
        if(timer < 0)return -1;
        //we put a timeout on the transformation
        var idTimer = window.setTimeout(function() {
            stopDelays();//we stop the mainLoop scrolling
            transfo.handle($('main'),item, delta, function() { continueDelays(); });        },timer);
        return idTimer;
    }
});