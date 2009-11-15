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
var jSeduiteTemplate = Class.create({

    // Initialize the Engine
    initialize: function(name) {
        this.name = name;
        this.bindings = new Array();
        this.deltas = new Array();
    },
    // Declare a new transformation
    declare: function(key, transformation, delta) {
        this.bindings[key] = transformation;
        this.deltas[key] = delta*1000;
    },
    getTransformation: function(key) { 
        if (null == this.bindings[key]) {
            addLog("Template error: ["+this.name+"] cannot handle ["
                    + key + "] information kind");
            return new jSeduiteTransformation();
        }
        return this.bindings[key];
    },
    getDelta: function(key) { 
        if (null == this.deltas[key])
            return 1000;
        return this.deltas[key];
    },
    getTargetLoop: function(key) { return "main"; }
});
