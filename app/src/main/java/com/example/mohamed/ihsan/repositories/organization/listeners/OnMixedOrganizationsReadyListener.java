package com.example.mohamed.ihsan.repositories.organization.listeners;

import com.example.mohamed.ihsan.repositories.organization.Organization;

import java.util.ArrayList;

/**
 * Created by Mohamed on 11/05/2018.
 */

public interface OnMixedOrganizationsReadyListener {
    void onSuccess(ArrayList<Organization> alreadyJoinedOrganizationsList, ArrayList<Organization> availableOrganizationList);
    void onFailure(String msg);
}
