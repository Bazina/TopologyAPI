package MasterMicro.TopologyAPI.DataAccessObject;

import MasterMicro.TopologyAPI.Device.Component;
import MasterMicro.TopologyAPI.Topology;

import java.io.IOException;
import java.util.ArrayList;

public interface DAOI {
    void load(String fileName) throws IOException;

    void save(String TopologyID) throws IOException;

    void delete(String ID);

    ArrayList<Component> queryDevices(String TopologyID);

    ArrayList<Component> queryDevicesWithNetlistNode(String TopologyID, String NetlistNodeID);

    ArrayList<Topology> getTopologies();
}
