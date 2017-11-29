package com.firebase.ameerhamza.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ameerhamza.myapplication.Bloodbank;
import com.firebase.ameerhamza.myapplication.R;

import java.util.List;

/**
 * Created by Ameer Hamza on 11/28/2017.
 */

public class BloodbankAdapter extends RecyclerView.Adapter<BloodbankAdapter.ViewHolder> {

    List<Bloodbank> bloodbanks;
    Context context;

    public BloodbankAdapter(List<Bloodbank> bloodbanks, Context context) {
        this.bloodbanks = bloodbanks;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_groups,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bloodbank bloodbank = bloodbanks.get(position);
        holder.group_name.setText(bloodbank.getGroup_name());
        holder.quantity.setText("Quantity : " + bloodbank.getQuantity());
    }

    @Override
    public int getItemCount() {
        return bloodbanks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView group_name;
        TextView quantity;

        public ViewHolder(View itemView) {
            super(itemView);
            group_name = itemView.findViewById(R.id.group_name);
            quantity = itemView.findViewById(R.id.quantity);
        }
    }
}
