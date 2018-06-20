package com.example.mohamed.ihsan.ui.globals.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.city.City;

import java.util.ArrayList;

/**
 * Created by Mohamed on 01/05/2018.
 */

public class SimpleCityAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<City> cities;

    public SimpleCityAdapter(Context context, int textViewResourceId, ArrayList<City> cities) {
        super(context, textViewResourceId, cities);

        this.context = context;
        this.cities = cities;
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return cities.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.custom_spinner, parent, false);

        TextView cityName = (TextView) row.findViewById(R.id.tv_city_name_spinner);
        cityName.setText(cities.get(position).getNameEn());

        return row;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.custom_spinner, parent, false);

        TextView cityName = (TextView) row.findViewById(R.id.tv_city_name_spinner);
        cityName.setText(cities.get(position).getNameEn());

        return row;
    }
}
