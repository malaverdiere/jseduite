/**
 * This file is part of jSeduite::apalWrapper
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::ApalWrapper is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::ApalWrapper is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::ICalReader; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 * @contributor 2009 Florent Siebert           [siebert@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.apal;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


/** A simple web service to wrap the APAL platform in a Jsseduite compliant way.
 * @author mosser
 */
@WebService()
public class ApalWrapper {

    private static final String APAL = "http://apal.polytech.unice.fr/";
    private static final String TOP10 = "top10.php?";

    /** Extract from the APAL absent students
     * @param promoId the targeted promo
     * @param treshold maximum expected number of result
     * @return An array (filled with null whe needed) of ApalInfo
     */
    @WebMethod(operationName="getTopWithTreshold")
    public ApalInfo[] getTopWithTreshold(@WebParam(name="promoId") int promoId,
            @WebParam(name="treshold") int treshold) {
        ArrayList<String> results = this.scrap(APAL+TOP10+"promo="+promoId);
        ApalInfo[] data = new ApalInfo[results.size()];
        for(int i = 0; i < results.size(); i++) {
            String[] raw = results.get(i).split(";");
            ApalInfo info = new ApalInfo();
            info.setLastname(raw[0]);
            info.setFirstname(raw[1]);
            info.setCounter(Integer.parseInt(raw[2]));
            data[i] = info;
        }
        return this.adjust(data, treshold);
    }

    /** Shortcut for 'getTopWithTreshold' and treshold=10;
     * @param promoId promoId the targeted promo
     * @return An array (filled with null whe needed) of ApalInfo
     */
    @WebMethod(operationName="getTop10")
    public ApalInfo[] getTop10(@WebParam(name="promoId") int promoId) {
        return this.getTopWithTreshold(promoId, 10);
    }

    /** Return School 'loosers' (aka most absent students, per promotion)
     * @param treshold number of looser expected by promotion
     * @return An array of loosers
     */
    @WebMethod(operationName="getLoosers")
    public ApalLooser[] getLoosers(@WebParam(name="treshold") int treshold) {
        ApalPromo[] promos = this.getPromos();
        ArrayList<ApalLooser> buffer = new ArrayList<ApalLooser>();
        for(ApalPromo p: promos) {
            ApalInfo[] infos = this.getTopWithTreshold(p.getId(), treshold);
            for(ApalInfo i: infos){
                if (null != i){
                    ApalLooser looser = new ApalLooser(i);
                    looser.setPromo(p.getName());
                    buffer.add(looser);
                }
            }
        }
        return buffer.toArray(new ApalLooser[buffer.size()]);
    }

    /** Extract all promotions declred in the APAL platform
     * @return An array of ApalPromo (Id, NickName)
     */
    @WebMethod(operationName="getPromos")
    public ApalPromo[] getPromos() {
        ArrayList<String> promos = this.scrap(APAL+TOP10+"promos");
        String[] buffer = promos.toArray(new String[promos.size()]);
        ApalPromo[] result = new ApalPromo[buffer.length-2];
        for(int i = 2; i < buffer.length; i++){
            String[] info = buffer[i].split("\t");
            ApalPromo p = new ApalPromo();
            p.setId(Integer.parseInt(info[0])); p.setName(info[1]);
            result[i-2] = p;
        }
        return result;

    }

    /** PRIVATE METHODS **/

    /**
     * @param url
     * @return
     * @throws java.lang.RuntimeException
     */
    private ArrayList<String> scrap(String url)
            throws RuntimeException  {
       try {
           InputStream is = new URL(url).openStream();
           BufferedReader bread = new BufferedReader(new InputStreamReader(is));
           String line = "";
           ArrayList<String> buffer = new ArrayList<String>();
           while((line = bread.readLine()) != null)
               buffer.add(new String(line.getBytes(),"UTF-8"));
            return buffer;
       } catch(Exception e) { throw new RuntimeException(e.getMessage()); }
    }

    // Adjust by truncating the array, or feeding with null (mandatory for BPEL processes)
    /**
     * @param raw
     * @param treshold
     * @return
     */
    private ApalInfo[] adjust(ApalInfo[] raw, int treshold) {
        ApalInfo[] result = new ApalInfo[treshold];
        for(int i = 0; i < Math.min(raw.length,treshold); i++)
            result[i] = raw[i];
        if (raw.length < treshold) {
            for(int i = raw.length; i < treshold; i++)
                result[i] = null;
        }
        return result;
    }
}
