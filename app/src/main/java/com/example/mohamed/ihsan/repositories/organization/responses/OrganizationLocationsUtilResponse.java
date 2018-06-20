package com.example.mohamed.ihsan.repositories.organization.responses;

import com.example.mohamed.ihsan.repositories.organization.OrganizationLocationsUtil;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 17/05/2018.
 */

public class OrganizationLocationsUtilResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private OrganizationLocationsUtil organizationLocationsUtil;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public OrganizationLocationsUtil getOrganizationLocationsUtil() {
        return organizationLocationsUtil;
    }
}
