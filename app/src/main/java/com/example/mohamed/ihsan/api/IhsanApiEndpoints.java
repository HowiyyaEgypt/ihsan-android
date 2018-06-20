package com.example.mohamed.ihsan.api;

import com.example.mohamed.ihsan.repositories.city.responses.AllCitiesResponse;
import com.example.mohamed.ihsan.repositories.delivery.responses.SingleDeliveryResponse;
import com.example.mohamed.ihsan.repositories.feed.responses.LatestFeedResponse;
import com.example.mohamed.ihsan.repositories.kitchen.requests.OpenNewKitchenRequest;
import com.example.mohamed.ihsan.repositories.kitchen.responses.AvailableKitchensResponse;
import com.example.mohamed.ihsan.repositories.kitchen.responses.SingleKitchenResponse;
import com.example.mohamed.ihsan.repositories.location.requests.AddNewUserLocationRequest;
import com.example.mohamed.ihsan.repositories.location.responses.NewUserLocationAddedResponse;
import com.example.mohamed.ihsan.repositories.location.responses.UserLocationsResponse;
import com.example.mohamed.ihsan.repositories.meal.responses.SingleMealResponse;
import com.example.mohamed.ihsan.repositories.organization.responses.MixedOrganizationResponse;
import com.example.mohamed.ihsan.repositories.organization.responses.OrganizationLocationsUtilResponse;
import com.example.mohamed.ihsan.repositories.organization.responses.SingleOrganizationResponse;
import com.example.mohamed.ihsan.repositories.tracking.responses.UserTrackingResponse;
import com.example.mohamed.ihsan.repositories.user.requests.LoginRequest;
import com.example.mohamed.ihsan.repositories.user.requests.SignupRequest;
import com.example.mohamed.ihsan.repositories.user.responses.LoginResponse;
import com.example.mohamed.ihsan.repositories.user.responses.SignupResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Mohamed on 25/04/2018.
 */

public interface IhsanApiEndpoints {

    String BASE_ENDPOINT = "/ihsan/server.php/api";

    /**
     Auth Endpoints - signup a new user
    **/

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST(BASE_ENDPOINT + "/signup")
    Call<SignupResponse> signup (@Body SignupRequest signupRequest);


    /**
     Auth Endpoints - login
     **/

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST(BASE_ENDPOINT + "/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);


    /**
     Tracking Endpoints - get the user's feed
     **/

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @GET(BASE_ENDPOINT + "/feeds")
    Call<LatestFeedResponse> getLatestFeed (@Query("token") String token);

    /**
     Tracking Endpoints - get the user's tracking data
     **/

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @GET(BASE_ENDPOINT + "/tracking")
    Call<UserTrackingResponse> getUserTrackingData (@Query("token") String token);


    /**
     Cities Endpoints - get all cities
     **/

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @GET(BASE_ENDPOINT + "/cities")
    Call<AllCitiesResponse> getAllCities (@Query("lang") String lang);


    /**
     Locations Endpoints - add a new location for a user
     **/

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @GET(BASE_ENDPOINT + "/locations/history")
    Call<UserLocationsResponse> getUserLocations (@Query("token") String token);


    /**
     Locations Endpoints - add a new location for a user
     **/

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST(BASE_ENDPOINT + "/locations/add")
    Call<NewUserLocationAddedResponse> addNewUserLocation (@Query("token") String token, @Body AddNewUserLocationRequest request);


    /**
     Kitchens Endpoints - get all available kitchens in a specific city / location
     **/

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @GET(BASE_ENDPOINT + "/kitchens/nearby")
    Call<AvailableKitchensResponse> getAvailableKitchensInACityOrLocation (@Query("mode") int mode, @Query("city_id") int cityId, @Query("lat") double lat, @Query("lng") double lng);


    /**
     Kitchens Endpoints - add a new kitchen for an organization
     **/

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @GET(BASE_ENDPOINT + "/kitchens/{kitchen}/view")
    Call<SingleKitchenResponse> getKitchenData (@Path("kitchen") int kitchenId, @Query("token") String token);


    /**
     Kitchens Endpoints - add a new kitchen for an organization
     **/

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST(BASE_ENDPOINT + "/kitchens/{organization}/create")
    Call<SingleKitchenResponse> openNewKitchen (@Path("organization") int organizationId, @Query("token") String token, @Body OpenNewKitchenRequest request);


    /**
     Meals Endpoints - donate a new meal
     **/

    @Multipart
    @POST(BASE_ENDPOINT + "/meals/donate")
    Call<SingleMealResponse> donateMeal (
            @Part("token") RequestBody token,
            @Part("mode") RequestBody mode,
            @Part("pick_up_location_id") RequestBody pickupLocationId,
            @Part("bellies") RequestBody bellies,
            @Part("description") RequestBody description,
            @Part("kitchen_id") RequestBody kitchenId,
            @Part List<MultipartBody.Part> photos
    );


    /**
     Delivery Endpoints - deliver a meal
     **/

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST(BASE_ENDPOINT + "/deliver/{meal}/{kitchen}")
    Call<SingleDeliveryResponse> deliverMeal (@Path("meal") int mealId, @Path("kitchen") int kitchenId, @Query("token") String token);


    /**
     Delivery Endpoints - cancel a delivery
     **/

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST(BASE_ENDPOINT + "/deliver/{meal}/confirm/{delivery}")
    Call<SingleDeliveryResponse> confirmMealReception (@Path("meal") int mealId, @Path("delivery") int deliveryId, @Query("token") String token, @Query("mode") int mode);


    /**
     Delivery Endpoints - confirm a delivery
     **/

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST(BASE_ENDPOINT + "/deliver/{meal}/cancel/{delivery}")
    Call<SingleMealResponse> cancelMealDelivery (@Path("meal") int mealId, @Path("delivery") int deliveryId, @Query("token") String token);


    /**
     Organizations Endpoints - get mixed organizations for a user
     **/

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @GET(BASE_ENDPOINT + "/organizations/mixed")
    Call<MixedOrganizationResponse> getMixedOrganizations (@Query("token") String token);


    /**
     Organizations Endpoints - view an organization
     **/

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @GET(BASE_ENDPOINT + "/organizations/{organization}/view")
    Call<SingleOrganizationResponse> viewOrganization (@Path("organization") int organizationId, @Query("token") String token);


    /**
     Organizations Endpoints - join an organization
     **/

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST(BASE_ENDPOINT + "/organizations/{organization}/join")
    Call<SingleOrganizationResponse> joinOrganization (@Path("organization") int organizationId, @Query("token") String token);


    /**
     Organizations Endpoints - leave an organization
     **/

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST(BASE_ENDPOINT + "/organizations/{organization}/leave")
    Call<SingleOrganizationResponse> leaveOrganization (@Path("organization") int organizationId, @Query("token") String token);


    /**
     Organizations Endpoints - get the organization location utils to open a new kitchen
     **/

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @GET(BASE_ENDPOINT + "/organizations/{organization}/location/utils")
    Call<OrganizationLocationsUtilResponse> getLocationUtil (@Path("organization") int organizationId);
}
