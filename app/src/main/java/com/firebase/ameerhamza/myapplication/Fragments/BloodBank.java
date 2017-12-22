package com.firebase.ameerhamza.myapplication.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ameerhamza.myapplication.Bloodbank;
import com.firebase.ameerhamza.myapplication.R;
import com.firebase.ameerhamza.myapplication.adapter.BloodbankAdapter;
import com.firebase.ameerhamza.myapplication.app.Edit_Profile;
import com.firebase.ameerhamza.myapplication.app.LoginActivity;
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
                adapter = new BloodbankAdapter(lists, getActivity());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Bloodbank>> call, Throwable t) {

            }
        });
        return view;
    }


    //This method will make sure to add overflow menu in the toolbar
    //setHasOptionMenu will add overflow icon on top
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //This method will inflate menu
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.only_overflow, menu); // Put your search menu in "menu_search" menu file.
        super.onCreateOptionsMenu(menu, inflater);
    }

    //This will perform task on the selected one
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_profile:
                Intent intent = new Intent(getActivity(), Edit_Profile.class);
                startActivity(intent);
                break;
            case R.id.about:
                break;
            case R.id.logout:
                Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
