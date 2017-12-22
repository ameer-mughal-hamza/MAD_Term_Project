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

    TextView name, email, mobileNumber, gender, bloodGroup, city;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_detail);
        initialize();
        Gson gson = new Gson();
        User list = gson.fromJson(getIntent().getStringExtra("myJson"), User.class);
        name.setText(list.getFirstName());
        email.setText(list.getEmail());
        bloodGroup.setText(list.getBloodGroup());
        mobileNumber.setText(list.getMobileNumber());
        city.setText(list.getCity());
        toolbar.setTitle(list.getFirstName());
    }

    private void initialize() {
        name = findViewById(R.id.name);
        mobileNumber = findViewById(R.id.mobileNumber);
        email = findViewById(R.id.email);
        gender = findViewById(R.id.gender);
        bloodGroup = findViewById(R.id.bloodGroup);
        city = findViewById(R.id.city);
        toolbar = findViewById(R.id.toolbar);
    }
}
