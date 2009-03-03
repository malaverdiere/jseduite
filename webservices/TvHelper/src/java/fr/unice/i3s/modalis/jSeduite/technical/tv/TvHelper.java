/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.i3s.modalis.jSeduite.technical.tv;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author mosser
 */
@WebService()
public class TvHelper {

    /** Extract information from a tv show title in a more easiest way than
     * using BPEL natives constructions
     */
    @WebMethod(operationName = "extract")
    public String[] extract(@WebParam(name = "complexString") String complexString) {

        String[] extracted = complexString.split("-");
        
        String start = extracted[extracted.length - 1];
        String channel = extracted[extracted.length - 2];

        String title = "";
        for(int i = 0; i < extracted.length - 2; i++ )
            title += extracted[i].trim() + " - ";

        String[] result = new String[3];
        result[0] = title.substring(0,title.length()-3).trim();
        result[1] = channel.trim();
        result[2] = start.trim();
        return result;
    }

}
