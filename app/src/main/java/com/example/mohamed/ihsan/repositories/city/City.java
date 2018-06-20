package com.example.mohamed.ihsan.repositories.city;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 01/05/2018.
 */

public class City {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("name_ar")
    @Expose
    String nameAr;

    @SerializedName("name_en")
    @Expose
    String nameEn;

    public int getId() {
        return id;
    }

    public String getNameAr() {
        return nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

}
