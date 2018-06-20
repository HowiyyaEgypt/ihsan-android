package com.example.mohamed.ihsan.ui.openkitchen.view;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.dagger.App;
import com.example.mohamed.ihsan.repositories.city.City;
import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.example.mohamed.ihsan.repositories.location.Location;
import com.example.mohamed.ihsan.repositories.organization.Organization;
import com.example.mohamed.ihsan.ui.globals.adapters.SimpleChooseLocationRecyclerAdapter;
import com.example.mohamed.ihsan.ui.globals.adapters.SimpleCityAdapter;
import com.example.mohamed.ihsan.ui.kitchen.view.KitchenActivity;
import com.example.mohamed.ihsan.ui.openkitchen.contracts.LocationChooserEchoContract;
import com.example.mohamed.ihsan.ui.openkitchen.contracts.OpenKitchenContract;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpenKitchenActivity extends AppCompatActivity implements OpenKitchenContract.View, OnMapReadyCallback, LocationChooserEchoContract {

    private Organization organization;

    // name and desc
    private String kitchenName;
    private String kitchenDescription;

    // spinner
    private ArrayList<City> cities;
    private SimpleCityAdapter adapter;
    private int selectedCityId;

    // recycler
    private ArrayList<Location> locations;
    private SimpleChooseLocationRecyclerAdapter recyclerAdapter;

    // map
    private SupportMapFragment mapFragment;
    private double lat;
    private double lng;

    // dates
    private String openingDateString;
    private String closingDateString;
    private String openingTimeString;
    private String closingTimeString;
    private long fullOpeningDateTimeUnix;
    private long fullClosingDateTimeUnix;

    @Inject
    public OpenKitchenContract.Presenter presenter;

    @BindView(R.id.rv_simple_location_recycler)
    RecyclerView rv_simple_location_recycler;

    @BindView(R.id.et_kitchen_name)
    EditText et_kitchen_name;

    @BindView(R.id.et_kitchen_description)
    EditText et_kitchen_description;

    @BindView(R.id.tv_kitchen_opening_date)
    TextView tv_kitchen_opening_date;

    @BindView(R.id.tv_kitchen_closing_date)
    TextView tv_kitchen_closing_date;

    @BindView(R.id.tv_kitchen_opening_time)
    TextView tv_kitchen_opening_time;

    @BindView(R.id.tv_kitchen_closing_time)
    TextView tv_kitchen_closing_time;

    @BindView(R.id.btn_kitchen_opening_date)
    Button btn_kitchen_opening_date;

    @BindView(R.id.btn_kitchen_opening_time)
    Button btn_kitchen_opening_time;

    @BindView(R.id.btn_kitchen_closing_date)
    Button btn_kitchen_closing_date;

    @BindView(R.id.btn_kitchen_closing_time)
    Button btn_kitchen_closing_time;

    @BindView(R.id.btn_add_kitchen)
    Button btn_add_kitchen;

    @OnClick(R.id.btn_kitchen_opening_date)
    public void pickOpeningDate() { launchDatePickerDialog(1); }

    @OnClick(R.id.btn_kitchen_closing_date)
    public void pickClosingDate() { launchDatePickerDialog(2); }

    @OnClick(R.id.btn_kitchen_opening_time)
    public void pickOpeningTime() { launchTimePickerDialog(1); }

    @OnClick(R.id.btn_kitchen_closing_time)
    public void pickClosingTime() { launchTimePickerDialog(2); }

    @BindView(R.id.spinner_cities)
    Spinner cities_spinner;

    @OnClick(R.id.btn_add_kitchen)
    public void validateD() {
        boolean valid_date = validateDates();
        boolean valid_name_and_desc = validateNameAndDescription();
        setSelectedCityId();

        if (valid_date && valid_name_and_desc) {
            presenter.requestOpeningNewKitchenInNewLocation(
                    organization.getId(),
                    fullOpeningDateTimeUnix,
                    fullClosingDateTimeUnix,
                    kitchenName,
                    kitchenDescription,
                    selectedCityId,
                    lat,
                    lng
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_kitchen);

        ((App) getApplication()).getComponent().inject(this);

        presenter.setView(this);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        organization = (Organization) intent.getSerializableExtra("organization");

        presenter.requestOrganizationLocationsUtil(organization.getId());

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_draggable);
        mapFragment.getMapAsync(this);
    }

    private boolean validateDates() {
        if (openingDateString == null || openingDateString.isEmpty()) {
            Toast.makeText(this, "Please provide the opening date", Toast.LENGTH_LONG).show();
            return false;
        }

        if (openingTimeString == null || openingTimeString.isEmpty()) {
            Toast.makeText(this, "Please provide the opening time", Toast.LENGTH_LONG).show();
            return false;
        }

        if (closingDateString == null || closingDateString.isEmpty()) {
            Toast.makeText(this, "Please provide the closing date", Toast.LENGTH_LONG).show();
            return false;
        }

        if (closingTimeString == null || closingTimeString.isEmpty()) {
            Toast.makeText(this, "Please provide the closing time", Toast.LENGTH_LONG).show();
            return false;
        }

        DateFormat dateFormat = new SimpleDateFormat("d M yyyy H:m");
        try {
            Date fullOpeningDateTime = dateFormat.parse(openingDateString + " " + openingTimeString);
            Date fullClosingDateTime = dateFormat.parse(closingDateString + " " + closingTimeString);

            fullOpeningDateTimeUnix = fullOpeningDateTime.getTime() / 1000;
            fullClosingDateTimeUnix = fullClosingDateTime.getTime() / 1000;

            if (fullOpeningDateTimeUnix > fullClosingDateTimeUnix) {
                Toast.makeText(this, "The closing time must be after the opening time", Toast.LENGTH_LONG).show();
                return false;
            }

            return true;

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void launchDatePickerDialog(final int mode) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int mMonth = month + 1;
               switch (mode) {
                   case 1:
                       openingDateString = dayOfMonth + " " + mMonth + " " + year;
                       tv_kitchen_opening_date.setText(dayOfMonth + "/" + mMonth + "/" + year);
                       break;
                   case 2:
                       closingDateString = dayOfMonth + " " + mMonth + " " + year;
                       tv_kitchen_closing_date.setText(dayOfMonth + "/" + mMonth + "/" + year);
                       break;
               }
            }
        },
            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH));

        dialog.show();
    }

    private void launchTimePickerDialog(final int mode) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                switch (mode) {
                    case 1:
                        openingTimeString = hourOfDay + ":" + minute;
                        tv_kitchen_opening_time.setText(openingTimeString);
                        break;
                    case 2:
                        closingTimeString = hourOfDay + ":" + minute;
                        tv_kitchen_closing_time.setText(closingTimeString);
                        break;
                }
            }
        },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        );

        dialog.show();
    }

    private void setSelectedCityId() {
        selectedCityId = ((City) cities_spinner.getSelectedItem()).getId();
    }

    @Override
    public void displayCitiesAndLocations(ArrayList<City> cities, ArrayList<Location> locations) {

        // launching the spinner
        adapter = new SimpleCityAdapter(this, R.layout.custom_spinner, cities);
        cities_spinner.setAdapter(adapter);

        // launching the recycler
        recyclerAdapter = new SimpleChooseLocationRecyclerAdapter(this, locations, this);
        rv_simple_location_recycler.setLayoutManager(new LinearLayoutManager(this));
        rv_simple_location_recycler.setAdapter(recyclerAdapter);
    }

    @Override
    public void navigateToKitchenAfterAddition(Kitchen kitchen) {
        Toast.makeText(this, "A new kitchen was added!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, KitchenActivity.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.frag_enter_from_left, R.anim.frag_exit_to_right);
        intent.putExtra("organization", organization);
        intent.putExtra("kitchen", kitchen);
        intent.putExtra("kitchen_mode", 1);
        startActivity(intent, options.toBundle());
    }

    @Override
    public void displayErrorMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
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
    public void locationWasChosenFromRecycler(Location location) {
        boolean valid_date = validateDates();
        boolean valid_name_and_desc = validateNameAndDescription();
        setSelectedCityId();

        if (valid_date && valid_name_and_desc) {
            presenter.requestOpeningNewKitchenInExistingLocation(
                    organization.getId(),
                    fullOpeningDateTimeUnix,
                    fullClosingDateTimeUnix,
                    kitchenName,
                    kitchenDescription,
                    selectedCityId,
                    location.getId()
            );
        }
    }

    private boolean validateNameAndDescription() {
        if (et_kitchen_name.getText().toString().isEmpty()) {
            et_kitchen_name.setError("kitchen name is required");
            return false;
        }

        if (et_kitchen_description.getText().toString().isEmpty()) {
            et_kitchen_description.setError("kitchen description is required");
            return false;
        }

        kitchenName = et_kitchen_name.getText().toString();
        kitchenDescription = et_kitchen_description.getText().toString();
        return true;
    }
}
