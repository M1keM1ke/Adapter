package ru.mike.adapter.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"temp"})
public class OpenWeatherResponse {
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public int getTemp() {
        HashMap<String, Object> tempInfo = (HashMap<String, Object>) additionalProperties.get("main");
        System.out.println();
       return (int)Math.round((double)tempInfo.get("temp") - 273.15);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}



