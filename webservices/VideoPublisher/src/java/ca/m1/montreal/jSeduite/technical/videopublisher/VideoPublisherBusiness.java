/**
 * This file is part of jSeduite::BusScheduleBusiness
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::BusScheduleBusiness is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::BusScheduleBusiness is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::BusScheduleBusiness; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Vincent Bonmalais          [vb.kouno@gmail.com]
 * @author      Main     Yannick Tahora             [ytahora@gmail.com]
 *
 **/

package ca.m1.montreal.jSeduite.technical.videopublisher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * A Web Service to handle Video
 *
 *
 * @author vincent bonmalais
 * @author yannick tahora
 */
@WebService(name = "VideoPublisherBusiness")
public class VideoPublisherBusiness {

    /**
     * Proof Of Concept : try to transfer a large file to a web service.
     *
     * @param file      a large file
     * @param md5       md5 of the large file
     *
     * @return identifier for the file
     */
    @WebMethod(operationName = "setFile")
    public boolean setFile(
            @WebParam(name = "file") byte[] file,
            @WebParam(name = "md5") byte[] md5)
    {
        //TODO this method should store :file:
        try {
            
            byte[] digest = toMD5(file);
            if (Arrays.equals(digest, md5))
                return true;

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(VideoPublisherBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    /**
     * Cut file
     *
     * @param url       location of the file
     */
    @WebMethod(operationName = "cutFile")
    public boolean cutFile(
            @WebParam(name = "url") String url)
    {
        //TODO implement cutting file code
        return false;
    }

     private byte[] toMD5(byte[] b) throws NoSuchAlgorithmException{
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(b, 0, b.length);
        return m.digest();
    }

}