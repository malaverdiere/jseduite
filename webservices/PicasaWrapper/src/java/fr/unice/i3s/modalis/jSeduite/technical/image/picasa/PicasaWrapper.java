/**
 * This file is part of jSeduite::PicasaWrapper
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::PicasaWrapper is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::PicasaWrapper is distributed in the hope that it will be useful,
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
package fr.unice.i3s.modalis.jSeduite.technical.image.picasa;

import java.net.URL;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.jdom.*;
import org.jdom.input.*;
import java.util.*;

/** A Web service to retrieve pictures URL from google Picasa Album
 * @author mosser
 */
@WebService()
public class PicasaWrapper {

    private static final String HOST = "picasaweb.google.com";
    private static final String SERVICE = "/data/feed/api";
    private static final Namespace NSPC =
            Namespace.getNamespace("http://www.w3.org/2005/Atom");

    /** Retrieve the content of a given Picasa Web Album
     * @param user album's owner (valid Google User Account)
     * @param album album's name (following Google's naming conventions)
     * @return an array of URL
     */
    @WebMethod(operationName = "getAlbumContent")
    public URL[] getAlbumContent(@WebParam(name = "user") String user,
            @WebParam(name = "album") String album)
            throws PicasaWrapperException {
        String address = "http://" + HOST + SERVICE + "/user/" + user;
        address += "/album/" + album + "?kind=photo";
        try {
            return this.transform(address);
        } catch (Exception e) {
            throw new PicasaWrapperException(e.getMessage());
        }
    }

    /** Retrieve some pictures from a picasa folksonomy
     * @param tag the expected tags
     * @param count number of pictures returned (less if less exists ^_^)
     * @return an array of URL
     */
    @WebMethod(operationName = "getFolksonomyContent")
    public URL[] getFolksonomyContent(@WebParam(name = "query") String query,
            @WebParam(name = "count") int count)
            throws PicasaWrapperException {
        String address = "http://"+HOST + SERVICE +"/all?kind=photo";
        address += "&max-results=" + count + "&tag=" + query;
        try {
            return this.transform(address);
        } catch (Exception e) {
            throw new PicasaWrapperException(e.getMessage());
        }
    }

    /** Retrieve the content of a given protected Picasa Web Album
     * @param user album's owner (valid Google User Account)
     * @param album album's name (following Google's naming conventions)
     * @param authKey album's authentication key
     * @return an array of URL
     */
    @WebMethod(operationName = "getProtectedAlbumContent")
    public URL[] getProtectedAlbumContent(@WebParam(name = "user") String user,
            @WebParam(name = "album") String album,
            @WebParam(name = "authKey") String authKey)
            throws PicasaWrapperException {
        String address = "http://" + HOST + SERVICE + "/user/" + user;
        address += "/album/" + album;
        address += "?authkey="+ authKey +"&kind=photo";
        try {
            return this.transform(address);
        } catch (Exception e) {
            throw new PicasaWrapperException(e.getMessage());
        }
    }

    private URL[] transform(String address) throws Exception {
        URL url = new URL(address);
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(url);
        Element feed = doc.getRootElement();
        Iterator itr = feed.getChildren("entry", NSPC).iterator();
        ArrayList<URL> result = new ArrayList<URL>();
        while (itr.hasNext()) {
            Element entry = (Element) itr.next();
            result.add(new URL(entry.getChild("content", NSPC).getAttributeValue("src")));
        }
        return result.toArray(new URL[result.size()]);
    }
}
