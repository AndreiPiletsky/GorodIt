package com.example.andreika.conferencegorodit.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by andreika on 13.07.2016.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

@JsonPropertyOrder({
        "site",
        "about",
        "logo",
        "isPartner",
        "company"




})

public class PartnerAndSponsor {
    @JsonProperty("site")
    private String site;
    @JsonProperty("about")
    private String about;
    @JsonProperty("logo")
    private String logo;
    @JsonProperty("isPartner")
    private Integer isPartner;
    @JsonProperty("company")
    private String company;





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
     * The site
     */
    @JsonProperty("site")
    public String getSite() {
        return site;
    }

    /**
     *
     * @param site
     * The site
     */
    @JsonProperty("site")
    public void setSite(String site) {
        this.site = site;
    }

    /**
     *
     * @return
     * The logo
     */
    @JsonProperty("logo")
    public String getLogo() {
        return logo;
    }

    /**
     *
     * @param logo
     * The logo
     */
    @JsonProperty("logo")
    public void setLogo(String logo) {
        this.logo = logo;
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

    /**
     *
     * @return
     * The isPartner
     */
    @JsonProperty("isPartner")
    public Integer getIsPartner() {
        return isPartner;
    }

    /**
     *
     * @param isPartner
     * The isPartner
     */
    @JsonProperty("isPartner")
    public void setIsPartner(Integer isPartner) {
        this.isPartner = isPartner;
    }

}