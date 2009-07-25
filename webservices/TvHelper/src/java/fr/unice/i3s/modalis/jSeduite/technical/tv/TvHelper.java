/**
 * This file is part of jSeduite::TvHelper
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::TvHelper is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::TvHelper is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::UserProfile; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 *
 **/

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
     *  Detected Pattern : CHANNEL '-' HH:MM ':' TITLE
     */
    @WebMethod(operationName = "extract")
    public String[] extract(@WebParam(name = "complexString") String complexString) {
        String[] result = new String[3];
        return result;

/**
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
 **/
    }

}
