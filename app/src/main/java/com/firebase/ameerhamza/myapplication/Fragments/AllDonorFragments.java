package com.firebase.ameerhamza.myapplication.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ameerhamza.myapplication.DonorList;
import com.firebase.ameerhamza.myapplication.R;
import com.firebase.ameerhamza.myapplication.adapter.DonorListAdapter;
import com.firebase.ameerhamza.myapplication.interfaces.DonorInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllDonorFragments extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_donor_fragments, container, false);
        recyclerView = view.findViewById(R.id.allDonorRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DonorInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DonorInterface api = retrofit.create(DonorInterface.class);
        Call<List<DonorList>> call = api.getDonors(token.toString());
        adapter = new DonorListAdapter(listItems,getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }

}
