package MasterMicro.TopologyAPI;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

@WebMvcTest(MainController.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
class MainControllerTest {
    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders.standaloneSetup(new MainController()).apply(sharedHttpSession()).build();
        mvc.perform(get("http://localhost:8080/topology/read")
                .accept(MediaType.APPLICATION_JSON)
                .param("fileName", "src/main/resources/static/topology.json"));

        mvc.perform(get("http://localhost:8080/topology/read")
                .accept(MediaType.APPLICATION_JSON)
                .param("fileName", "src/main/resources/static/topology3.json"));
    }

    @Test
    void readJSON() throws Exception {
        mvc.perform(get("http://localhost:8080/topology/read")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("fileName", "src/main/resources/static/topology2.json"))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(result -> Assertions.assertEquals("true", result.getResponse().getContentAsString()));

    }

    @Test
    void writeJSON() throws Exception {
        mvc.perform(post("http://localhost:8080/topology/write")
                        .contentType(MediaType.APPLICATION_JSON).content("top1"))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(result -> Assertions.assertEquals("true", result.getResponse().getContentAsString()));
    }

    @Test
    void deleteJSON() throws Exception {
        mvc.perform(delete("http://localhost:8080/topology/delete")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("TopologyID", "top1"))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(result -> Assertions.assertEquals("true", result.getResponse().getContentAsString()));
    }

    @Test
    void queryTopologies() throws Exception {
        mvc.perform(delete("http://localhost:8080/topology/delete")
                .accept(MediaType.APPLICATION_JSON)
                .param("TopologyID", "top2"));

        mvc.perform(get("http://localhost:8080/topology/read")
                .accept(MediaType.APPLICATION_JSON)
                .param("fileName", "src/main/resources/static/topology.json"));

        String expected = "[{\"id\":\"top3\"," +
                          "\"components\":[{\"type\":\"resistor\"," +
                          "\"id\":\"r1\"," +
                          "\"resistance\":{\"default\":100.0," +
                          "\"min\":10.0," +
                          "\"max\":1000.0}," +
                          "\"netlist\":{\"t1\":\"Vdd\"," +
                          "\"t2\":\"vin\"}}," +
                          "{\"type\":\"resistor\"," +
                          "\"id\":\"r2\"," +
                          "\"resistance\":{\"default\":100.0," +
                          "\"min\":10.0," +
                          "\"max\":1000.0}," +
                          "\"netlist\":{\"t1\":\"Vdd\"," +
                          "\"t2\":\"n1\"}}," +
                          "{\"type\":\"nmos\"," +
                          "\"id\":\"m1\"," +
                          "\"m(l)\":{\"default\":2.0," +
                          "\"min\":1.0," +
                          "\"max\":2.0}," +
                          "\"netlist\":{\"gate\":\"vin\"," +
                          "\"source\":\"vss\"," +
                          "\"drain\":\"n1\"}}," +
                          "{\"type\":\"nmos\"," +
                          "\"id\":\"m2\"," +
                          "\"m(l)\":{\"default\":3.0," +
                          "\"min\":1.0," +
                          "\"max\":3.0}," +
                          "\"netlist\":{\"gate\":\"vin\"," +
                          "\"source\":\"vss\"," +
                          "\"drain\":\"n2\"}}]}," +
                          "{\"id\":\"top1\"," +
                          "\"components\":[{\"type\":\"resistor\"," +
                          "\"id\":\"res1\"," +
                          "\"resistance\":{\"default\":100.0," +
                          "\"min\":10.0," +
                          "\"max\":1000.0}," +
                          "\"netlist\":{\"t1\":\"vdd\"," +
                          "\"t2\":\"n1\"}}," +
                          "{\"type\":\"nmos\"," +
                          "\"id\":\"m1\"," +
                          "\"m(l)\":{\"default\":1.5," +
                          "\"min\":1.0," +
                          "\"max\":2.0}," +
                          "\"netlist\":{\"gate\":\"vin\"," +
                          "\"source\":\"vss\"," +
                          "\"drain\":\"n1\"}}]}]";

        mvc.perform(get("http://localhost:8080/topology/queryTopologies")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(result -> Assertions.assertEquals(expected, result.getResponse().getContentAsString()));
    }

    @Test
    void queryDevices() throws Exception {
        String expected = "[{\"type\":\"resistor\"," +
                          "\"id\":\"r1\"," +
                          "\"resistance\":{\"default\":100.0," +
                          "\"min\":10.0," +
                          "\"max\":1000.0}," +
                          "\"netlist\":{\"t1\":\"Vdd\"," +
                          "\"t2\":\"vin\"}}," +
                          "{\"type\":\"resistor\"," +
                          "\"id\":\"r2\"," +
                          "\"resistance\":{\"default\":100.0," +
                          "\"min\":10.0," +
                          "\"max\":1000.0}," +
                          "\"netlist\":{\"t1\":\"Vdd\"," +
                          "\"t2\":\"n1\"}}," +
                          "{\"type\":\"nmos\"," +
                          "\"id\":\"m1\"," +
                          "\"m(l)\":{\"default\":2.0," +
                          "\"min\":1.0," +
                          "\"max\":2.0}," +
                          "\"netlist\":{\"gate\":\"vin\"," +
                          "\"source\":\"vss\"," +
                          "\"drain\":\"n1\"}}," +
                          "{\"type\":\"nmos\"," +
                          "\"id\":\"m2\"," +
                          "\"m(l)\":{\"default\":3.0," +
                          "\"min\":1.0," +
                          "\"max\":3.0}," +
                          "\"netlist\":{\"gate\":\"vin\"," +
                          "\"source\":\"vss\"," +
                          "\"drain\":\"n2\"}}]";

        mvc.perform(get("http://localhost:8080/topology/queryDevices")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("TopologyID", "top3"))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(result -> Assertions.assertEquals(expected, result.getResponse().getContentAsString()));
    }

    @Test
    void queryDevicesWithNetlistNode() throws Exception {
        String expected = "[{\"type\":\"resistor\"," +
                          "\"id\":\"r2\"," +
                          "\"resistance\":{\"default\":100.0," +
                          "\"min\":10.0," +
                          "\"max\":1000.0}," +
                          "\"netlist\":{\"t1\":\"Vdd\"," +
                          "\"t2\":\"n1\"}}," +
                          "{\"type\":\"nmos\"," +
                          "\"id\":\"m1\"," +
                          "\"m(l)\":{\"default\":2.0," +
                          "\"min\":1.0," +
                          "\"max\":2.0}," +
                          "\"netlist\":{\"gate\":\"vin\"," +
                          "\"source\":\"vss\"," +
                          "\"drain\":\"n1\"}}]";

        mvc.perform(get("http://localhost:8080/topology/queryNetlist")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("TopologyID", "top3").param("NetlistNodeID", "n1"))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(result -> Assertions.assertEquals(expected, result.getResponse().getContentAsString()));
    }
}