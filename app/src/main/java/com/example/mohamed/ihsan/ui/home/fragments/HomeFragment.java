package com.example.mohamed.ihsan.ui.home.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.dagger.App;
import com.example.mohamed.ihsan.repositories.feed.Feed;
import com.example.mohamed.ihsan.repositories.organization.Organization;
import com.example.mohamed.ihsan.ui.home.adapters.FeedRecyclerAdapter;
import com.example.mohamed.ihsan.ui.home.contracts.HomeContract;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed on 28/04/2018.
 */

public class HomeFragment extends Fragment implements HomeContract.View {

    private int totalDonatedMeals;
    private int totalDeliveries;
    private int totalKitchens;
    private ArrayList<Feed> feeds;

    private static final String PERSISTENT_FEED = "persistentFeed";
    private static final String PERSISTENT_MEALS = "persistentMeals";
    private static final String PERSISTENT_DELIVERIES = "persistentDeliveries";
    private static final String PERSISTENT_KITCHENS = "persistentKitchens";

    @BindView(R.id.tv_donated_meals_count)
    TextView tv_donated_meals_count;

    @BindView(R.id.tv_deliveries_count)
    TextView tv_deliveries_count;

    @BindView(R.id.tv_kitchens_count)
    TextView tv_kitchens_count;

    @BindView(R.id.empty_view)
    TextView empty_view;

    @BindView(R.id.rv_feed_recycler)
    RecyclerView rv_feed_recycler;

    private FeedRecyclerAdapter adapter;

    @Inject
    HomeContract.Presenter presenter;

    public HomeFragment() {
        setArguments(new Bundle());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home_from_home, container, false);

        ButterKnife.bind(this, root);

        presenter.setView(this);

        // to make the fragment calls the api one time only, then cache the result
        if (feeds == null) {
            presenter.requestLatestFeed();
        } else {
            Bundle mySavedInstanceState = getArguments();
            feeds = (ArrayList<Feed>) mySavedInstanceState.getSerializable(PERSISTENT_FEED);
            totalDonatedMeals = mySavedInstanceState.getInt(PERSISTENT_MEALS);
            totalDeliveries = mySavedInstanceState.getInt(PERSISTENT_DELIVERIES);
            totalKitchens = mySavedInstanceState.getInt(PERSISTENT_KITCHENS);
            displayLatestFeed(feeds, totalDonatedMeals, totalDeliveries , totalKitchens);
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

        Bundle b = getArguments();

        b.putSerializable(PERSISTENT_FEED, feeds);
        getArguments().putInt(PERSISTENT_MEALS, 0);
        getArguments().putInt(PERSISTENT_DELIVERIES, 0);
        getArguments().putInt(PERSISTENT_KITCHENS, 0);
    }


    @Override
    public void displayLatestFeed(ArrayList<Feed> feeds, int totalMeals, int totalDeliveries, int totalKitchens) {
        this.feeds = feeds;
        this.totalDonatedMeals = totalMeals;
        this.totalDeliveries = totalDeliveries;
        this.totalKitchens = totalKitchens;

        adapter = new FeedRecyclerAdapter(getActivity(), feeds);
        rv_feed_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_feed_recycler.setAdapter(adapter);

        if(feeds.size() > 0) {
            empty_view.setVisibility(View.GONE);
        }

        tv_donated_meals_count.setText("" + totalMeals);
        tv_deliveries_count.setText("" + totalDeliveries);
        tv_kitchens_count.setText("" + totalKitchens);
    }

    @Override
    public void displayOrganizations(ArrayList<Organization> alreadyJoinedOrganizations, ArrayList<Organization> availableOrganizations) {
        // do nothing
    }

    @Override
    public void navigateToOrganizationAfterJoiningOrViewing(Organization organization, int mode) {
        // do nothing
    }

    @Override
    public void navigateToHomeAfterLeavingOrganization(Organization organization) {
        // do nothing
    }
}
