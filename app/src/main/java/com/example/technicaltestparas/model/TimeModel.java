package com.example.technicaltestparas.model;

import com.google.gson.annotations.SerializedName;

public class TimeModel {
    @SerializedName("startTime")
    String startTime;
    @SerializedName("endTime")
    String endTime;
    @SerializedName("duration")
    int duration;
    @SerializedName("_id")
    String id;

    public TimeModel(String startTime, String endTime, int duration, String id) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.id = id;
    }

    public TimeModel() {
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
