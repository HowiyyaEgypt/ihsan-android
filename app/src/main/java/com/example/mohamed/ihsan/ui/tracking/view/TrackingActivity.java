package com.example.mohamed.ihsan.ui.tracking.view;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.dagger.App;
import com.example.mohamed.ihsan.repositories.delivery.Delivery;
import com.example.mohamed.ihsan.repositories.meal.Meal;
import com.example.mohamed.ihsan.ui.tracking.adapter.SimpleTrackerPagerAdapter;
import com.example.mohamed.ihsan.ui.tracking.contracts.OnTrackingItemUpdatedListener;
import com.example.mohamed.ihsan.ui.tracking.contracts.TrackingActionEchoContract;
import com.example.mohamed.ihsan.ui.tracking.contracts.TrackingContract;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrackingActivity extends AppCompatActivity implements TrackingContract.View, TrackingActionEchoContract.ParentView {

    @BindView(R.id.vp_tracking)
    ViewPager vp_tracking;

    @BindView(R.id.tl_tracking_tabs)
    TabLayout tl_tracking_tabs;

    @BindView(R.id.pb_tracking_data)
    ProgressBar pb_tracking_data;

    @Inject
    TrackingContract.Presenter presenter;

    private Delivery toBeDeletedDelivery;
    private SimpleTrackerPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        ((App) getApplication()).getComponent().inject(this);

        presenter.setView(this);

        ButterKnife.bind(this);

        presenter.requestTrackingData();
    }

    @Override
    public void displayMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        adapter.requestFragmentsToSyncAfterMealCancel(toBeDeletedDelivery);
    }

    @Override
    public void displayTrackingData(ArrayList<Meal> meals, ArrayList<Delivery> deliveries) {

        pb_tracking_data.setVisibility(View.GONE);

        adapter = new SimpleTrackerPagerAdapter(getSupportFragmentManager(), meals, deliveries);
        vp_tracking.setAdapter(adapter);
        tl_tracking_tabs.setupWithViewPager(vp_tracking);
    }

    @Override
    public void cancelMealDelivery(int mealId, Delivery delivery) {
        toBeDeletedDelivery = delivery;
        String title = "Cancel delivery confirmation";
        String msg = "Are you sure you want to cancel this delivery?";
        showDeliveryCancelConfirmationDialog(mealId, delivery.getId(), title, msg);
    }

    @Override
    public void confirmMealReception(int mode, int mealId, int deliveryId, OnTrackingItemUpdatedListener listener) {
        String title = "Meal reception confirmation";
        String msg = "Are you sure you have received the meal?";
        showMealReceptionConfirmationDialog(mealId, deliveryId, title, msg, listener);
    }

    private void showDeliveryCancelConfirmationDialog(final int mealId, final int deliveryId, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                presenter.cancelDelivery(mealId, deliveryId);

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

    private void showMealReceptionConfirmationDialog(final int mealId, final int deliveryId, String title, String message, final OnTrackingItemUpdatedListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                presenter.confirmMealReception(1, mealId, deliveryId, listener);

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
