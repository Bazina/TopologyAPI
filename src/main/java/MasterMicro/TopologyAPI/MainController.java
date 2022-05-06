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

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public boolean readJSON(@RequestParam String fileName) throws IOException {
        dao.load(fileName);
        return true;
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public boolean writeJSON(@RequestBody String TopologyID) throws IOException {
        dao.save(TopologyID);
        return true;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public boolean deleteJSON(@RequestParam String TopologyID) {
        dao.delete(TopologyID);
        return true;
    }

    @RequestMapping(value = "/queryTopologies", method = RequestMethod.GET)
    public ArrayList<Topology> queryTopologies() {
        return dao.getTopologies();
    }

    @RequestMapping(value = "/queryDevices", method = RequestMethod.GET)
    public ArrayList<Component> queryDevices(@RequestParam String TopologyID) {
        return dao.queryDevices(TopologyID);
    }

    @RequestMapping(value = "/queryNetlist", method = RequestMethod.GET)
    public ArrayList<Component> queryDevicesWithNetlistNode(@RequestParam String TopologyID,
                                                            @RequestParam String NetlistNodeID) {
        return dao.queryDevicesWithNetlistNode(TopologyID, NetlistNodeID);
    }
}
