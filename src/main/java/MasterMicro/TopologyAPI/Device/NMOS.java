package MasterMicro.TopologyAPI.Device;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.HashMap;

@JsonPropertyOrder({"type", "id", "m(l)", "netlist"})
@JsonTypeName("nmos")
public class NMOS extends Component {
    private Specifications specifications;

    public NMOS() {
    }

    public NMOS(String id, HashMap<String, String> netList, Specifications specifications) {
        super(id, netList);
        this.specifications = specifications;
    }

    @JsonProperty("m(l)")
    protected Specifications getSpecifications() {
        return specifications;
    }

    protected void setSpecifications(Specifications specifications) {
        this.specifications = specifications;
    }
}
