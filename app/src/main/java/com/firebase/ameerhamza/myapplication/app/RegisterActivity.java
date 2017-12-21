package com.firebase.ameerhamza.myapplication.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ameerhamza.myapplication.Donor;
import com.firebase.ameerhamza.myapplication.R;
import com.firebase.ameerhamza.myapplication.interfaces.DonorInterface;
import com.firebase.ameerhamza.myapplication.interfaces.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private static final String DOMAIN_NAME = "gmail.com";

    private ProgressBar mProgressBar;


    EditText first_name, last_name, email, password, c_password, mobile_number;
    Button registerBtn;
    Spinner spinner;
    Spinner city_name;
    String sp_value;
    String sp_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mProgressBar = findViewById(R.id.progressBar);
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        c_password = findViewById(R.id.c_password);
        mobile_number = findViewById(R.id.mobile_number);
        spinner = findViewById(R.id.spinner);
        city_name = findViewById(R.id.city_name);

        registerBtn = findViewById(R.id.registerBtn);

        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.blood_groups, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sp_value = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> city_name_adapter;
        city_name_adapter = ArrayAdapter.createFromResource(this, R.array.city_names, android.R.layout.simple_spinner_item);
        city_name_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city_name.setAdapter(city_name_adapter);
        city_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sp_city = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });


    }

    private void register() {
//        String firstName = first_name.getText().toString();
//        String lastName = last_name.getText().toString();
//        String Email = email.getText().toString();
//        String Password = password.getText().toString();
//        String confirmPassword = c_password.getText().toString();
//        String bloodGroup = sp_value;
//        String city = sp_city;
//        String mobileNo = mobile_number.getText().toString();

        if (!isEmpty(first_name.getText().toString()) && !isEmpty(last_name.getText().toString())
                && !isEmpty(email.getText().toString()) && !isEmpty(password.getText().toString())
                && !isEmpty(c_password.getText().toString()) && !isEmpty(sp_value)
                && !isEmpty(sp_city) && !isEmpty(mobile_number.getText().toString())) {
            if (isValidDomain(email.getText().toString())) {
                if (doStringsMatch(password.getText().toString(), c_password.getText().toString())) {
                    showDialog();

                    Donor donor = new Donor(first_name.getText().toString(), last_name.getText().toString(),
                            email.getText().toString(), password.getText().toString(), c_password.getText().toString(),
                            sp_value, sp_city, mobile_number.getText().toString());
                    DonorInterface api = RetrofitClient.getClient().create(DonorInterface.class);
                    Call<Donor> call = api.register(donor);
                    call.enqueue(new Callback<Donor>() {
                        @Override
                        public void onResponse(Call<Donor> call, Response<Donor> response) {
                            if (response.isSuccessful()) {
                                Donor donor1 = response.body();
                                String token = donor1.getToken().toString();
                                Intent i = new Intent(RegisterActivity.this, AppActivity.class);
                                i.putExtra("token", token);
                                startActivity(i);
                            } else {
                                Toast.makeText(RegisterActivity.this, "There is some issue in your Method :(", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Donor> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "Failure!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(this, "Password and Confirm password must be same!", Toast.LENGTH_SHORT).show();
                }//End of password confirmation check
            } else {
                Toast.makeText(this, "Plese enter a valid Domain : @gmail.com)", Toast.LENGTH_SHORT).show();
            }//End of Domain check
            hideDialog();
        } else {
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidDomain(String email) {
        String domain = email.substring(email.indexOf("@") + 1).toLowerCase();
        return domain.equals(DOMAIN_NAME);
    }

    private boolean doStringsMatch(String s1, String s2) {
        return s1.equals(s2);
    }

    private boolean isEmpty(String string) {
        return string.equals("");
    }

    private void showDialog() {
        mProgressBar.setVisibility(View.VISIBLE);

    }

    private void hideDialog() {
        if (mProgressBar.getVisibility() == View.VISIBLE) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

}
