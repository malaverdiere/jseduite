/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.m1.hebut.jSeduite.technical.breaktime.admin;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;

/**
 *
 * @author Administrator
 */
public class BreakTimeForAdmin {
      private String day;
      private String breakPromoLnk;
      private String start;
      private String end;
      private String kind;
      private int id;

    public BreakTimeForAdmin() {
    }
     public BreakTimeForAdmin(DalResultSet rset) {
        this.day= rset.getValue("day");
        this.end = rset.getValue("end");
        this.start  = rset.getValue("start");
        this.kind = rset.getValue("kind");
        this.id = Integer.parseInt(rset.getValue("break_id"));
      }

    public String getBreakPromoLnk() {
        return breakPromoLnk;
    }

    public void setBreakPromoLnk(String breakPromoLnk) {
        this.breakPromoLnk = breakPromoLnk;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
       

}
