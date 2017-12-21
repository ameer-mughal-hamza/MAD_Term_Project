package com.firebase.ameerhamza.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Bloodbank {
    String group_name;
    int quantity;

    //Blood Bank information like how much blood is available against each blood group

    public Bloodbank(String group_name, int quantity) {
        this.group_name = group_name;
        this.quantity = quantity;
    }

    public String getGroup_name() {
        return group_name;
    }

    public int getQuantity() {
        return quantity;
    }
}
