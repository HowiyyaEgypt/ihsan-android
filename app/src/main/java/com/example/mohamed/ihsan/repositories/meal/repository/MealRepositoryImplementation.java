package com.example.mohamed.ihsan.repositories.meal.repository;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;

import com.example.mohamed.ihsan.api.IhsanApiEndpoints;
import com.example.mohamed.ihsan.helpers.Uri.UriHelpers;
import com.example.mohamed.ihsan.repositories.meal.listeners.OnMealReadyListener;
import com.example.mohamed.ihsan.repositories.meal.responses.SingleMealResponse;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mohamed on 05/05/2018.
 */

public class MealRepositoryImplementation implements MealRepository {

    private IhsanApiEndpoints ihsanApi;
    private Context context;

    public MealRepositoryImplementation(IhsanApiEndpoints ihsanApiEndpoints, Context context) {
        this.ihsanApi = ihsanApiEndpoints;
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void donateMeal(String token, int mode, Integer myLocationId, int bellies, String comment, int kitchenId, ArrayList<Uri> fileUris, final OnMealReadyListener listener) {

        RequestBody tokenPart = RequestBody.create(MultipartBody.FORM, token);
        RequestBody modePart = RequestBody.create(MultipartBody.FORM, String.valueOf(mode));
        RequestBody belliesPart = RequestBody.create(MultipartBody.FORM, String.valueOf(bellies));
        RequestBody commentPart = RequestBody.create(MultipartBody.FORM, comment);
        RequestBody kitchenIdPart = RequestBody.create(MultipartBody.FORM, String.valueOf(kitchenId));

        RequestBody myLocationIdPart = null;

        if(myLocationId != null) {
            myLocationIdPart = RequestBody.create(MultipartBody.FORM, String.valueOf(myLocationId));
        }

        ArrayList<MultipartBody.Part> filesList = new ArrayList<>();

        if(fileUris != null && fileUris.size() > 0) {
            for (int i = 0; i < fileUris.size(); i++) {
                filesList.add(preparePhotoForTheRequest("photos[]", UriHelpers.getFileForUri(context, fileUris.get(i))));
            }
        }

        Call<SingleMealResponse> call = ihsanApi.donateMeal(tokenPart, modePart,myLocationIdPart,belliesPart,commentPart,kitchenIdPart, filesList);

        call.enqueue(new Callback<SingleMealResponse>() {
            @Override
            public void onResponse(Call<SingleMealResponse> call, Response<SingleMealResponse> response) {

                switch (response.code()) {

                    case 200:
                        listener.onSuccess(response.body().getMeal());
                        break;

                }
            }

            @Override
            public void onFailure(Call<SingleMealResponse> call, Throwable t) {

            }
        });
    }

    private MultipartBody.Part preparePhotoForTheRequest(String parameterName, File file) {
        RequestBody imageBody = RequestBody.create(MediaType.parse("image/*"), file);
        return MultipartBody.Part.createFormData(parameterName, file.getName(), imageBody);
    }
}
