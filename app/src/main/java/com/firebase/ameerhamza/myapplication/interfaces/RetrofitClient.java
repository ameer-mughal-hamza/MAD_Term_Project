package com.firebase.ameerhamza.myapplication.interfaces;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ameer Hamza on 12/2/2017.
 */

public class RetrofitClient {
    public static final String BASE_URL = "http://192.168.130.1/blood_bank/public/api/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
