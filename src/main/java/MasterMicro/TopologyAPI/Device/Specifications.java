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

    /***
     * Returns the defaultValue.
     * @return the defaultValue.
     */
    @JsonProperty("default")
    protected double getDefaultValue() {
        return defaultValue;
    }

    /***
     * Sets the defaultValue.
     * @param defaultValue the defaultValue to set.
     */
    protected void setDefaultValue(double defaultValue) {
        this.defaultValue = defaultValue;
    }

    /***
     * Returns the maxValue.
     * @return the maxValue.
     */
    @JsonProperty("max")
    protected double getMaxValue() {
        return maxValue;
    }

    /***
     * Sets the maxValue.
     * @param maxValue the maxValue to set.
     */
    protected void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    /***
     * Returns the minValue.
     * @return the minValue.
     */
    @JsonProperty("min")
    protected double getMinValue() {
        return minValue;
    }

    /***
     * Sets the minValue.
     * @param minValue the minValue to set.
     */
    protected void setMinValue(double minValue) {
        this.minValue = minValue;
    }
}
