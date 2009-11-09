/**
 * This file is part of jSeduite::HyperMachin
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::HyperMachin is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::HyperMachin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::HyperMachin; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main   Sébastien Mosser          [mosser@polytech.unice.fr]
 * @contributor [2009] Claudine Peyrat           [claudine@polytech.unice.fr]
 *
 **/

package util;
import data.*;
import java.io.*;
import net.fortuna.ical4j.model.*;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;
import java.util.ArrayList;
import java.net.URL;
import net.fortuna.ical4j.data.CalendarOutputter;



public class HyperCache {

    private HyperPromo remote;
    private static String LOCK = "/lock";
    private static int VALIDITY = 2 * 1000 * 60* 60;
    
    public HyperCache(HyperPromo p) { this.remote = p; }
    
    public HyperCache(String promoId) throws Exception {
        DataAccessLayer dal = new DataAccessLayer();
        String sql4Id = "SELECT `id` as `promoId`, `name` as `promoName` ";
        sql4Id += " FROM `promos`  WHERE `code` = '" + promoId + "';";
        this.remote = new HyperPromo();
        this.remote.setCode(promoId);
        this.remote.setName(dal.extractScalar(sql4Id, "promoName"));
        int pId = Integer.parseInt(dal.extractScalar(sql4Id, "promoId"));
        String sql4groups = "SELECT `name` AS `groupName`, `planning` ";
        sql4groups += " FROM `promo_groups` WHERE `promo_id`= " + pId;
        DalResultSet rSet = dal.extractDataSet(sql4groups);
        ArrayList<HyperGroup> groups = new ArrayList<HyperGroup>();
        for(int i=0; i < rSet.size(); i++) {
            String name = rSet.getValue("groupName");
            URL url = new URL(rSet.getValue("planning"));
            groups.add(new HyperGroup(name, url));
            rSet.next();
        }
        this.remote.setGroups(groups.toArray(new HyperGroup[groups.size()]));
    }

    public void repatriate() throws Exception {
        this.lock(true);
        for(HyperGroup g: this.remote.getGroups())
            getRemoteCalendar(g.getName(), g.getUrl());
        this.lock(false);
    }

    public HyperCacheStatus isValid() {
        File dir = getDirectory();
        if (0 == dir.list().length)
            return HyperCacheStatus.EMPTY;
        if (new File(dir.getAbsolutePath()+ LOCK).exists())
            return HyperCacheStatus.SYNC;
        long modif = dir.lastModified();
        long now = System.currentTimeMillis();
        return (now - modif < VALIDITY? HyperCacheStatus.VALID: HyperCacheStatus.OLD) ;
    }

    public static String[] getCacheContent() throws Exception {
        return new File(Config.PATH).list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
               return ! name.startsWith(".");
            }
        });
    }
    /** Private Methods **/

    private void getRemoteCalendar(String name, URL url) throws Exception {

        Period restriction = getRestrictionPeriod();
        Calendar c = HyperHelper.retrieveCalendar(url);
        ComponentList all =c.getComponents(Component.VEVENT);
        ComponentList restricted = HyperHelper.restrict(all, restriction);
        c.getComponents().clear();
        c.getComponents().addAll(restricted);
        File f = new File(getDirectory() + "/" + name + ".ics");
        FileOutputStream fout = new FileOutputStream(f);
        CalendarOutputter outputter = new CalendarOutputter();
        try {
            outputter.output(c, fout);
        } catch (Exception e){ f.delete(); }
    }

    public HyperPromo localize() throws Exception {
        HyperPromo result = new HyperPromo();
        result.setCode(this.remote.getCode());
        result.setName(this.remote.getName());
        ArrayList<HyperGroup> groups = new ArrayList<HyperGroup>();
        File dir = getDirectory();
        for(String s: dir.list()) {
            HyperGroup g = new HyperGroup(s.split("\\.")[0],
                    new URL("file://" + dir.getAbsolutePath()+"/"+s));
            groups.add(g);
        }
            
        result.setGroups(groups.toArray(new HyperGroup[groups.size()]));
        return result;
    }


    private Period getRestrictionPeriod() {
        java.util.Calendar now = java.util.Calendar.getInstance();
        now.set(java.util.Calendar.HOUR_OF_DAY, 0);
        now.clear(java.util.Calendar.MINUTE);
        now.clear(java.util.Calendar.SECOND);
        Period r = new Period(new DateTime(now.getTime()), new Dur(1,0,0,0));
        return  r;
    }

    private boolean lock(boolean bolt) throws Exception {
        File f = new File(getDirectory().getAbsolutePath() + LOCK);
        return (bolt? f.createNewFile(): f.delete());
    }

    private File getDirectory() {
        File result = new File(Config.PATH + this.remote.getCode()+"/");
        if (! result.exists())
            result.mkdir();
        return result;
    }


}
