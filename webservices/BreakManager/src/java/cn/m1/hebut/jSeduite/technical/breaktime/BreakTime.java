/**
 * This file is part of jSeduite::BreakTimeManager
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::BreakTimeManager is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::BreakTimeManager is distributed in the hope that it will be useful,
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
     private String pCode;
      private String promo;
      private String start;
      private String end;
      private String kind;

      public BreakTime(){}

       public BreakTime(DalResultSet rset) throws Exception {

        this.pCode= rset.getValue("p_code");
        this.promo = rset.getValue("promo");
        this.end = rset.getValue("end");
        this.start  = rset.getValue("start");
        this.kind = rset.getValue("kind");
       }

       public BreakTime(String pcode, String promo, String start, String end, String kind){
           this.pCode=pcode;
           this.promo=promo;
           this.start=start;
           this.end=end;
           this.kind=kind;
       }

    public String getPCode() {
        return pCode;
    }

    public void setPCode(String pCode) {
        this.pCode = pCode;
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

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
