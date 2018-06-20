package com.example.mohamed.ihsan.repositories.organization.responses;

import com.example.mohamed.ihsan.repositories.organization.MixedOrganizations;
import com.example.mohamed.ihsan.repositories.organization.Organization;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 12/05/2018.
 */

public class SingleOrganizationResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Organization organization;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Organization getOrganization() {
        return organization;
    }
}
