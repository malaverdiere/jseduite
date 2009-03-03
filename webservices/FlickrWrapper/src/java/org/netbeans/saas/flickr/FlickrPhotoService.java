/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.netbeans.saas.flickr;

import java.io.IOException;
import org.netbeans.saas.RestConnection;
import org.netbeans.saas.RestResponse;

/**
 * FlickrPhotoService Service
 *
 * @author mosser
 */

public class FlickrPhotoService {

    /** Creates a new instance of FlickrPhotoService */
    public FlickrPhotoService() {
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch(Throwable th) {}
    }

    /**
     *
     * @param photosetId
     * @param extras
     * @param privacyFilter
     * @param perPage
     * @param page
     * @return an instance of RestResponse
     */
    public static RestResponse photosetsGetPhotos(String photosetId, String extras, String privacyFilter, String perPage, String page) throws IOException {
        String method = "flickr.photosets.getPhotos";
        FlickrPhotoServiceAuthenticator.login();
        String apiKey = FlickrPhotoServiceAuthenticator.getApiKey();
        String authToken = FlickrPhotoServiceAuthenticator.getAuthToken();
        String apiSig = FlickrPhotoServiceAuthenticator.sign(new String[][]{{"api_key", apiKey}, {"photoset_id", photosetId}, {"extras", extras}, {"privacy_filter", privacyFilter}, {"per_page", perPage}, {"page", page}, {"method", method}, {"auth_token", authToken}});
        String[][] pathParams = new String[][]{};
        String[][] queryParams = new String[][]{{"api_key", "" + apiKey + ""}, {"photoset_id", photosetId}, {"extras", extras}, {"privacy_filter", privacyFilter}, {"per_page", perPage}, {"page", page}, {"method", method}, {"auth_token", authToken}, {"api_sig", apiSig}};
        RestConnection conn = new RestConnection("http://api.flickr.com/services/rest", pathParams, queryParams);
        sleep(1000);
        return conn.get(null);
    }
}
