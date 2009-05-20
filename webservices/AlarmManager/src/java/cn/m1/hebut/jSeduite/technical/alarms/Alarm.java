/**
 * This file is part of jSeduite::BreakTimeManager
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::AlarmManager is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::AlarmManager is distributed in the hope that it will be useful,
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
package cn.m1.hebut.jSeduite.technical.alarms;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;

/**
 *
 * @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
 */
public class Alarm {
    private int alarmId;
    private String alarmContent;
    private String position;
    public Alarm(){}
    public Alarm(DalResultSet rset){
        this.alarmId= Integer.parseInt(rset.getValue("alarm_id"));
        this.alarmContent = rset.getValue("content");
        this.position=rset.getValue("position");
    }
    public Alarm(int alarmId, String alarmContent,String position) {
        this.alarmId = alarmId;     
        this.alarmContent = alarmContent;
        this.position=position;
    }

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
   
}
