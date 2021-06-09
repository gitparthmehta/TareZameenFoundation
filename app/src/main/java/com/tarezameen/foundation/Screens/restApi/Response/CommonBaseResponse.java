package com.tarezameen.foundation.Screens.restApi.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tarezameen.foundation.Screens.model.RegisterRoleListModel;

import java.util.ArrayList;

public class CommonBaseResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ArrayList<RegisterRoleListModel> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<RegisterRoleListModel> getData() {
        return data;
    }

    public void setData(ArrayList<RegisterRoleListModel> data) {
        this.data = data;
    }
}
