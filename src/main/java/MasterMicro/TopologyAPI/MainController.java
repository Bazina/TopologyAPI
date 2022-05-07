package MasterMicro.TopologyAPI;

import MasterMicro.TopologyAPI.DataAccessObject.DAO;
import MasterMicro.TopologyAPI.DataAccessObject.DAOI;
import MasterMicro.TopologyAPI.Device.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@CrossOrigin()
@RestController
@RequestMapping("/topology")
public class MainController {
    private static final DAOI dao = new DAO();

    /***
     * Reads the topology with the given file name from JSON file.
     * @param fileName the name of the file to read.
     * @return True if the progress succeeded, false if not.
     * @throws IOException if the file cannot be read.
     */
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public boolean readJSON(@RequestParam String fileName) throws IOException {
        dao.load(fileName);
        return true;
    }

    /***
     * Writes the topology with the given file name to JSON file.
     * @param TopologyID the name of the file to write.
     * @return True if the progress succeeded, false if not.
     * @throws IOException if the file cannot be written.
     */
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public boolean writeJSON(@RequestBody String TopologyID) throws IOException {
        dao.save(TopologyID);
        return true;
    }

    /***
     * Deletes a topology with a given ID from the database.
     * @param TopologyID the ID of the topology to delete.
     * @return True if the progress succeeded, false if not.
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public boolean deleteJSON(@RequestParam String TopologyID) {
        dao.delete(TopologyID);
        return true;
    }

    /***
     * Queries the topologies saved in the DAO.
     * @return an ArrayList of the topologies saved in the DAO.
     */
    @RequestMapping(value = "/queryTopologies", method = RequestMethod.GET)
    public ArrayList<Topology> queryTopologies() {
        return dao.getTopologies();
    }

    /***
     * Queries the devices saved in the DAO within a topology with a given ID.
     * @param TopologyID the ID of the topology to query.
     * @return an ArrayList of the devices saved in the DAO within a topology with a given ID.
     */
    @RequestMapping(value = "/queryDevices", method = RequestMethod.GET)
    public ArrayList<Component> queryDevices(@RequestParam String TopologyID) {
        return dao.queryDevices(TopologyID);
    }

    /***
     * Queries the devices saved in the DAO within a topology with a given ID and have a specific node.
     * @param TopologyID the ID of the topology to query.
     * @param NetlistNodeID the ID of the node to query.
     * @return an ArrayList of the devices saved in the DAO within a topology with a given ID and have a specific node.
     */
    @RequestMapping(value = "/queryNetlist", method = RequestMethod.GET)
    public ArrayList<Component> queryDevicesWithNetlistNode(@RequestParam String TopologyID,
                                                            @RequestParam String NetlistNodeID) {
        return dao.queryDevicesWithNetlistNode(TopologyID, NetlistNodeID);
    }
}
