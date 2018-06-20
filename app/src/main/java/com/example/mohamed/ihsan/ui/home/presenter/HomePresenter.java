package com.example.mohamed.ihsan.ui.home.presenter;

import android.content.Context;

import com.example.mohamed.ihsan.helpers.Authentication.LocalAuthUserHelper;
import com.example.mohamed.ihsan.repositories.feed.Feed;
import com.example.mohamed.ihsan.repositories.feed.listeners.OnFeedReadyListener;
import com.example.mohamed.ihsan.repositories.feed.repository.FeedRepository;
import com.example.mohamed.ihsan.repositories.organization.Organization;
import com.example.mohamed.ihsan.repositories.organization.listeners.OnMixedOrganizationsReadyListener;
import com.example.mohamed.ihsan.repositories.organization.listeners.OnOrganizationActionListener;
import com.example.mohamed.ihsan.repositories.organization.repositories.OrganizationRepository;
import com.example.mohamed.ihsan.repositories.user.User;
import com.example.mohamed.ihsan.ui.home.contracts.HomeContract;

import java.util.ArrayList;

/**
 * Created by Mohamed on 11/05/2018.
 */

public class HomePresenter implements HomeContract.Presenter {

    private Context context;
    private HomeContract.View view;
    private OrganizationRepository organization_repository;
    private FeedRepository feed_repository;

    public HomePresenter(Context context, OrganizationRepository organizationRepository, FeedRepository feedRepository) {
        this.context = context;
        this.organization_repository = organizationRepository;
        this.feed_repository = feedRepository;
    }

    @Override
    public void setView(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void requestLatestFeed() {

        User localAuthUser = LocalAuthUserHelper.getLocalAuthUser(context);

        feed_repository.requestLatestFeed(localAuthUser.getToken(), new OnFeedReadyListener() {
            @Override
            public void onSuccess(ArrayList<Feed> feeds, int totalDonatedMeals, int totalDeliveries, int totalKitchens) {
                view.displayLatestFeed(feeds, totalDonatedMeals, totalDeliveries, totalKitchens);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    public void requestOrganizations() {

        User localAuthUser = LocalAuthUserHelper.getLocalAuthUser(context);

        organization_repository.getMixedOrganization(localAuthUser.getToken(), new OnMixedOrganizationsReadyListener() {
            @Override
            public void onSuccess(ArrayList<Organization> alreadyJoinedOrganizationsList, ArrayList<Organization> availableOrganizationList) {
                if (view != null) {
                    view.displayOrganizations(alreadyJoinedOrganizationsList, availableOrganizationList);
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    public void viewOrganization(Organization organization) {

        User localAuthUser = LocalAuthUserHelper.getLocalAuthUser(context);

        organization_repository.viewOrganization(localAuthUser.getToken(), organization.getId(), new OnOrganizationActionListener() {
            @Override
            public void onSuccess(Organization organization) {
                view.navigateToOrganizationAfterJoiningOrViewing(organization, 1);
            }

            @Override
            public void onFailure(String msg) {

            }
        });

    }

    @Override
    public void joinOrganization(Organization organization) {

        User localAuthUser = LocalAuthUserHelper.getLocalAuthUser(context);

        organization_repository.joinOrganization(localAuthUser.getToken(), organization.getId(), new OnOrganizationActionListener() {
            @Override
            public void onSuccess(Organization organization) {
                view.navigateToOrganizationAfterJoiningOrViewing(organization, 2);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    public void leaveOrganization(Organization organization) {

        User localAuthUser = LocalAuthUserHelper.getLocalAuthUser(context);

        organization_repository.leaveOrganization(localAuthUser.getToken(), organization.getId(), new OnOrganizationActionListener() {
            @Override
            public void onSuccess(Organization organization) {
                view.navigateToHomeAfterLeavingOrganization(organization);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
}
