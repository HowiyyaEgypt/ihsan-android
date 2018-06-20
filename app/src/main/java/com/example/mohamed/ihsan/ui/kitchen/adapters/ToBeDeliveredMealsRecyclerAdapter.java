package com.example.mohamed.ihsan.ui.kitchen.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.meal.Meal;
import com.example.mohamed.ihsan.ui.kitchen.contracts.KitchenActionsEchoContract;

import java.util.ArrayList;

/**
 * Created by Mohamed on 19/05/2018.
 */

public class ToBeDeliveredMealsRecyclerAdapter extends RecyclerView.Adapter implements KitchenActionsEchoContract.AdapterSync {

    private Context context;
    private ArrayList<Meal> list;
    private boolean canManage;
    private KitchenActionsEchoContract.ParentView parentView;

    public ToBeDeliveredMealsRecyclerAdapter(Context context, ArrayList<Meal> list, boolean canManage, KitchenActionsEchoContract.ParentView parentView) {
        this.context = context;
        this.list = list;
        this.canManage = canManage;
        this.parentView = parentView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root;
        if (canManage) {
            root = LayoutInflater.from(context).inflate(R.layout.recycler_kitchen_admin_to_be_delivered_meals, parent, false);
            return new ToBeDeliveredMealsRecyclerAdapter.ToBeDeliveredMealsAdminViewHolder(root);
        } else {
            root = LayoutInflater.from(context).inflate(R.layout.recycler_kitchen_volunteer_to_be_delivered_meals, parent, false);
            return new ToBeDeliveredMealsRecyclerAdapter.ToBeDeliveredMealsVolunteerViewHolder(root);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final Meal meal = list.get(position);

        if (canManage) {
//            ((ToBeDeliveredMealsAdminViewHolder) holder).mealPhoto
        } else {

            ((ToBeDeliveredMealsVolunteerViewHolder) holder).mealDonorName.setText(meal.getDonorName());
            ((ToBeDeliveredMealsVolunteerViewHolder) holder).deliverMealAlone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parentView.mealWasPickedForDelivery(meal);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void syncMeals(int mode, Meal meal) {
        switch (mode) {
            // case 1: remove a meal from 'to be delivered' and add it to 'currently being delivered'
            case 1:
                this.list.remove(meal);
                break;
            case 2:
                this.list.add(meal);
                break;
        }

        notifyDataSetChanged();
    }

    public class ToBeDeliveredMealsAdminViewHolder extends RecyclerView.ViewHolder {

        ImageView mealPhoto;
        TextView mealDonorName;
        TextView mealLocationDescription;
        ImageView deliverMealAlone;
        ImageView deliverMealWithSomeone;
        ImageView notifyNearbyVolunteers;


        public ToBeDeliveredMealsAdminViewHolder(View itemView) {
            super(itemView);

            mealPhoto = (ImageView) itemView.findViewById(R.id.iv_meal_photo);
            deliverMealAlone = (ImageView) itemView.findViewById(R.id.iv_deliver_meal);
            deliverMealWithSomeone = (ImageView) itemView.findViewById(R.id.iv_deliver_meal_share);
            notifyNearbyVolunteers = (ImageView) itemView.findViewById(R.id.iv_notify_volunteers);
            mealDonorName = (TextView) itemView.findViewById(R.id.tv_meal_donor_name);
            mealLocationDescription = (TextView) itemView.findViewById(R.id.tv_meal_location_description);
        }
    }

    public class ToBeDeliveredMealsVolunteerViewHolder extends RecyclerView.ViewHolder {

        ImageView mealPhoto;
        TextView mealDonorName;
        TextView mealLocationDescription;
        ImageView deliverMealAlone;
        ImageView deliverMealWithSomeone;

        public ToBeDeliveredMealsVolunteerViewHolder(View itemView) {
            super(itemView);

            mealPhoto = (ImageView) itemView.findViewById(R.id.iv_meal_photo);
            deliverMealAlone = (ImageView) itemView.findViewById(R.id.iv_deliver_meal);
            deliverMealWithSomeone = (ImageView) itemView.findViewById(R.id.iv_deliver_meal_share);
            mealDonorName = (TextView) itemView.findViewById(R.id.tv_meal_donor_name);
            mealLocationDescription = (TextView) itemView.findViewById(R.id.tv_meal_location_description);
        }
    }
}
