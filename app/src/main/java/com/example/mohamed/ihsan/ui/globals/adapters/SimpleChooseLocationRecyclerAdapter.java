package com.example.mohamed.ihsan.ui.globals.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.location.Location;
import com.example.mohamed.ihsan.ui.openkitchen.contracts.LocationChooserEchoContract;

import java.util.ArrayList;

/**
 * Created by Mohamed on 17/05/2018.
 */

public class SimpleChooseLocationRecyclerAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Location> locations;
    private LocationChooserEchoContract locationChooserEchoContract;

    public SimpleChooseLocationRecyclerAdapter(Context context, ArrayList<Location> locations, LocationChooserEchoContract locationChooserEchoContract) {
        this.context = context;
        this.locations = locations;
        this.locationChooserEchoContract = locationChooserEchoContract;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.recycler_simple_location_chooser, parent, false);

        return new SimpleChooseLocationViewHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final Location location = locations.get(position);

        ((SimpleChooseLocationViewHolder) holder).locationDescription.setText(location.getDescription());
        ((SimpleChooseLocationViewHolder) holder).cityName.setText(location.getCityName());

        ((SimpleChooseLocationViewHolder) holder).chooseLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationChooserEchoContract.locationWasChosenFromRecycler(location);
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    class SimpleChooseLocationViewHolder extends RecyclerView.ViewHolder {

        TextView locationDescription;
        TextView cityName;
        TextView chooseLocation;

        public SimpleChooseLocationViewHolder(View itemView) {
            super(itemView);
            locationDescription = (TextView) itemView.findViewById(R.id.tv_location_description);
            cityName = (TextView) itemView.findViewById(R.id.tv_location_city_name);
            chooseLocation = (TextView) itemView.findViewById(R.id.tv_choose_location);
        }
    }
 }
