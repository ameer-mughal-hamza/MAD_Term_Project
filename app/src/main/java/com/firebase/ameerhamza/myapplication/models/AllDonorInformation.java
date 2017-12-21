package com.firebase.ameerhamza.myapplication.models;

/**
 * Created by Ameer Hamza on 11/30/2017.
 */

public class AllDonorInformation {
    static int id;
    static String first_name;
    static String last_name;
    static String email;
    static String blood_group;
    static String city;
    static String mobile_number;
    static String token;

    public AllDonorInformation(int id, String first_name, String last_name, String email, String blood_group, String city, String mobile_number, String token) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.blood_group = blood_group;
        this.city = city;
        this.mobile_number = mobile_number;
        this.token = token;
    }

    public AllDonorInformation() {
    }

    public static int getId() {
        return id;
    }

    public static String getFirst_name() {
        return first_name;
    }

    public static String getLast_name() {
        return last_name;
    }

    public static String getEmail() {
        return email;
    }

    public static String getBlood_group() {
        return blood_group;
    }

    public static String getCity() {
        return city;
    }

    public static String getMobile_number() {
        return mobile_number;
    }

    public static String getToken() {
        return token;
    }
}
