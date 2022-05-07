package MasterMicro.TopologyAPI;

import MasterMicro.TopologyAPI.Device.Component;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Topology {
    private String id;
    private ArrayList<Component> components;

    public Topology() {
    }

    public Topology(String id, ArrayList<Component> components) {
        this.id = id;
        this.components = components;
    }

    /***
     * Returns the id.
     * @return the id.
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /***
     * Sets the id.
     * @param id the id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /***
     * Returns the components.
     * @return the components.
     */
    @JsonProperty("components")
    public ArrayList<Component> getComponents() {
        return components;
    }

    /***
     * Sets the components.
     * @param components the components.
     */
    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }
}
