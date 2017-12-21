package com.firebase.ameerhamza.myapplication.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ameerhamza.myapplication.Bloodbank;
import com.firebase.ameerhamza.myapplication.R;
import com.firebase.ameerhamza.myapplication.adapter.BloodbankAdapter;
import com.firebase.ameerhamza.myapplication.interfaces.DonorInterface;
import com.firebase.ameerhamza.myapplication.interfaces.RetrofitClient;
import com.firebase.ameerhamza.myapplication.models.AllDonorInformation;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BloodBank extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blood_bank, container, false);
        recyclerView = view.findViewById(R.id.allGroups);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DonorInterface api = RetrofitClient.getClient().create(DonorInterface.class);
        Call<List<Bloodbank>> call = api.getAll("Bearer " + AllDonorInformation.getToken().toString());
        call.enqueue(new Callback<List<Bloodbank>>() {
            @Override
            public void onResponse(Call<List<Bloodbank>> call, Response<List<Bloodbank>> response) {
                List<Bloodbank> lists = response.body();
                adapter = new BloodbankAdapter(lists,getActivity());
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Bloodbank>> call, Throwable t) {

            }
        });
        return view;
    }
}
