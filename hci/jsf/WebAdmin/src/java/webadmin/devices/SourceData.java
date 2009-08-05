package webadmin.devices;

import fr.unice.i3s.modalis.jseduite.technical.profiles.sources.Source;
import java.util.ArrayList;
/**
 *
 * @author Steve Colombié
 */
public class SourceData {
    private Source source;
    private ArrayList<CallData> calls;

    public SourceData() {

    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public ArrayList<CallData> getCalls() {
        return calls;
    }

    public void setCalls(ArrayList<CallData> calls) {
        this.calls = calls;
    }
}
