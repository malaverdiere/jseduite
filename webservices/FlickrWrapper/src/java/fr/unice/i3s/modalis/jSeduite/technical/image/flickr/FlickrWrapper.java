/**
 * This file is part of jSeduite::FlickrWrapper
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::FlickrWrapper is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::FlickrWrapper is distributed in the hope that it will be useful,
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
package fr.unice.i3s.modalis.jSeduite.technical.image.flickr;

import javax.jws.WebService;
import javax.jws.WebParam;
import javax.jws.WebMethod;
import java.net.URL;
import org.netbeans.saas.flickr.FlickrPhotoService;
import org.netbeans.saas.RestResponse;

/**
 *
 * @author mosser
 */
@WebService()
public class FlickrWrapper {

    /** Retrieve the content of a given flickr Web Album
     * @param user album's owner (valid Google User Account)
     * @param album album's name (following Google's naming conventions)
     * @return an array of URL
     */
    @WebMethod(operationName = "getAlbumContent")
    public URL[] getAlbumContent(@WebParam(name = "user") String user,
            @WebParam(name = "album") String album)
            throws FlickrWrapperException {

        try {
            String photosetId = "";
            String extras = null;
            String privacyFilter = null;
            String perPage = null;
            String page = null;
            RestResponse result = FlickrPhotoService.photosetsGetPhotos(photosetId, extras, privacyFilter, perPage, page);
            if (result.getDataAsObject(flickr.photoservice.flickrresponse.Rsp.class) instanceof flickr.photoservice.flickrresponse.Rsp) {
                flickr.photoservice.flickrresponse.Rsp resultObj = result.getDataAsObject(flickr.photoservice.flickrresponse.Rsp.class);
            }
            return null;
        } catch (Exception ex) {
            throw new FlickrWrapperException(ex.getMessage());
        }
    }

    /** Retrieve some pictures from a flickr folksonomy
     * @param tag the expected tags
     * @param count number of pictures returned (less if less exists ^_^)
     * @return an array of URL
     */
    @WebMethod(operationName = "getFolksonomyContent")
    public URL[] getFolksonomyContent(@WebParam(name = "tags") String[] tags,
            @WebParam(name = "count") int count)
            throws FlickrWrapperException {
        return null;
    }
}
