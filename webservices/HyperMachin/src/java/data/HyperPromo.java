/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

/**
 *
 * @author mosser
 */
public class HyperPromo {

    private String name;
    private HyperGroup[] groups;

    /** XML Serialization **/
    public HyperPromo() {}

    public HyperGroup[] getGroups() {
        return groups;
    }

    public void setGroups(HyperGroup[] groups) {
        this.groups = groups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
