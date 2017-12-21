package com.firebase.ameerhamza.myapplication.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ameerhamza.myapplication.Login;
import com.firebase.ameerhamza.myapplication.R;
import com.firebase.ameerhamza.myapplication.interfaces.DonorInterface;
import com.firebase.ameerhamza.myapplication.interfaces.RetrofitClient;
import com.firebase.ameerhamza.myapplication.models.AllDonorInformation;
import com.firebase.ameerhamza.myapplication.models.DonorList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ProgressBar mProgressbar;
    String token = "";
    EditText username_et, password_et;
    Button login, register, learn_more_btn;
    CheckBox remember_me;
    TextInputLayout username_til, password_til;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;
    Boolean saveLogin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeViews();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    //initialize views
    public void initializeViews()
    {
        mProgressbar = findViewById(R.id.progressBar);
        username_et = findViewById(R.id.username);
        password_et = findViewById(R.id.password);

        username_til = findViewById(R.id.usernameTextLayout);
        password_til = findViewById(R.id.passwordTextLayout);

        login = findViewById(R.id.loginBtn);
        register = findViewById(R.id.registerBtn);
        learn_more_btn = findViewById(R.id.learn_more_btn);

        remember_me = findViewById(R.id.remebre_me_check_box);
        sharedPreferences = getSharedPreferences("remember_me",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        saveLogin = sharedPreferences.getBoolean("saveLogin",false);
        if (saveLogin == true)
        {
            username_et.setText(sharedPreferences.getString("email",""));
            password_et.setText(sharedPreferences.getString("password",""));
        }

    }

    // This will use to login a user
    private void register() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    // This will use to register a user
    private void login() {
        showDialog();
        String email = username_et.getText().toString();
        String pass = password_et.getText().toString();
        if (remember_me.isChecked())
        {
            editor.putBoolean("saveLogin",true);
            editor.putString("email",email);
            editor.putString("password",pass);
            editor.apply();
        }
        else
        {
            editor.clear().apply();
        }

        if (!isEmpty(email) && !isEmpty(pass))
        {
            DonorInterface api = RetrofitClient.getClient().create(DonorInterface.class);
            Login login = new Login(email, pass);
            Call<DonorList> call = api.login(login);

            call.enqueue(new Callback<DonorList>() {
                @Override
                public void onResponse(Call<DonorList> call, Response<DonorList> response) {
                    if (response.isSuccessful()) {
                        DonorList donorList = response.body();
                        //TODO: Method for Initialize the object for using all purposes
                        allUserInformation(donorList);
                        Intent intent = new Intent(LoginActivity.this, AppActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Username or Password is incorrect!", Toast.LENGTH_SHORT).show();
                    }
               }
                @Override
                public void onFailure(Call<DonorList> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Issue in method", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            username_til.setError("*Username is required");
            password_til.setError("*Password is required");
        }
        hideDialog();
    }

    public void allUserInformation(DonorList list)
    {
        int id = list.getUser().getId();
        String first_name = list.getUser().getFirstName();
        String last_name = list.getUser().getLastName();
        String email = list.getUser().getEmail();
        String blood_group = list.getUser().getBloodGroup();
        String city = list.getUser().getCity();
        String mobile_number = list.getUser().getMobileNumber();
        String token = list.getToken();

        AllDonorInformation allDonorInformation = new AllDonorInformation(id,first_name,last_name,email,blood_group,city,mobile_number,token);
    }

    private boolean isEmpty(String string){
        return string.equals("");
    }


    private void showDialog(){
        mProgressbar.setVisibility(View.VISIBLE);

    }

    private void hideDialog(){
        if(mProgressbar.getVisibility() == View.VISIBLE){
            mProgressbar.setVisibility(View.INVISIBLE);
        }
    }

}
