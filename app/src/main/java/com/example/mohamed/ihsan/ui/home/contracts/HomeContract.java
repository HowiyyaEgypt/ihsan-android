package com.example.mohamed.ihsan.ui.home.contracts;

import com.example.mohamed.ihsan.repositories.feed.Feed;
import com.example.mohamed.ihsan.repositories.organization.Organization;

import java.util.ArrayList;

/**
 * Created by Mohamed on 11/05/2018.
 */

public interface HomeContract {

    interface View {
        void displayLatestFeed(ArrayList<Feed> feeds, int totalMeals, int totalDeliveries, int totalKitchens);
        void displayOrganizations(ArrayList<Organization> alreadyJoinedOrganizations, ArrayList<Organization> availableOrganizations);
        void navigateToOrganizationAfterJoiningOrViewing(Organization organization, int mode);
        void navigateToHomeAfterLeavingOrganization(Organization organization);
    }

    interface Presenter {
        void setView(HomeContract.View view);
        void requestLatestFeed();
        void requestOrganizations();
        void viewOrganization(Organization organization);
        void joinOrganization(Organization organization);
        void leaveOrganization(Organization organization);
    }

}
