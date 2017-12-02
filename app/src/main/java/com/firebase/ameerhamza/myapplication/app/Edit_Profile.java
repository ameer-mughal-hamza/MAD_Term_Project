package com.firebase.ameerhamza.myapplication.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.ameerhamza.myapplication.R;
import com.firebase.ameerhamza.myapplication.interfaces.DonorInterface;
import com.firebase.ameerhamza.myapplication.interfaces.RetrofitClient;
import com.firebase.ameerhamza.myapplication.models.AllDonorInformation;
import com.firebase.ameerhamza.myapplication.models.User;

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
    EditText mobile_number;
    Spinner blood_groups;
    Spinner city;
    Button update_btn;

    String blood_group,city_name;

    @Subscribe(threadMode = ThreadMode.MAIN)
    void test(User user) {
        first_name.setText(user.getFirstName().toString());
        last_name.setText(user.getLastName().toString());
        mobile_number.setText(user.getMobileNumber().toString());
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

        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.blood_groups,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blood_groups.setAdapter(adapter);
        blood_groups.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                blood_group = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final ArrayAdapter<CharSequence> city_name_adapter;
        city_name_adapter = ArrayAdapter.createFromResource(this, R.array.city_names,android.R.layout.simple_spinner_item);
        city_name_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(city_name_adapter);
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city_name = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        DonorInterface api = RetrofitClient.getClient().create(DonorInterface.class);
        int id = AllDonorInformation.getId();
        Call<User> call = api.editDonor("Bearer "+AllDonorInformation.getToken().toString(),AllDonorInformation.getId());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful())
                {
                    User edit = response.body();
                    EventBus.getDefault().post(edit);
                }
                else
                {
                    String s = "";
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                String s = "";
            }
        });
    }


    //Update method
    private void update() {

        DonorInterface api = RetrofitClient.getClient().create(DonorInterface.class);
        //TODO: Update User
    }

    //This is used to initialize the Views
    public void initialize()
    {
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        mobile_number = findViewById(R.id.mobile_number);
        blood_groups = findViewById(R.id.spinner);
        city = findViewById(R.id.city_name);
        update_btn = findViewById(R.id.update_btn);
    }
}
