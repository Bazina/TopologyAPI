package MasterMicro.TopologyAPI.Device;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.HashMap;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = NMOS.class, name = "nmos"),
        @JsonSubTypes.Type(value = Resistor.class, name = "resistor")
})
public abstract class Component {
    private String id;
    private HashMap<String, String> netList;
    protected Specifications specifications;

    public Component() {
    }

    public Component(String id, HashMap<String, String> netList) {
        this.id = id;
        this.netList = netList;
    }

    /***
     * Returns the nodes connected to a component.
     * @return the nodes connected to a component.
     */
    @JsonIgnore
    public HashMap<String, Boolean> getNodes() {
        HashMap<String, Boolean> nodes = new HashMap<>();

        for (String node : netList.values())
            nodes.put(node, true);

        return nodes;
    }

    /***
     * Returns the id of the component.
     * @return the id of the component.
     */
    @JsonProperty("id")
    protected String getId() {
        return id;
    }

    /***
     * Sets the id of the component.
     * @param id the id of the component.
     */
    protected void setId(String id) {
        this.id = id;
    }

    /***
     * Returns the netlist of the component.
     * @return the netlist of the component.
     */
    @JsonProperty("netlist")
    protected HashMap<String, String> getNetList() {
        return netList;
    }

    /***
     * Returns the type of the component.
     * @param netList the netlist of the component.
     */
    protected void setNetList(HashMap<String, String> netList) {
        this.netList = netList;
    }

    /***
     * Returns the specifications of the component.
     * @return the specifications of the component.
     */
    protected abstract Specifications getSpecifications();

    /***
     * Sets the specifications of the component.
     * @param specifications the specifications of the component.
     */
    protected abstract void setSpecifications(Specifications specifications);
}
