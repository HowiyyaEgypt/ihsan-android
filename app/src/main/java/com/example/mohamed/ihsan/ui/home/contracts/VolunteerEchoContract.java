package com.example.mohamed.ihsan.ui.home.contracts;

import com.example.mohamed.ihsan.repositories.organization.Organization;

/**
 * Created by Mohamed on 12/05/2018.
 */

public interface VolunteerEchoContract {
    void joinOrganization(Organization organization);
    void viewOrganization(Organization organization);
    void leaveOrganization(Organization organization);
}
