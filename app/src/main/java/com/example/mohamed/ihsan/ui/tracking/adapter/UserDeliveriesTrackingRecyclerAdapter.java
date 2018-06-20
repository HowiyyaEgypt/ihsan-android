package com.example.mohamed.ihsan.ui.tracking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.delivery.Delivery;
import com.example.mohamed.ihsan.repositories.meal.Meal;
import com.example.mohamed.ihsan.ui.tracking.contracts.OnTrackingItemUpdatedListener;
import com.example.mohamed.ihsan.ui.tracking.contracts.TrackingActionEchoContract;

import java.util.ArrayList;

/**
 * Created by Mohamed on 22/05/2018.
 */

public class UserDeliveriesTrackingRecyclerAdapter extends RecyclerView.Adapter implements TrackingActionEchoContract.SyncTrackingAdapter {

    private Context context;
    private ArrayList<Delivery> deliveries;
    private TrackingActionEchoContract.ParentView echoContract;

    public UserDeliveriesTrackingRecyclerAdapter(Context context, ArrayList<Delivery> deliveries, TrackingActionEchoContract.ParentView trackingActionEchoContract) {
        this.context = context;
        this.deliveries = deliveries;
        this.echoContract = trackingActionEchoContract;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.recycler_user_deliveries_tracking, parent, false);

        return new UserDeliveriesViewHolder(root);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final Delivery delivery = deliveries.get(position);

        if (delivery.getMealStage() > 1) {
            ((UserDeliveriesViewHolder) holder).ll_delivery_actions.setVisibility(View.GONE);
        }

        ((UserDeliveriesViewHolder) holder).mealDescription.setText(delivery.getMealDescription());
        ((UserDeliveriesViewHolder) holder).mealBellies.setText("This meal for: " + delivery.getMealBellies() + " person(s)");
        ((UserDeliveriesViewHolder) holder).mealDonorName.setText("Donor: " + delivery.getMealDonorName());
        ((UserDeliveriesViewHolder) holder).mealPickupDate.setText("Pickup date: " + delivery.getPickupDate());
        ((UserDeliveriesViewHolder) holder).mealDeliveryDate.setText("Delivery date: " + delivery.getDeliveryDate());
        ((UserDeliveriesViewHolder) holder).mealPickupLocationDescription.setText("Location: " + delivery.getPickupLocationDescription());
        ((UserDeliveriesViewHolder) holder).mealKitchenName.setText("Kitchen: " + delivery.getKitchenId());

        ((UserDeliveriesViewHolder) holder).cancelDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                echoContract.cancelMealDelivery(delivery.getMealId(), delivery);
            }
        });

        ((UserDeliveriesViewHolder) holder).confirmReception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                echoContract.confirmMealReception(1, delivery.getMealId(), delivery.getId(), new OnTrackingItemUpdatedListener() {
                    @Override
                    public void onSuccess() {
                        ((UserDeliveriesViewHolder) holder).ll_delivery_actions.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return deliveries.size();
    }

    @Override
    public void syncAdapter(Delivery delivery) {
        this.deliveries.remove(delivery);
        notifyDataSetChanged();
    }

    @Override
    public void syncAdapter(Meal meal) {
        // do nothing
    }

    class UserDeliveriesViewHolder extends RecyclerView.ViewHolder {

        TextView mealDescription;
        TextView mealBellies;
        TextView mealDonorName;
        TextView mealPickupDate;
        TextView mealDeliveryDate;
        TextView mealPickupLocationDescription;
        TextView mealKitchenName;
        TextView mealStage;
        TextView cancelDelivery;
        TextView confirmReception;
        LinearLayout ll_delivery_actions;

        public UserDeliveriesViewHolder(View itemView) {
            super(itemView);

            mealDescription = (TextView) itemView.findViewById(R.id.tv_tracking_meal_description);
            mealBellies = (TextView) itemView.findViewById(R.id.tv_tracking_meal_bellies);
            mealDonorName = (TextView) itemView.findViewById(R.id.tv_tracking_meal_donor_name);
            mealPickupDate = (TextView) itemView.findViewById(R.id.tv_tracking_meal_pickup_date);
            mealDeliveryDate = (TextView) itemView.findViewById(R.id.tv_tracking_meal_delivery_date);
            mealPickupLocationDescription = (TextView) itemView.findViewById(R.id.tv_tracking_pickup_location_description);
            mealKitchenName = (TextView) itemView.findViewById(R.id.tv_tracking_kitchen_name);
            mealStage = (TextView) itemView.findViewById(R.id.tv_tracking_meal_stage);
            cancelDelivery = (TextView) itemView.findViewById(R.id.tv_cancel_delivery);
            confirmReception = (TextView) itemView.findViewById(R.id.tv_confirm_reception);
            ll_delivery_actions = (LinearLayout) itemView.findViewById(R.id.ll_delivery_actions);
        }
    }
}
