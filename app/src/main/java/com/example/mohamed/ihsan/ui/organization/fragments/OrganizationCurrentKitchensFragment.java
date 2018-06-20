package com.example.mohamed.ihsan.ui.organization.fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.example.mohamed.ihsan.ui.kitchen.view.KitchenActivity;
import com.example.mohamed.ihsan.ui.organization.adapters.DetailedOrganizationDataRecyclerAdapter;
import com.example.mohamed.ihsan.ui.organization.contracts.KitchenActionEchoContract;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed on 12/05/2018.
 */

public class OrganizationCurrentKitchensFragment extends Fragment implements KitchenActionEchoContract {

    @BindView(R.id.rv_current_kitchens)
    RecyclerView rv_current_kitchens;

    @BindView(R.id.empty_view)
    TextView empty_view;

    private ArrayList<Kitchen> kitchens;

    public void setKitchens(ArrayList<Kitchen> kitchens) {
        this.kitchens = kitchens;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_organization_current_kitchens, container, false);

        ButterKnife.bind(this, root);

        if (kitchens != null && kitchens.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
            rv_current_kitchens.setVisibility(View.GONE);
        } else {
            DetailedOrganizationDataRecyclerAdapter adapter = new DetailedOrganizationDataRecyclerAdapter(getActivity(), kitchens, 1, this);
            rv_current_kitchens.setAdapter(adapter);
            rv_current_kitchens.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        return root;
    }

    @Override
    public void goToKitchen(Kitchen kitchen) {
        Intent intent = new Intent(getActivity(), KitchenActivity.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.frag_enter_from_right, R.anim.frag_exit_to_left);
        intent.putExtra("kitchen", kitchen);
        startActivity(intent, options.toBundle());
    }
}
