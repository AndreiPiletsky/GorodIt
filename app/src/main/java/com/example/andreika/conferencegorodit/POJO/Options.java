package com.example.andreika.conferencegorodit.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
 @JsonPropertyOrder({
        "version",
        "map"
})
/**
 * Created by andreika on 18.07.2016.
 */
public class Options {
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("map")
    private Integer map;

    /**
     *
     * @return
     * The version
     */
    @JsonProperty("version")
    public Integer getVersion() {
        return version;
    }

    /**
     *
     * @param version
     * The version
     */
    @JsonProperty("version")
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     *
     * @return
     * The map
     */
    @JsonProperty("map")
    public Integer getMap() {
        return map;
    }

    /**
     *
     * @param map
     * The map
     */
    @JsonProperty("map")
    public void setMap(Integer map) {
        this.map = map;
    }

}
