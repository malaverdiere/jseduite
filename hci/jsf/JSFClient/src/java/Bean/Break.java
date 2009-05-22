/**
 * This file is part of jSeduite::BreakTimeManager
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::BreakManagerJSFClient is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::BreakManagerJSFClient is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::InternalNews; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
 *
 **/

package Bean;


import java.util.List;
import  cn.m1.hebut.jseduite.technical.breaktime.*;
import java.util.ArrayList;

/**
 *
 * @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
 */
public class Break {
      private String day;
      private String start;
      private String end;
      private String kind;
      private int id;
      private boolean breakChecked=false;

    public Break() {
    }

    public Break(String day,String start, String end, String kind, int id, boolean breakChecked) {
        this.day = day;
        this.start = start;
        this.end = end;
        this.kind = kind;
        this.id = id;
        this.breakChecked = breakChecked;
    }

    public boolean isBreakChecked() {
        return breakChecked;
    }

    public void setBreakChecked(boolean breakChecked) {
        this.breakChecked = breakChecked;
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
    //get the break list
    public List<BreakTime> getBreakList() throws BreakTimeException_Exception{

        try { // Call Web Service Operation
            BreakTimeManagerService service = new BreakTimeManagerService();
            BreakTimeManager port = service.getBreakTimeManagerPort();
            List<BreakTime> result = port.getAllBreakTime();
             return result;
        } catch (BreakTimeException_Exception ex) {
           ex.getMessage();
        }
     return null;
    }
    //then add a property to break list
    public List<Break> getbreak() throws BreakTimeException_Exception{
        if(getBreakList().size()==0) return null;
        List<Break> list=new ArrayList<Break>();
        for(int i=0;i<getBreakList().size();i++){
            Break b=new Break(getBreakList().get(i).getDay(),
                    getBreakList().get(i).getStart(),getBreakList().get(i).getEnd(),
                    getBreakList().get(i).getKind(), getBreakList().get(i).getId(),
                    breakChecked);
            list.add(b);
        }
        return list;
    }
    //for editing alarm
    public List<Break> listBreak(int bid) throws BreakTimeException_Exception, DALException_Exception, Exception_Exception{
         if(getBreakList().size()==0) return null;
         List<Break> list=new ArrayList<Break>();
         BreakTimeManagerService service = new BreakTimeManagerService();
         BreakTimeManager port = service.getBreakTimeManagerPort();
         List<BreakTime> result = port.getBreakByAlarm(bid);
          for(int i=0;i<getBreakList().size();i++){
             Break b=new Break(getBreakList().get(i).getDay(),
             getBreakList().get(i).getStart(),getBreakList().get(i).getEnd(),
             getBreakList().get(i).getKind(), getBreakList().get(i).getId(),
             false);
             list.add(b);
          }
           for(int i=0;i<getBreakList().size();i++){
             for(int j=0;j<result.size();j++){
                 if(getBreakList().get(i).getId()==result.get(j).getId()){
                    list.get(i).setBreakChecked(true);
                 }
             }
             }
          
          return list;
    }
}
