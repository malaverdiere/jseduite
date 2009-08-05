package webadmin.devices;

import java.util.ArrayList;
import fr.unice.i3s.modalis.jseduite.technical.profiles.params.Param;

/**
 *
 * @author Steve Colombié
 */
public class CallData {
    private int setId;
    private ArrayList<ParamData> parameters;

    public CallData (ArrayList<Param> parameters) {
        setParameters(parameters);
    }

    public ArrayList<ParamData> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Param> parameters) {
        ArrayList<ParamData> newParameters = new ArrayList<ParamData>();

        ParamData buffer;

        for(Param param : parameters) {
            buffer = new ParamData();
            buffer.setParam(param);
            newParameters.add(buffer);
        }

        this.parameters = newParameters;
    }

    public int getSetId() {
        return setId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    void addParameter(Param parameter) {
        for(ParamData oldParam : parameters) {
            if(oldParam.getParam().getName().equals(parameter.getName())) {
                oldParam.getParam().setValue(parameter.getValue());
                oldParam.getParam().setSetId(setId);
            }
        }
    }
}
