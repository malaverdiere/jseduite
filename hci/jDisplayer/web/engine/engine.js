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
 **/

var jSeduiteEngine = Class.create({

    // Initialize the Engine
    initialize: function(url, screen,tplName) {
        this.provider = url;
        this.screen = screen;
        // this.initLoops();
        loadCss("templates/"+tplName+"/style.css");
        loadJs("templates/"+tplName+"/init.js");
    },
    initLoops: function() {
        this.loops = new Array();
        this.loops["main"] = new mainLoop(this.tpl);
        this.loops["main"].setCallback(function() { engine.run(); });
        this.loops["scroll"] = new scrollLoop(this.tpl);
        this.loops["timer"] = new loop(this.tpl);
        this.clearLoops();
    },
    // Ste the template to use
    setTemplate: function(tpl) { this.tpl = tpl; this.initLoops();  },
    // Run the engine
    run: function() {
        setLoadingState();
        addLog("Retrieving informations");
        new Ajax.Request(this.provider, {
            method:'get',
            parameters: {display: this.screen},
            onSuccess: function(transport){
                removeLoadingState();
                addOkLog("Information set successfully received")
                var infos = transport.responseXML;
                engine.feed(infos);
                engine.startLoops();
            },
            onFailure: function(){
                setLoadingState();
                var err = "Cannot retrieve information!";
                $("main").update(buildSpan(null, "error", err));
                addErrLog("Engine#run(): Unable to retrieve informations (Server failure)");
                window.setTimeout(function() { engine.run(); }, 5000);
            },
            onException: function(self, exception) {
                setLoadingState();
                addErrLog("Engine#run() exception: " + exception);
                window.setTimeout(function() { engine.run(); }, 10000);
            }
        });
    }, 
    /*********************/
    /** Private Methods **/
    /*********************/
    clearLoops: function() {
        this.loops["main"].clear();
        this.loops["scroll"].clear();
        this.loops["timer"].clear();
    },
    // dispatch informations
    feed: function(xml) {
        this.clearLoops();
        var allResults = getNode("result", xml);
        var root = Element.extend(allResults[0]);
        var allItems = root.children;

        for(var i=0; i<allItems.length; i++){
            myItem = allItems[i];
            this.loops[this.tpl.dispatch(getSource(myItem))].feed(myItem);
        }
    },
    startLoops: function() {
        this.loops["main"].start();
        this.loops["scroll"].start();
        //this.loops["timer"] = new loop(this.tpl);
    }
});

function setLoadingState() {
    var loading = "<center><div id=\"loading_animation\"> </div></center>";
    $('main').update("<br/><br/><br/><br/><br/>" + loading);
}

function removeLoadingState() { $('main').update(""); }


