package com.example.mohamed.ihsan.ui.kitchen.view;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.dagger.App;
import com.example.mohamed.ihsan.helpers.Authentication.LocalAuthUserHelper;
import com.example.mohamed.ihsan.repositories.delivery.Delivery;
import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.example.mohamed.ihsan.repositories.meal.Meal;
import com.example.mohamed.ihsan.repositories.organization.Organization;
import com.example.mohamed.ihsan.ui.auth.view.AuthActivity;
import com.example.mohamed.ihsan.ui.kitchen.contracts.KitchenActionsEchoContract;
import com.example.mohamed.ihsan.ui.kitchen.adapters.ComplexKitchenPagerAdapter;
import com.example.mohamed.ihsan.ui.kitchen.contracts.KitchenContract;
import com.example.mohamed.ihsan.ui.tracking.view.TrackingActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KitchenActivity extends AppCompatActivity implements KitchenContract.View, KitchenActionsEchoContract.ParentView {

    @Inject
    KitchenContract.Presenter presenter;

    private Organization organization;
    private Kitchen kitchen;
    private Meal mealToBeAddedToCurrentlyDelivered;
    private Meal mealToBeReturnedToBelDelivered;
    private Meal finallyDeliveredMeal;
    private int kitchenMode;
    private ComplexKitchenPagerAdapter adapter;

    @BindView(R.id.tv_kitchen_name)
    TextView tv_kitchen_name;

    @BindView(R.id.tv_location_description)
    TextView tv_location_description;

    @BindView(R.id.tv_opening_time)
    TextView tv_opening_time;

    @BindView(R.id.tv_closing_time)
    TextView tv_closing_time;

    @BindView(R.id.tv_meals_delivered_count)
    TextView tv_meals_delivered_count;

    @BindView(R.id.tv_meals_currently_being_delivered)
    TextView tv_meals_currently_being_delivered;

    @BindView(R.id.tv_meals_need_delivery)
    TextView tv_meals_need_delivery;

    @BindView(R.id.vp_single_kitchen)
    ViewPager vp_single_kitchen;

    @BindView(R.id.tl_kitchen_tabs)
    TabLayout tl_kitchen_tabs;


    @BindView(R.id.btn_close_kitchen)
    Button btn_close_kitchen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        ((App) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);

        presenter.setView(this);

        Intent intent = getIntent();
        organization = (Organization) intent.getSerializableExtra("organization");
        kitchen = (Kitchen) intent.getSerializableExtra("kitchen");
        kitchenMode = intent.getIntExtra("kitchen_mode", 0);

        // if the mode == 1, then the kitchen was just created, so we don't need to call the api to get it's data
        if (kitchenMode == 1) {
            kitchenDataIsReady(kitchen);
        } else {
            presenter.requestKitchenData(kitchen.getId());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        switch (item.getItemId()) {
            case R.id.notification_icon:
                break;

            case R.id.tracking_icon:
                intent = new Intent(this, TrackingActivity.class);
                startActivity(intent);
                break;

            case R.id.logout_icon:
                LocalAuthUserHelper.destroyLocalAuthUserData(this);
                intent = new Intent(this, AuthActivity.class);
                startActivity(intent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void kitchenDataIsReady(Kitchen kitchen) {
        tv_kitchen_name.setText(kitchen.getName());
        tv_opening_time.setText("Opening time: " + kitchen.getOpeningTime());
        tv_closing_time.setText("Closing time: " + kitchen.getClosingTime());
        tv_location_description.setText("Location: " + kitchen.getLocationDescription());
        tv_meals_delivered_count.setText("" + kitchen.getMealsDeliveredCount());
        tv_meals_currently_being_delivered.setText("" + kitchen.getMealsCurrentlyBeingDeliveredCount());
        tv_meals_need_delivery.setText("" + kitchen.getMealsNeedToBeDeliveredCount());

        if (kitchen.isCanManage()) {
            btn_close_kitchen.setVisibility(View.VISIBLE);
        }

        adapter = new ComplexKitchenPagerAdapter(getSupportFragmentManager(), kitchen.isCanManage(), kitchen.getMealsDelivered(), kitchen.getMealsCurrentlyBeingDelivered(), kitchen.getMealsNeedToBeDelivered());
        vp_single_kitchen.setAdapter(adapter);
        tl_kitchen_tabs.setupWithViewPager(vp_single_kitchen);
        tl_kitchen_tabs.setTabTextColors(ColorStateList.valueOf(Color.parseColor("#ffffff")));
    }

    @Override
    public void displayMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayKitchenData(Kitchen kitchen) {
        this.kitchen = kitchen;
        kitchenDataIsReady(kitchen);
    }

    @Override
    public void navigateToTracking(int mode, Delivery delivery) {

        switch (mode) {
            // case 1: a meal will be transferred from 'to be delivered' to 'currently being delivered'
            case 1:
                // setting the id of the delivery to the meal for future requests
                mealToBeAddedToCurrentlyDelivered.setDeliveryId(delivery.getId());
                mealToBeAddedToCurrentlyDelivered.setDeliveredByMe(true);
                adapter.requestFragmentsToSyncAfterMealTransaction(1, mealToBeAddedToCurrentlyDelivered);
                Toast.makeText(this, "You have picked this meal!", Toast.LENGTH_LONG).show();
                break;


            // case 2: a meal will be transferred from to 'currently being delivered' to 'delivered'
            case 2:
                adapter.requestFragmentsToSyncAfterMealTransaction(3, finallyDeliveredMeal);
                Toast.makeText(this, "Meal reception was confirmed!", Toast.LENGTH_LONG).show();
                break;
        }

        final Intent intent = new Intent(this, TrackingActivity.class);
        final ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.frag_enter_from_left, R.anim.frag_exit_to_right);

        (new Handler())
                .postDelayed(
                        new Runnable() {
                            public void run() {
                                startActivity(intent, options.toBundle());
                            }
                        }, 2000);
    }

    @Override
    public void returnMealBackToBeDelivered(Meal meal) {

        Toast.makeText(this, "Delivery has been cancelled", Toast.LENGTH_LONG).show();

        mealToBeReturnedToBelDelivered.resetDeliveryId();
        mealToBeReturnedToBelDelivered.setDeliveredByMe(false);

        adapter.requestFragmentsToSyncAfterMealTransaction(2, mealToBeReturnedToBelDelivered);
    }

    /**
     * in this method, we will send a request to deliver an-undelivered meal
     * in case of the success, we will navigate to the tracking activity
     * also, we will simulate the sync between the adapters, the meal will be transferred to the 'currently being del. meals'
     * @param meal
     */
    @Override
    public void mealWasPickedForDelivery(Meal meal) {
        this.mealToBeAddedToCurrentlyDelivered = meal;
        presenter.deliverMeal(meal.getId(), kitchen.getId());
    }

    @Override
    public void cancelMealDelivery(int mode, Meal meal, int deliveryId) {
        mealToBeReturnedToBelDelivered = meal;
        showDeliveryCancelConfirmationDialog(mode, meal.getId(), deliveryId);
    }

    @Override
    public void confirmMealReception(int mode, Meal meal, int deliveryId) {
        finallyDeliveredMeal = meal;
        showMealReceptionConfirmationDialog(mode, meal.getId(), deliveryId);
    }

    private void showDeliveryCancelConfirmationDialog(final int mode, final int mealId, final int deliveryId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Delivery cancel confirmation");
        builder.setMessage("Are you sure you want to cancel the delivery?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                presenter.cancelMealDelivery(mode, mealId, deliveryId);

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void showMealReceptionConfirmationDialog(final int mode, final int mealId, final int deliveryId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Delivery cancel confirmation");
        builder.setMessage("Are you sure you want to cancel the delivery?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                presenter.confirmMealReception(mode, mealId, deliveryId);

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

}
