package com.firebase.ameerhamza.myapplication.Fragments;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.firebase.ameerhamza.myapplication.R;
import com.firebase.ameerhamza.myapplication.RecyclerItemTouchHelper;
import com.firebase.ameerhamza.myapplication.adapter.DonorListAdapter;
import com.firebase.ameerhamza.myapplication.app.Edit_Profile;
import com.firebase.ameerhamza.myapplication.app.LoginActivity;
import com.firebase.ameerhamza.myapplication.interfaces.DonorInterface;
import com.firebase.ameerhamza.myapplication.interfaces.RetrofitClient;
import com.firebase.ameerhamza.myapplication.models.AllDonorInformation;
import com.firebase.ameerhamza.myapplication.models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllDonorFragments extends Fragment implements SearchView.OnQueryTextListener,RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    public static final String TAG = "MyTag";
    RecyclerView recyclerView;
    DonorListAdapter adapter;
    List<User> userList = new ArrayList<>();
    FrameLayout frameLayout;
    Menu menu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_donor_fragments, container, false);
//        frameLayout = view.findViewById(R.id.)
        recyclerView = view.findViewById(R.id.allDonorRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final DonorInterface api = RetrofitClient.getClient().create(DonorInterface.class);
        String s = AllDonorInformation.getToken();
        Call<List<User>> call = api.getDonors("Bearer " + AllDonorInformation.getToken());
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response)
            {
                if (response.isSuccessful())
                {
                    userList = response.body();
                    adapter = new DonorListAdapter(userList, getActivity());
                    adapter.notifyDataSetChanged();
                    adapter.setListener(new DonorListAdapter.Listener()
                    {
                        @Override
                        public void onClick(int position)
                        {

                        }
                    });
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(adapter);
                }
                else
                {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        return view;
    }

//    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.menu_main, menu); // Put your search menu in "menu_search" menu file.
        MenuItem mSearchItem = menu.findItem(R.id.search);
        SearchView sv = (SearchView) MenuItemCompat.getActionView(mSearchItem);
        sv.setIconified(true);
        SearchManager searchManager = (SearchManager)  getActivity().getSystemService(Context.SEARCH_SERVICE);
        sv.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        sv.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = "";

        switch (item.getItemId()) {
            case R.id.search:
                msg = "Search Box is selected!";
                break;
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
    public boolean onQueryTextSubmit(String query)
    {
        if (adapter != null)
        {
            query = String.valueOf(query.equals(""));
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        newText = newText.toLowerCase();
        List<User> userArrayList = new ArrayList<>();
        for (User userList : userList)
        {
            String name = userList.getFirstName().toLowerCase();
            if (name.contains(newText))
            {
                userArrayList.add(userList);
            }
        }
        adapter.setFilter(userArrayList);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof DonorListAdapter.ViewHolder) {
//            get the removed item name to display it in snack bar
//            String name = cartList.get(viewHolder.getAdapterPosition()).getName();
//            String name = mAdapter.cartList.get(position).getName();//get(viewHolder.getAdapterPosition()).getName();

            String mobile_number = adapter.userList.get(position).getMobileNumber();
//             backup of removed item for undo purpose
//            final Item deletedItem = mAdapter.cartList.get(viewHolder.getAdapterPosition());
//            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            adapter.callNumber(mobile_number);
            adapter.notifyItemChanged(position);
//            mAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
//            Snackbar snackbar = Snackbar
//                    .make(frameLayout, name + " removed from cart!", Snackbar.LENGTH_LONG);
//            snackbar.setAction("UNDO", new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    // undo is selected, restore the deleted item
//                    mAdapter.restoreItem(deletedItem, deletedIndex);
//                }
//            });
//            snackbar.setActionTextColor(Color.YELLOW);
//            snackbar.show();
        }
    }
}