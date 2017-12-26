package com.firebase.ameerhamza.myapplication.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.firebase.ameerhamza.myapplication.R;
import com.firebase.ameerhamza.myapplication.RecyclerItemTouchHelper;
import com.firebase.ameerhamza.myapplication.adapter.DonorListAdapter;

public class NotificationActivity extends AppCompatActivity {

    TextView title, content, user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Notification");

        Intent intent = getIntent();

        String notification_title = intent.getStringExtra("title");
        String notification_content = intent.getStringExtra("content");
        String notification_user = intent.getStringExtra("user");

        title = findViewById(R.id.notification_title);
        content = findViewById(R.id.notification_content);
        user_email = findViewById(R.id.user_email);

        title.setText(notification_title);
        content.setText(notification_content);
        user_email.setText(notification_user);
    }
}
