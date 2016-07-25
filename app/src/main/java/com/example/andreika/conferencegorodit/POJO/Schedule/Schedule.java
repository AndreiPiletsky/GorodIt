package com.example.andreika.conferencegorodit.POJO.Schedule;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

@JsonPropertyOrder({
        "time_end",
        "time_start",
        "hall",
        "speakers",
        "presentation",
        "url",
        "section"






})
public class Schedule {
    @JsonProperty("section")
    private Section section;
    @JsonProperty("time_start")
    private long timeStart;
    @JsonProperty("time_end")
    private long timeEnd;
    @JsonProperty("hall")
    private Hall hall;
    @JsonProperty("presentation")
    private Presentation presentation;
    @JsonProperty("speakers")
    private List<Integer> speakers = new ArrayList<Integer>();
    @JsonProperty("url")
    private String url;

    /**
     *
     * @return
     * The section
     */
    @JsonProperty("section")
    public Section getSection() {
        return section;
    }

    /**
     *
     * @param section
     * The section
     */
    @JsonProperty("section")
    public void setSection(Section section) {
        this.section = section;
    }

    /**
     *
     * @return
     * The timeStart
     */
    @JsonProperty("time_start")
    public long getTimeStart() {
        return timeStart;
    }

    /**
     *
     * @param timeStart
     * The time_start
     */
    @JsonProperty("time_start")
    public void setTimeStart(long timeStart) {
        this.timeStart = timeStart;
    }

    /**
     *
     * @return
     * The timeEnd
     */
    @JsonProperty("time_end")
    public long getTimeEnd() {
        return timeEnd;
    }

    /**
     *
     * @param timeEnd
     * The time_end
     */
    @JsonProperty("time_end")
    public void setTimeEnd(long timeEnd) {
        this.timeEnd = timeEnd;
    }

    /**
     *
     * @return
     * The hall
     */
    @JsonProperty("hall")
    public Hall getHall() {
        return hall;
    }

    /**
     *
     * @param hall
     * The hall
     */
    @JsonProperty("hall")
    public void setHall(Hall hall) {
        this.hall = hall;
    }

    /**
     *
     * @return
     * The presentation
     */
    @JsonProperty("presentation")
    public Presentation getPresentation() {
        return presentation;
    }

    /**
     *
     * @param presentation
     * The presentation
     */
    @JsonProperty("presentation")
    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    /**
     *
     * @return
     * The speakers
     */
    @JsonProperty("speakers")
    public List<Integer> getSpeakers() {
        return speakers;
    }

    /**
     *
     * @param speakers
     * The speakers
     */
    @JsonProperty("speakers")
    public void setSpeakers(List<Integer> speakers) {
        this.speakers = speakers;
    }

    /**
     *
     * @return
     * The url
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

}