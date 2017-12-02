package com.firebase.ameerhamza.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ameerhamza.myapplication.DonorDetail;
import com.firebase.ameerhamza.myapplication.models.DonorList;
import com.firebase.ameerhamza.myapplication.R;
import com.firebase.ameerhamza.myapplication.models.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ameer Hamza on 11/26/2017.
 */

public class DonorListAdapter extends RecyclerView.Adapter<DonorListAdapter.ViewHolder> {

    List<User> users ;
    Context context;

    public DonorListAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donor_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User list = users.get(position);
        holder.donor_name.setText(list.getFirstName());
        holder.mobile_number.setText(list.getMobileNumber());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DonorDetail.class);
                Gson gson = new Gson();
                String myJson = gson.toJson(list);
                intent.putExtra("myJson",myJson);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView donor_name;
        TextView mobile_number;
        LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            donor_name = itemView.findViewById(R.id.first_name);
            mobile_number = itemView.findViewById(R.id.mobile_number);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

    public void setFilter(List<User> list)
    {
        users = new ArrayList<>();
        users.addAll(list);
        notifyDataSetChanged();
    }

}
