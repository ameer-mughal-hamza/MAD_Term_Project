package com.firebase.ameerhamza.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ameerhamza.myapplication.models.DonorList;
import com.firebase.ameerhamza.myapplication.models.User;
import com.google.gson.Gson;

public class DonorDetail extends AppCompatActivity {

    TextView name;
    TextView mobile_number;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_detail);
        initialize();
        Gson gson = new Gson();
        User list = gson.fromJson(getIntent().getStringExtra("myJson"),User.class);
        name.setText(list.getFirstName()+ " " +list.getLastName());
        mobile_number.setText(list.getMobileNumber());
        toolbar.setTitle(list.getFirstName());
    }

    private void initialize() {
        name = findViewById(R.id.name);
        mobile_number = findViewById(R.id.mobile_number);
        toolbar = findViewById(R.id.toolbar);
    }
}
