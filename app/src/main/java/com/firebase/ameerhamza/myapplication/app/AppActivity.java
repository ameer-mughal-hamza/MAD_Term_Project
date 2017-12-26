package com.firebase.ameerhamza.myapplication.app;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.firebase.ameerhamza.myapplication.Fragments.AllDonorFragments;
import com.firebase.ameerhamza.myapplication.Fragments.BloodBank;
import com.firebase.ameerhamza.myapplication.Fragments.Third;
import com.firebase.ameerhamza.myapplication.R;
import com.firebase.ameerhamza.myapplication.adapter.TabsAdapter;
import com.firebase.ameerhamza.myapplication.models.Notification;
import com.google.gson.Gson;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class AppActivity extends AppCompatActivity {

    private static final String TAG = "AppActivity";
    StringBuilder token = new StringBuilder("Bearer ");
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private ViewPager viewPager;
    private TabsAdapter adapter;
    private TabLayout tabLayout;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        context = getApplicationContext();

        /*----------------------Pusher Notification-----------------------------*/
        /*----------------------Register for single user------------------------*/

        PusherOptions options = new PusherOptions();
        options.setCluster("ap2");
        Pusher pusher = new Pusher("4cbe851fbcae6be370b6", options);
        Channel channel = pusher.subscribe("test-channelhamza6417307@gmail.com");
        channel.bind("App\\Events\\PushNotificationSingleUserEvent", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                System.out.println(data);
                Log.d(TAG, "onEvent: " + data);
                Gson gson = new Gson();
                // Serializing Json to Respective POJO
                Notification message = gson.fromJson(data, Notification.class);
                Intent i = new Intent(context, Notification.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder notification = new NotificationCompat.Builder(context, "Channel1")
                        .setContentTitle(message.getTitle())
                        .setContentText(message.getContent())
                        .setSmallIcon(R.drawable.blood_logo)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(101, notification.build());

                //Eventbus code for posting data to set in views
                EventBus.getDefault().post(message);
            }
        });
        pusher.connect();

        /*----------------------End Pusher Notification--------------------------*/


        /*----------------------Pusher Notification------------------------------*/
        /*----------------Pusher Registeration for single user-------------------*/

        PusherOptions options2 = new PusherOptions();
        options2.setCluster("ap2");
        Pusher pusher2 = new Pusher("4cbe851fbcae6be370b6", options2);
        Channel channel2 = pusher2.subscribe("test-channel");
        channel2.bind("App\\Events\\PushNotificationEvent", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                System.out.println(data);
                Log.d(TAG, "onEvent: " + data);
                Gson gson = new Gson();
                Notification message = gson.fromJson(data, Notification.class);
                PendingIntent intent = PendingIntent.getActivity(AppActivity.this, 0, new Intent(AppActivity.this, NotificationActivity.class), 0);
                //Android Notifications
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_HIGH);

                    // Configure the notification channel2.
                    notificationChannel.setDescription("Channel description");
                    notificationChannel.enableLights(true);
                    notificationChannel.setLightColor(Color.RED);
                    notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                    notificationChannel.enableVibration(true);
                    notificationManager.createNotificationChannel(notificationChannel);
                }

                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(AppActivity.this, NOTIFICATION_CHANNEL_ID);

                notificationBuilder.setAutoCancel(true)
                        .setDefaults(android.app.Notification.DEFAULT_ALL)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(message.getTitle())
                        .setContentText(message.getContent());

                notificationManager.notify(/*notification id*/1, notificationBuilder.build());

                //Eventbus code for posting data to set in views
                EventBus.getDefault().post(message);
            }
        });
        pusher2.connect();

        /*----------------------End Pusher Notification--------------------------*/


        Intent intent = getIntent();
        token.append(intent.getStringExtra("token"));
        EventBus.getDefault().post(token.toString());
        initialize();
        prepareDataResource();

        /*---Passing Fragmentlist and title list to Custom TabsAdapter class----*/
        adapter = new TabsAdapter(getSupportFragmentManager(), fragmentList, titleList);

        /*---------set adapter using ViewPager setAdapter method----------------*/
        viewPager.setAdapter(adapter);

        /*--------------------------set tab layout------------------------------*/
        tabLayout.setupWithViewPager(viewPager);
    }


    /*-------------------Initializing widgets here-------------------------*/

    private void initialize() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Blood Bank");
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabs);
    }

    /*-------------------Preparing fragments list here---------------------*/

    private void prepareDataResource() {
        addData(new AllDonorFragments(), "Donor");
        addData(new BloodBank(), "Blood Bank");
        addData(new Third(), "Notifications");
    }

    /*-------Add fragment and title to fragmentlist and title list---------*/

    private void addData(Fragment fragment, String title) {
        fragmentList.add(fragment);
        titleList.add(title);
    }
}
