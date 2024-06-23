package com.example.technicaltestparas.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetSlotsDetailsRes {

    @SerializedName("msg")
    ArrayList<MsgModel> msg;
    @SerializedName("status")
    int status;

    public GetSlotsDetailsRes(ArrayList<MsgModel> msg, int status) {
        this.msg = msg;
        this.status = status;
    }

    public GetSlotsDetailsRes() {
    }

    public ArrayList<MsgModel> getMsg() {
        return msg;
    }

    public void setMsg(ArrayList<MsgModel> msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
