package com.example.mohamed.ihsan.repositories.organization.repositories;

import com.example.mohamed.ihsan.repositories.organization.listeners.OnMixedOrganizationsReadyListener;
import com.example.mohamed.ihsan.repositories.organization.listeners.OnOrganizationActionListener;
import com.example.mohamed.ihsan.repositories.organization.listeners.OnOrganizationLocationsUtilReadyListener;

/**
 * Created by Mohamed on 11/05/2018.
 */

public interface OrganizationRepository {
    void getMixedOrganization(String token, OnMixedOrganizationsReadyListener listener);
    void viewOrganization(String token, int organizationId, OnOrganizationActionListener listener);
    void joinOrganization(String token, int organizationId, OnOrganizationActionListener listener);
    void leaveOrganization(String token, int organizationId, OnOrganizationActionListener listener);
    void getOrganizationLocationUtils(int organizationId, OnOrganizationLocationsUtilReadyListener listener);
}
