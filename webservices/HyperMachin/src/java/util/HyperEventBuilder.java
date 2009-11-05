/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;
import data.*;
import java.util.ArrayList;
import net.fortuna.ical4j.model.*;

/**
 *
 * @author mosser
 */
public class HyperEventBuilder {

    private HyperPromo promo;
    private Period restriction;
    private ArrayList<HyperEvent> result;


    public HyperEventBuilder(HyperPromo p, Period r) {
        this.promo = p;
        this.restriction = r;
        this.result = new ArrayList<HyperEvent>();
    }

    public HyperEventBuilder(HyperPromo p) { this(p,null); }

    public ArrayList<HyperEvent> getResult() { return result; }

    public void transform() throws Exception {
        for(HyperGroup g: promo.getGroups()) {
            ComponentList cList = HyperHelper.retrieveComponents(g.getUrl());
            if (null != this.restriction)
                cList = HyperHelper.restrict(cList,this.restriction);
            this.process(g.getName(), cList);
        }
    }

    private void process(String groupName, ComponentList cList) {
        for(Object o: cList) 
            this.process(groupName,(Component)o);
    }

    private void process(String groupName, Component c) {
        HyperEvent e = new HyperEvent();
        e.fill((Component) c, groupName);
        if (! this.result.contains(e)) {
            this.result.add(e);
        } else {
            this.result.get(this.result.indexOf(e)).addGroup(groupName);
        }
    }


}
