package com.gzeinnumer.paginationmanualjava.base;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseListResponse<T> {

    @SerializedName("total")
    private int total;

    @SerializedName("data")
    private List<T> data;

    @SerializedName("title")
    private String title;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public int getTotal() {
        return total;
    }

    public List<T> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this, BaseListResponse.class);
    }
}
