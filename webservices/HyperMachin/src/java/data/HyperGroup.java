/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;
import java.net.URL;

/**
 *
 * @author mosser
 */
public class HyperGroup {
    private String name;
    private URL url;

    public HyperGroup(String n, URL u) {
        this.name = n;
        this.url = u;
    }

    /** XML Serialisation **/
    public HyperGroup() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

}
