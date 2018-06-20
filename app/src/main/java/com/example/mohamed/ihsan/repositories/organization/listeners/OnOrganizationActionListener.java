package com.example.mohamed.ihsan.repositories.organization.listeners;

import com.example.mohamed.ihsan.repositories.organization.Organization;


/**
 * Created by Mohamed on 12/05/2018.
 */

public interface OnOrganizationActionListener {
    void onSuccess(Organization organization);
    void onFailure(String msg);
}
