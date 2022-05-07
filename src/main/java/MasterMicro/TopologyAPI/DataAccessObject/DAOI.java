package MasterMicro.TopologyAPI.DataAccessObject;

import MasterMicro.TopologyAPI.Device.Component;
import MasterMicro.TopologyAPI.Topology;

import java.io.IOException;
import java.util.ArrayList;

public interface DAOI {
    /***
     * Loads a topology from a file.
     * @param fileName the name of the file.
     * @throws IOException if the file does not exist.
     */
    void load(String fileName) throws IOException;

    /***
     * Saves a given topology to a JSON file.
     * @param TopologyID the ID of the topology.
     * @throws IOException if the topology does not exist.
     */
    void save(String TopologyID) throws IOException;

    /***
     * Deletes the topology with the given ID.
     * @param ID the ID of the topology.
     */
    void delete(String ID);

    /***
     * Returns the components of the topology with the given ID.
     * @param TopologyID the ID of the topology.
     * @return the components of the topology with the given ID.
     */
    ArrayList<Component> queryDevices(String TopologyID);

    /***
     * Return the component that has specific node within a topology with a given ID.
     * @param TopologyID the ID of the topology.
     * @param NetlistNodeID the ID of the node.
     * @return the component that has specific node within a topology with a given ID.
     */
    ArrayList<Component> queryDevicesWithNetlistNode(String TopologyID, String NetlistNodeID);

    /***
     * Returns the topologies in the DAO.
     * @return the topologies in the DAO.
     */
    ArrayList<Topology> getTopologies();
}
