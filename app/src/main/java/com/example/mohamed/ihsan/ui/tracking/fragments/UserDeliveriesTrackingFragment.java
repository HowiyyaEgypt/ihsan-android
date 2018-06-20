package com.example.mohamed.ihsan.ui.tracking.fragments;

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
import com.example.mohamed.ihsan.repositories.delivery.Delivery;
import com.example.mohamed.ihsan.repositories.meal.Meal;
import com.example.mohamed.ihsan.ui.tracking.adapter.UserDeliveriesTrackingRecyclerAdapter;
import com.example.mohamed.ihsan.ui.tracking.contracts.TrackingActionEchoContract;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed on 22/05/2018.
 */

public class UserDeliveriesTrackingFragment extends Fragment implements TrackingActionEchoContract.SyncTrackingFragment{

    @BindView(R.id.rv_user_deliveries_tracking)
    RecyclerView rv_user_deliveries_tracking;

    @BindView(R.id.empty_view)
    TextView empty_view;

    private ArrayList<Delivery> deliveries;
    private TrackingActionEchoContract.ParentView echoContract;
    private UserDeliveriesTrackingRecyclerAdapter adapter;

    public void setDeliveries(ArrayList<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user_deliveries_tracking, container, false);

        ButterKnife.bind(this, root);

        if (deliveries != null && deliveries.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
            rv_user_deliveries_tracking.setVisibility(View.GONE);
        } else {
            initializeAdapter();
        }

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.echoContract = (TrackingActionEchoContract.ParentView) context;
    }

    @Override
    public void syncFragment(Delivery delivery) {
        if (deliveries != null && deliveries.size() == 0) {
            initializeAdapter();
            adapter.syncAdapter(delivery);
            checkListSizeAfterSyncing();
        } else {
            adapter.syncAdapter(delivery);
            checkListSizeAfterSyncing();
        }
    }

    @Override
    public void syncFragment(Meal meal) {
        // do nothing
    }

    private void initializeAdapter() {
        adapter = new UserDeliveriesTrackingRecyclerAdapter(getActivity(), deliveries, echoContract);
        rv_user_deliveries_tracking.setAdapter(adapter);
        rv_user_deliveries_tracking.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void checkListSizeAfterSyncing() {
        if (deliveries != null && deliveries.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
            rv_user_deliveries_tracking.setVisibility(View.GONE);
        } else {
            empty_view.setVisibility(View.GONE);
            rv_user_deliveries_tracking.setVisibility(View.VISIBLE);
        }
    }
}
