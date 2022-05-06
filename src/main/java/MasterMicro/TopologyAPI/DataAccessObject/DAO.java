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

    public static void main(String[] args) throws IOException {
        DAO dao = new DAO();
        dao.load("src/main/resources/static/topology.json");
        dao.load("src/main/resources/static/topology2.json");
        dao.queryDevicesWithNetlistNode("top1", "n1");
        dao.queryDevices("top1");
        dao.queryDevicesWithNetlistNode("top2", "n2");
        dao.save("top1");
        dao.save("top2");
        dao.delete("top1");
    }

    @Override
    public void load(String fileName) throws IOException {
        Topology topology = mapper.readValue(new File(fileName), Topology.class);
        addTopology(topology);
    }

    @Override
    public void save(String TopologyID) throws IOException {
        Topology topology = topologies.get(TopologyID);
        mapper.writeValue(new File("src/main/resources/static/" + topology.getId() + ".json"), topology);
    }

    @Override
    public void delete(String ID) {
        topologies.remove(ID);
    }

    @Override
    public ArrayList<Component> queryDevices(String TopologyID) {
        return topologies.get(TopologyID).getComponents();
    }

    @Override
    public ArrayList<Component> queryDevicesWithNetlistNode(String TopologyID, String NetlistNodeID) {
        ArrayList<Component> components = new ArrayList<>();

        for (Component component : topologies.get(TopologyID).getComponents())
            if (component.getNodes().containsKey(NetlistNodeID))
                components.add(component);

        return components;
    }

    private void addTopology(Topology topology) {
        topologies.put(topology.getId(), topology);
    }

    @Override
    public ArrayList<Topology> getTopologies() {
        return new ArrayList<>(topologies.values());
    }
}
