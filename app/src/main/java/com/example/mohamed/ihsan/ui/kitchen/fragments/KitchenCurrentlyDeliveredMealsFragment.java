package com.example.mohamed.ihsan.ui.kitchen.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.meal.Meal;
import com.example.mohamed.ihsan.ui.kitchen.adapters.CurrentlyDeliveredMealsRecyclerAdapter;
import com.example.mohamed.ihsan.ui.kitchen.contracts.KitchenActionsEchoContract;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed on 19/05/2018.
 */

public class KitchenCurrentlyDeliveredMealsFragment extends Fragment implements KitchenActionsEchoContract.FragmentSync {

    private ArrayList<Meal> meals;
    private boolean canManage;
    private CurrentlyDeliveredMealsRecyclerAdapter adapter;
    private KitchenActionsEchoContract.ParentView parentView;

    public void setCanManage(boolean canManage) {
        this.canManage = canManage;
    }

    public void setCurrentlyDeliveredMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    @BindView(R.id.rv_kitchen_currently_delivered_meals)
    RecyclerView rv_kitchen_currently_delivered_meals;

    @BindView(R.id.empty_view)
    TextView empty_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_kitchen_currently_delivered_meals, container, false);

        ButterKnife.bind(this, root);

        if (meals != null && meals.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
            rv_kitchen_currently_delivered_meals.setVisibility(View.GONE);
        } else {
            initiateAdapter();
        }

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.parentView = (KitchenActionsEchoContract.ParentView) context;
    }

    @Override
    public void requestAdapterToSync(int mode, Meal meal) {
        // because meals can be null (was empty when the api was called, then a meal was added from another tab)
        if (meals != null && meals.size() == 0) {
            initiateAdapter();
            adapter.syncMeals(mode, meal);
            checkListSizeAfterSyncing();
        } else {
            adapter.syncMeals(mode, meal);
            checkListSizeAfterSyncing();
        }

    }

    private void initiateAdapter() {
        adapter = new CurrentlyDeliveredMealsRecyclerAdapter(getActivity(), meals, canManage, parentView);
        rv_kitchen_currently_delivered_meals.setAdapter(adapter);
        rv_kitchen_currently_delivered_meals.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void checkListSizeAfterSyncing() {
        if (meals != null && meals.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
            rv_kitchen_currently_delivered_meals.setVisibility(View.GONE);
        } else {
            empty_view.setVisibility(View.GONE);
            rv_kitchen_currently_delivered_meals.setVisibility(View.VISIBLE);
        }
    }
}
