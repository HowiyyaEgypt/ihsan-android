package com.example.mohamed.ihsan.dagger;

import com.example.mohamed.ihsan.api.IhsanApiEndpoints;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.mohamed.ihsan.api.ApiUrl.BASE_URL;

/**
 * Created by Mohamed on 25/04/2018.
 */

@Module
public class ApiModule {

    @Provides
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    public Retrofit provideRetrofit(String baseUrl, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public IhsanApiEndpoints provideApiEndpoints() {
        return provideRetrofit(BASE_URL, provideOkHttpClient()).create(IhsanApiEndpoints.class);
    }

}
