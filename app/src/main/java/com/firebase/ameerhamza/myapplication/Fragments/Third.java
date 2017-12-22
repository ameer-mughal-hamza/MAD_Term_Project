package com.firebase.ameerhamza.myapplication.Fragments;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ameerhamza.myapplication.R;
import com.firebase.ameerhamza.myapplication.app.Edit_Profile;
import com.firebase.ameerhamza.myapplication.app.LoginActivity;


public class Third extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
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

}
