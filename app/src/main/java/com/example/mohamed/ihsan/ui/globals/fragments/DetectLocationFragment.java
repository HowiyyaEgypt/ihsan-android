package com.example.mohamed.ihsan.ui.globals.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.city.City;
import com.example.mohamed.ihsan.ui.donatemeal.contracts.DonateMealEchoContract;
import com.example.mohamed.ihsan.ui.globals.adapters.SimpleCityAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mohamed on 01/05/2018.
 */

public class DetectLocationFragment extends Fragment {

    @BindView(R.id.btn_detect_my_location)
    Button btn_detect_my_location;

    @BindView(R.id.btn_step_two)
    Button btn_step_two;

    @OnClick(R.id.btn_step_two)
    public void proceedToMapAfterCityWasSelected() {
        City c = (City) cities_spinner.getSelectedItem();

        // we delegate the location selection to the parent
        // if there is available kitchens, we proceed to the 3rd step, if not we choose another location
        echoContract.cityWasSelectedFromSpinner(c.getId());
    }

    @BindView(R.id.spinner_cities)
    Spinner cities_spinner;

    private DonateMealEchoContract echoContract;
    private ArrayList<City> cities;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detect_location, container, false);

        ButterKnife.bind(this, root);

        // the cities is fetched from the activity using the setter

        final SimpleCityAdapter adapter = new SimpleCityAdapter(getContext(), R.layout.custom_spinner, cities);
        cities_spinner.setAdapter(adapter);


        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        echoContract = (DonateMealEchoContract) context;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }
}
