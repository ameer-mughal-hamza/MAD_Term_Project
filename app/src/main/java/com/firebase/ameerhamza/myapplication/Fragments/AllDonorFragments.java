package com.firebase.ameerhamza.myapplication.Fragments;


import android.app.SearchManager;
import android.content.Context;
import android.icu.lang.UScript;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ameerhamza.myapplication.R;
import com.firebase.ameerhamza.myapplication.adapter.DonorListAdapter;
import com.firebase.ameerhamza.myapplication.interfaces.DonorInterface;
import com.firebase.ameerhamza.myapplication.interfaces.RetrofitClient;
import com.firebase.ameerhamza.myapplication.models.AllDonorInformation;
import com.firebase.ameerhamza.myapplication.models.DonorList;
import com.firebase.ameerhamza.myapplication.models.User;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllDonorFragments extends Fragment {
    RecyclerView recyclerView;
    DonorListAdapter adapter;
    List<User> userList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_donor_fragments, container, false);
        recyclerView = view.findViewById(R.id.allDonorRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final DonorInterface api = RetrofitClient.getClient().create(DonorInterface.class);
        String s = AllDonorInformation.getToken();
        Call<List<User>> call = api.getDonors("Bearer " + AllDonorInformation.getToken());
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    userList = response.body();
                    adapter = new DonorListAdapter(userList, getActivity());
                    recyclerView.setAdapter(adapter);

                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });
        return view;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.menu_main, menu);
//        try {
//            // Associate searchable configuration with the SearchView
//            SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
//            SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String s) {
//                    // do your search
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String s) {
//                    s = s.toLowerCase();
//                    List<User> users = new ArrayList<>();
//                    for (User user : userList)
//                    {
//                        String name = userList.get(0).getCity();
//                        if (name.contains(s));
//                            users.add(user);
//                    }
//                    adapter.setFilter(users);
//                    return false;
//                }
//            });
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}