package MasterMicro.TopologyAPI.Device;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.HashMap;

@JsonPropertyOrder({"type", "id", "resistance", "netlist"})
@JsonTypeName("resistor")
public class Resistor extends Component {
    private Specifications specifications;

    public Resistor() {
    }

    public Resistor(String id, HashMap<String, String> netList, Specifications specifications) {
        super(id, netList);
        this.specifications = specifications;
    }

    @JsonProperty("resistance")
    protected Specifications getSpecifications() {
        return specifications;
    }

    protected void setSpecifications(Specifications specifications) {
        this.specifications = specifications;
    }
}
