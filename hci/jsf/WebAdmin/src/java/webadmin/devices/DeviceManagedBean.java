package webadmin.devices;

import javax.xml.ws.WebServiceRef;
import fr.unice.i3s.modalis.jseduite.technical.profiles.devices.Device;
import fr.unice.i3s.modalis.jseduite.technical.profiles.devices.DeviceCRUD;
import fr.unice.i3s.modalis.jseduite.technical.profiles.devices.DeviceCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.profiles.devices.DeviceFinder;
import fr.unice.i3s.modalis.jseduite.technical.profiles.devices.DeviceFinderService;
import fr.unice.i3s.modalis.jseduite.technical.profiles.sources.SourceCRUD;
import fr.unice.i3s.modalis.jseduite.technical.profiles.sources.SourceCRUDService;
import fr.unice.i3s.modalis.jseduite.technical.profiles.sources.SourceFinder;
import fr.unice.i3s.modalis.jseduite.technical.profiles.sources.SourceFinderService;
import fr.unice.i3s.modalis.jseduite.technical.profiles.params.Param;
import fr.unice.i3s.modalis.jseduite.technical.profiles.params.ParamFinder;
import fr.unice.i3s.modalis.jseduite.technical.profiles.params.ParamFinderService;
import fr.unice.i3s.modalis.jseduite.technical.profiles.params.ParamCreator;
import fr.unice.i3s.modalis.jseduite.technical.profiles.params.ParamCreatorService;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Steve Colombié
 */

public class DeviceManagedBean {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/ProfileManager/DeviceFinderService?wsdl")
    DeviceFinderService finderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/ProfileManager/DeviceCRUDService?wsdl")
    DeviceCRUDService crudService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/ProfileManager/SourceFinderService?wsdl")
    SourceFinderService sourceFinderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/ProfileManager/SourceCRUDService?wsdl")
    SourceCRUDService sourceCrudService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/ProfileManager/ParamFinderService?wsdl")
    ParamFinderService paramFinderService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/jSeduite/ProfileManager/ParamCreatorService?wsdl")
    ParamCreatorService paramCreatorService;


    //The list of the devices
    private ArrayList<Device> devices;

    // The list cardinality
    private int devicesCard;

    //The transient devices
    private Device cDevice = new Device();
    private Device uDevice = new Device();

    //The current ID
    private String name = "";

    //The list of the sources
    private ArrayList<SourceData> sources;

    //The source to add a call
    private String sourceAddition;

    //The source contening the call to delete
    private String sourceDeletion;

    //The call to delete
    private int callDeletion;


    /**
     * Constructor
     */
    public DeviceManagedBean () {

    }

    /**
     * Get the devices cardinality
     * @return the devices cardinality
     */
    public int getDeviceCard() {
        return devicesCard;
    }

    /**
     * Get the created device
     * @return the created device
     */
    public Device getcDevice() {
        return cDevice;
    }

    /**
     * Set the created device
     * @param d the device to create
     */
    public void setcDevice(Device d) {
        this.cDevice = d;
    }

    /**
     * Get the device to update
     * @return the device to update
     */
    public Device getuDevice() {
        return uDevice;
    }

    /**
     * Set the device to update
     * @param d the device to update
     */
    public void setuDevice(Device d) {
        this.uDevice = d;
    }


    /**
     * Get the identifier
     * @return the identifer
     */
    public String getName() {
        return this.name;

    }

    /**
     * Set the identifier
     * @param name the identifier
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the sources
     * @return the list of sources
     */
    public ArrayList<SourceData> getSources() {
        return sources;
    }

    /**
     * Get the source to add a call
     * @return the source to add a call
     */
    public String getSourceAddition() {
        return sourceAddition;
    }

    /**
     * Set the source to add a call
     * @param sourceAddition the source to add a call
     */
    public void setSourceAddition(String sourceAddition) {
        this.sourceAddition = sourceAddition;
    }

    /**
     * Get the source containing the call to delete
     * @return the source containing the call to delete
     */
    public String getSourceDeletion() {
        return sourceDeletion;
    }

    /**
     * Set the source containing the call to delete
     * @param sourceDeletion the source containing the call to delete
     */
    public void setSourceDeletion(String sourceDeletion) {
        this.sourceDeletion = sourceDeletion;
    }

    /**
     * Get the call to delete
     * @return the call to delete
     */
    public int getCallDeletion() {
        return callDeletion;
    }

    /**
     * Set the call to delete
     * @param callDeletion the call to delete
     */
    public void setCallDeletion(int callDeletion) {
        this.callDeletion = callDeletion;
    }

