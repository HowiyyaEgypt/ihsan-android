package com.example.mohamed.ihsan.ui.home.fragments;

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
import com.example.mohamed.ihsan.repositories.organization.Organization;
import com.example.mohamed.ihsan.ui.home.adapters.SimpleOrganizationRecyclerAdapter;
import com.example.mohamed.ihsan.ui.home.contracts.VolunteerEchoContract;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed on 11/05/2018.
 */

public class AlreadyJoinedOrganizationsFragment extends Fragment {

    @BindView(R.id.rv_already_joined_organizations)
    RecyclerView rv_already_joined_organizations;

    @BindView(R.id.empty_view)
    TextView empty_view;

    private ArrayList<Organization> alreadyJoinedOrganizationsList;
    public VolunteerEchoContract echoContract;

    // saving state variables
    private static final String PERSISTENT_ORGANIZATION_VARIABLE = "persistentOrganizationValue";

    public AlreadyJoinedOrganizationsFragment() {
        setArguments(new Bundle());
    }

    @Override
    public void onPause() {
        super.onPause();
        getArguments().putSerializable(PERSISTENT_ORGANIZATION_VARIABLE, alreadyJoinedOrganizationsList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_already_joined_organizations, container, false);

        ButterKnife.bind(this, root);

        // we can't call onAttach, because the fragment implements the contract, not the activity
        VolunteerFragment fragment = (VolunteerFragment) AlreadyJoinedOrganizationsFragment.this.getParentFragment();
        echoContract = fragment;

        if (alreadyJoinedOrganizationsList == null) {
            Bundle mySavedInstanceState = getArguments();
            alreadyJoinedOrganizationsList = (ArrayList<Organization>) mySavedInstanceState.getSerializable(PERSISTENT_ORGANIZATION_VARIABLE);
        }

        if (alreadyJoinedOrganizationsList != null) {
            if (alreadyJoinedOrganizationsList.size() == 0) {
                empty_view.setVisibility(View.VISIBLE);
                rv_already_joined_organizations.setVisibility(View.GONE);
            } else {
                SimpleOrganizationRecyclerAdapter adapter = new SimpleOrganizationRecyclerAdapter(getActivity(), alreadyJoinedOrganizationsList, 1, echoContract);
                rv_already_joined_organizations.setAdapter(adapter);
                rv_already_joined_organizations.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        }

        return root;
    }

    public void setAlreadyJoinedOrganizationsList(ArrayList<Organization> alreadyJoinedOrganizationsList) {
        this.alreadyJoinedOrganizationsList = alreadyJoinedOrganizationsList;
    }
}
