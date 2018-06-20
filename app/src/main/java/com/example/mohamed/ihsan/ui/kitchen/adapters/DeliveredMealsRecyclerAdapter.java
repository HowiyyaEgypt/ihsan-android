package com.example.mohamed.ihsan.ui.kitchen.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.meal.Meal;
import com.example.mohamed.ihsan.ui.kitchen.contracts.KitchenActionsEchoContract;

import java.util.ArrayList;

/**
 * Created by Mohamed on 19/05/2018.
 */

public class DeliveredMealsRecyclerAdapter extends RecyclerView.Adapter implements KitchenActionsEchoContract.AdapterSync {

    private Context context;
    private ArrayList<Meal> list;
    private boolean canManage;

    public DeliveredMealsRecyclerAdapter(Context context, ArrayList<Meal> list, boolean canManage) {
        this.context = context;
        this.list = list;
        this.canManage = canManage;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root;
        if (canManage) {
            root = LayoutInflater.from(context).inflate(R.layout.recycler_kitchen_admin_delivered_meals, parent, false);
            return new DeliveredMealsRecyclerAdapter.DeliveredMealsAdminViewHolder(root);
        } else {
            root = LayoutInflater.from(context).inflate(R.layout.recycler_kitchen_admin_delivered_meals, parent, false);
            return new DeliveredMealsRecyclerAdapter.DeliveredMealsVolunteerViewHolder(root);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void syncMeals(int mode, Meal meal) {
        switch (mode) {
            case 3:
                this.list.add(meal);
                break;
        }

        notifyDataSetChanged();
    }

    public class DeliveredMealsAdminViewHolder extends RecyclerView.ViewHolder {

        public DeliveredMealsAdminViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class DeliveredMealsVolunteerViewHolder extends RecyclerView.ViewHolder {

        public DeliveredMealsVolunteerViewHolder(View itemView) {
            super(itemView);
        }
    }
}
