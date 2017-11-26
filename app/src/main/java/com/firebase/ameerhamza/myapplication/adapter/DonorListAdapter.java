package com.firebase.ameerhamza.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.firebase.ameerhamza.myapplication.DonorList;
import com.firebase.ameerhamza.myapplication.R;

import java.util.List;

/**
 * Created by Ameer Hamza on 11/26/2017.
 */

public class DonorListAdapter extends RecyclerView.Adapter<DonorListAdapter.ViewHolder> {

    List<DonorList> donorLists ;
    Context context;

    public DonorListAdapter(List<DonorList> donorLists, Context context) {
        this.donorLists = donorLists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donor_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DonorList list = donorLists.get(position);
        holder.donor_name.setText(list.getFirstName());
        holder.donor_name.setText(list.getMobileNumber());
    }

    @Override
    public int getItemCount() {
        return donorLists.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView donor_name;
        TextView mobile_number;
        public ViewHolder(View itemView) {
            super(itemView);
            donor_name = itemView.findViewById(R.id.first_name);
            mobile_number = itemView.findViewById(R.id.mobile_number);
        }
    }
}
