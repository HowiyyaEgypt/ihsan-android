package com.example.mohamed.ihsan.ui.donatemeal.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.location.Location;
import com.example.mohamed.ihsan.ui.donatemeal.contracts.DonateMealEchoContract;

import java.util.ArrayList;

/**
 * Created by Mohamed on 07/05/2018.
 */

public class UserOldLocationsSimpleAdapter extends RecyclerView.Adapter<UserOldLocationsSimpleAdapter.UserOldLocationsViewHolder> {

    private ArrayList<Location> locations;
    private Context context;
    private DonateMealEchoContract echoContract;

    public UserOldLocationsSimpleAdapter(Context context, ArrayList<Location> locations, DonateMealEchoContract echoContract) {
        this.locations = locations;
        this.context = context;
        this.echoContract = echoContract;
    }

    @Override
    public UserOldLocationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(context).inflate(R.layout.recycler_old_locations_simple, parent, false);

        return new UserOldLocationsViewHolder(root);
    }

    @Override
    public void onBindViewHolder(UserOldLocationsViewHolder holder, final int position) {

        holder.locationDescription.setText(locations.get(position).getDescription());

        holder.chooseLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                echoContract.proceedToMealFormAfterSelectionOldLocation(locations.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class UserOldLocationsViewHolder extends RecyclerView.ViewHolder {

        TextView locationDescription;
        TextView chooseLocation;

        public UserOldLocationsViewHolder(View itemView) {
            super(itemView);

            locationDescription = (TextView) itemView.findViewById(R.id.tv_user_location_description);
            chooseLocation = (TextView) itemView.findViewById(R.id.tv_choose_location);

        }
    }
}
