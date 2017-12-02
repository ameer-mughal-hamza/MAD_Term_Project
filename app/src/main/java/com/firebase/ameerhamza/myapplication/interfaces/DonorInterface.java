package com.firebase.ameerhamza.myapplication.interfaces;

import com.firebase.ameerhamza.myapplication.Bloodbank;
import com.firebase.ameerhamza.myapplication.Donor;
import com.firebase.ameerhamza.myapplication.models.DonorList;
import com.firebase.ameerhamza.myapplication.Login;
import com.firebase.ameerhamza.myapplication.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ameer Hamza on 11/21/2017.
 */

public interface DonorInterface {



    @POST("login")
    Call<DonorList> login(@Body Login login);

    @POST("register")
    Call<Donor> register(@Body Donor donor);

    @GET("bloodbank")
    Call<List<Bloodbank>> getAll(@Header("Authorization") String authToken);

    @GET("donor/edit/{id}")
    Call<User> editDonor(@Header("Authorization") String token,
                         @Path("id") int id);

    @GET("donor")
    Call<List<User>> getDonors(@Header("Authorization") String authToken);
}
