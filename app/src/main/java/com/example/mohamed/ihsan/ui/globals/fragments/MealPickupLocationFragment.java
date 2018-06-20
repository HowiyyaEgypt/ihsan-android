package com.example.mohamed.ihsan.ui.globals.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.location.Location;
import com.example.mohamed.ihsan.ui.donatemeal.adapters.UserOldLocationsSimpleAdapter;
import com.example.mohamed.ihsan.ui.donatemeal.contracts.DonateMealEchoContract;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by Mohamed on 05/05/2018.
 */

public class MealPickupLocationFragment extends Fragment implements OnMapReadyCallback {

    @BindView(R.id.rg_delivery_mode)
    RadioGroup rg_delivery_mode;

    @BindView(R.id.tv_pick_location_hint)
    TextView tv_pick_location_hint;

    @BindView(R.id.rv_my_locations)
    RecyclerView recyclerView;

    @BindView(R.id.btn_save_new_location)
    Button btn_save_new_location;

    @BindView(R.id.et_location_description)
    EditText et_location_description;

    @OnClick(R.id.btn_save_new_location)
    public void addNewUserLocation() {
        if(lat == null || lng == null) {
            Toast.makeText(getActivity(), "please drag the marker and select a location", Toast.LENGTH_LONG).show();
        } else if(et_location_description.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "please enter a description", Toast.LENGTH_LONG).show();
        } else {
            echoContract.newUserLocationWasPickedFromMap(lat, lng, et_location_description.getText().toString());
        }
    }

    @OnCheckedChanged({R.id.rb_deliver_meal_myself, R.id.rb_meal_picked_up})
    public void showOrHideMapAndLocations(CompoundButton button, boolean checked) {

        if (checked) {
            switch (button.getId()) {
                case R.id.rb_deliver_meal_myself:
                    echoContract.proceedToMealFormWithSelfDelivery();
                    break;

                case R.id.rb_meal_picked_up:
                    break;
            }
        }
    }

    private SupportMapFragment mapFragment;
    private DonateMealEchoContract echoContract;
    private Double lat, lng;
    private ArrayList<Location> locations;
    private UserOldLocationsSimpleAdapter adapter;

    public MealPickupLocationFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_meal_pickup_location, container, false);

        ButterKnife.bind(this, root);

        if (locations != null) {

            adapter = new UserOldLocationsSimpleAdapter(getActivity(), locations, echoContract);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
        }

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_draggable);
        mapFragment.getMapAsync(this);

        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.addMarker(new MarkerOptions()
            .position(new LatLng(30.61227028194164,32.2954772785306))
            .draggable(true)
        );

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.61227028194164,32.2954772785306), 12));

        googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

                lat = marker.getPosition().latitude;
                lng = marker.getPosition().longitude;

            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        echoContract = (DonateMealEchoContract) context;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }
}
