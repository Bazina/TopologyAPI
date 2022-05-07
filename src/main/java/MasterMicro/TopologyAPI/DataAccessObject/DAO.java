package MasterMicro.TopologyAPI.DataAccessObject;

import MasterMicro.TopologyAPI.Device.Component;
import MasterMicro.TopologyAPI.Topology;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DAO implements DAOI {
    private static final ObjectMapper mapper = new ObjectMapper();
    private HashMap<String, Topology> topologies;

    public DAO() {
        topologies = new HashMap<>();
    }

    /***
     * Loads a topology from a file.
     * @param fileName the name of the file.
     * @throws IOException if the file does not exist.
     */
    @Override
    public void load(String fileName) throws IOException {
        Topology topology = mapper.readValue(new File(fileName), Topology.class);
        addTopology(topology);
    }

    /***
     * Saves a given topology to a JSON file.
     * @param TopologyID the ID of the topology.
     * @throws IOException if the topology does not exist.
     */
    @Override
    public void save(String TopologyID) throws IOException {
        Topology topology = topologies.get(TopologyID);
        mapper.writeValue(new File("src/main/resources/static/" + topology.getId() + ".json"), topology);
    }

    /***
     * Deletes the topology with the given ID.
     * @param ID the ID of the topology.
     */
    @Override
    public void delete(String ID) {
        topologies.remove(ID);
    }

    /***
     * Returns the components of the topology with the given ID.
     * @param TopologyID the ID of the topology.
     * @return the components of the topology with the given ID.
     */
    @Override
    public ArrayList<Component> queryDevices(String TopologyID) {
        return topologies.get(TopologyID).getComponents();
    }

    /***
     * Returns the component that has specific node within a topology with a given ID.
     * @param TopologyID the ID of the topology.
     * @param NetlistNodeID the ID of the node.
     * @return the component that has specific node within a topology with a given ID.
     */
    @Override
    public ArrayList<Component> queryDevicesWithNetlistNode(String TopologyID, String NetlistNodeID) {
        ArrayList<Component> components = new ArrayList<>();

        for (Component component : topologies.get(TopologyID).getComponents())
            if (component.getNodes().containsKey(NetlistNodeID))
                components.add(component);

        return components;
    }

    /***
     * Adds a topology to the DAO.
     * @param topology the topology to be added.
     */
    private void addTopology(Topology topology) {
        topologies.put(topology.getId(), topology);
    }

    /***
     * Returns the topologies in the DAO.
     * @return the topologies in the DAO.
     */
    @Override
    public ArrayList<Topology> getTopologies() {
        return new ArrayList<>(topologies.values());
    }
}
