package com.example.technicaltestparas.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MsgModel {
    @SerializedName("_id")
    String id;
    @SerializedName("doctor_id")
    String doctorId;
    @SerializedName("model_type")
    String modelType;
    @SerializedName("Time")
    ArrayList<TimeModel> timeList;
    @SerializedName("Available")
    boolean available;
    @SerializedName("AvailableType")
    String availableType;
    @SerializedName("Days")
    ArrayList<String> daysList;
    @SerializedName("created")
    String created;
    @SerializedName("modified")
    String modified;


    public MsgModel(String id, String doctorId, String modelType, ArrayList<TimeModel> timeList, boolean available, String availableType, ArrayList<String> daysList, String created, String modified) {
        this.id = id;
        this.doctorId = doctorId;
        this.modelType = modelType;
        this.timeList = timeList;
        this.available = available;
        this.availableType = availableType;
        this.daysList = daysList;
        this.created = created;
        this.modified = modified;
    }

    public MsgModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public ArrayList<TimeModel> getTimeList() {
        return timeList;
    }

    public void setTimeList(ArrayList<TimeModel> timeList) {
        this.timeList = timeList;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getAvailableType() {
        return availableType;
    }

    public void setAvailableType(String availableType) {
        this.availableType = availableType;
    }

    public ArrayList<String> getDaysList() {
        return daysList;
    }

    public void setDaysList(ArrayList<String> daysList) {
        this.daysList = daysList;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
}
