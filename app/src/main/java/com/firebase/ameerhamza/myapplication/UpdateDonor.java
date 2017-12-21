package com.firebase.ameerhamza.myapplication;

/**
 * Created by Ameer Hamza on 12/18/2017.
 */

public class UpdateDonor {

    private String first_name;
    private String last_name;
    private String blood_group;
    private String city;
    private String mobile_number;

    public UpdateDonor() {

    }

    public UpdateDonor(String first_name, String last_name, String blood_group, String city, String mobile_number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.blood_group = blood_group;
        this.city = city;
        this.mobile_number = mobile_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    @Override
    public String toString() {
        return "UpdateDonor{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", blood_group='" + blood_group + '\'' +
                ", city='" + city + '\'' +
                ", mobile_number='" + mobile_number + '\'' +
                '}';
    }
}
