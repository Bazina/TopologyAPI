package MasterMicro.TopologyAPI.DataAccessObject;

import MasterMicro.TopologyAPI.MainController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

class DAOTest {
    private final DAOI dao = new DAO();

    @BeforeEach
    public void setup() throws IOException {
        dao.load("src/main/resources/static/topology.json");
    }

    @Test
    void load() {
        Assertions.assertDoesNotThrow(() -> dao.load("src/main/resources/static/topology2.json"));
    }

    @Test
    void save() {
        Assertions.assertDoesNotThrow(() -> dao.save("top1"));
    }

    @Test
    void delete() throws IOException {
        dao.load("src/main/resources/static/topology2.json");
        Assertions.assertDoesNotThrow(() -> dao.delete("top1"));
    }

    @Test
    void deleteSize() throws IOException {
        dao.load("src/main/resources/static/topology2.json");
        dao.delete("top1");
        Assertions.assertEquals(1, dao.getTopologies().size());
    }

    @Test
    void queryDevices() {
        Assertions.assertEquals(2, dao.queryDevices("top1").size());
    }

    @Test
    void queryDevicesWithNetlistNode() {
        Assertions.assertEquals(1, dao.queryDevicesWithNetlistNode("top1", "vdd").size());
    }
}