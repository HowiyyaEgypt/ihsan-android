package com.example.mohamed.ihsan.repositories.organization.repositories;

import android.util.Log;

import com.example.mohamed.ihsan.api.IhsanApiEndpoints;
import com.example.mohamed.ihsan.repositories.organization.listeners.OnMixedOrganizationsReadyListener;
import com.example.mohamed.ihsan.repositories.organization.listeners.OnOrganizationActionListener;
import com.example.mohamed.ihsan.repositories.organization.listeners.OnOrganizationLocationsUtilReadyListener;
import com.example.mohamed.ihsan.repositories.organization.responses.MixedOrganizationResponse;
import com.example.mohamed.ihsan.repositories.organization.responses.OrganizationLocationsUtilResponse;
import com.example.mohamed.ihsan.repositories.organization.responses.SingleOrganizationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mohamed on 11/05/2018.
 */

public class OrganizationRepositoryImplementation implements OrganizationRepository {

    private IhsanApiEndpoints ihsanApi;

    public OrganizationRepositoryImplementation(IhsanApiEndpoints ihsanApiEndpoints) {
        this.ihsanApi = ihsanApiEndpoints;
    }

    @Override
    public void getMixedOrganization(String token, final OnMixedOrganizationsReadyListener listener) {

        Call<MixedOrganizationResponse> call = ihsanApi.getMixedOrganizations(token);

        call.enqueue(new Callback<MixedOrganizationResponse>() {
            @Override
            public void onResponse(Call<MixedOrganizationResponse> call, Response<MixedOrganizationResponse> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body().getMixedOrganizations().getAlreadyJoinedOrganizations(), response.body().getMixedOrganizations().getAvailableToJoinOrganizations());
                        break;
                }
            }

            @Override
            public void onFailure(Call<MixedOrganizationResponse> call, Throwable t) {
                Log.e("err:", t.getMessage());
            }
        });
    }

    @Override
    public void viewOrganization(String token, int organizationId, final OnOrganizationActionListener listener) {

        Call<SingleOrganizationResponse> call = ihsanApi.viewOrganization(organizationId, token);

        call.enqueue(new Callback<SingleOrganizationResponse>() {
            @Override
            public void onResponse(Call<SingleOrganizationResponse> call, Response<SingleOrganizationResponse> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body().getOrganization());
                        break;
                }
            }

            @Override
            public void onFailure(Call<SingleOrganizationResponse> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });

    }

    @Override
    public void joinOrganization(String token, int organizationId, final OnOrganizationActionListener listener) {

        Call<SingleOrganizationResponse> call = ihsanApi.joinOrganization(organizationId, token);

        call.enqueue(new Callback<SingleOrganizationResponse>() {
            @Override
            public void onResponse(Call<SingleOrganizationResponse> call, Response<SingleOrganizationResponse> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body().getOrganization());
                        break;
                }
            }

            @Override
            public void onFailure(Call<SingleOrganizationResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void leaveOrganization(String token, int organizationId, final OnOrganizationActionListener listener) {

        Call<SingleOrganizationResponse> call = ihsanApi.leaveOrganization(organizationId, token);

        call.enqueue(new Callback<SingleOrganizationResponse>() {
            @Override
            public void onResponse(Call<SingleOrganizationResponse> call, Response<SingleOrganizationResponse> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body().getOrganization());
                        break;
                }
            }

            @Override
            public void onFailure(Call<SingleOrganizationResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void getOrganizationLocationUtils(int organizationId, final OnOrganizationLocationsUtilReadyListener listener) {

        Call<OrganizationLocationsUtilResponse> call = ihsanApi.getLocationUtil(organizationId);

        call.enqueue(new Callback<OrganizationLocationsUtilResponse>() {
            @Override
            public void onResponse(Call<OrganizationLocationsUtilResponse> call, Response<OrganizationLocationsUtilResponse> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body().getOrganizationLocationsUtil().getCities(), response.body().getOrganizationLocationsUtil().getLocations());
                        break;
                }
            }

            @Override
            public void onFailure(Call<OrganizationLocationsUtilResponse> call, Throwable t) {
                Log.e("err:", t.getMessage());
            }
        });

    }
}
