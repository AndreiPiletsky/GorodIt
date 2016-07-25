package com.example.andreika.conferencegorodit.POJO.Schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

@JsonPropertyOrder({
        "id",
        "about",
        "title"

})
public class Presentation {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("about")
    private String about;

    /**
     *
     * @return
     * The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The about
     */
    @JsonProperty("about")
    public String getAbout() {
        return about;
    }

    /**
     *
     * @param about
     * The about
     */
    @JsonProperty("about")
    public void setAbout(String about) {
        this.about = about;
    }

}