package com.firebase.ameerhamza.myapplication.Fragments;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ameerhamza.myapplication.R;
import com.firebase.ameerhamza.myapplication.RecyclerItemTouchHelper;
import com.firebase.ameerhamza.myapplication.adapter.DonorListAdapter;
import com.firebase.ameerhamza.myapplication.adapter.NotificationAdapter;
import com.firebase.ameerhamza.myapplication.app.Edit_Profile;
import com.firebase.ameerhamza.myapplication.app.LoginActivity;
import com.firebase.ameerhamza.myapplication.app.NotificationActivity;
import com.firebase.ameerhamza.myapplication.interfaces.DonorInterface;
import com.firebase.ameerhamza.myapplication.interfaces.RetrofitClient;
import com.firebase.ameerhamza.myapplication.models.AllDonorInformation;
import com.firebase.ameerhamza.myapplication.models.Notification;
import com.firebase.ameerhamza.myapplication.models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.media.CamcorderProfile.get;


public class Third extends Fragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    RecyclerView notificationRecyclerView;
    RecyclerView.Adapter adapter;
    List<Notification> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        notificationRecyclerView = view.findViewById(R.id.notificationRecyclerView);

        final DonorInterface api = RetrofitClient.getClient().create(DonorInterface.class);
        Call<List<Notification>> call = api.getNotifications("Bearer " + AllDonorInformation.getToken());
        call.enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    adapter = new NotificationAdapter(list, getActivity());
                    adapter.notifyDataSetChanged();
                    notificationRecyclerView.setAdapter(adapter);
                    notificationRecyclerView.setHasFixedSize(true);
                    notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                } else {
                    Toast.makeText(getActivity(), "Not Success", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failure", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.only_overflow, menu); // Put your search menu in "menu_search" menu file.
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = "";

        switch (item.getItemId()) {
            case R.id.edit_profile:
                Intent intent = new Intent(getActivity(), Edit_Profile.class);
                startActivity(intent);
                break;
            case R.id.about:
                msg = "About is selected!";
                break;
            case R.id.logout:
                Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof DonorListAdapter.ViewHolder) {
            String name = "";
        }
    }
}
