package MasterMicro.TopologyAPI.Device;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.HashMap;

@JsonPropertyOrder({"type", "id", "resistance", "netlist"})
@JsonTypeName("resistor")
public class Resistor extends Component {
    public Resistor() {
    }

    public Resistor(String id, HashMap<String, String> netList, Specifications specifications) {
        super(id, netList);
        this.specifications = specifications;
    }

    @Override
    @JsonProperty("resistance")
    protected Specifications getSpecifications() {
        return specifications;
    }

    @Override
    protected void setSpecifications(Specifications specifications) {
        this.specifications = specifications;
    }
}
