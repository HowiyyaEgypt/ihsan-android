package com.example.mohamed.ihsan.ui.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.organization.Organization;
import com.example.mohamed.ihsan.ui.home.contracts.VolunteerEchoContract;

import java.util.ArrayList;

/**
 * Created by Mohamed on 11/05/2018.
 */

public class SimpleOrganizationRecyclerAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Organization> list;
    private int mode;
    private VolunteerEchoContract echoContract;

    public SimpleOrganizationRecyclerAdapter(Context context, ArrayList<Organization> list, int mode, VolunteerEchoContract echoContract) {
        this.context = context;
        this.mode = mode;
        this.list = list;
        this.echoContract = echoContract;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View root;

        switch (mode) {

            case 1:
            default:
                root = LayoutInflater.from(context).inflate(R.layout.recycler_already_joined_organizations, parent, false);
                return new SimpleAlreadyJoinedOrganizationsViewHolder(root);

            case 2:
                root = LayoutInflater.from(context).inflate(R.layout.recycler_available_to_join_organizations, parent, false);
                return new SimpleAvailableToJoinOrganizationsViewHolder(root);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final Organization organization = list.get(position);
        int mealsCount = organization.getDonatedMealsCount() + organization.getOriginalMealsCount();

        switch (mode) {

            case 1:
                ((SimpleAlreadyJoinedOrganizationsViewHolder) holder).organizationName.setText(organization.getName());
                ((SimpleAlreadyJoinedOrganizationsViewHolder) holder).mealsCount.setText("meals count: " + mealsCount);
                ((SimpleAlreadyJoinedOrganizationsViewHolder) holder).volunteers.setText("volunteers count: " + organization.getUsersCount());

                ((SimpleAlreadyJoinedOrganizationsViewHolder) holder).viewOrganization.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        echoContract.viewOrganization(organization);
                    }
                });

                ((SimpleAlreadyJoinedOrganizationsViewHolder) holder).leaveOrganization.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        echoContract.leaveOrganization(organization);
                    }
                });

                break;

            case 2:
                ((SimpleAvailableToJoinOrganizationsViewHolder) holder).organizationName.setText(organization.getName());
                ((SimpleAvailableToJoinOrganizationsViewHolder) holder).mealsCount.setText("meals count: " + mealsCount);
                ((SimpleAvailableToJoinOrganizationsViewHolder) holder).volunteers.setText("volunteers count: " + organization.getUsersCount());

                ((SimpleAvailableToJoinOrganizationsViewHolder) holder).joinOrganization.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        echoContract.joinOrganization(organization);
                        list.remove(organization);
                        notifyDataSetChanged();
                    }
                });

                break;

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SimpleAlreadyJoinedOrganizationsViewHolder extends RecyclerView.ViewHolder {

        TextView organizationName;
        TextView mealsCount;
        TextView volunteers;
        TextView viewOrganization;
        TextView leaveOrganization;

        public SimpleAlreadyJoinedOrganizationsViewHolder(View itemView) {
            super(itemView);

            organizationName = (TextView) itemView.findViewById(R.id.tv_organization_name);
            mealsCount = (TextView) itemView.findViewById(R.id.tv_organization_donated_meals);
            volunteers = (TextView) itemView.findViewById(R.id.tv_organization_users);
            viewOrganization = (TextView) itemView.findViewById(R.id.tv_view_organization);
            leaveOrganization = (TextView) itemView.findViewById(R.id.tv_leave_organization);
        }
    }

    public class SimpleAvailableToJoinOrganizationsViewHolder extends RecyclerView.ViewHolder {

        TextView organizationName;
        TextView mealsCount;
        TextView volunteers;
        TextView joinOrganization;

        public SimpleAvailableToJoinOrganizationsViewHolder(View itemView) {
            super(itemView);

            organizationName = (TextView) itemView.findViewById(R.id.tv_organization_name);
            mealsCount = (TextView) itemView.findViewById(R.id.tv_organization_donated_meals);
            volunteers = (TextView) itemView.findViewById(R.id.tv_organization_users);
            joinOrganization = (TextView) itemView.findViewById(R.id.tv_join_organization);
        }
    }
}
