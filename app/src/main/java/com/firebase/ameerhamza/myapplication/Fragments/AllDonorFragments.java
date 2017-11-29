package com.firebase.ameerhamza.myapplication.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ameerhamza.myapplication.DonorList;
import com.firebase.ameerhamza.myapplication.R;
import com.firebase.ameerhamza.myapplication.adapter.DonorListAdapter;
import com.firebase.ameerhamza.myapplication.interfaces.DonorInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllDonorFragments extends Fragment {
    private String token = "";
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Subscribe
    void test(String token)
    {
        this.token = token;
    }

    @Override
    public void onPause() {
        EventBus.getDefault().register(this);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().unregister(this);
    }

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
        Call<List<DonorList>> call = api.getDonors("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImJmNDM5YzEyNzQ2ZjlkMzUwZjllNzcxYmM3ZTI0ZGFkYjc5MGJjZmExODE4N2RiNGY2MjZjYWM3YTUxMjI4YjdkY2U3N2Q3NzY2ZjU1YzM0In0.eyJhdWQiOiIxIiwianRpIjoiYmY0MzljMTI3NDZmOWQzNTBmOWU3NzFiYzdlMjRkYWRiNzkwYmNmYTE4MTg3ZGI0ZjYyNmNhYzdhNTEyMjhiN2RjZTc3ZDc3NjZmNTVjMzQiLCJpYXQiOjE1MTE5MDE2MDgsIm5iZiI6MTUxMTkwMTYwOCwiZXhwIjoxNTQzNDM3NjA4LCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.Lo8bF1opw_2GKPk-wV7eymXJ-1DIZmdzY8rJVirIqk_kuP3On2QONXTOPDNu70mArvvwe40cKqEqbWg3Q-pQCDTDI2BUQNu5_U5ductD50-EE0QL6GNozUOYTSif6elG86HYhfKaxZt1cl6FWwxcig5gg3uzf0_Xz9MvsW3f7lJC39E-NeUULr6p4jvhgFrRuvWrEBZa8kjUpKs934K-pFMrWnMZwPKwSbP17r4g-CW0j6E5_B0xGgxORLqotQozyiE8FSqIJtcUJQCul_gGQyYtX2MNb5cSVIql5eQ_x5HHKBterlhZxjyvgtLOhNTI_2rybYoEJKQHGd_cBRqYGaSx9D4D7vy6kdhYSuSKLs3RgfJUGLjUfPVJa3OyQl8TX7lr7PyHPh4XZyx-QpFzMeHnKPvlYJ1Zk5XZUPFSGHUHWyJEIzruQXiDIdV7lrBYRf3jM_v2y21QvYmPuDF56ijMgNxvI21li5p6NkuEpu9QhjY9K6SMjyQPFTd8Y2ZWL4V5D0H-urbtoqdHAY-HVD32_PcpFav7RlEFGYgVwvQFLaVAcNPIHAQDomNuJJinozgkpfjqwgSEyJ75Jrq0jwf3OKAc7GRkm7XHm0ek3F-dgdpgng5a7gmj_sMgUDpAibqFsYN8Mvd5rxJ6Gk9QH25-QgEt-_1DAeEdXWYcpSs");
        call.enqueue(new Callback<List<DonorList>>() {
            @Override
            public void onResponse(Call<List<DonorList>> call, Response<List<DonorList>> response) {
                List<DonorList> lists = response.body();
                adapter = new DonorListAdapter(lists,getActivity());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<DonorList>> call, Throwable t) {
                Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
