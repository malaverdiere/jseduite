/**
 * This file is part of jSeduite::BreakTimeManager
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::BreakManager is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::BreakManager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::InternalNews; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
   @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 **/
package cn.m1.hebut.jSeduite.technical.breaktime;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;


public class BreakTime {
      private String day;
      private String start;
      private String end;
      private String kind;
      private int id;


      public BreakTime(){}

       public BreakTime(DalResultSet rset) throws Exception {

        this.day= rset.getValue("day");
        this.end = rset.getValue("end");
        this.start  = rset.getValue("start");
        this.kind = rset.getValue("kind");
        this.id = Integer.parseInt(rset.getValue("break_id"));
       
       }

 

       

       public BreakTime(String day,String start, String end, String kind,int id){

           this.day=day;
           this.start=start;
           this.end=end;
           this.kind=kind;
             this.id=id;

       }

   public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }


    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
