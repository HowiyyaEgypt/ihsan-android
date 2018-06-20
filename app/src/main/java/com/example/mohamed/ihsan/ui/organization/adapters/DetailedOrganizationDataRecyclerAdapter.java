package com.example.mohamed.ihsan.ui.organization.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.example.mohamed.ihsan.repositories.user.User;
import com.example.mohamed.ihsan.ui.organization.contracts.KitchenActionEchoContract;

import java.util.ArrayList;

/**
 * Created by Mohamed on 12/05/2018.
 * This adapter used to display 3 things:
 * current opened kitchens, closed kitchens & current volunteers
 */

public class DetailedOrganizationDataRecyclerAdapter extends RecyclerView.Adapter {

    private ArrayList<?> list;
    private Context context;
    private int mode;
    private KitchenActionEchoContract kitchenActionEchoContract;

    public DetailedOrganizationDataRecyclerAdapter(Context context, ArrayList<?> list, int mode) {
        this.context = context;
        this.mode = mode;
        this.list = list;
    }

    public DetailedOrganizationDataRecyclerAdapter(Context context, ArrayList<?> list, int mode, KitchenActionEchoContract echoContract) {
        this.context = context;
        this.mode = mode;
        this.list = list;
        this.kitchenActionEchoContract = echoContract;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View root;

        switch (mode) {
            case 1:
            default:
                root = LayoutInflater.from(context).inflate(R.layout.recycler_detailed_current_kitchen, parent, false);
                return new DetailedCurrentKitchensViewHolder(root);

            case 2:
                root = LayoutInflater.from(context).inflate(R.layout.recycler_detailed_current_kitchen, parent, false);
                return new DetailedClosedKitchensViewHolder(root);

            case 3:
                root = LayoutInflater.from(context).inflate(R.layout.recycler_user_as_a_voulnteer, parent, false);
                return new DetailedCurrentUsersViewHolder(root);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (mode) {
            case 1:
                final Kitchen currentKitchen = (Kitchen) list.get(position);
                ((DetailedCurrentKitchensViewHolder) holder).name.setText(currentKitchen.getName());
                ((DetailedCurrentKitchensViewHolder) holder).comment.setText("address: " + currentKitchen.getDescription());
                ((DetailedCurrentKitchensViewHolder) holder).meals.setText("donated meals: " + currentKitchen.getMealsCount());
                ((DetailedCurrentKitchensViewHolder) holder).opening.setText("opening time: " + currentKitchen.getOpeningTime());
                ((DetailedCurrentKitchensViewHolder) holder).closing.setText("closing time: " + currentKitchen.getClosingTime());

                ((DetailedCurrentKitchensViewHolder) holder).viewKitchen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        kitchenActionEchoContract.goToKitchen(currentKitchen);
                    }
                });

                break;

            case 2:
                Kitchen historyKitchen = (Kitchen) list.get(position);
                ((DetailedClosedKitchensViewHolder) holder).name.setText(historyKitchen.getName());
                ((DetailedClosedKitchensViewHolder) holder).comment.setText("address: " + historyKitchen.getDescription());
                ((DetailedClosedKitchensViewHolder) holder).meals.setText("donated meals: " + historyKitchen.getMealsCount());
                ((DetailedClosedKitchensViewHolder) holder).opening.setText("opening time" + historyKitchen.getOpeningTime());
                ((DetailedClosedKitchensViewHolder) holder).closing.setText("closing time" + historyKitchen.getClosingTime());

                break;

            case 3:
                User user = (User) list.get(position);
                ((DetailedCurrentUsersViewHolder) holder).username.setText(user.getName());
                ((DetailedCurrentUsersViewHolder) holder).email.setText("email: " + user.getEmail());
                ((DetailedCurrentUsersViewHolder) holder).donatedMealsCount.setText("" + user.getMealsDonatedForAnOrganizationsCount());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DetailedCurrentKitchensViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView comment;
        TextView meals;
        TextView opening;
        TextView closing;
        ImageView viewKitchen;

        public DetailedCurrentKitchensViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.tv_current_kitchen_name);
            comment = (TextView) itemView.findViewById(R.id.tv_current_kitchen_comment);
            meals = (TextView) itemView.findViewById(R.id.tv_current_kitchen_meals);
            opening = (TextView) itemView.findViewById(R.id.tv_current_kitchen_opening_time);
            closing = (TextView) itemView.findViewById(R.id.tv_current_kitchen_closing_time);
            viewKitchen = (ImageView) itemView.findViewById(R.id.iv_view_kitchen);
        }
    }

    public class DetailedClosedKitchensViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView comment;
        TextView meals;
        TextView opening;
        TextView closing;
        LinearLayout ll_organization_kitchen_action;

        public DetailedClosedKitchensViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.tv_current_kitchen_name);
            comment = (TextView) itemView.findViewById(R.id.tv_current_kitchen_comment);
            meals = (TextView) itemView.findViewById(R.id.tv_current_kitchen_meals);
            opening = (TextView) itemView.findViewById(R.id.tv_current_kitchen_opening_time);
            closing = (TextView) itemView.findViewById(R.id.tv_current_kitchen_closing_time);
            ll_organization_kitchen_action = (LinearLayout) itemView.findViewById(R.id.ll_organization_kitchen_action);

            ll_organization_kitchen_action.setVisibility(View.GONE);
        }
    }

    public class DetailedCurrentUsersViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView email;
        TextView donatedMealsCount;

        public DetailedCurrentUsersViewHolder(View itemView) {
            super(itemView);

            username = (TextView) itemView.findViewById(R.id.tv_username);
            email = (TextView) itemView.findViewById(R.id.tv_user_email);
            donatedMealsCount = (TextView) itemView.findViewById(R.id.tv_donated_meals_count);
        }
    }
}
