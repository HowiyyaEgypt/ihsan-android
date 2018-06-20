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
import com.example.mohamed.ihsan.repositories.user.User;
import com.example.mohamed.ihsan.ui.organization.adapters.DetailedOrganizationDataRecyclerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed on 12/05/2018.
 */

public class OrganizationCurrentVolunteersFragment extends Fragment {

    @BindView(R.id.rv_current_members)
    RecyclerView rv_current_members;

    @BindView(R.id.empty_view)
    TextView empty_view;

    private ArrayList<User> users;

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_organization_current_volunteers, container, false);

        ButterKnife.bind(this, root);

        if (users != null && users.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
            rv_current_members.setVisibility(View.GONE);
        } else {
            DetailedOrganizationDataRecyclerAdapter adapter = new DetailedOrganizationDataRecyclerAdapter(getActivity(), users, 3);
            rv_current_members.setAdapter(adapter);
            rv_current_members.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        return root;
    }
}