    /**
     * Get the devices
     * @return a list of the devices
     */
    public ArrayList<Device> getDevices() {
        devices = new ArrayList<Device>();

        try {
            //Get the devices ids
            this.finderService = new DeviceFinderService();
            DeviceFinder finderPort = finderService.getDeviceFinderPort();
            List<String> deviceNames = finderPort.getAllDeviceReferences();

            //Get the devices
            this.crudService = new DeviceCRUDService();
            DeviceCRUD crudPort = crudService.getDeviceCRUDPort();

            for(int i=0; i<deviceNames.size(); i++) {
                devices.add(crudPort.readDevice(deviceNames.get(i)));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        devicesCard = devices.size();

        return devices;
    }


    /**
     * Create a device
     * @return a string indicating the device is created
     */
    public String create() {
        try {
            this.crudService = new DeviceCRUDService();
            DeviceCRUD crud = crudService.getDeviceCRUDPort();

            crud.createDevice(cDevice);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        cDevice = new Device();

        return "created";
    }

    /**
     * Cancel modifications
     * @return a string indicating modification/creation is canceled
     */
    public String cancel() {
        return "cancel";
    }


    /**
     * Delete the device corresponding with the identifier
     */
    public void delete() {
        try {

            this.crudService = new DeviceCRUDService();
            DeviceCRUD crud = crudService.getDeviceCRUDPort();

            Device DeviceToDelete = crud.readDevice(name);
            crud.deleteDevice(DeviceToDelete);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initiate the update process
     * @return a string indicating the update is ready to be done
     */
    public String goUpdate()
    {
        try {
            this.crudService = new DeviceCRUDService();
            DeviceCRUD crud = crudService.getDeviceCRUDPort();

            this.paramFinderService = new ParamFinderService();
            ParamFinder paramFinder = paramFinderService.getParamFinderPort();

            this.sourceFinderService = new SourceFinderService();
            SourceFinder sourceFinder = sourceFinderService.getSourceFinderPort();

            this.sourceCrudService = new SourceCRUDService();
            SourceCRUD sourceCrud = sourceCrudService.getSourceCRUDPort();

            // Read the device to update
            uDevice = crud.readDevice(name);

            // Get the list of available sources
            List<String> nicknames = sourceFinder.getAllSourceReferences();
            sources = new ArrayList<SourceData>();

            for(String nickname : nicknames) {
                SourceData buffer = new SourceData();
                buffer.setCalls(new ArrayList<CallData>());
                buffer.setSource(sourceCrud.readSource(nickname));

                // Set default parameters
                for(int i=1; i<=paramFinder.getParameterCard(nickname, name); i++) {
                    CallData call = new CallData((ArrayList<Param>) paramFinder.getParametersForSource(nickname));
                    call.setSetId(i);
                    buffer.getCalls().add(call);
                }

                // Set effective parameters
                List<Param> effectiveParam = paramFinder.getParameters(nickname, name);
                for(Param parameter : effectiveParam) {
                    buffer.getCalls().get(parameter.getSetId()-1).addParameter(parameter);
                }

                sources.add(buffer);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "update";
    }

    /**
     * Update the uDevice device
     * @return a string indicating the device is updated
     */
    public String update() {
        try {
            this.crudService = new DeviceCRUDService();
            DeviceCRUD crud = crudService.getDeviceCRUDPort();

            this.paramCreatorService = new ParamCreatorService();
            ParamCreator paramCreator = paramCreatorService.getParamCreatorPort();

            // Reinitialization
            crud.deleteDevice(uDevice);
            crud.createDevice(uDevice);

            for(SourceData source : sources) {
                // Device subscription
                paramCreator.subscription(uDevice.getName(), source.getSource().getNickname(), source.getCalls().size());

                // Device parametrization
                for(CallData call : source.getCalls()) {
                    for(ParamData param : call.getParameters()) {
                        paramCreator.parametrization(uDevice.getName(), param.getParam(), call.getSetId());
                    }
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "updated";
    }

    /**
     * Set the sort method
     * @return a string indicating the device are ready to be sorted
     */
    public String sortBy() {
        return "sorted";
    }

    /**
     * Add the new calls
     * @return a string indicating the addition of call is done
     */
    public String addition() {
        for(SourceData source : sources)
        {
            source.setAnchor("");

            if(source.getSource().getNickname().equals(sourceAddition)){
                try {
                    this.paramFinderService = new ParamFinderService();
                    ParamFinder paramFinder = paramFinderService.getParamFinderPort();

                    // Set default parameters
                    source.setAnchor("anchor");
                    CallData call = new CallData((ArrayList<Param>) paramFinder.getParametersForSource(sourceAddition));
                    call.setSetId(source.getCalls().size()+1);
                    source.getCalls().add(call);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return "add";
    }

    /**
     * Delete a call
     * @return a string indicating the call is deleted
     */
    public String deletion() {
        for(SourceData source : sources)
        {
            source.setAnchor("");

            if(source.getSource().getNickname().equals(sourceDeletion)) {
                source.getCalls().remove(callDeletion-1);

                source.setAnchor("anchor");

                //setId Refactoring
                for(int i=0; i<source.getCalls().size(); i++) {
                    source.getCalls().get(i).setSetId(i+1);
                }
            }
        }

        return "del";
    }
}
