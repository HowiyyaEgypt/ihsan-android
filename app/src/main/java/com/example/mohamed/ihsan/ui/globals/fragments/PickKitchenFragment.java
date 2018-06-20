package com.example.mohamed.ihsan.ui.globals.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.example.mohamed.ihsan.ui.donatemeal.adapters.AvailableKitchensSimpleAdapter;
import com.example.mohamed.ihsan.ui.donatemeal.contracts.DonateMealEchoContract;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed on 29/04/2018.
 */

public class PickKitchenFragment extends Fragment implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private ArrayList<Kitchen> kitchens;
    private AvailableKitchensSimpleAdapter adapter;
    private HashMap<Marker, Kitchen> kitchenDataInsideMarker;
    private DonateMealEchoContract echoContract;

    @BindView(R.id.rv_simple_kitchens)
    RecyclerView recyclerView;

    public PickKitchenFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pick_kitchen, container, false);

        ButterKnife.bind(this, root);

        setupKitchensRecyclerView();

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.echoContract = (DonateMealEchoContract) context;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (kitchens != null) {

            kitchenDataInsideMarker = new HashMap<Marker, Kitchen>();

            for (Kitchen kitchen : kitchens) {

                Marker marker = googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(kitchen.getLocationLat(), kitchen.getLocationLng()))
                        .title(kitchen.getName()));

                // storing kitchen data within the marker, so we can access it @OnClick
                kitchenDataInsideMarker.put(marker, kitchen);
            }

            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    // we get the kitchen we clicked on, then pass it the donate meal echo instance
                    Kitchen kitchen = kitchenDataInsideMarker.get(marker);
                    echoContract.kitchenWasSelectedFromRecyclerOrMap(kitchen);
                    return false;
                }
            });

            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(kitchens.get(0).getLocationLat(), kitchens.get(0).getLocationLng()), 14));
        }


    }

    public void setKitchens(ArrayList<Kitchen> kitchens) {
        this.kitchens = kitchens;
    }

    private void setupKitchensRecyclerView() {
        if (kitchens != null) {
            adapter = new AvailableKitchensSimpleAdapter(getActivity(), echoContract, kitchens);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

    }
}
