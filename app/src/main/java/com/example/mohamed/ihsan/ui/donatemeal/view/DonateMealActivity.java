package com.example.mohamed.ihsan.ui.donatemeal.view;


import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.dagger.App;
import com.example.mohamed.ihsan.repositories.city.City;
import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.example.mohamed.ihsan.repositories.location.Location;
import com.example.mohamed.ihsan.ui.donatemeal.contracts.DonateMealContract;
import com.example.mohamed.ihsan.ui.donatemeal.contracts.DonateMealEchoContract;
import com.example.mohamed.ihsan.ui.globals.fragments.DetectLocationFragment;
import com.example.mohamed.ihsan.ui.globals.fragments.DonateMealFormFragment;
import com.example.mohamed.ihsan.ui.globals.fragments.MealPickupLocationFragment;
import com.example.mohamed.ihsan.ui.globals.fragments.PickKitchenFragment;
import com.example.mohamed.ihsan.ui.home.view.HomeActivity;
import com.example.mohamed.ihsan.ui.main.MainActivity;


import java.util.ArrayList;

import javax.inject.Inject;

public class DonateMealActivity extends AppCompatActivity implements DonateMealContract.View, DonateMealEchoContract {

    private DetectLocationFragment detectLocationFragment;
    private PickKitchenFragment pickKitchenFragment;
    private MealPickupLocationFragment mealPickupLocationFragment;
    private DonateMealFormFragment donateMealFormFragment;

    public static final int PICK_MEAL_IMAGE = 100;
    public static final int MY_PERMISSIONS_REQUEST_READ_MEDIA = 101;
    private ArrayList<Uri> fileUris;

    // temp vars
    private int deliveryMode;
    private Kitchen mSelectedKitchen;
    private int mBellies;
    private String mDescription;
    private int mKitchenId;
    private Integer mPickupLocatiodId;

