/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webadmin.devices;

import fr.unice.i3s.modalis.jseduite.technical.profiles.params.Param;

/**
 *
 * @author Steve Colombié
 */
public class ParamData {

    private boolean isDefault;
    private Param param;

    public ParamData() {
        
    }

    public boolean getIsDefault() {
        //return false;
        return (param.getValue().equals("") || param.getValue().equals(param.getDefaultValue()));
    }

    public Param getParam() {
        return param;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public void setParam(Param param) {
        this.param = param;
    }
}
