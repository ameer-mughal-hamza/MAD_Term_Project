package com.firebase.ameerhamza.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.firebase.ameerhamza.myapplication.interfaces.DonorInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Edit_Profile extends AppCompatActivity {

    EditText first_name;
    EditText last_name;
    EditText email;
    EditText mobile_number;

    @Subscribe(threadMode = ThreadMode.MAIN)
    void test(DonorList donorList) {
        first_name.setText(donorList.getFirstName().toString());
        last_name.setText(donorList.getLastName().toString());
        email.setText(donorList.getMobileNumber().toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile);
        initialize();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DonorInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DonorInterface api = retrofit.create(DonorInterface.class);
        Call<DonorList> call = api.editDonor();
        call.enqueue(new Callback<DonorList>() {
            @Override
            public void onResponse(Call<DonorList> call, Response<DonorList> response) {
                DonorList edit = response.body();
                EventBus.getDefault().post(edit);
            }

            @Override
            public void onFailure(Call<DonorList> call, Throwable t) {
                String s = "";
            }
        });
    }

    public void initialize()
    {
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        mobile_number = findViewById(R.id.mobile_number);
    }
}
