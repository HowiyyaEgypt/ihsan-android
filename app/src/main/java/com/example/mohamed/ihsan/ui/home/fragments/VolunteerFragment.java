package com.example.mohamed.ihsan.ui.home.fragments;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.dagger.App;
import com.example.mohamed.ihsan.repositories.feed.Feed;
import com.example.mohamed.ihsan.repositories.organization.Organization;
import com.example.mohamed.ihsan.ui.home.adapters.SimpleOrganizationPagerAdapter;
import com.example.mohamed.ihsan.ui.home.contracts.HomeContract;
import com.example.mohamed.ihsan.ui.home.contracts.VolunteerEchoContract;
import com.example.mohamed.ihsan.ui.home.view.HomeActivity;
import com.example.mohamed.ihsan.ui.organization.view.OrganizationActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed on 28/04/2018.
 */

public class VolunteerFragment extends Fragment implements HomeContract.View, VolunteerEchoContract {

    // saving state variables
    private int counter = 0;
    private static final String PERSISTENT_COUNTER_VARIABLE = "persistentCounterValue";
    private static final String PERSISTENT_ORGANIZATION_A = "persistentOrganizationA";
    private static final String PERSISTENT_ORGANIZATION_B = "persistentOrganizationB";

    @Inject
    HomeContract.Presenter presenter;

    @BindView(R.id.pb_organizations)
    ProgressBar pb_organizations;

    @BindView(R.id.vp_organizations)
    ViewPager vp_organizations;

    @BindView(R.id.tl_organizations_tabs)
    TabLayout tl_organizations_tabs;

    // organizations
    private ArrayList<Organization> alreadyJoinedOrganizations;
    private ArrayList<Organization> availableToJoinOrganization;

    public VolunteerFragment() {
        setArguments(new Bundle());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragmet_volunteer_from_home, container, false);

        ButterKnife.bind(this, root);

        presenter.setView(this);

        // to make the fragment calls the api one time only, then cache the result
        if (alreadyJoinedOrganizations == null && availableToJoinOrganization == null) {
            presenter.requestOrganizations();
        } else {
            Bundle mySavedInstanceState = getArguments();
            alreadyJoinedOrganizations = (ArrayList<Organization>) mySavedInstanceState.getSerializable(PERSISTENT_ORGANIZATION_A);
            availableToJoinOrganization = (ArrayList<Organization>) mySavedInstanceState.getSerializable(PERSISTENT_ORGANIZATION_B);
            displayOrganizations(alreadyJoinedOrganizations, alreadyJoinedOrganizations);
        }


        return root;
    }

    @Override
    public void onAttach(Context context) {
        ((App) getActivity().getApplication()).getComponent().inject(this);
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
        getArguments().putSerializable(PERSISTENT_ORGANIZATION_A, alreadyJoinedOrganizations);
        getArguments().putSerializable(PERSISTENT_ORGANIZATION_B, availableToJoinOrganization);
    }


    @Override
    public void displayLatestFeed(ArrayList<Feed> feeds, int totalMeals, int totalDeliveries, int totalKitchens) {
        // do nothing
    }

    @Override
    public void displayOrganizations(ArrayList<Organization> AlreadyJoinedOrganizations, ArrayList<Organization> AvailableOrganizations) {
        this.alreadyJoinedOrganizations = AlreadyJoinedOrganizations;
        this.availableToJoinOrganization = AvailableOrganizations;

        pb_organizations.setVisibility(View.GONE);

        vp_organizations.setAdapter(new SimpleOrganizationPagerAdapter(getChildFragmentManager(), getActivity(), alreadyJoinedOrganizations, availableToJoinOrganization));
        tl_organizations_tabs.setupWithViewPager(vp_organizations);
    }

    @Override
    public void navigateToOrganizationAfterJoiningOrViewing(Organization organization, int mode) {

        final Intent intent;
        final ActivityOptions options = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.frag_enter_from_left, R.anim.frag_exit_to_right);

        switch (mode) {
            case 1:
                intent = new Intent(getActivity(), OrganizationActivity.class);
                intent.putExtra("organization", organization);
                startActivity(intent, options.toBundle());
                break;

            case 2:
                Toast.makeText(getActivity(), "You have successfully joined " + organization.getName() , Toast.LENGTH_LONG).show();

                intent = new Intent(getActivity(), OrganizationActivity.class);
                intent.putExtra("organization", organization);


                (new Handler())
                        .postDelayed(
                                new Runnable() {
                                    public void run() {
                                        startActivity(intent, options.toBundle());
                                    }
                                }, 2000);
                break;
        }
    }

    @Override
    public void navigateToHomeAfterLeavingOrganization(Organization organization) {
        Toast.makeText(getActivity(), "You have left " + organization.getName() , Toast.LENGTH_LONG).show();

        final Intent intent = new Intent(getActivity(), HomeActivity.class);
        final ActivityOptions options = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.frag_enter_from_left, R.anim.frag_exit_to_right);
        intent.putExtra("organization", organization);

        (new Handler())
                .postDelayed(
                        new Runnable() {
                            public void run() {
                                startActivity(intent, options.toBundle());
                                getActivity().finish();
                            }
                        }, 2000);
    }

    @Override
    public void joinOrganization(Organization organization) {
        presenter.joinOrganization(organization);
    }

    @Override
    public void viewOrganization(Organization organization) {
        presenter.viewOrganization(organization);
    }

    @Override
    public void leaveOrganization(Organization organization) {
        presenter.leaveOrganization(organization);
    }
}
