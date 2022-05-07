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

    @JsonIgnore
    public HashMap<String, Boolean> getNodes() {
        HashMap<String, Boolean> nodes = new HashMap<>();

        for (String node : netList.values())
            nodes.put(node, true);

        return nodes;
    }

    @JsonProperty("id")
    protected String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    @JsonProperty("netlist")
    protected HashMap<String, String> getNetList() {
        return netList;
    }

    protected void setNetList(HashMap<String, String> netList) {
        this.netList = netList;
    }

    protected abstract Specifications getSpecifications();

    protected abstract void setSpecifications(Specifications specifications);
}
