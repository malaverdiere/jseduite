package webadmin.util;

/**
 *
 * @author Steve Colombié
 */
public class URLParser {

    public static String getProvider(String url) {
        String result = url.replaceFirst("http://", "");
        result = result.split("/")[0];

        return result;
    }

    public static String getFeed(String url) {
        String result = url.replaceFirst("http://", "");
        result =  result.replaceFirst("[^/]*/", "/");

        return result;
    }
}
