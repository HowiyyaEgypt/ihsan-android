package com.example.mohamed.ihsan.ui.tracking.fragments;

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
import com.example.mohamed.ihsan.repositories.meal.Meal;
import com.example.mohamed.ihsan.ui.tracking.adapter.UserDeliveriesTrackingRecyclerAdapter;
import com.example.mohamed.ihsan.ui.tracking.adapter.UserMealsTrackingRecyclerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed on 22/05/2018.
 */

public class UserMealsTrackingFragment extends Fragment {

    @BindView(R.id.rv_user_meals_tracking)
    RecyclerView rv_user_meals_tracking;

    @BindView(R.id.empty_view)
    TextView empty_view;

    private ArrayList<Meal> meals;

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user_meals_tracking, container, false);

        ButterKnife.bind(this, root);

        if (meals != null && meals.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
            rv_user_meals_tracking.setVisibility(View.GONE);
        } else {
            UserMealsTrackingRecyclerAdapter adapter = new UserMealsTrackingRecyclerAdapter(getActivity(), meals);
            rv_user_meals_tracking.setAdapter(adapter);
            rv_user_meals_tracking.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        return root;
    }
}
