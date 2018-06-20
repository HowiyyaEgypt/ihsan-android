package com.example.mohamed.ihsan.ui.tracking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.delivery.Delivery;
import com.example.mohamed.ihsan.repositories.meal.Meal;

import java.util.ArrayList;

/**
 * Created by Mohamed on 22/05/2018.
 */

public class UserMealsTrackingRecyclerAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Meal> meals;

    public UserMealsTrackingRecyclerAdapter(Context context, ArrayList<Meal> meals) {
        this.context = context;
        this.meals = meals;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.recycler_user_meals_tracking, parent, false);

        return new UserMealsTrackingViewHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Meal meal = meals.get(position);

        ((UserMealsTrackingViewHolder) holder).mealDescription.setText(meal.getDescription());
        ((UserMealsTrackingViewHolder) holder).mealBellies.setText("This meal for: " + meal.getBellies() + " person(s)");
        ((UserMealsTrackingViewHolder) holder).mealPickupDate.setText("Pickup date: " + meal.getPickupDate());
        ((UserMealsTrackingViewHolder) holder).mealDeliveryDate.setText("Delivery date: " + meal.getDeliveryDate());
        ((UserMealsTrackingViewHolder) holder).mealKitchenName.setText("Kitchen name: " + meal.getKitchenName());

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class UserMealsTrackingViewHolder extends RecyclerView.ViewHolder {

        TextView mealDescription;
        TextView mealBellies;
        TextView mealPickupDate;
        TextView mealDeliveryDate;
        TextView mealKitchenName;
        TextView mealStage;
        TextView mealDeliveryType;

        public UserMealsTrackingViewHolder(View itemView) {
            super(itemView);

            mealDescription = (TextView) itemView.findViewById(R.id.tv_tracking_meal_description);
            mealBellies = (TextView) itemView.findViewById(R.id.tv_tracking_meal_bellies);
            mealPickupDate = (TextView) itemView.findViewById(R.id.tv_tracking_meal_pickup_date);
            mealDeliveryDate = (TextView) itemView.findViewById(R.id.tv_tracking_meal_delivery_date);
            mealKitchenName = (TextView) itemView.findViewById(R.id.tv_tracking_kitchen_name);
            mealStage = (TextView) itemView.findViewById(R.id.tv_tracking_meal_stage);
            mealDeliveryType = (TextView) itemView.findViewById(R.id.tv_tracking_meal_delivery_type);
        }
    }
}
