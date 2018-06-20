package com.example.mohamed.ihsan.repositories.organization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mohamed on 11/05/2018.
 */

public class MixedOrganizations implements Serializable {

    @SerializedName("already_joined_organizations")
    @Expose
    ArrayList<Organization> alreadyJoinedOrganizations;

    @SerializedName("available_to_join_organizations")
    @Expose
    ArrayList<Organization> availableToJoinOrganizations;

    public ArrayList<Organization> getAlreadyJoinedOrganizations() {
        return alreadyJoinedOrganizations;
    }

    public ArrayList<Organization> getAvailableToJoinOrganizations() {
        return availableToJoinOrganizations;
    }
}
