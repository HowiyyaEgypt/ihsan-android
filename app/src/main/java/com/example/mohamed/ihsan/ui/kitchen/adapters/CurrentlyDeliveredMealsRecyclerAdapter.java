package com.example.mohamed.ihsan.ui.kitchen.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.meal.Meal;
import com.example.mohamed.ihsan.ui.kitchen.contracts.KitchenActionsEchoContract;

import java.util.ArrayList;

/**
 * Created by Mohamed on 19/05/2018.
 */

public class CurrentlyDeliveredMealsRecyclerAdapter extends RecyclerView.Adapter implements KitchenActionsEchoContract.AdapterSync {

    private Context context;
    private ArrayList<Meal> list;
    private boolean canManage;
    private KitchenActionsEchoContract.ParentView parentView;

    public CurrentlyDeliveredMealsRecyclerAdapter(Context context, ArrayList<Meal> list, boolean canManage, KitchenActionsEchoContract.ParentView parentView) {
        this.context = context;
        this.list = list;
        this.canManage = canManage;
        this.parentView = parentView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root;
        if (canManage) {
            root = LayoutInflater.from(context).inflate(R.layout.recycler_kitchen_admin_currently_delivered_meals, parent, false);
            return new CurrentlyDeliveredMealsAdminViewHolder(root);
        } else {
            root = LayoutInflater.from(context).inflate(R.layout.recycler_kitchen_volunteer_currently_delivered_meals, parent, false);
            return new CurrentlyDeliveredMealsVolunteerViewHolder(root);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final Meal meal = list.get(position);

        if (canManage) {

            if (meal.getStage() > 1) {
                ((CurrentlyDeliveredMealsAdminViewHolder) holder).cancelDelivery.setVisibility(View.GONE);
            }

            ((CurrentlyDeliveredMealsAdminViewHolder) holder).mealDonorName.setText("Donated by: "+meal.getDonorName());
            ((CurrentlyDeliveredMealsAdminViewHolder) holder).mealDelivererName.setText("Delivered by: "+meal.getDelivererName());

            ((CurrentlyDeliveredMealsAdminViewHolder) holder).cancelDelivery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parentView.cancelMealDelivery(1, meal, meal.getDeliveryId());
                }
            });


            ((CurrentlyDeliveredMealsAdminViewHolder) holder).confirmReception.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parentView.confirmMealReception(2, meal, meal.getDeliveryId());
                }
            });

        } else {

            if (meal.getStage() > 1) {
                ((CurrentlyDeliveredMealsVolunteerViewHolder) holder).ll_meal_tracking_tools.setVisibility(View.GONE);
            }

            ((CurrentlyDeliveredMealsVolunteerViewHolder) holder).mealDonorName.setText("Donated by: "+meal.getDonorName());
            ((CurrentlyDeliveredMealsVolunteerViewHolder) holder).mealDelivererName.setText("Delivered by: "+meal.getDelivererName());

            if (meal.isDeliveredByMe()) {
                ((CurrentlyDeliveredMealsVolunteerViewHolder) holder).ll_meal_tracking_tools.setVisibility(View.VISIBLE);

                ((CurrentlyDeliveredMealsVolunteerViewHolder) holder).cancelDelivery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        parentView.cancelMealDelivery(2, meal, meal.getDeliveryId());
                    }
                });

                ((CurrentlyDeliveredMealsVolunteerViewHolder) holder).confirmReception.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        parentView.confirmMealReception(1, meal, meal.getDeliveryId());
                    }
                });
            }


        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void syncMeals(int mode, Meal meal) {
        switch (mode) {
            // case 1: add a meal from 'to be delivered' to 'currently being delivered'
            case 1:
                this.list.add(meal);
                break;
            case 2:
            case 3:
                this.list.remove(meal);
                break;
        }

        notifyDataSetChanged();
    }

    public class CurrentlyDeliveredMealsAdminViewHolder extends RecyclerView.ViewHolder {

        TextView mealDonorName;
        TextView mealDelivererName;
        TextView mealStage;
        TextView confirmReception;
        TextView cancelDelivery;

        public CurrentlyDeliveredMealsAdminViewHolder(View itemView) {
            super(itemView);

            mealDonorName = (TextView) itemView.findViewById(R.id.tv_meal_donor_name);
            mealDelivererName = (TextView) itemView.findViewById(R.id.tv_meal_deliverer_name);
            mealStage = (TextView) itemView.findViewById(R.id.tv_meal_stage);
            confirmReception = (TextView) itemView.findViewById(R.id.tv_confirm_reception);
            cancelDelivery = (TextView) itemView.findViewById(R.id.tv_cancel_delivery);
        }
    }

    public class CurrentlyDeliveredMealsVolunteerViewHolder extends RecyclerView.ViewHolder {

        TextView mealDonorName;
        TextView mealDelivererName;
        TextView mealStage;
        TextView confirmReception;
        TextView cancelDelivery;
        LinearLayout ll_meal_tracking_tools;

        public CurrentlyDeliveredMealsVolunteerViewHolder(View itemView) {
            super(itemView);

            mealDonorName = (TextView) itemView.findViewById(R.id.tv_meal_donor_name);
            mealDelivererName = (TextView) itemView.findViewById(R.id.tv_meal_deliverer_name);
            mealStage = (TextView) itemView.findViewById(R.id.tv_meal_stage);
            confirmReception = (TextView) itemView.findViewById(R.id.tv_confirm_reception);
            cancelDelivery = (TextView) itemView.findViewById(R.id.tv_cancel_delivery);
            ll_meal_tracking_tools = (LinearLayout) itemView.findViewById(R.id.ll_meal_tracking_tools);
        }
    }
}
