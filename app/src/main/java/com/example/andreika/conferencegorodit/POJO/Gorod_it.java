package com.example.andreika.conferencegorodit.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

@JsonPropertyOrder({
        "about",
        "address",
        "bus",
        "trolley",
        "contact",
        "mobile_phone",
        "email"
})
public class Gorod_it {

    @JsonProperty("about")
    private String about;
    @JsonProperty("address")
    private String address;
    @JsonProperty("bus")
    private String bus;
    @JsonProperty("trolley")
    private String trolley;
    @JsonProperty("contact")
    private String contact;
    @JsonProperty("mobile_phone")
    private String mobilePhone;
    @JsonProperty("email")
    private String email;

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

    /**
     *
     * @return
     * The address
     */
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The bus
     */
    @JsonProperty("bus")
    public String getBus() {
        return bus;
    }

    /**
     *
     * @param bus
     * The bus
     */
    @JsonProperty("bus")
    public void setBus(String bus) {
        this.bus = bus;
    }

    /**
     *
     * @return
     * The trolley
     */
    @JsonProperty("trolley")
    public String getTrolley() {
        return trolley;
    }

    /**
     *
     * @param trolley
     * The trolley
     */
    @JsonProperty("trolley")
    public void setTrolley(String trolley) {
        this.trolley = trolley;
    }

    /**
     *
     * @return
     * The contact
     */
    @JsonProperty("contact")
    public String getContact() {
        return contact;
    }

    /**
     *
     * @param contact
     * The contact
     */
    @JsonProperty("contact")
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     *
     * @return
     * The mobilePhone
     */
    @JsonProperty("mobile_phone")
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     *
     * @param mobilePhone
     * The mobile_phone
     */
    @JsonProperty("mobile_phone")
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     *
     * @return
     * The email
     */
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

}