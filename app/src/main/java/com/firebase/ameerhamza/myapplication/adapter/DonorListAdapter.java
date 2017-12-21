package com.firebase.ameerhamza.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ameerhamza.myapplication.DonorDetail;
import com.firebase.ameerhamza.myapplication.R;
import com.firebase.ameerhamza.myapplication.models.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ameer Hamza on 11/26/2017.
 */

public class DonorListAdapter extends RecyclerView.Adapter<DonorListAdapter.ViewHolder> {

    public List<User> userList;
    Context context;
    private int mLastPosition = -1;
    private Listener listener;

    public DonorListAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donor_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User list = userList.get(position);
        holder.donor_name.setText(list.getFirstName());
        holder.mobile_number.setText(list.getMobileNumber());
        holder.viewForeground.setOnClickListener(new View.OnClickListener() {
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
        return userList.size();
    }

    public void callNumber(String mobile_number) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + mobile_number));
        context.startActivity(callIntent);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView donor_name,mobile_number;
        public RelativeLayout viewBackground;
        public LinearLayout viewForeground;
        public ViewHolder(View itemView) {
            super(itemView);
            donor_name = itemView.findViewById(R.id.first_name);
            mobile_number = itemView.findViewById(R.id.mobile_number);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
        }
    }

    public void setFilter(List<User> userArrayList) {
        userList = new ArrayList<>();
        userList.addAll(userArrayList);
        notifyDataSetChanged();
    }

    public interface Listener {
        void onClick(int position);
    }

}
