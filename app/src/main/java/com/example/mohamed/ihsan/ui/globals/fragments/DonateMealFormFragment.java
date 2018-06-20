package com.example.mohamed.ihsan.ui.globals.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.example.mohamed.ihsan.ui.donatemeal.contracts.DonateMealEchoContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mohamed on 05/05/2018.
 */

public class DonateMealFormFragment extends Fragment {

    @BindView(R.id.tv_kitchen_title)
    TextView tv_kitchen_title;

    @BindView(R.id.et_meal_bellies)
    TextView et_meal_bellies;

    @BindView(R.id.et_meal_description)
    TextView et_meal_description;


    @BindView(R.id.btn_meal_picture_upload)
    Button btn_meal_picture_upload;

    @OnClick(R.id.btn_meal_picture_upload) public void delegateActivityToUploadPicture () { echoContract.uploadMealPhoto(); }

    @BindView(R.id.btn_submit_meal)
    Button btn_submit_meal;

    @OnClick(R.id.btn_submit_meal)
    public void submitMeal() {

        String comment = et_meal_description.getText().toString().trim();

        if( et_meal_bellies.getText().toString().isEmpty()) {
            et_meal_bellies.setError("Number of people is required");
        } else if (comment.isEmpty()) {
            et_meal_description.setError("Description is required");
        } else {
            Integer bellies = Integer.parseInt(et_meal_bellies.getText().toString());
            echoContract.submitMeal(bellies, comment, kitchen.getId());
        }

    }
    
    private Kitchen kitchen;
    private DonateMealEchoContract echoContract;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_donate_meal_form, container, false);

        ButterKnife.bind(this, root);

        if (kitchen != null) {

            tv_kitchen_title.setText(kitchen.getName());

        }
        
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        echoContract = (DonateMealEchoContract) context;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
    }
}