    @Inject
    DonateMealContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_meal);

        ((App) getApplication()).getComponent().inject(this);

        presenter.setView(this);

        presenter.requestAllCities();

    }

    /**
     * step 1 - select a location to display it's kitchens
     * Displaying all cities for the 1st fragment
     * @param cities
     */
    @Override
    public void displayAllCities(ArrayList<City> cities) {
        // 1st step: adding detection fragment after the success call of the Api
        detectLocationFragment = new DetectLocationFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.frag_enter_from_right, R.anim.frag_exit_to_left);
        transaction.add(R.id.rl_donate_meal, detectLocationFragment, "detectLocationFragment");
        transaction.commit();

        // setting fragment additional data
        detectLocationFragment.setCities(cities);
    }

    /**
     * step 2 - select a kitchen from a map or a recycler
     * Displaying all kitchens for the 2nd fragment
     * @param kitchens
     */
    @Override
    public void displayAvailableKitchens(ArrayList<Kitchen> kitchens) {

        pickKitchenFragment = new PickKitchenFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        // setting kitchens for the map / recycler
        pickKitchenFragment.setKitchens(kitchens);

        transaction.setCustomAnimations(R.anim.frag_enter_from_right, R.anim.frag_exit_to_left);
        transaction.replace(R.id.rl_donate_meal, pickKitchenFragment, "pickKitchenFragment");
        transaction.commit();

    }

    /**
     * step 3 - choosing how the meal will be delivered
     * @param locations
     */
    @Override
    public void displayAllUserLocations(ArrayList<Location> locations) {

        mealPickupLocationFragment = new MealPickupLocationFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        // setting old locations recycler data
        mealPickupLocationFragment.setLocations(locations);

        transaction.setCustomAnimations(R.anim.frag_enter_from_right, R.anim.frag_exit_to_left);
        transaction.replace(R.id.rl_donate_meal,mealPickupLocationFragment, "mealPickupLocationFragment");
        transaction.commit();
    }

    /**
     * proceed from step 3 (choosing how to deliver a meal) to step 4 (meal submission form)
     * after adding a new location
     * @param location
     */
    @Override
    public void proceedToMealFormAfterAddingNewLocation(Location location) {
        deliveryMode = 2;
        mPickupLocatiodId = location.getId();
        proceedToMealForm(mSelectedKitchen, location);
    }

    /**
     * Show error msg
     * @param msg
     */
    @Override
    public void showNoAvailableKitchensError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFinalSuccessMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

        final Intent intent = new Intent(this, HomeActivity.class);

        (new Handler())
        .postDelayed(
                new Runnable() {
                    public void run() {
                        startActivity(intent);
                    }
                }, 3000);
    }

    /**
     * A location was selected automatically from the 1st fragment
     */
    @Override
    public void cityWasAutomaticallyDetected() {

    }

    /**
     * A city was selected manually from the 1st fragment
     * @param cityId
     */
    @Override
    public void cityWasSelectedFromSpinner(int cityId) {
        // 1st step was finished successfully
        // we proceed to 2nd step
        // call the api to get the available kitchens in this area
        // if there is available kitchens, we display them on the map
        // else, we show a toast that there isn't any available kitchen in this area right now


        // first param == 1 (we have city id, not lat not lng)
        // we request all available kitchens in this city
        presenter.requestAvailableKitchens(1, cityId, 0, 0);

    }

    /**
     * step 3 - choose how the meal will be delivered
     * A kitchen was selected from the 2nd fragment (from a map marker, or an item in the recycler)
     * we are requesting the user's old location from Api to display it in the 3rd step recycler
     * @param kitchen
     */
    @Override
    public void kitchenWasSelectedFromRecyclerOrMap(Kitchen kitchen) {

        mSelectedKitchen = kitchen;
        presenter.requestUserLocations();
    }

    /**
     * proceed from step 4 (choosing how to deliver a meal) to step 4 (meal submission form)
     * after selecting an old location
     * @param location
     */
    @Override
    public void proceedToMealFormAfterSelectionOldLocation(Location location) {
        deliveryMode = 2;
        mPickupLocatiodId = location.getId();
        proceedToMealForm(mSelectedKitchen, location);
    }

    /**
     * proceed from step 4 after the user choses to deliver the meal himself/herself
     */
    @Override
    public void proceedToMealFormWithSelfDelivery() {
        deliveryMode = 1;
        proceedToMealForm(mSelectedKitchen, null);
    }

    @Override
    public void newUserLocationWasPickedFromMap(double lat, double lng, String locationDescription) {
        presenter.addNewUserLocation(lat, lng, locationDescription);
    }

    /**
     * Uploading the photo of the meal from the activity, because we need to access 'onActivityResult'
     * life cycle hook, and it is not available inside the fragment, so we will press a button
     * inside the fragment, and that button will trigger an action inside the activity
     */
    @Override
    public void uploadMealPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                PICK_MEAL_IMAGE
        );
    }

    /**
     * The 3rd fragment has all data of the meal, we are ready to submit it
     * @param bellies
     * @param description
     * @param kitchenId
     */
    @Override
    public void submitMeal(int bellies, String description, int kitchenId) {

        mBellies = bellies;
        mDescription = description;
        mKitchenId = kitchenId;

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_MEDIA);
        } else {

            if (mPickupLocatiodId != null) {
                presenter.submitMeal(deliveryMode, mPickupLocatiodId, bellies, description, kitchenId,fileUris);
            } else {
                presenter.submitMeal(deliveryMode, bellies, description,kitchenId,fileUris);
            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_MEAL_IMAGE && resultCode == Activity.RESULT_OK) {
            fileUris = new ArrayList<>();
            ClipData clipData = data.getClipData();

            // multi upload
            if (clipData != null) {
                for (int i = 0; i<clipData.getItemCount(); i++) {
                    ClipData.Item item = clipData.getItemAt(i);
                    Uri uri = item.getUri();
                    fileUris.add(uri);
                }
            }
            // single photo
            else {
                fileUris.add(data.getData());
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_MEDIA:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                    if (mPickupLocatiodId != null) {
                        presenter.submitMeal(deliveryMode, mPickupLocatiodId, mBellies, mDescription, mKitchenId,fileUris);
                    } else {
                        presenter.submitMeal(deliveryMode, mBellies, mDescription,mKitchenId,fileUris);
                    }

                }
                break;

        }
    }

    /**
     * The final step of donating a meal
     * @param mSelectedKitchen
     * @param location
     */
    private void proceedToMealForm(Kitchen mSelectedKitchen, @Nullable  Location location) {
        donateMealFormFragment = new DonateMealFormFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        // setting the selected kitchen
        donateMealFormFragment.setKitchen(mSelectedKitchen);

        transaction.setCustomAnimations(R.anim.frag_enter_from_right, R.anim.frag_exit_to_left);
        transaction.replace(R.id.rl_donate_meal, donateMealFormFragment, "donateMealFormFragment");
        transaction.commit();
    }
}
