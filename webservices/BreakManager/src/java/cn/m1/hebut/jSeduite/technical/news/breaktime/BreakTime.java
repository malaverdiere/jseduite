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
 * @author      Main Zhao Yichen  Qin Zhaobo         [yichenzhao18@gmail.com&Bienvenueqin@gmail.com]
 **/
package cn.m1.hebut.jSeduite.technical.news.breaktime;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;


public class BreakTime {
     private String p_code;
      private String promo;
      private String start;
      private String end;
      private String kind;

      public BreakTime(){}

       public BreakTime(DalResultSet rset) throws Exception {

        this.p_code= rset.getValue("p_code");
        this.promo = rset.getValue("promo");
        this.end = rset.getValue("end");
        this.start  = rset.getValue("start");
        this.kind = rset.getValue("kind");
       }

       public BreakTime(String p_code, String promo, String start, String end, String kind){
           this.p_code=p_code;
           this.promo=promo;
           this.start=start;
           this.end=end;
           this.kind=kind;
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

    public String getP_code() {
        return p_code;
    }

    public void setP_code(String p_code) {
        this.p_code = p_code;
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
