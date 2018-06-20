package com.example.mohamed.ihsan.repositories.organization.responses;

import com.example.mohamed.ihsan.repositories.organization.MixedOrganizations;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 11/05/2018.
 */

public class MixedOrganizationResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private MixedOrganizations mixedOrganizations;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public MixedOrganizations getMixedOrganizations() {
        return mixedOrganizations;
    }
}
