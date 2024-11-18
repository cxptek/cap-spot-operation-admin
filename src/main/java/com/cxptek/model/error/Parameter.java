package com.cxptek.model.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Parameter {
    @JsonProperty(
            value = "field_name",
            index = 1)
    private String fieldName;
    @JsonProperty(
            index = 2)
    private Object value;

    public static Parameter of(String fieldName, Object value) {
        return new Parameter(fieldName, value);
    }

    @JsonProperty(
            value = "field_name",
            index = 1)
    public void setFieldName(final String fieldName) {
        this.fieldName = fieldName;
    }

    @JsonProperty(
            index = 2)
    public void setValue(final Object value) {
        this.value = value;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Parameter)) {
            return false;
        } else {
            Parameter other = (Parameter) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$fieldName = this.getFieldName();
                Object other$fieldName = other.getFieldName();
                if (this$fieldName == null) {
                    if (other$fieldName != null) {
                        return false;
                    }
                } else if (!this$fieldName.equals(other$fieldName)) {
                    return false;
                }

                Object this$value = this.getValue();
                Object other$value = other.getValue();
                if (this$value == null) {
                    if (other$value != null) {
                        return false;
                    }
                } else if (!this$value.equals(other$value)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Parameter;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $fieldName = this.getFieldName();
        result = result * 59 + ($fieldName == null ? 43 : $fieldName.hashCode());
        Object $value = this.getValue();
        result = result * 59 + ($value == null ? 43 : $value.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getFieldName();
        return "Parameter(fieldName=" + var10000 + ", value=" + String.valueOf(this.getValue()) + ")";
    }

    public Parameter(final String fieldName, final Object value) {
        this.fieldName = fieldName;
        this.value = value;
    }
}
