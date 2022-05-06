package MasterMicro.TopologyAPI.Device;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"default", "min", "max"})
public class Specifications {
    private double defaultValue, maxValue, minValue;

    public Specifications() {
    }

    public Specifications(String name, String type, double defaultValue, double maxValue, double minValue) {
        this.defaultValue = defaultValue;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    @JsonProperty("default")
    protected double getDefaultValue() {
        return defaultValue;
    }

    protected void setDefaultValue(double defaultValue) {
        this.defaultValue = defaultValue;
    }

    @JsonProperty("max")
    protected double getMaxValue() {
        return maxValue;
    }

    protected void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    @JsonProperty("min")
    protected double getMinValue() {
        return minValue;
    }

    protected void setMinValue(double minValue) {
        this.minValue = minValue;
    }
}
