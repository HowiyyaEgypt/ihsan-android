package com.example.mohamed.ihsan.ui.organization.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.organization.Organization;
import com.example.mohamed.ihsan.ui.openkitchen.view.OpenKitchenActivity;
import com.example.mohamed.ihsan.ui.organization.adapters.ComplexOrganizationPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrganizationActivity extends AppCompatActivity {

    private Organization organization;

    @BindView(R.id.vp_single_organization)
    ViewPager vp_single_organization;

    @BindView(R.id.tl_organization_tabs)
    TabLayout tl_organization_tabs;

    @BindView(R.id.tv_organization_main_name)
    TextView tv_organization_main_name;

    @BindView(R.id.tv_organization_total_meals)
    TextView tv_organization_total_meals;

    @BindView(R.id.tv_organization_total_kitchens)
    TextView tv_organization_total_kitchens;

    @BindView(R.id.tv_organization_total_users)
    TextView tv_organization_total_users;

    @BindView(R.id.btn_add_new_kitchen)
    Button btn_add_new_kitchen;

    @OnClick(R.id.btn_add_new_kitchen)
    public void addNewKitchen() {
        Intent intent = new Intent(this, OpenKitchenActivity.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.frag_enter_from_right, R.anim.frag_exit_to_left);
        intent.putExtra("organization", organization);
        startActivity(intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        organization = (Organization) intent.getSerializableExtra("organization");

        if (organization.canManage()) {
            btn_add_new_kitchen.setVisibility(View.VISIBLE);
        }

        int totalMeals = organization.getDonatedMealsCount() + organization.getOriginalMealsCount();

        tv_organization_main_name.setText(organization.getName());
        tv_organization_total_meals.setText(""+totalMeals);
        tv_organization_total_kitchens.setText(""+organization.getTotalKitchensCount());
        tv_organization_total_users.setText(""+organization.getUsersCount());

        vp_single_organization.setAdapter(new ComplexOrganizationPager(getSupportFragmentManager(), organization.getOpenedKitchens(), organization.getClosedKitchens(), organization.getUsers()));
        tl_organization_tabs.setTabTextColors(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        tl_organization_tabs.setupWithViewPager(vp_single_organization);
    }
}
