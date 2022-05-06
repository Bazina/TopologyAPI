package MasterMicro.TopologyAPI;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

@WebMvcTest(MainController.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
class MainControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

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
    public void givenWac_whenServletContext_thenItProvidesMainController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assertions.assertNotNull(servletContext);
        Assertions.assertTrue(servletContext instanceof MockServletContext);
        Assertions.assertNotNull(webApplicationContext.getBean("mainController"));
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
    void queryTopologies() {
    }

    @Test
    void queryDevices() {
    }

    @Test
    void queryDevicesWithNetlistNode() {
    }
}