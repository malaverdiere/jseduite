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


var imageScrapTransfo = Class.create(jSeduiteTransformation, {
    isSelfHandled: function() { return true; },
    handle: function(aDiv,xml,delta, callback) {
        var diapo = new Diaporama(this.buildImgs(xml), delta, callback);
        var content = "";
        content += "<div id=\"info_logo\" class=\"scrap_logo\"></div>";
        content += "<p class=\"title\"> &nbsp; Folksonomie</p>";
        content += diapo.prepare();
        aDiv.update(content);
        diapo.start();
        return;
        
    },
    buildImgs: function(xml) {
        var result = new Array();
        var items = getNode("item", xml);
        for(var k=0; k < items.length; k++){
            result.push(imageHacking(items[k].textContent));
        }
        return result;
    }
});