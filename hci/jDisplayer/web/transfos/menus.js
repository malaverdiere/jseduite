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
 * @author      Main     Christophe Desclaux    [desclaux@polytech.unice.fr]
 **/

var menusFullScreen = Class.create(jSeduiteTransformation, {
        perform: function(xml) {

        var date = getTag("date", xml);
        var content = "<span class =\"menus\">";
        content += "<div class=\"menus_title\">";
        content += getTag("typeMenu", xml)+ " à ";
        content +=  date.substring(11,13) + " heures</div>";

        content += "<div id=\"menus_content\" class=\"menus_content\">";
        var coursesNode = getNode("courses",xml);
        
        var courses = new Array();
        courses["entree"] = "";
        courses["plat"] = "";
        courses["dessert"] = "";
        for(var i=0; i <coursesNode.length;i++){
            var kind = getTag("kind",coursesNode[i]);
            var name = getTag("name",coursesNode[i]);
            if(courses[kind] == null) courses[kind] = name;
            else courses[kind] +="<br/> " + name;
        }
        content += courses["entree"] + "<br/>~~~";
        content += courses["plat"] + "<br/>~~~";
        content += courses["dessert"];


        content += "</div></span>";
        return [content];
    }
});

var menus = Class.create(jSeduiteTransformation, {
        perform: function(xml) {

        //max lines in a page
        var maxLines = 7;


        var coursesNode = getNode("courses",xml);
        //the datas to print
        var courses = new Array();
        var kinds = new Array();
        var kindsCount = new Array();
        var i;
        for(i=0; i <coursesNode.length;i++){
            var kind = getTag("kind",coursesNode[i]);
            var name = getTag("name",coursesNode[i]);
            if(courses[kind] == null){
                kinds.push(kind);
                kindsCount[kind]= 1;
                courses[kind] = name+"<br/>";
            }
            else{
                courses[kind] += name+"<br/>";
                kindsCount[kind] += 1;
            }
        }


        var pages = new Array();//the pages to print
        var curNbLines = 0; //number of lines in content page

        var content = ""; //cur page we are making
        content += "<div id=\"menus_logo\" class=\"menus_logo\"></div>";
        content += "<p class=\"title\"> &nbsp; ";
        content += getTag("typeMenu", xml) + " à ";
        content +=  getTag("date", xml).substring(11,13) + " heures</p>";
        content += "<div class=\"clearDiv\">&nbsp;</div>";
        content += "<p class=\"menus_content\">";



        //we add datas in pages
        for(i=0; i < kinds.length;i++){
            var curKind = kinds[i];
            if((curNbLines + kindsCount[curKind]) > maxLines){
                //we need to add a page
                content += "</p>";
                pages.push(content);

                content = "<div id=\"info_logo\" class=\"menus_logo\"></div>";
                content += "<p class=\"title\"> &nbsp; ";
                content += getTag("typeMenu", xml) + " à ";
                content +=  getTag("date", xml).substring(11,13) + " heures</p>";
                content += "<div class=\"clearDiv\">&nbsp;</div>";
                content += "<p class=\"menus_content\">";
                curNbLines = 0;
                
            }
            content += "<u><b>"+curKind+":</b></u><br/>";
            content += courses[curKind]+"<br/>";
            curNbLines += kindsCount[curKind] + 1;
        }
        content += "</p>";
        pages.push(content);
        return pages;
    }
});