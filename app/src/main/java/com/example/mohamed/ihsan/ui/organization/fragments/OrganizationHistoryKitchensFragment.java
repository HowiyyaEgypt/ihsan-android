package com.example.mohamed.ihsan.ui.organization.fragments;

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
import com.example.mohamed.ihsan.ui.organization.adapters.DetailedOrganizationDataRecyclerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed on 12/05/2018.
 */

public class OrganizationHistoryKitchensFragment extends Fragment {

    @BindView(R.id.rv_history_kitchens)
    RecyclerView rv_history_kitchens;

    @BindView(R.id.empty_view)
    TextView empty_view;

    private ArrayList<Kitchen> kitchens;

    public void setKitchens(ArrayList<Kitchen> kitchens) {
        this.kitchens = kitchens;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_organization_history_kitchens, container, false);

        ButterKnife.bind(this, root);

        if (kitchens != null && kitchens.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
            rv_history_kitchens.setVisibility(View.GONE);
        } else {
            DetailedOrganizationDataRecyclerAdapter adapter = new DetailedOrganizationDataRecyclerAdapter(getActivity(), kitchens, 2);
            rv_history_kitchens.setAdapter(adapter);
            rv_history_kitchens.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        return root;
    }
}
