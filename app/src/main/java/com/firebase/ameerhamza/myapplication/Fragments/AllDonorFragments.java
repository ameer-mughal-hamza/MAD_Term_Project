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
        Call<List<DonorList>> call = api.getDonors("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImI1OTA0MmYxZDk2ZmNiYmYwYzIwN2RkZTg0YTk1N2NhOWI3MjVmYzVkZGJlZDA4ODU3NjQwNjA3NzhjZGY3M2RiZjMzOWNhOWI5Mzc2NTk2In0.eyJhdWQiOiIxIiwianRpIjoiYjU5MDQyZjFkOTZmY2JiZjBjMjA3ZGRlODRhOTU3Y2E5YjcyNWZjNWRkYmVkMDg4NTc2NDA2MDc3OGNkZjczZGJmMzM5Y2E5YjkzNzY1OTYiLCJpYXQiOjE1MTE0Nzk2NTgsIm5iZiI6MTUxMTQ3OTY1OCwiZXhwIjoxNTQzMDE1NjU4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.DQ8kiN124IVLMnQHUq6dj-3X8HZkDlCrFWz6vTKVsMTYNfXYPwDluJbPfS69J64m-5H_l3i2a-SFi7OMmhDo8tGQ6L_vhhWytCW5gZ-O6jY7o24V1UmSYvR48EOAh072glp8t2poGh8BYFINrW9YBRSPTitrM-nobLOdaU6XqaIb0BYnB03mlyZPLK2LlwqY5sJiveTOZHTuligp3y2UCYwIXsruXq7XLyqtB7slW_k8orVC7Og8LWViPfyIM48XBDEfDafPnMaOnWsqy1YZWTVQ0rPVlJs8MH-iFvGBfO063Iehxi18N2BDuNLLg0QiOPERO1KKS12NKwz9hPJ1D8_WOV7dNa4_5OZulI_iJbDLP8bf72f6HvV0Y9sLJxLb83TF3WpJ9qKguYMEmMdYzClXppMjpWKZI3Q70-zIjqAyypYZaJu19pXJwKPw1dAOAtbua0-jBfEFNFQ77EwxDTrCfxX3vgVHRdsglkPIvU_IpIMJvkEpYqNha2sfzxoSad6lCJ_mSjnc_E-D6nRxybtkA6ZfcuPzWNBsgiE8KXmBvHoVwFXWBU7wnClzVd1H4QvJwXaQzRemrzOMiV2Tl7CsLoWy5fOJs9QRsGvOaJuwWxv1j_4u7M2pyjxHbE-zD6msGS_c0xEWUIiedhUc0mqeLx_Pq2ogJ2FeEdr7Hq0");
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
