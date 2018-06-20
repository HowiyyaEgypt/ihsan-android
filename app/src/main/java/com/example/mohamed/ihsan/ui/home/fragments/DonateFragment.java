package com.example.mohamed.ihsan.ui.home.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.ui.donatemeal.view.DonateMealActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mohamed on 28/04/2018.
 */

public class DonateFragment extends Fragment {

    @BindView(R.id.iv_donate_meal)
    ImageView iv_donate_meal;

    @OnClick(R.id.iv_donate_meal)
    public void launchDonateMeal() {
        Intent intent = new Intent(getActivity(), DonateMealActivity.class);
        startActivity(intent);
//        getActivity().finish();
    }


    @BindView(R.id.iv_donate_money)
    ImageView iv_donate_money;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_donate_from_home, container, false);

        ButterKnife.bind(this, root);

        return root;
    }
}
