package com.example.mohamed.ihsan.repositories.feed;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mohamed on 01/06/2018.
 */

public class Feed implements Serializable{

    @SerializedName("id")
    private int id;

    @SerializedName("message_ar")
    private String messageAr;

    @SerializedName("message_en")
    private String messageEn;

    @SerializedName("date")
    private String date;

    public int getId() {
        return id;
    }

    public String getMessageAr() {
        return messageAr;
    }

    public String getMessageEn() {
        return messageEn;
    }

    public String getDate() {
        return date;
    }
}
