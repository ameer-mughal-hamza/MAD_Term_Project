package com.firebase.ameerhamza.myapplication;

/**
 * Created by Ameer Hamza on 11/21/2017.
 */

public class Donor {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String c_password;
    private String blood_group;
    private String city;
    private String mobile_number;
    private String token;

    public Donor(String first_name, String last_name,
                 String email,
                 String password, String c_password,
                 String blood_group, String city,
                 String mobile_number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.c_password = c_password;
        this.blood_group = blood_group;
        this.city = city;
        this.mobile_number = mobile_number;
    }

    public Donor() {

    }

    public Donor(String first_name, String last_name, String blood_group, String city, String mobile_number) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.blood_group = blood_group;
        this.city = city;
        this.mobile_number = mobile_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getC_password() {
        return c_password;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Donor{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", c_password='" + c_password + '\'' +
                ", blood_group='" + blood_group + '\'' +
                ", city='" + city + '\'' +
                ", mobile_number='" + mobile_number + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }
}
