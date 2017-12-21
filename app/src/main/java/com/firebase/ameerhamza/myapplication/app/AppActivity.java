package com.firebase.ameerhamza.myapplication.app;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ameerhamza.myapplication.Fragments.AllDonorFragments;
import com.firebase.ameerhamza.myapplication.Fragments.BloodBank;
import com.firebase.ameerhamza.myapplication.Fragments.Third;
import com.firebase.ameerhamza.myapplication.R;
import com.firebase.ameerhamza.myapplication.adapter.DonorListAdapter;
import com.firebase.ameerhamza.myapplication.adapter.TabsAdapter;
import com.firebase.ameerhamza.myapplication.models.AllDonorInformation;
import com.firebase.ameerhamza.myapplication.models.User;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class AppActivity extends AppCompatActivity {

    StringBuilder token = new StringBuilder("Bearer ");
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private ViewPager viewPager;
    private TabsAdapter adapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        Intent intent = getIntent();
        token.append(intent.getStringExtra("token"));
        EventBus.getDefault().post(token.toString());
        initialize();
        prepareDataResource();
        adapter = new TabsAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
//        setTabIcons();
    }

    private void initialize() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Blood Bank");
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabs);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        String msg = "";
//
//        switch (item.getItemId()) {
//            case R.id.search:
//                msg = "Search Box is selected!";
//                break;
//            case R.id.edit_profile:
//                Intent intent = new Intent(AppActivity.this, Edit_Profile.class);
//                startActivity(intent);
//                break;
//            case R.id.about:
//                msg = "About is selected!";
//                break;
//            case R.id.logout:
//                Intent intent1 = new Intent(AppActivity.this, LoginActivity.class);
//                startActivity(intent1);
//                finish();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void prepareDataResource() {
        addData(new AllDonorFragments(), "Donor");
        addData(new BloodBank(), "Blood Bank");
        addData(new Third(), "Third");
    }

    private void addData(Fragment fragment, String title) {
        fragmentList.add(fragment);
        titleList.add(title);
    }

//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }

//    @Override
//    public boolean onQueryTextChange(String newText) {
//        newText = newText.toLowerCase();
//        List<User> list;
//        for (User user : )
//        return false;
//    }


    //For settings ICONS on Tabs
//    private void setTabIcons() {
//        tabLayout.getTabAt(0).setIcon(R.drawable.person);
//        tabLayout.getTabAt(1).setIcon(R.drawable.person);
//        tabLayout.getTabAt(2).setIcon(R.drawable.person);
//    }
}
