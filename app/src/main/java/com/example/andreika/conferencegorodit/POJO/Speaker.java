package com.example.andreika.conferencegorodit.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

@JsonPropertyOrder({
        "position",
        "id",
        "about",
        "photo",
        "name",
        "company",
        "city",


})
public class Speaker {
    @JsonProperty("position")
    private String position;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("about")
    private String about;
    @JsonProperty("photo")
    private String photo;
    @JsonProperty("name")
    private String name;

    @JsonProperty("company")
    private String company;
    @JsonProperty("city")
    private String city;



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
     * The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The position
     */
    @JsonProperty("position")
    public String getPosition() {
        return position;
    }

    /**
     *
     * @param position
     * The position
     */
    @JsonProperty("position")
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     *
     * @return
     * The company
     */
    @JsonProperty("company")
    public String getCompany() {
        return company;
    }

    /**
     *
     * @param company
     * The company
     */
    @JsonProperty("company")
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     *
     * @return
     * The city
     */
    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The photo
     */
    @JsonProperty("photo")
    public String getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo
     * The photo
     */
    @JsonProperty("photo")
    public void setPhoto(String photo) {
        this.photo = photo;
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

