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
 * along with jSeduite::FlickrWrapper; if not, write to the Free Software
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
import java.util.*;
import org.jdom.*;
import org.jdom.input.*;



/** A web service to wrap flickr queries (read only mode, no secret stuff)
 * @author mosser
 */
@WebService()
public class FlickrWrapper {

    private static final String HOST = "api.flickr.com";
    private static final String REST_SERVICE = "/services/rest/";
    
    /** Retrieve the content of a given flickr Web Album
     * @param album album's id (see flickr link)
     * @param key the Flickr API Key
     *   => http://www.flickr.com/services/api/misc.api_keys.html
     * @return an array of URL
     */
    @WebMethod(operationName = "getAlbumContent")
    public URL[] getAlbumContent(@WebParam(name = "album") String album,
            @WebParam(name="key") String key)
            throws FlickrWrapperException {
        try {
            String address = buildAddressPrefix("photosets.getPhotos",key);
            address += "&photoset_id=" + album;
            return this.transform("photoset",address);
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
    public URL[] getFolksonomyContent(@WebParam(name = "tags") String tags,
            @WebParam(name = "count") int count,
            @WebParam(name="key") String key)
            throws FlickrWrapperException {
        try {
            String address = buildAddressPrefix("photos.search",key);
            address += "&tags=" + tags + "&per_page="+count;
            return this.transform("photos",address);
        } catch (Exception e){
            throw new FlickrWrapperException(e.getMessage());
        }
    }

    /** Build the address prefix (ie without argument) for a remote Flickr call
     * @param operationName the name of the expected operation
     * @return a string containing the address prefix following Flickr's convention
     */
    private String buildAddressPrefix(String operationName, String key) {
        String tmp = "http://" + HOST + REST_SERVICE + "?method=flickr.";
        tmp += operationName + "&api_key=" + key;
        return tmp;
    }

    /** Transform a XML document (conforms to Flickr XSD) into an URL[]
     * @param rootNode the name of the expected node inside the XML document
     * @param address the xml document address
     * @return a set of URL representing all pictures in the document
     * @throws Exception
     */
    private URL[] transform(String rootNode, String address) throws Exception {
        URL url = new URL(address);
 	    SAXBuilder builder = new SAXBuilder();
 	    Document doc = builder.build(url);
 	    Element root = doc.getRootElement();
 	    Element photosNode = root.getChild(rootNode);
 	    List photoNodes = photosNode.getChildren();
 	    Iterator itr = photoNodes.iterator();
 	    ArrayList<URL> result = new ArrayList<URL>();
 	    while(itr.hasNext()) {
            Element photo = (Element) itr.next();
 	        result.add(parseXml(photo));
 	    }
 	    return result.toArray(new URL[result.size()]);
    }

    /** Extract an URL from a Flickr XmlElement
     * @param e
     * @return
     * @throws java.lang.Exception
     */
    private URL parseXml(Element e) throws Exception {
        String farm = e.getAttributeValue("farm");
        String serverId = e.getAttributeValue("server");
 	    String secret = e.getAttributeValue("secret");
 	    String id = e.getAttributeValue("id");
        String tmp = "http://farm" + farm + ".static.flickr.com/";
        tmp += serverId + "/" + id + "_" + secret + ".jpg";
        return new URL(tmp);
    }

}
