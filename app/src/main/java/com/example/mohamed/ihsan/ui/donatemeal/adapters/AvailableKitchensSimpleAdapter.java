package com.example.mohamed.ihsan.ui.donatemeal.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.example.mohamed.ihsan.ui.donatemeal.contracts.DonateMealEchoContract;

import java.util.ArrayList;

/**
 * Created by Mohamed on 04/05/2018.
 */

public class AvailableKitchensSimpleAdapter extends RecyclerView.Adapter<AvailableKitchensSimpleAdapter.AvailableKitchensSimpleViewHolder> {

    private Context context;
    private ArrayList<Kitchen> kitchens;
    private DonateMealEchoContract echoContract;

    public AvailableKitchensSimpleAdapter(Context context, DonateMealEchoContract donateMealEchoContract, ArrayList<Kitchen> kitchens) {
        this.context = context;
        this.kitchens = kitchens;
        this.echoContract = donateMealEchoContract;
    }

    @Override
    public AvailableKitchensSimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(context).inflate(R.layout.recycler_available_kitchens_simple, parent, false);

        return new AvailableKitchensSimpleViewHolder(root);
    }

    @Override
    public void onBindViewHolder(AvailableKitchensSimpleViewHolder holder, final int position) {
        holder.kitchenName.setText(kitchens.get(position).getName());
        holder.organizationName.setText("organization:" + kitchens.get(position).getOrganizationName());
        holder.locationDescription.setText("location: " + kitchens.get(position).getDescription());
        holder.openingTime.setText("time: " + kitchens.get(position).getOpeningTime() + " to " + kitchens.get(position).getClosingTime());

        holder.donateSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                echoContract.kitchenWasSelectedFromRecyclerOrMap(kitchens.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return kitchens.size();
    }

    public class AvailableKitchensSimpleViewHolder extends RecyclerView.ViewHolder {

        TextView kitchenName;
        TextView organizationName;
        TextView locationDescription;
        TextView openingTime;
        ImageView donateSymbol;

        public AvailableKitchensSimpleViewHolder(View itemView) {
            super(itemView);

            kitchenName = (TextView) itemView.findViewById(R.id.tv_kitchen_name_simple);
            organizationName = (TextView) itemView.findViewById(R.id.tv_kitchen_organization_name);
            locationDescription = (TextView) itemView.findViewById(R.id.tv_kitchen_location_description);
            openingTime = (TextView) itemView.findViewById(R.id.tv_kitchen_opening_time);
            donateSymbol = (ImageView) itemView.findViewById(R.id.iv_donate);

        }
    }
}
